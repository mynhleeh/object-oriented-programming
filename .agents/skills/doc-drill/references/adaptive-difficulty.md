# Adaptive Difficulty — Doc Drill Reference

Rules for dynamically adjusting quiz difficulty **between rounds** (not
between individual questions). Based on the 70–90% retrieval band principle
from cognitive science (Roediger & Karpicke; FSRS target retention ~0.9),
adapted for weighted batch scoring.

---

## Weighted Scoring System

Each question earns points based on its difficulty tier:

| Difficulty | Points if correct | Points if wrong |
|---|---|---|
| easy | 1.0 | 0 |
| medium | 1.5 | 0 |
| hard | 2.0 | 0 |

```
round_score = (sum of points earned / sum of points possible) × 100%
```

Partial credit for `short_answer` and `written`:
- **Correct** → full points
- **Partial** → 50% of points
- **Incorrect** → 0 points

---

## The 70–85% Band (Round Level)

The optimal difficulty produces a round score in the 70–85% range:

| Round score | Meaning | Action |
|---|---|---|
| > 85% (too easy) | Learner is above current difficulty | Escalate difficulty next round |
| 70–85% (optimal) | Productive challenge zone | Maintain current distribution |
| 60–70% (slightly hard) | Acceptable struggle — do not panic de-escalate | Maintain; shift 10% of hard → medium |
| < 60% (too hard) | Learner is overwhelmed | De-escalate next round |

---

## Difficulty Adjustment Rules

> **Only applies when UI config `difficulty` = "auto".**
> If the user has set a specific difficulty (easy / medium / hard) in the
> QuizConfigPanel, always honour that setting and skip these rules.

### Escalation (score > 85%)

Shift the difficulty distribution upward:

| Current distribution | Next round distribution |
|---|---|
| 50% easy · 50% medium · 0% hard | 20% easy · 60% medium · 20% hard |
| 20% easy · 60% medium · 20% hard | 0% easy · 50% medium · 50% hard |
| 0% easy · 50% medium · 50% hard | 0% easy · 20% medium · 80% hard |

Also: unlock `written` (essay) question type when hard ratio ≥ 30%.

> Do NOT jump more than one tier in a single step. Reward consistency, not
> a single lucky round.

### De-escalation (score < 60%)

Shift the distribution downward:

| Current distribution | Next round distribution |
|---|---|
| 0% easy · 20% medium · 80% hard | 0% easy · 50% medium · 50% hard |
| 0% easy · 50% medium · 50% hard | 20% easy · 60% medium · 20% hard |
| 20% easy · 60% medium · 20% hard | 50% easy · 50% medium · 0% hard |

Also: remove `written` (essay) from the allowed types for the next round.
Prefer `choice` and `fill_in_blank` to reduce cognitive load.

> De-escalation is not failure — it means the step was too large.
> Productive struggle with a smaller step is more effective than
> receiving hints or skipping content.

### Maintenance (60–85%)

Keep the current difficulty distribution. No change needed.
If the score is at the lower end (60–70%), silently shift 10% of hard
questions to medium in the next round — do not announce this to the user.

---

## Topic-Level Mastery Tracking

Each topic has a `mastery` value (0–100) computed across rounds using an
exponential moving average (EMA):

```
First appearance:   mastery = question_score (0 or 100 for binary; 0/50/100 for partial)
Subsequent rounds:  mastery = 0.6 × old_mastery + 0.4 × new_question_score
```

When a topic is tested with multiple questions in the same round, average
the scores for that topic before applying EMA.

### Mastery Tiers

| Tier | Range | Treatment in next round |
|---|---|---|
| **Weak** | < 60% | Priority coverage · use simpler types (choice, FIB) |
| **Developing** | 60–85% | Moderate coverage · mix types |
| **Strong** | > 85% | Light coverage · upgrade difficulty; reduce frequency |
| **Mastered** | > 85% for ≥ 2 consecutive rounds | Maintenance only (≤1 question per 2 rounds) |

---

## Diagnostic Placement (Session Start)

Round 1 always starts at a balanced baseline because there is no prior
performance data:

- If UI `difficulty` = "auto" or "easy" → 50% easy + 50% medium
- If UI `difficulty` = "medium" → 30% easy + 50% medium + 20% hard
- If UI `difficulty` = "hard" → 0% easy + 40% medium + 60% hard

---

## Misconception vs. Knowledge Gap

When a user answers a topic incorrectly across multiple rounds, distinguish
the cause before de-escalating:

- **Knowledge gap**: learner hasn't encountered this yet or forgot it.
  → De-escalate type difficulty, provide `fill_in_blank` or `concept` questions
    on that topic before returning to `short_answer`.
- **Broken mental model (misconception)**: learner gives a consistent wrong
  pattern — same incorrect answer across rounds.
  → Correct the model first (see `misconceptions.md`), then re-test at easy
    before resuming normal escalation.

---

## Per-Round State to Maintain

```
round_number: {N}
last_round_score: {%}
consecutive_above_85: {0 | 1 | 2+}
consecutive_below_60: {0 | 1 | 2+}
current_difficulty_distribution:
  easy: {%}
  medium: {%}
  hard: {%}
topic_mastery:
  {topic_id}: {0–100}
```

At `[WRAP UP]`, topics where `mastery < 60%` are flagged in the
"Cần ôn thêm" section of the Session Summary.
