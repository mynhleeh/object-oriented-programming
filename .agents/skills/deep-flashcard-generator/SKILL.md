---
name: deep-flashcard-generator
description: "Reads any attached document (PDF, DOCX, TXT, images, etc.), exhaustively extracts core concepts and generates high-quality Anki-ready flashcards using a strict source-only approach. Every card — definition, mechanism, comparison, How/Why, and knowledge connection — must be directly grounded in the provided document. Works with any subject. Trigger whenever the user attaches a document and wants study flashcards. Do NOT trigger when the user wants a summary, translation, quiz, or vocabulary lookup rather than flashcards — use doc-drill or vocab-lookup for those."
version: 2.0.0
tags:
  - flashcards
  - anki
  - study
  - knowledge-extraction
license: MIT
compatibility: "Any agent"
---

# Deep Flashcard Generator

> Turn any attached document into a high-quality, multi-dimensional Anki flashcard deck — exhaustively extracted from the source with strict fidelity. Every card is directly grounded in the provided document.

## When to Activate

Activate when the user attaches a document (PDF, DOCX, TXT, slides, images, notes) and wants to study it by generating Anki-ready flashcards. Works with a **minimal prompt**: `activate deep-flashcard-generator` + an attached document. Any parameter not explicitly stated by the user → apply the skill's defaults.

> **Do NOT trigger** when the user wants a summary, translation, a quiz, or a vocabulary lookup of specific words. Use `doc-drill` (document quiz), `document-vocab-extractor` (extract all vocabulary), or `vocab-lookup` (look up specific words) for those.

## Overview

This skill turns any attached document into a **multi-dimensional Anki flashcard deck**, exhaustively extracted from the source with strict fidelity. Cards span definitions, mechanisms, comparisons, How/Why reasoning, and knowledge connections — all grounded in the document. The pipeline enforces a **source-test / anchor-test** gate: if the answer is not clearly in the document, the card is omitted rather than invented.

> **Model-agnostic by design.** This skill is engineered so that **every model — weak or strong — follows the same mechanical rules** and produces a reliable deck. Every rule is **mechanical and countable** — "0 yes/no fronts", "≤ 25% of fronts share one opener", "≤ 2 cards per topic", "back ≤ 3 sentences" — never a matter of taste or judgment. The pipeline has exactly **3 phases, run in order**: **Knowledge Map** → **Draft (1 card per unit)** → **Mechanical Self-Audit**. Do not skip a phase; do not merge phases into a single pass. A single-pass "write and self-check" deck is what produced the failure modes this skill is built to prevent (duplicate cards, yes/no fronts, trivia cards, leaked example content).

## Language Policy

Follow the house language policy (summary — full rules + anti-examples live in `references/language-and-output.md`):
- **Priority**: user's explicit request > document's dominant language > English default.
- The target language applies to **all content** — the front and the back must both be in the target language.
- Internal processing follows these English instructions; only the output is in the target language.
- IT/technical terms (protocols, algorithms, tools, constructs) always stay in English.

---

## Role

You are an expert **document analyst and instructional designer**. Your mission:
1. **Read and deeply understand** the entire attached document.
2. **Exhaustively extract** every concept, mechanism, process, comparison, and How/Why explanation that passes the Materiality Filter — leave nothing *material* behind.
3. **Connect knowledge** by identifying relationships between 2 or more concepts that both appear in the document — causal chains, dependency maps, synthesis, contrasts. All connection cards are treated as regular cards with no special prefix.
4. **Guarantee absolute accuracy (100%)** — every card answer must be *Explicit* or *Entailed* in the source (see Grounding Fidelity Ladder). Never speculate, never fabricate. If you are not 100% certain about the fidelity level, **omit the card** (or, only in opt-in inference mode, tag it `inf::`). Universally known facts may support an answer only when the core concept is clearly stated in the document.

---

## Step 1: Document Analysis → Build the Knowledge Map

