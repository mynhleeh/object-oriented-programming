# Deep Flashcard Generator — Card Types & Generation Rules

Read this when generating cards. This holds the Grounding Fidelity Ladder, the Materiality Filter, the full per-type rules, the Source/Anchor/Connection-Fidelity Tests, the Type 2/4 boundary, and the Diversity Rule.

## Guiding Principle

> **One card = One unit of knowledge (Minimum Information Principle)**

The card count is determined by the document content, not a fixed quota.

## Knowledge Map (Mandatory Intermediate Step)

Before writing any card, build the **Knowledge Map** — one row per knowledge unit, deduplicated and filtered. This is the step that prevents duplicate cards and deck bloat; it is not optional, and it is not the same as the raw scan in Step 1.

| ID | Knowledge unit | Type (1–5) | Anchor |
|---|---|---|---|
| U1 | A hub is any facility where parcels are sorted and routed | 1 | §1.1 |
| U2 | End points are sources/sinks of parcels; intermediate hubs forward parcels between routes | 3 | §1.2 |

**Rules:**
- Each row is a **single knowledge unit** — one fact, one distinction, one mechanism. If a row holds two facts, split it.
- **Merge rule**: if two candidate rows answer the same recall question (same fact from different angles), merge them into one row *now* — before any card is written.
- **Drop rule**: apply the Materiality Filter at this stage. Trivia rows get no row.
- **Anchor rule**: every row carries a source anchor (`§section`, `p.page`, `¶paragraph`). A row without an anchor is a row whose fidelity you cannot prove — fix it or drop it.

**Dedup test for candidate rows:**
> "If I asked a learner to recall this, would rows A and B both be triggered by the same answer?" — YES → merge. NO → keep both.

The finished row count is the **ceiling** for deck size (see SKILL.md Step 2 hard caps): deck ≤ map rows + second-cards earned.

## Grounding Fidelity Ladder (Critical)

Every card answer must be traceable to the document. Before creating any card, classify the fidelity of its content:

| Level | Meaning | Allowed? |
|---|---|---|
| **Explicit** | Stated directly, word-for-word or in direct paraphrase, in the document. | ✅ Default |
| **Entailed** | A logical consequence that *must* be true given what the document states (e.g., the doc says "X requires Y" and "this system uses X" ⇒ "this system requires Y"). No outside knowledge is used. | ✅ Default |
| **Inferred** | A plausible extension using outside knowledge or counterfactual reasoning. | ⚠️ Inference mode only, tagged `inf::` |

**Rules:**
1. **Default mode** allows only **Explicit** and **Entailed** content. When in doubt, classify as *Inferred* and omit the card.
2. **Inference mode** is opt-in: the user must explicitly ask for it (e.g., "allow inference", "inference mode"). Inferred cards must carry an `inf::` tag, and their back must still be anchored to at least one document concept.
3. The Source Test (Type 4) and the Anchor Test / Connection Fidelity Test (Type 5) below are applications of this ladder.

## The 5 Card Types

### Type 1 — Definition
Cover every key term, concept, and acronym that passes the Materiality Filter (Significance criterion). At least 1 card per qualifying term; single-mention throwaway terms get no card.

### Type 2 — Mechanism / Process
Cover every mechanism, algorithm, workflow, or causal relationship that passes the Materiality Filter. 1 card per topic.

> **Boundary with Type 4**: Type 2 describes *how a mechanism operates* (the process itself, 1 card per topic). Type 4 asks *why it was designed that way*, *what must be true for it to work*, or *what happens when a condition occurs* — and only when the document states that rationale or condition. If the document only describes the mechanism, you get a Type 2 card and **no** Type 4 card for that topic.

### Type 3 — Comparison / Distinction
Cover pairs of concepts that could be confused or that appear side by side **in the document**, and that pass the Materiality Filter. 1 card per pair. Do NOT enumerate every possible pair — all-pairs coverage is not a goal.

### Type 4 — How / Why (Source-Grounded)
Generate cards asking **Why** and **under-what-conditions** — design rationale and causal conditions — **only when the answer is Explicit or Entailed in the document** (see Grounding Fidelity Ladder). Mechanism description itself belongs to Type 2, not here.

**Permitted angles:**
- **Why it is designed this way**: "Why is [component/approach] designed as described?" — answer must be Explicit or Entailed in the document.
- **What happens when**: "What happens when [process/condition] occurs?" — answer must be Explicit or Entailed in the document.
- **Prerequisites / conditions**: "What must be true for [concept X] to work?" — answer must be Explicit or Entailed in the document.

**NOT permitted:**
- Re-asking a mechanism that a Type 2 card already covers for the same topic (duplicate).
- Anything not Explicit or Entailed in the document — counterfactuals and speculation are inference-mode only.

**Type 2/4 boundary — mechanical decision test** (run before writing any Type 4 card for topic X):
1. Does a Type 2 (or Type 1) card for X already exist in the map? If **NO** → the "how it works" question belongs in Type 2, not Type 4.
2. If **YES** → is the Type 4 answer (rationale / condition / what-happens-when) **stated in the document**? Point at the sentence. If the document only describes the mechanism, there is **no** Type 4 card for X — omit it.
3. Never write a Type 4 card that a learner could answer with the Type 2 back. If both cards would receive the same answer, you have a duplicate — drop the Type 4 card.

