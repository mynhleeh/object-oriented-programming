# Deep Flashcard Generator — Mechanical Self-Audit (7-Item Checklist)

Read this at **Step 5**, after the draft is complete. Review the deck as a **stranger reviewing someone else's work** — not as the author defending it. This is a separate re-read; never audit in the same pass that wrote the draft.

Each item has: a **countable pass criterion**, a **fix recipe**, and a **bad → fixed example**. Run all 7 items, apply every fix, then **re-run all 7** once more. Only when all 7 pass, emit the output block.

---

## Item 1 — Zero Yes/No Fronts

**Pass criterion:** no front starts with — or could be answered with — `Can / Could / Is / Are / Do / Does / Did / Will / Would / Should / May / Might`. Count: must be **0**.

**Fix recipe:** apply the Step 3 rewrite recipes:
- `Can/Could X…?` → `What can X do?` / `What do X and Y share?`
- `Is/Are X…?` → `What is X?` / `What distinguishes X?`
- `Do/Does X…?` → `What does X do?` / `How does X …?`
- `Will/Would X…?` → `What happens when X…?` / `What is the result of X?`

**Bad → fixed:**
- ❌ "Can a van and a drone both be called a delivery vehicle?" → ✅ "What do a van and a drone have in common?"
- ❌ "Are there end points that don't receive parcels?" → ✅ "What kinds of end points do not receive parcels?"
- ❌ "Could an intermediate hub be considered a route?" → ✅ "Why is an intermediate hub not a route?"

---

## Item 2 — Opener Budget

**Pass criterion:** no opener pattern accounts for more than **25% of fronts**. The classic failure is a wall of "What is / What are X?" — count them explicitly.

**Fix recipe:** convert the excess to fill-in-the-blank, scenario, or "What role does / What distinguishes / What happens when" forms.

**Bad → fixed (deck of 20, of which 10 start with "What is/are"):**
- ❌ 10/20 = 50% "What is/are X?" → ✅ convert 5 of them: "Fill in the blank: a ___ forwards parcels between routes." / "What role does the route play between hubs?" / "Two hubs moving parcels to each other — that is ___, not a network."
- ✅ Final count ≤ 5/20 = 25%.

---

## Item 3 — Non-Redundancy

**Pass criterion:** no two backs answer the same recall question. Verification method: read every back and ask *"would a learner who knows card A also answer card B correctly without reading the document again?"* If yes for two cards → duplicate.

Also verify against the map: **deck size ≤ map rows + second-cards earned** (SKILL.md Step 2). If the deck is bigger than the map, the dedup failed.

**Fix recipe:** merge the two cards into one at the map level (delete one row, keep one), or drop the weaker card. If both cards came from separate map rows that answer the same question, the map itself was wrong — fix the map.

**Bad → fixed:**
- ❌ "What is NimbusCorp?" (back: "an inter-hub network — a network connecting hubs") + "What does the name 'NimbusCorp' literally mean?" (back: "Inter-hub network = a network connecting hubs") → ✅ one card: "What does the name 'NimbusCorp' literally mean?" → "Inter-hub network — a network connecting hubs."
- ❌ "Why is a hub not typically called a depot?" + "Could the service of sorting parcels characterize a facility as a depot?" (same answer: hubs are intermediate transfer points) → ✅ keep one, drop the other.

---

## Item 4 — Materiality

**Pass criterion:** no back is a **bare number, a single word, or answerable from the front itself**. Every card must test understanding, not trivia.

**Fix recipe:** drop the card, or reframe it to test the concept behind the fact.

**Bad → fixed:**
- ❌ "How many parcels did NimbusCorp process in 2025?" → "4.2 million." → ✅ drop (trivia).
- ❌ "How many routes can connect two hubs?" → "One or more." → ✅ drop (empty answer).
- ❌ "What is a hub?" → "A hub." → ✅ drop (answer repeats the front).

---

## Item 5 — Back Length

**Pass criterion:** every back ≤ **3 sentences** or ≤ **4 bullets**; no filler openers ("This is…", "The answer is…").

**Fix recipe:** if a back exceeds 3 sentences, split into two cards — but only if the document supports two distinct units; otherwise trim to the essential 3 sentences.

**Bad → fixed:**
- ❌ Back with 5 sentences cramming definition + examples + exception → ✅ split: one card for the definition, one card for the exception (if both are in the document), each ≤ 3 sentences.
- ❌ "This is a facility that sorts parcels." → ✅ "A facility that sorts parcels."

---

## Item 6 — Fidelity

**Pass criterion:** every answer is **Explicit or Entailed** in the document (or tagged `inf::` in inference mode), and **every concept on a card has a row in the Knowledge Map**. A card with no anchor is a card whose fidelity you cannot prove.

**Fix recipe:** for each card, point at the map row (and the anchor in the document) that supports its back. If you cannot point at it, the card is Inferred — omit it in default mode.

**Bad → fixed:**
- ❌ Card whose back uses an analogy from outside the document ("like a thread connecting beads") when the document never mentions it → ✅ drop, or re-anchor the back to what the document actually says.
- ❌ Connection card linking concepts where only one concept appears in the document → ✅ drop (Anchor Test fails).

---

## Item 7 — Contamination Scan

**Pass criterion:** every back can be quoted from the **attached document** — never from the worked example or any other example file. Verification method: for each card, type out the supporting sentence from the attached document. If you cannot type it out, the card is fabricated — drop it.

This matters most when the attached document is about a different subject than the example (e.g., biology, history, programming). A deck must never contain facts that exist only in the skill's examples (NimbusCorp, hubs, routes, vans, drones…).

**Fix recipe:** drop the card. Do NOT "adapt" an example fact into the document's domain — adapting is fabricating.

**Bad → fixed:**
- ❌ A biology deck contains "What is a hub in a delivery network?" → "A hub is any facility where parcels are sorted and routed." (fact from the example, not from the biology document) → ✅ drop the card.
- ❌ A programming deck contains "Why is a hub not typically called a depot?" → ✅ drop — nothing in the attached document supports it.

---

## Recheck Loop

After applying every fix:
1. Re-count Item 1 (must be 0) and Item 2 (must be ≤ 25%).
2. Re-scan Item 3 (deck size vs map rows) and Item 4.
3. Re-read Item 5 and Item 6 on the changed cards only.
4. Re-scan Item 7 on the **entire deck** — not just the changed cards.

Only when **all 7 pass** on the final deck, emit the output block.
