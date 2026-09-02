# Deep Flashcard Generator — Language & Output Format

Read this when choosing the target language and formatting the output (default mode vs. audit mode).

## Language Rules (Strictly Enforced)

**Priority order**: User's explicit request > Document's dominant language > English (default).

If the user does not specify a language, auto-detect the dominant language of the attached document and use it for all cards.

> **The target language applies to ALL content — including how the question is phrased on the Front, not just the answer on the Back.**

This is a hard rule. If the target language is Vietnamese, the question itself must be written in Vietnamese. If the target language is English, the question must be in English.

**The only exception**: IT / technical terms (protocol names, algorithm names, tool names, data structures, design patterns, programming constructs) always stay in English regardless of the output language.

### Anti-examples (do not do this)

```
❌ WRONG — language split (Front English, Back Vietnamese):
Front: "What is NimbusCorp?"
Back:  "NimbusCorp là mạng lưới liên-hub (inter-hub network) kết nối các hub."

❌ WRONG — language split (Front English, Back Vietnamese):
Front: "What is the difference between hubs and depots?"
Back:  "Hub là điểm trung chuyển; depot là điểm cuối."
```

```
✅ CORRECT — full Vietnamese:
Front: "NimbusCorp đóng vai trò gì trong mạng lưới giao hàng?"
Back:  "NimbusCorp là mạng lưới liên-hub (inter-hub network) kết nối các hub, giúp vận chuyển bưu kiện giữa các hub trong toàn hệ thống."

✅ CORRECT — full English:
Front: "What role does NimbusCorp play in the delivery network?"
Back:  "NimbusCorp is an inter-hub network that connects hubs, moving parcels between hubs across the whole system."
```

## Output Format (Strictly Enforced)

Two output modes exist. **Default mode** is always active unless the user explicitly requests audit mode.

### Default Mode (2 columns)

1. **2 columns, Tab-Separated (TSV)**: `front[TAB]back`
2. **One line = one card.** No line breaks inside card content.
3. **No header row** (do not write `front\tback` at the top).
4. **No tab characters inside card content**: use `;` or `|` as inline separators when listing items.
5. Wrap the entire output in a **single markdown text block**:

````
```text
[front of card 1]	[back of card 1]
[front of card 2]	[back of card 2]
[front of card 3]	[back of card 3]
```
````

6. **No conversational text, explanations, or any content outside the `text` block.** The only exceptions: one warning line when the document is truncated/unreadable (see below), the **`## Working Notes` block** (mandatory, all modes — see below), and — in audit mode only — the `## Audit Summary` block.

### Working Notes block (mandatory, all modes)

A markdown block emitted **before** the `text` block (after the optional warning line), containing:
1. The final **Knowledge Map table** (IDs, units, types, anchors) — the dedup audit trail proving Step 1 ran.
2. The **7-item audit counts**: 0 yes/no fronts · ≤25% opener · deck ≤ map rows · no trivia · backs ≤ 3 sentences · all anchored · no contamination.

**Do not import** — only the `text` block is Anki-importable. The Working Notes block is **distinct from** the audit-mode `## Audit Summary`: Working Notes comes *before* the `text` block and holds the map + audit counts; `## Audit Summary` comes *after* the `text` block (audit mode only) and holds the tag ledger + unit ledger + skip log.

### Audit Mode (3 columns — user opt-in)

Trigger: the user asks for "audit mode", "source mode", "kèm nguồn", or wants to verify where cards came from. Otherwise never activate this mode.

All default-mode rules apply, plus:

1. **3 columns**: `front[TAB]back[TAB]tags`.
2. **Tags column** — lowercase, space-separated, no tabs:
   - `card_type::{def|mech|comp|hw|conn}` — the card type (see Card Type Notes).
   - `src::{anchor}` — source anchor (`§section`, `p.page`, or `¶paragraph`) where the card content lives. Every card must have exactly one.
   - `inf::` — present on cards created through inference (inference mode only). Never present in default mode.
3. Column 3 is a standard Anki tags field. **For Anki import, copy only the `text` block** — in Anki's text-file import, map column 3 to the Tags field and each card's tags are applied on import. The `## Audit Summary` block is review-only; never include it in the import.
4. After the `text` block, emit exactly one **`## Audit Summary`** markdown block containing: card counts per `card_type`, the **unit ledger** (units enumerated / merged / dropped at the Knowledge Map stage — proves dedup; e.g., `Units: 35 enumerated · 3 merged · 2 dropped → 30 rows in map → 35 cards`), the **skip log** (concepts considered but omitted, with the reason — not explicit / not entailed / materiality / redundancy), and any document warnings. Nothing else outside the two blocks. The Audit Summary is metadata for review/verification, not card data — do not import it.

### Document Warning Line (all modes)

If the document is truncated, has unreadable sections (bad OCR, blurred images), or parts could not be verified: emit exactly **one** line immediately before the `text` block, then the block. Do not elaborate.

```
⚠️ Document truncated after §3 — content after this point could not be verified. Cards cover only the readable portion.
```

If no such issue exists, emit nothing before the block.

## Card Type Notes

Generate **Basic cards only** (front / back). Do **NOT** use Cloze deletion format (`{{c1::...}}`).

Card type codes used in audit-mode tags:

| Code | Type |
|---|---|
| `def` | Definition |
| `mech` | Mechanism / Process |
| `comp` | Comparison / Distinction |
| `hw` | How / Why (source-grounded) |
| `conn` | Knowledge Connection |
