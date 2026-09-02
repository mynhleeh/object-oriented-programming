# Round Protocol — Doc Drill Reference

Detailed specification for the round lifecycle: generation schema, lazy
evaluation flow, answer collection, grading, gap analysis, and regeneration
triggers. This is the operational playbook for managing a round from creation
to completion.

---

## Round Lifecycle

```
┌─────────────────────────────────────────────────────────┐
│  GENERATE ROUND                                         │
│  ─────────────                                          │
│  1. Read UI config (num_questions, difficulty, types)   │
│  2. Allocate topics (mastery-weighted)                  │
│  3. Select question type per question                   │
│  4. Write questions WITHOUT revealing answers           │
│  5. Present all N questions in one response             │
│  6. Store round in pending_answers                      │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼  [User reads & works through questions]
                      │
                      │  [EVALUATE] ──────────────────────────────┐
                      │  or                                        │
                      │  [NEXT ROUND] / "tiếp theo" ──────────────┤
                      │                                            │
                      ▼                                            ▼
┌─────────────────────────────────┐  ┌─────────────────────────────────┐
│  COLLECT ANSWERS                │  │  COLLECT ANSWERS                │
│  ─────────────────              │  │  (same as left)                 │
│  Ask for Q1…Qn answers          │  └──────────────┬──────────────────┘
└──────────────┬──────────────────┘                 │
               │                                    │
               ▼                                    ▼
┌─────────────────────────────────┐  ┌─────────────────────────────────┐
│  GRADE & GAP ANALYSIS           │  │  GRADE & GAP ANALYSIS           │
│  ──────────────────────────     │  │  (same as left)                 │
│  Score each question            │  └──────────────┬──────────────────┘
│  Update topic_mastery           │                 │
│  Detect misconceptions          │                 ▼
│  Output round report            │  ┌─────────────────────────────────┐
│  STOP — no new round            │  │  ADAPTIVE ENGINE                │
└─────────────────────────────────┘  │  ─────────────────              │
                                     │  Adjust difficulty              │
                                     │  Prioritize topics              │
                                     │  GENERATE NEXT ROUND            │
                                     └─────────────────────────────────┘
```

---

## Round Data Schema

Maintain this structure in memory for the current round:

```
current_round:
  round_number: {N}
  questions:
    - question_id: "q{n}"           ← e.g. "q1", "q2"
      question_type: {choice|concept|fill_in_blank|short_answer|written}
      topic_id: {string}
      topic_name: {string}
      difficulty: {easy|medium|hard}
      bloom_level: {remember|understand|apply|analyze|evaluate}
      question_text: {string}
      options: {A: ..., B: ..., C: ..., D: ...}  ← only for choice
      correct_answer: {string}      ← NEVER revealed before grading
      explanation: {string}         ← NEVER revealed before grading
      points_possible: {1.0|1.5|2.0}
  status: generating | presented | evaluating | completed
  user_answers: {}                  ← populated during collection
  points_earned: 0
  points_possible: 0
  round_score: null                 ← computed after grading
```

---

## Answer Collection Protocol

When evaluation is triggered (either path), collect answers with a single
structured prompt. Do NOT ask for answers one by one — batch the request:

### For rounds with only `choice` and `fill_in_blank`:

```
Hãy cung cấp đáp án cho Round {N} (câu nào bỏ trống sẽ tính là sai):

Q1: ___  Q2: ___  Q3: ___  Q4: ___  Q5: ___
(MCQ: gõ A/B/C/D · Điền chỗ trống: gõ từ/cụm từ)
```

### For rounds with `short_answer` or `written`:

```
Hãy cung cấp đáp án cho Round {N}:

Q1 [MCQ]: ___
Q2 [Short Answer]: {câu trả lời ngắn của bạn}
Q3 [MCQ]: ___
Q4 [Essay]: {câu trả lời của bạn}
Q5 [FIB]: ___

(Gõ "skip" nếu muốn bỏ qua câu nào)
```

### Handling incomplete answers:

| Situation | Action |
|---|---|
| User skips a question | Mark as incorrect (0 points); note "skipped" in report |
| User provides partial answer | Grade as-is; partial credit applies for short_answer/written |
| User says "I don't know" | Mark as incorrect; flag topic for remediation |
| User gives answers in wrong format | Parse best-effort; if ambiguous, ask once for clarification |

---

## Grading Protocol

### Binary types (`choice`, `fill_in_blank`, `concept`):

```python
# choice:  exact match on option key (case-insensitive)
is_correct = user_answer.strip().upper() == correct_answer.upper()

# fill_in_blank: fuzzy match
is_correct = (
    user_answer.lower() in [correct_answer.lower(), *synonyms]
    or edit_distance(user_answer, correct_answer) <= 2
)

# concept: key-term match
is_correct = all(key_term in user_answer.lower() for key_term in required_terms)
partial = any(key_term in user_answer.lower() for key_term in required_terms)
```

### Judgment types (`short_answer`, `written`):

Use this rubric when evaluating open-ended answers:

**Correct (full points)**:
- Core mechanism / trade-off / answer is present AND accurate
- At least one supporting detail or justification
- No significant factual errors

