# Misconceptions — Doc Drill Reference

A **misconception** is not a knowledge gap — it is an *actively wrong* mental
model that generates consistent, predictable errors. Patching the surface
symptom (correcting one wrong answer) while leaving the underlying model intact
means the same errors will keep recurring.

Based on the hypercorrection effect (Butterfield & Metcalfe) and misconception
repair frameworks from cognitive science.

---

## Step 1 — Detect: Is This a Misconception or a Knowledge Gap?

| Signal | Likely cause |
|---|---|
| User confidently states something incorrect | Misconception — they *believe* it |
| User gives the same wrong answer pattern across multiple questions | Misconception — a model is generating it |
| User is simply silent or says "I don't know" | Knowledge gap — information missing |
| User's answer is partially right but consistently missing one dimension | Could be either — probe further |

**Detection probe**: If you suspect a misconception, ask:
> "What did you expect to happen, and why?"

The explanation reveals the model. If the model is wrong, fix the model.

---

## Step 2 — Name the Misconception

Before correcting, explicitly name what the wrong model is:

> "It sounds like you're working with the idea that [wrong model]. That's a
> very common starting point — here's where it breaks down."

Naming it:
1. Validates that the learner's reasoning was internally consistent
2. Makes the correction more memorable (hypercorrection effect — you remember
   corrections to confident wrong answers better than answers to blank questions)
3. Prevents the learner from thinking they were "just guessing wrong"

---

## Step 3 — Correct at the Root

Correct the **model**, not just the answer:

1. Show *why* the wrong model produces incorrect predictions.
   Example: "If [wrong model] were true, we'd expect [X]. But we actually
   observe [Y]. So the model has to be different."
2. Introduce the correct model with a concrete example or analogy.
3. State where any analogy breaks (to prevent it from becoming a new misconception).

---

## Step 4 — Re-ask to Confirm the Fix

After correcting, **do not move on immediately**. Ask a fresh question that
requires the learner to apply the corrected model:

> "Now, using what we just discussed — [slightly varied scenario]?"

If the learner gets this right, the model is repaired. If not, repeat Step 3
with a different format (see `format-ladder.md`).

---

## Step 5 — Log for Wrap-Up

Note the misconception in your session mental tally:

```
Misconception detected: [description of the wrong model]
Topic: [concept name]
Corrected: [yes / still uncertain]
```

At `[WRAP UP]`, include all corrected misconceptions in the "Misconceptions
corrected" section of the Session Summary so the learner has an explicit record.

---

## Common Misconception Patterns by Domain

### Computer Networks
- TCP/UDP differ only in speed (wrong: they differ in reliability guarantees,
  connection state, and error recovery)
- The Transport Layer communicates host-to-host (wrong: it communicates
  process-to-process via ports)

### Programming / Software
- Concurrency = parallelism (wrong: concurrency is about structure, parallelism
  is about simultaneous execution)
- More threads always means faster (wrong: depends on I/O vs CPU bound nature)

### General Science
- Correlation implies causation
- Absence of evidence = evidence of absence

> Add domain-specific misconceptions from the document being studied as they
> are discovered during the session.

---

## What NOT to Do

- **Do not correct the surface answer and move on.** The model will generate
  the same error next time.
- **Do not be dismissive.** Misconceptions are rational generalizations that
  happen to be wrong in this domain. Treat them as evidence of thinking, not
  failure.
- **Do not give the correct answer before probing the model.** If you correct
  without understanding the misconception, you may not address the right thing.
