# Question Types — Doc Drill Reference

Five question types available in Doc Drill (all DeepTutor types except
`coding`). Each entry includes: purpose, when to use, content-type fit,
difficulty range, format template, and a concrete example.

---

## Type Overview

| Type | Key | Best for | Difficulty range |
|---|---|---|---|
| Multiple Choice | `choice` | Factual recall, concept discrimination | easy → hard |
| Concept Question | `concept` | Definitions, comparisons, principles | easy → medium |
| Fill in the Blank | `fill_in_blank` | Terminology, formulas, sequences | easy → medium |
| Short Answer | `short_answer` | Mechanisms, applications, explanations | medium → hard |
| Essay | `written` | Synthesis, trade-offs, complex scenarios | hard only |

> **Not used in Doc Drill:** `coding` — excluded by design.

---

## 1. Multiple Choice (`choice`)

**Purpose**: Efficient factual recall and concept discrimination. Forces the
learner to distinguish between plausible alternatives — not just recall an answer
but reject three distractors.

**When to use**:
- Factual or conceptual content where a crisp right answer exists
- Testing definitions, cause-effect, and "which of these" questions
- Any difficulty level — adjust by choosing more similar distractors for hard

**Content-type fit**: Factual ✅ · Conceptual ✅ · Procedural (simple steps) ✅

**Difficulty calibration**:
- **Easy**: one obviously correct answer; distractors are clearly wrong
- **Medium**: two distractors are partially plausible; correct answer requires understanding
- **Hard**: three distractors are plausible misconceptions; correct answer requires precision

**Format**:
```
{Question}?
A) {option}
B) {option}
C) {option}
D) {option}
```

**Pitfall**: Avoid "all of the above" / "none of the above" — they test
test-taking skill, not content knowledge.

**Example** (Computer Networks):
> Which layer of the OSI model is responsible for end-to-end error recovery
> and flow control?
> A) Network layer
> B) Data Link layer
> C) Transport layer
> D) Session layer

---

## 2. Concept Question (`concept`)

**Purpose**: Tests understanding of a concept at an explanatory level — not just
what it is, but what it means and how it relates to other ideas.

**When to use**:
- Definitions that need more than a one-word answer
- Comparisons between two related concepts
- First question on a new topic (equivalent to Definition tier in Bloom)

**Content-type fit**: Conceptual ✅ · Factual (extended) ✅ · Procedural ❌

**Difficulty calibration**:
- **Easy**: define a single term in your own words
- **Medium**: explain the relationship or difference between two concepts

**Format**:
```
{Open question requiring a 2–4 sentence conceptual explanation}
```

**Expected answer length**: 2–4 sentences. Reject single-word answers;
reward explanations that use the concept in context.

**Pitfall**: Do not accept "it's when you..." without the learner naming
the concept and explaining *why* it works that way.

**Example**:
> In your own words, what is the difference between flow control and
> congestion control in TCP? Why does TCP need both?

---

## 3. Fill in the Blank (`fill_in_blank`)

**Purpose**: Tests precise recall of terminology, formulas, sequences, or
fixed phrases — the kind of knowledge that must be retrieved exactly.

**When to use**:
- Technical terms and acronyms that must be known exactly
- Mathematical formulas or algorithmic sequences
- Key phrases from the document that carry specific meaning

**Content-type fit**: Factual ✅ · Conceptual (terminology) ✅ · Procedural (steps) ✅

**Difficulty calibration**:
- **Easy**: single blank, answer is a well-known term
- **Medium**: multiple blanks or a less common term; one blank is a trap (similar term)

**Format**:
```
{Sentence with one or more blanks indicated by _____}
```

Fuzzy matching for grading: accept synonyms, minor spelling errors (1–2
characters), and common abbreviations.

**Pitfall**: Avoid blanks at the very start of a sentence — they give no
context and become guessing games.

**Example**:
> TCP uses a _____ handshake to establish a connection, which involves
> three steps: SYN, _____, and ACK.

---

## 4. Short Answer (`short_answer`)