Scan the entire document and identify (internal lists, do not output):
- **Concepts / Terms**: All definitions, key terms, and acronyms present.
- **Mechanisms / Processes**: How systems, algorithms, or workflows operate.
- **Comparisons / Distinctions**: Pairs of concepts that are commonly confused or placed side by side.
- **Real-world Applications**: Specific use cases or examples mentioned.
- **Relationships**: Dependencies, hierarchies, or causal chains between concepts in the document.
- **Source anchors**: an internal map `concept → anchor` (`§section`, `p.page`, `¶paragraph`). Kept internal; surfaced only in audit mode.
- **Document health**: note any truncated, unreadable, or unverifiable content (bad OCR, blurred images, cut-off sections). If any exists, you must emit the single warning line before the output block (see `references/language-and-output.md`).

Then build the **Knowledge Map** — one row per knowledge unit. This is a mandatory intermediate step; never skip it and never write cards directly from the raw scan. **The finished map MUST be written into the `## Working Notes` block before any card is drafted** (see Output Contract) — the map is the audit trail that proves dedup; it is review-only, never imported. Template (full rules in `references/card-types.md`):

| ID | Knowledge unit | Type (1–5) | Anchor |
|---|---|---|---|
| U1 | A hub is any facility where parcels are sorted and routed | 1 | §1.1 |
| U2 | A hub forwards parcels between routes but is not an end point, so it is not called a depot | 1 | §1.3 |

**Deduplicate at the map stage — not later.** If two candidate rows answer the same recall question (same fact approached from different angles), merge them into one row *now*. Apply the Materiality Filter here too: drop trivia rows now. The row count of the finished map is the **ceiling** on the number of cards — if the deck ever has more cards than the map has rows, the dedup failed. (See `examples/worked-example.md` for a full map build.)

> **⚠️ Examples are PROCESS illustrations, not CONTENT sources.** The worked example uses a fictional company (NimbusCorp) to demonstrate the workflow. Facts from any example must never appear in a deck — unless the attached document itself contains them. If you cannot point at the sentence in the attached document that supports a card, the card is fabricated → drop it.

---

## Step 2: Card Generation Strategy (One Card per Knowledge Unit)

The card count is capped by the Knowledge Map from Step 1, under one guiding principle:

> **One card = One knowledge unit. No card is created without a map row; no map row produces more than 2 cards.**

Generate cards across 5 types: definition, mechanism/process, comparison/distinction, How/Why (source-grounded), and knowledge connection.

**Hard caps (mechanical, not judgment):**
- **1 card per map row by default; never more than 2 cards per topic.** A 2nd card for the same topic is allowed only for a genuinely different recall angle (e.g., one definition card + one "why" card). Never two cards that ask the same question from different wording.
- **Deck size ≤ map rows + (number of topics that legitimately earned a 2nd card).** If the draft has more cards than the map's rows, the dedup failed — go back to Step 1 and merge rows, do not keep the extra cards.

Apply, in order: the **Grounding Fidelity Ladder** (Explicit/Entailed by default; Inferred only in opt-in inference mode), the **Materiality Filter** (no bloat), the **Source Test** and **Anchor/Connection-Fidelity Tests** (omit anything that fails), the **Type 2/4 boundary** (mechanisms → Type 2; rationale/conditions → Type 4), and the **Diversity Rule** (opener budget in Step 3). Read `references/card-types.md` before generating cards — it holds the full per-type rules, the ladder, the filters, and all tests.

**Phase gate:** do not draft a single card until the Knowledge Map is complete **and written into the `## Working Notes` block**. Skipping the written map is a contract violation.

---

## Step 3: Card Writing Rules (Mandatory)

### Front
- Short, clear, unambiguous. Phrased to provoke active recall.
- Can be a direct question, a fill-in-the-blank statement, or a scenario prompt — as long as it is unambiguous and tests a single concept.
- **Hard rule — ZERO yes/no fronts.** A front is a yes/no question if it starts with — or could be answered with — *Can / Could / Is / Are / Do / Does / Did / Will / Would / Should / May / Might*. Rewrite every one before it enters the deck. This is a countable rule, not a style preference. Apply the rewrite recipes below mechanically.

**Yes/No → open-question rewrite recipes** (apply mechanically, do not skip):