### Type 5 — Knowledge Connection
After completing Types 1–4, identify conceptual links between 2 or more concepts that **both appear in the document**.

**Valid connection angles (must be Explicit or Entailed — see Grounding Fidelity Ladder):**
- Causal chains: "Concept A enables Concept B, which in turn makes Concept C possible"
- Dependency maps: "What does Concept X depend on, and what depends on it?"
- Synthesis: "How do components A, B, C work together to achieve outcome Y?"
- Contrast within document: "How do A and B differ in purpose, even though they both serve function X?"

**Classification:** All connection cards are treated equally — no prefix.

**Connection Fidelity Test** — for each proposed connection, ask:
> *"Is this connection Explicit (stated) or Entailed (forced by the document's statements)?"*
- **Explicit or Entailed** → Create the card.
- **Neither** → Skip in default mode. In inference mode, create it only with an `inf::` tag and a back anchored to at least one document concept.

**Anchor Test**: All concepts referenced must exist in the document.

**Materiality Test**: a connection card is worth creating only if it deepens understanding of at least one referenced concept (see Materiality Filter). Chaining every term to every other term is not a goal.

**Number of connection cards**: No fixed quota — the Materiality Filter applies.

## Materiality Filter (Critical)

"Exhaustive" does not mean "everything gets a card". Apply this filter before creating *any* card — it prevents deck bloat and low-value cards: **the Materiality Filter is the supreme gate** — no card is created for content that fails it, regardless of any per-type "cover every" language above.

1. **Significance** — the concept appears at least twice in the document **or** carries structural weight (a section is devoted to it, it is a prerequisite for other concepts, it is explicitly defined). Single-mention throwaway terms get **no** card.
2. **Independent learning value** — the card tests knowledge worth recalling on its own. If the front already gives away the answer, skip it.
3. **Non-redundancy** — no two cards test the same knowledge unit from the same angle. If a Type 2 card already covers the mechanism, do not create a near-identical Type 4 card (see the Type 2/4 boundary).

A deck built under this filter is **dense but small** — every card earns its place.

**Real FAIL examples (from field testing — these must NOT get cards):**
- ❌ "How many parcels did NimbusCorp process in 2025?" → "4.2 million." A standalone statistic with no conceptual weight — tests trivia, not understanding.
- ❌ "How many routes can connect two hubs?" → "One or more." The answer is empty (everything is "one or more"); no independent learning value.
- ❌ "What do intermediate hubs do?" → "Forward parcels." When this fact already lives inside the "intermediate hubs" unit, a separate card is redundant.
- ❌ Two cards where one asks "What is NimbusCorp?" and another asks "What does the name 'NimbusCorp' literally mean?" → same answer twice. Merge into one unit at the map stage.

**Materiality Test for any candidate card:** *"Does recalling this answer make the learner understand the document better?"* — a card fails if the answer is a bare number, a single word, obvious from the front, or a rehash of another card's back.

## Diversity Rule (Critical)

Diversity has two independent layers. **Layer 1 is mandatory; Layer 2 is source-gated.**

### Layer 1 — Phrasing Diversity (always mandatory)
Actively vary the *question phrasing* across all cards. Do NOT repeat the same sentence structure for multiple cards. Vary question form (direct question vs. fill-in-the-blank vs. scenario prompt), sentence opener and syntax, and front length/specificity.

**Countable opener budget (hard cap):** no opener pattern may account for more than **25% of the deck**. The most common failure is a wall of "What is / What are X?" fronts. Count them; if > 25%, convert the excess to fill-in-the-blank, scenario, or "What role does / What distinguishes / What happens when" forms. Layer 1 is purely stylistic — it never requires content beyond the document.

### Layer 2 — Content-Angle Diversity (source-gated)
Each angle below is allowed **only if** the document supports it for that concept (Explicit or Entailed — run the Source Test / Anchor Test):

| Angle | Example | Gate |
|---|---|---|
| What it is / does | "What does X do?" | Source Test |
| How it works | "How does X operate?" | Type 2 (mechanism) |
| Why it exists / design rationale | "Why was X designed this way?" | Source Test (Type 4) |
| Prerequisite thinking | "What must be true for X to work?" | Source Test (Type 4) |
| What happens when it fails / is misused | only if the document describes failure behavior | Source Test |
| When to use vs. when not to | only if the document states usage conditions | Source Test |
| Comparison with another document concept | "How does X differ from Y?" | Anchor Test (both in doc) |
| Connection | "How do X and Y depend on each other?" | Anchor Test + Connection Fidelity Test (Type 5) |

**Inference-mode only (default-mode exclusions, require `inf::` tags):**
- ✗ **Inversion** — "what would the system look like WITHOUT this component?"
- ✗ **Stakeholder perspective** — "how does this look from the user's vs. developer's viewpoint?"
- ✗ **Degree/scale extremes** — "what happens if this is pushed to extremes?"
- ✗ **Real-world analogies** — outside the document

The goal is a deck that feels **rich and multi-dimensional** — not a list of `"What is X?"` cards — without ever sacrificing source fidelity.
