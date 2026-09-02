# Format Ladder — Doc Drill Reference

When a learner doesn't understand an explanation, the problem is almost never
*length* — it's *format*. More words in the same format add cognitive load
without adding signal.

The format ladder provides a structured escalation path: start at the simplest
format and move one rung up only when the current format fails.

Based on the format failure principle from cognitive science (Sweller, 1988;
Mayer, 2009) and the `learning` skill (Clawic).

---

## The Ladder (bottom to top)

### Rung 1 — Plain Prose (always start here)
A direct explanation in clear, jargon-free sentences.

**Use when**: First attempt at explaining any concept.
**Fail signal**: User says "I still don't get it" or gives an answer that
shows the explanation didn't land.

**Format**:
> "[Concept] means [plain statement]. The reason it works this way is [causal
> explanation in 1–2 sentences]."

**Example**:
> Flow control in TCP means the receiver tells the sender how much data it can
> handle at once. The reason: if the sender transmits faster than the receiver
> can process, packets get dropped and must be retransmitted — wasting bandwidth.

---

### Rung 2 — Concrete Example
A specific, real instance walked through end-to-end.

**Use when**: Plain prose failed. Learner needs something tangible to anchor to.
**Fail signal**: User understands the example but can't generalize beyond it.

**Format**:
> "Here's a specific case: [specific scenario with specific values/actors/steps].
> Notice how [key mechanism] shows up at [specific step]."

**Example**:
> Imagine your browser (receiver) has a buffer that can hold 65,535 bytes. It
> tells the server (sender) via the TCP header: "I can accept 65,535 bytes right
> now." The server sends that much, then waits. When your browser processes 10,000
> bytes and frees up space, it sends an ACK saying "you can send 10,000 more."

---

### Rung 3 — Analogy
Map the concept onto something the learner already knows.

**Use when**: Concrete example didn't create the "click." Learner needs a
familiar mental scaffold.
**Fail signal**: Learner confuses the source domain with the target domain
(the analogy created a new misconception).

**Format**:
> "[Concept] works like [familiar thing]: [mapping]. This analogy holds up to
> [point], but breaks down when [boundary] — so don't use it to predict [X]."

**Example**:
> TCP flow control is like filling a glass of water: you pour (send) at the
> rate the glass can handle (receiver buffer). If you pour too fast, it
> overflows (packet loss). This analogy works for the basic idea but breaks
> down when you add congestion control — the "glass size" isn't fixed, it
> shrinks based on network conditions too.

> ⚠️ **Always state where the analogy breaks** when you introduce it. An
> analogy left open-ended imports false properties into the learner's model.

---

### Rung 4 — Table or Diagram (text-based)
Structured visual organization of relationships.

**Use when**: Analogy failed or the concept involves multiple interacting parts
that are hard to hold in prose. Especially effective for comparisons and
multi-step processes.
**Fail signal**: The structure itself is unclear, or the learner still can't
identify the relationships.

**Format**:
> Use a markdown table for comparisons, a numbered list for processes, or an
> ASCII diagram for spatial/flow relationships.

**Example**:
```
Sender side:               Receiver side:
┌──────────┐               ┌──────────────────────┐
│ Send     │──[data]──────→│ Receive buffer       │
│ buffer   │               │ (advertised window)  │
└──────────┘               └──────────────────────┘
     ↑                              │
     └────[ACK + window size]───────┘
```

---

### Rung 5 — Worked Problem
Walk through a problem step by step, explaining *why* each step is taken.

**Use when**: Procedural or mathematical content where all other formats have
failed. The learner needs to see the process from the inside.
**Note**: Only appropriate for procedural content (algorithms, calculations,
formal proofs). Skip for purely conceptual topics.

**Format**:
> "Let's work through [specific problem] step by step. At each step, I'll tell
> you not just *what* to do, but *why* we do it."

---

## Rules for Using the Ladder

1. **Start at Rung 1 every time.** Do not skip to analogy on first attempt
   just because it feels more interesting.
2. **Move exactly one rung at a time.** Jumping from prose to worked problem
   skips formats that may have worked with less overhead.
3. **Never repeat a failed rung with more words.** If prose failed, more prose
   fails harder.
4. **After any explanation, check understanding** with one question before
   resuming the Q&A loop. Only count produced evidence (explain-back, application)
   as understanding — not "makes sense."
5. **After using an analogy, always state the boundary** where it breaks.

---

## Format Selection by Content Type

| Content type | Best starting rung | Most effective rung |
|---|---|---|
| Abstract concept (e.g., a principle) | 1 — Prose | 3 — Analogy |
| Multi-step process (e.g., algorithm) | 1 — Prose | 5 — Worked problem |
| Comparison between two things | 1 — Prose | 4 — Table |
| Cause-and-effect mechanism | 1 — Prose | 2 — Concrete example |
| Spatial or flow relationship | 4 — Diagram | 4 — Diagram |