**Purpose**: Tests the ability to explain *how* something works, apply
a concept to a scenario, or reason about cause and effect in 1–3 sentences.
Requires AI judging (not exact match).

**When to use**:
- Mechanism questions: "How does X work?"
- Application questions: "In this scenario, what would happen?"
- After `choice` and `concept` questions have established the foundation

**Content-type fit**: Conceptual ✅ · Procedural ✅ · Factual (justification) ✅

**Difficulty calibration**:
- **Medium**: single mechanism or application; answer fits in 2–3 sentences
- **Hard**: two-step reasoning or cross-concept application; 3–5 sentences expected

**Grading rubric** (AI judge):
- **Correct** (full points): core mechanism/answer present + at least one supporting detail
- **Partial** (50% points): core idea present but missing key supporting detail or contains minor inaccuracy
- **Incorrect** (0 points): wrong mechanism, missing core idea, or completely off-topic

**Format**:
```
{Scenario or mechanism question expecting a 2–5 sentence answer}
```

**Pitfall**: Do not use short_answer for questions with a single-word answer
— use `choice` or `fill_in_blank` instead. Short answer is for explanation.

**Example**:
> A client sends a TCP SYN to a server that is currently handling maximum
> connections. What does the server respond with, and why?

---

## 5. Essay (`written`)

**Purpose**: Tests synthesis, trade-off analysis, and deep reasoning across
multiple concepts. The highest-difficulty type — requires sustained argument,
not just recall.

**When to use**:
- **Hard difficulty only** — never use for easy or medium rounds
- Cross-concept synthesis questions
- Trade-off or "design decision" questions
- After the learner has demonstrated solid Short Answer performance on the topic

**Content-type fit**: Conceptual ✅ · Procedural ✅ · Factual ❌

**Difficulty calibration**:
- **Hard only**: synthesize ≥ 2 concepts; identify a tension or trade-off;
  justify a design decision

**Grading rubric** (AI judge):
- **Correct** (full points): identifies the core trade-off or synthesis, provides reasoning, and demonstrates understanding of both sides
- **Partial** (50% points): identifies the concept but reasoning is shallow or one-sided; OR covers all points but with one significant inaccuracy
- **Incorrect** (0 points): misidentifies the core concept, shows a misconception, or is too vague to evaluate

**Format**:
```
{Open-ended question expecting a structured 1–3 paragraph response}
```

**Pitfall**: Essay questions are NOT "list everything you know." They should
require the learner to identify a *relationship*, *tension*, or *design
rationale*. Avoid vague prompts like "Explain TCP."

**Example**:
> TCP's reliability mechanisms (sequence numbers, acknowledgments, retransmission)
> introduce overhead that can hurt real-time applications. Design a transport
> protocol for live video streaming: which TCP mechanisms would you keep, which
> would you drop, and why? What new mechanisms, if any, would you add?

---

## Bloom Taxonomy → Difficulty Mapping

| Bloom Level | Question types | Difficulty |
|---|---|---|
| **Remember** (recall facts) | `choice`, `fill_in_blank` | easy |
| **Understand** (explain concepts) | `concept`, `choice` (medium) | easy–medium |
| **Apply** (use in new context) | `short_answer`, `choice` (hard) | medium |
| **Analyze** (break down, compare) | `short_answer` (hard), `concept` (medium) | medium–hard |
| **Evaluate / Create** (judge, synthesize) | `written` | hard |

---

## Type Selection Quick Reference

When `question_types` in UI config is empty (auto-select):

```
content_type = factual   + difficulty = easy    → choice (prefer) or fill_in_blank
content_type = factual   + difficulty = medium  → choice or fill_in_blank (multiple blanks)
content_type = factual   + difficulty = hard    → choice (hard distractors) or short_answer
content_type = conceptual + difficulty = easy   → concept or choice
content_type = conceptual + difficulty = medium → concept or short_answer
content_type = conceptual + difficulty = hard   → short_answer or written
content_type = procedural + difficulty = medium → short_answer
content_type = procedural + difficulty = hard   → short_answer or written
```