| ❌ Starts with | ✅ Rewrite to | Example |
|---|---|---|
| Can / Could X…? | What can X do? / What do X and Y share? | ❌ "Can a van and a drone both be called a delivery vehicle?" → ✅ "What do a van and a drone have in common?" |
| Is / Are X…? | What is X? / What distinguishes X? | ❌ "Are there end points that don't receive parcels?" → ✅ "What kinds of end points do not receive parcels?" |
| Do / Does X…? | What does X do? / How does X …? | ❌ "Do hubs forward parcels?" → ✅ "What does a hub do with parcels?" |
| Will / Would X…? | What happens when X…? / What is the result of X? | ❌ "Would removing a hub break the network?" → ✅ "What happens to the network when a hub is absent?" |

**Opener budget (hard cap):** no opener pattern may exceed **25% of all fronts**. The classic failure is a wall of "What is / What are X?" — count them; if more than 25%, convert the excess to fill-in-the-blank, scenario, or "What role does / What distinguishes / What happens when" forms. This is a countable check, not a preference.

### Back
- Short to medium length: **max 2–3 sentences** or a compact list (≤ 4 bullets).
- Direct, accurate, easy to recall.
- If an explanation exceeds 3 sentences → **split into multiple cards** instead of cramming into one.
- No filler openers like "This is...", "The answer is...".

### Accuracy
- Apply the **Grounding Fidelity Ladder**: by default only *Explicit* or *Entailed* content; *Inferred* content only in inference mode (tagged `inf::`).
- Never speculate or fabricate information. If uncertain about the fidelity level → classify as *Inferred* and omit (or tag, in inference mode).
- All cards must be grounded in the document. Universally known facts may support an answer whose core concept is clearly in the document — never to introduce new topics.

---

## Step 4: Card Type

Generate **Basic cards only** (front / back).  
**Do NOT** use Cloze deletion format (`{{c1::...}}`).

---

## Step 5: Mechanical Self-Audit (Mandatory)

Review the finished deck as a **stranger reviewing someone else's work** — not as its author defending it. This is a separate re-read of the completed deck; never audit in the same breath you wrote the draft. Models — weak or strong — fail when they "check" their own output in the same pass that produced it.

Run the **7-item Mechanical Self-Audit checklist** in `references/self-audit.md` — every item has a countable pass criterion, a fix recipe, and a bad→fixed example:

1. **Zero yes/no fronts** — scan the first word of every front (`Can/Could/Is/Are/Do/Does/Will/Would/Should/May/Might`). Any hit → rewrite with the Step 3 recipes.
2. **Opener budget** — no opener pattern exceeds 25% of fronts.
3. **Non-redundancy** — no two backs answer the same recall question (merge or drop; verify against the map's row count).
4. **Materiality** — no back is a bare number, a single word, or answerable from the front itself.
5. **Back length** — each back ≤ 3 sentences or ≤ 4 bullets; no filler openers.
6. **Fidelity** — every answer is *Explicit* or *Entailed* (or `inf::` in inference mode); every concept on a card has a row in the Knowledge Map.
7. **Contamination** — every back can be quoted from the **attached document**, never from an example; any card not traceable to the document is dropped.

**Fix-then-recheck**: apply every fix, then re-run all 7 checks once more. Only when all 7 pass, emit the output block.

## Output Contract

Emission order for **every run**: `## Working Notes` → (optional warning line) → `text` block → (audit mode only) `## Audit Summary`.

- **Working Notes (mandatory, all modes)**: a `## Working Notes` markdown block emitted **before** the `text` block, containing (1) the final Knowledge Map table and (2) the 7-item audit counts (0 yes/no · ≤25% opener · deck ≤ map rows · no trivia · backs ≤ 3 sentences · all anchored · no contamination). It is the audit trail that proves the pipeline ran — **review-only, never import**.
- **Default mode**: a **single `text` fenced block** containing the TSV cards (`front[TAB]back`, one card per line). No preamble, no postamble, no explanations.
- **Audit mode** (user opt-in): the `text` block with a 3rd tags column (`card_type::*`, `src::*`, `inf::` where applicable), followed by exactly one `## Audit Summary` block.
- **Document warning**: at most one line before the block, only when the document is truncated/unreadable.
- **Anki import**: copy only the `text` block; `## Working Notes` and `## Audit Summary` are review-only and must not be imported.
- Anything else — explanations, summaries (default mode), commentary — is a contract violation.

## Step 6: Output (Strictly Enforced)

Every run emits, in order: a **`## Working Notes`** block (Knowledge Map + 7-item audit counts — review-only, never imported), then in **default mode** a **2-column TSV** (`front[TAB]back`) in a single `text` block — nothing outside it. **Audit mode** (user opt-in) emits a **3-column TSV** (`front[TAB]back[TAB]tags`) plus a `## Audit Summary` block. One warning line precedes the blocks only when the document is truncated/unreadable. For Anki import, copy only the `text` block. Full rules, templates, and the audit tag schema are in `references/language-and-output.md`.

## Anti-Examples

| ❌ Avoid | ✅ Prefer | Why |
|---|---|---|
| "What is NimbusCorp?" (Front English, Back Vietnamese) | "NimbusCorp đóng vai trò gì trong mạng lưới giao hàng?" (full Vietnamese) | The target language must apply to the front too. |
| Blending languages inside a single card | Writing the whole card in one target language, keeping only technical terms in English | Prevents confusing language-split cards. |
| Building a connection card from "reasonable inference" | Creating it only when the connection is Explicit or Entailed (or `inf::`-tagged in inference mode) | Preserves the 100% source-fidelity guarantee. |
| "What would the system look like without X?" in default mode | Replacing with "What role does X play, per the document?" | Counterfactuals are inference-mode only. |
| Carding every single-mention term | Applying the Materiality Filter | Keeps the deck dense, not bloated. |
| Asking Type 4 "How does X work?" when a Type 2 card already covers the mechanism | Asking Type 4 only for rationale/conditions stated in the document | Prevents duplicate cards. |
| Adding prose or a summary before/after the TSV block | Emitting only the `text` block (plus the one permitted warning line) | Output must be machine-parseable. |
| Audit mode: omitting `card_type::` or `src::` tags | Tagging every card with `card_type::*` and `src::*` | Makes the deck auditable. |
| "Can a van and a drone both be called a delivery vehicle?" (yes/no front) | "What do a van and a drone have in common?" | Yes/no fronts are guessable 50/50; rewrite to open questions. |
| Two cards that both answer "what is NimbusCorp?" | One card per knowledge unit — merge duplicates at the map stage | Duplicate cards waste reviews; the map is the dedup point. |
| Carding a standalone statistic ("How many parcels did NimbusCorp process in 2025?") | Card the concept the statistic supports, or drop it | Trivia cards fail the Materiality Filter. |

## Common Issues

| Issue | Fix |
|---|---|
| Deck feels bloated with trivial cards | Apply the Materiality Filter strictly; single-mention throwaway terms get no card |
| Type 2 and Type 4 cards on the same topic feel duplicated | Type 2 describes the mechanism; Type 4 only if the document states rationale/conditions |
| A card's answer drifts outside the document | Classify fidelity (Explicit / Entailed / Inferred); omit, or tag `inf::` in inference mode |
| Front and back in different languages | Rewrite both sides in the target language; keep technical terms in English |
| Prose sneaks in before/after the TSV block | Emit only the block; the sole exception is the one-line truncation warning |
| User asks where a card came from | Re-run in audit mode (`card_type::*`, `src::*` tags + `## Audit Summary`) |
| Two cards ask the same thing from different angles (e.g., "What is NimbusCorp?" + "What does the name 'NimbusCorp' literally mean?") | Merge at the Knowledge Map stage: if two rows answer the same recall question, keep one row |
| A wall of "What is / What are X?" fronts | Count openers; convert the excess past 25% to fill-in-the-blank / scenario / "What role does" forms |
| Yes/no fronts slip in ("Can X…?", "Are there…?") | Apply the Step 3 rewrite recipes mechanically; zero yes/no is a hard rule |
| Back is a bare number or single word ("one or more") | The unit fails independent learning value — drop it or reframe to test the concept behind it |
| The model "audits" the deck in the same breath it wrote it | Step 5 is a separate re-read: review as a stranger, fix, then re-run all 7 checks |

## References
- `references/card-types.md` — read **before generating cards**: Knowledge Map template, 5 types, Grounding Fidelity Ladder, Materiality Filter (with FAIL examples), Source/Anchor/Connection-Fidelity Tests, Type 2/4 boundary, Diversity Rule.
- `references/self-audit.md` — read **at Step 5**: the 7-item Mechanical Self-Audit checklist with pass criteria, fix recipes, and bad→fixed examples.
- `examples/worked-example.md` — read **before first use**: a complete walkthrough (map → draft → audit → final TSV) showing every rule applied on a neutral fictional excerpt (NimbusCorp — a made-up delivery company).
- `references/language-and-output.md` — read **before formatting output**: language rules, default vs. audit mode, tag schema, warning line, audit summary.
- `../skill-forge/references/house-language-policy.md` — read when choosing the response language.

## Example Output

### Working Notes (emitted before the `text` block — review-only, never imported)

| ID | Knowledge unit | Type | Anchor |
|---|---|---|---|
| U1 | A hub is any facility where parcels are sorted and routed | 1 | §1.1 |
| U2 | End points are sources/sinks of parcels; intermediate hubs forward parcels between routes | 3 | §1.2 |
| … | (one row per knowledge unit — this is the dedup audit trail) | … | … |

Audit: 0 yes/no fronts · "What is/are" 2/14 (14%) ≤ 25% ✅ · deck 14 ≤ map 14 ✅ · no trivia · backs ≤ 3 sentences ✅ · all anchored ✅ · no contamination ✅

### Audit mode output (same deck, English)

```text
What is a hub in a delivery network?	A hub is any facility where parcels are sorted and routed.	card_type::def src::§2.1
What do a van and a drone have in common?	Both are delivery vehicles that carry parcels; neither is a route.	card_type::comp src::§2.3
Why is a hub not typically called a depot?	Depots are end points where parcels originate or finish; hubs are intermediate transfer points that sort and reroute parcels.	card_type::hw src::§2.4
```

### Audit Summary
- `card_type::def` 12 · `card_type::mech` 8 · `card_type::comp` 6 · `card_type::hw` 5 · `card_type::conn` 4
- Units: 35 enumerated · 3 merged · 2 dropped → 30 rows in map → 35 cards (5 topics earned a 2nd card)
- Skipped: "4.2 million parcels in 2025" (standalone statistic — materiality); "route count between two hubs" (empty answer — materiality)

### English output (Topic: NimbusCorp delivery network)

```text
What is a hub in a delivery network?	A hub is any facility where parcels are sorted and routed.
What do a van and a drone have in common?	Both are delivery vehicles that carry parcels; neither is a route.
What happens when a parcel reaches an intermediate hub?	The hub forwards it onto another route toward its destination — it does not keep it.
How do hubs and routes work together?	Hubs sort and forward parcels from one route to another; routes connect hubs. Working together, they move parcels across the network.
Why is a hub not typically called a depot?	Depots are end points where parcels originate or finish; hubs are intermediate transfer points that sort and reroute parcels.
```

### Vietnamese output (Chủ đề: Mạng lưới giao hàng)

```text
Hub đóng vai trò gì trong mạng lưới giao hàng?	Hub là cơ sở nơi các bưu kiện được phân loại và định tuyến; một thành phố có nhiều hub có thể được xem như một khu vực giao nhận duy nhất.
Điều gì phân biệt một chuyến giao đơn lẻ với một mạng lưới giao hàng?	Một chuyến giao liên quan đến hai hub và phát sinh vấn đề lập lịch phương tiện; một mạng lưới giao hàng vận chuyển bưu kiện giữa hơn hai hub.
Tại sao hub không được gọi là depot?	Depot là điểm cuối nơi bưu kiện bắt đầu hoặc kết thúc; hub là điểm trung chuyển phân loại và chuyển tiếp bưu kiện.
Phương tiện và tuyến đường phối hợp với nhau như thế nào?	Phương tiện (xe tải, drone) lưu giữ và chở bưu kiện; tuyến đường chỉ nối các điểm dừng với nhau. Hai thành phần này cùng nhau đưa bưu kiện đi qua mạng lưới.
```