**Partial (50% points)**:
- Core idea is present but explanation is shallow or missing key detail
- OR: covers all required points but contains one minor inaccuracy
- OR: answer is too brief but not wrong

**Incorrect (0 points)**:
- Wrong mechanism or concept identified
- Misconception present (see `misconceptions.md`)
- Answer is off-topic or too vague to evaluate

### Points Calculation:

```
For each question:
  if correct:   points_earned += points_possible
  if partial:   points_earned += points_possible × 0.5
  if incorrect: points_earned += 0

round_score = (points_earned / sum(points_possible)) × 100%
```

---

## Gap Analysis Protocol

Run immediately after grading, before outputting the round report:

### Step 1 — Update Topic Mastery

For each topic that appeared in the round:
```
# Compute topic-level score for this round:
topic_round_score = avg(question_scores_for_this_topic)
# question_score: 100 = correct, 50 = partial, 0 = incorrect (as %)

# Update EMA:
if topic not in topic_mastery:
    topic_mastery[topic] = topic_round_score
else:
    topic_mastery[topic] = 0.6 × topic_mastery[topic] + 0.4 × topic_round_score
```

### Step 2 — Update Topic History

```
topic_history[topic].append(round_number)
```

### Step 3 — Classify Topics

```
weak:        topic_mastery[topic] < 60
developing:  60 ≤ topic_mastery[topic] ≤ 85
strong:      topic_mastery[topic] > 85
mastered:    strong AND topic appeared in ≥ 2 consecutive rounds above 85
unseen:      topic not in topic_history
```

### Step 4 — Misconception Detection

Flag a misconception when:
- Same topic answered incorrectly in ≥ 2 consecutive rounds, AND
- The pattern of wrong answers is consistent (same incorrect concept cited)

When flagged → add to `misconceptions` list and apply correction protocol
from `misconceptions.md` during the round report explanation.

---

## Round Report Format

Output the report immediately after grading. Use this exact structure:

```
📊 Kết quả Round {N}: {round_score:.0f}% ({points_earned:.1f}/{points_possible:.1f} điểm)
────────────────────────────────────────────────────

✅ Đúng ({correct_count} câu): Q{n}, Q{n}, ...

❌ Sai ({wrong_count} câu):
   Q{n} [{topic_name}] · {difficulty}
       → Đáp án đúng: {correct_answer}
       💡 {1–2 sentence explanation from document}

⚠️  Partial ({partial_count} câu):        ← omit section if 0
   Q{n} [{topic_name}] → {brief feedback on what was missing}

{if misconception detected:}
🚨 Chú ý — Nhận thấy pattern sai ở [{topic_name}]:
   Có vẻ bạn đang hiểu rằng "{wrong_model}".
   Điều này không đúng vì: {root correction — 2–3 sentences}.
   Chúng ta sẽ tập trung ôn lại topic này ở round tiếp theo.

🎯 Chủ đề cần cải thiện: {comma-separated weak topics}
📈 Tiến trình: {R1: X%} → {R2: Y%} → ... → {RN: Z%}
────────────────────────────────────────────────────
```

If triggered by `[EVALUATE]` (no new round), add:
```
💬 Gõ [NEXT ROUND] để tiếp tục, hoặc [WRAP UP] để tổng kết.
```

If triggered by `[NEXT ROUND]`, omit the footer and proceed directly to
generating Round {N+1}.

---

## Regeneration Decision Table

After evaluation, use this table to determine Round {N+1} configuration:

| round_score | consecutive_above_85 | consecutive_below_60 | Action |
|---|---|---|---|
| > 85% | 1 | 0 | Escalate one difficulty tier |
| > 85% | ≥ 2 | 0 | Escalate one tier; check auto-completion |
| 70–85% | any | 0 | Maintain difficulty; normal topic rotation |
| 60–70% | 0 | 0 | Maintain; silently shift 10% hard → medium |
| < 60% | 0 | 1 | De-escalate one tier |
| < 60% | 0 | ≥ 2 | De-escalate one tier; expand easy question count |

---

## Auto-Completion Check

After every evaluation, check:

```
all_topics_seen = all(topic in topic_history for topic in kpm_topics)
last_two_above_85 = len(round_scores) >= 2 and all(s >= 85 for s in round_scores[-2:])

if all_topics_seen and last_two_above_85:
    output auto-completion suggestion
```

Auto-completion suggestion:
```
🎉 Bạn đã nắm vững toàn bộ tài liệu!
   Gõ [WRAP UP] để xem tổng kết phiên học,
   hoặc [NEXT ROUND] để đào sâu thêm với câu hỏi nâng cao.
```

---

## Edge Cases

| Situation | Handling |
|---|---|
| `num_questions` > topics in KPM | Distribute extra questions across topics proportionally; accept repetition |
| User requests `[FOCUS: topic]` on a mastered topic | Generate the round but use higher difficulty for that topic |
| User types `[HARDER]` mid-round | Apply to **next** round only; do not regenerate current round |
| User provides answers before any round exists | Politely note that no round has been generated yet; ask to start |
| Document has only 1 topic | All questions target that topic; type variety is the differentiator |
| User mixes Q1 answer into a different question's slot | Parse best-effort; if unresolvable, ask the user to re-provide that answer |
