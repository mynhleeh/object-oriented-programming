# Deep Flashcard Generator — Worked Example (End-to-End)

Read this **before first use** of the skill. It shows the full pipeline on a neutral fictional excerpt — **Knowledge Map → Draft → Mechanical Self-Audit → Final TSV** — so you can see every rule applied, not just described.

> **⚠️ CONTAMINATION GUARD — this file illustrates the PROCESS, not the CONTENT.** Everything here (NimbusCorp, hubs, routes, vans, drones…) is a **fictional example** used only to demonstrate the workflow. None of these facts may ever appear in a deck you generate — unless the attached document itself contains them. If you cannot point at the exact sentence in the attached document that supports a card, that card is fabricated → drop it.

---

## Source excerpt (fictional — NimbusCorp delivery network, ~2 pages)

> A hub is any facility where parcels are sorted and routed. Multiple hubs can exist in one city, and one city with several hubs can be treated as a single delivery zone. Hubs may be heterogeneous — not similar to each other. A delivery network enables parcel transfer among hubs; it generally consists of heterogeneous hubs and involves more than two hubs. A single delivery involves two hubs and raises vehicle-scheduling issues, whereas a network enables parcel transfer among more than two hubs.
>
> A route is usually a sequence of stops — such as streets, buildings, or pickup points — that connects hubs. An intermediate hub is not a route; a route is a sequence of stops. End points are sinks or sources of parcels — e.g., homes, offices, and lockers. Intermediate hubs forward/switch parcels from one route to another — e.g., sorting facilities and transfer points. Depots are end points. Hubs are transfer points. Vans and drones are delivery vehicles. Vehicles can store and carry parcels, while routes simply connect stops from one end to another — like a thread connecting beads.
>
> NimbusCorp is an inter-hub network — a network connecting hubs. A carrier provides delivery service for parcels. Logistics companies such as NimbusCorp, as well as neighborhood pickup shops (via lockers), can be carriers. A customer dropping a parcel at a neighborhood shop has that shop as their carrier. In 2025 there were approximately 4.2 million parcels processed by NimbusCorp. Two hubs can be connected by one route or by more than one route.

---

## Phase 1 — Knowledge Map (before any card)

Scan the excerpt, list every candidate unit, apply the Materiality Filter, and **merge duplicates now**:

| ID | Knowledge unit | Type | Anchor | Keep? |
|---|---|---|---|---|
| U1 | A hub is any facility where parcels are sorted and routed | 1 def | p.3 ¶1 | ✅ |
| U2 | One city can host multiple hubs and be treated as a single delivery zone | 1 def | p.3 ¶1 | ✅ |
| U3 | Heterogeneous hubs are hubs that may not be similar to each other | 1 def | p.3 ¶1 | ✅ |
| U4 | A delivery network enables parcel transfer among hubs; involves >2 hubs, often heterogeneous | 1 def | p.3 ¶1 | ✅ |
| U5 | Single delivery vs network: 2 hubs + vehicle-scheduling issues vs >2 hubs | 3 comp | p.3 ¶1 | ✅ |
| U6 | A route is a sequence of stops (streets, buildings, pickup points) connecting hubs; an intermediate hub is not a route | 1 def | p.3 ¶2 | ✅ (merge: "intermediate hub is not a route" folded into U6) |
| U7 | End points are sources/sinks of parcels (homes, offices, lockers) | 1 def | p.3 ¶2 | ✅ |
| U8 | Intermediate hubs forward/switch parcels between routes (sorting facilities, transfer points) | 1 def | p.3 ¶2 | ✅ |
| U9 | Depots = end points; hubs = transfer points; vans and drones are delivery vehicles | 1 def | p.3 ¶2 | ✅ (one unit — the taxonomy) |
| U10 | Vehicles store-and-carry parcels; routes just connect stops (thread vs beads) | 2 mech | p.3 ¶2 | ✅ |
| U11 | Why a hub is not called a depot (intermediate transfer point) | 4 hw | p.3 ¶2 | ✅ |
| U12 | NimbusCorp is an inter-hub network — a network connecting hubs | 1 def | p.3 ¶3 | ✅ |
| U13 | A carrier provides delivery service; logistics companies and pickup shops can be carriers | 1 def | p.3 ¶3 | ✅ |
| U14 | A customer's carrier is whoever provides their actual delivery (pickup shop example) | 4 hw | p.3 ¶3 | ✅ |
| U15 | "4.2 million parcels processed in 2025" | — | p.3 ¶3 | ❌ DROP — standalone statistic, trivia (Materiality) |
| U16 | "Two hubs can be connected by one route or more than one route" | — | p.3 ¶3 | ❌ DROP — empty answer ("one or more"), no learning value (Materiality) |
| U17 | "What is a hub?" asked twice from different angles (definition + city-with-many-hubs) | — | p.3 ¶1 | ❌ MERGED — U1 and the "multiple hubs in one city" claim are distinct; but two *cards* asking "what is a hub" would be duplicates → keep 1 card per row |

**Map result: 14 rows kept (15 → 14 after dropping U15, U16).** Deck ceiling = 14 rows + a few legitimately earned 2nd cards.

---

## Phase 2 — Draft (one card per unit; ≤ 2 per topic)

Draft 14 cards from the 14 rows, applying the Step 3 rules (open questions, varied openers, back ≤ 3 sentences):

```text
What is a hub in a delivery network?	A hub is any facility where parcels are sorted and routed.
Can one city host multiple hubs?	Yes — multiple hubs can exist in one city, and such a city can be treated as a single delivery zone.
What are heterogeneous hubs?	Hubs that may not be similar to each other (as opposed to homogeneous hubs, which are similar).
What is a delivery network?	A delivery network enables parcel transfer among hubs; it generally consists of heterogeneous hubs and involves more than two hubs (e.g., a city-wide network).
How does a single delivery differ from a network?	A single delivery involves two hubs and raises vehicle-scheduling issues; a network enables parcel transfer among more than two hubs.
What is a route?	A route is usually a sequence of stops — such as streets, buildings, or pickup points — that connects hubs.
What are end points?	Points that are sinks or sources of parcels — e.g., homes, offices, and lockers.
What are intermediate hubs?	Hubs that forward/switch parcels from one route to another — e.g., sorting facilities and transfer points.
How do depots, hubs, vans, and drones relate to each other?	Depots are end points; hubs are transfer points. Vans and drones are both delivery vehicles.
What distinguishes vehicles from routes in parcel transfer?	Vehicles can store and carry parcels, while routes simply connect stops from one end to another — like a thread connecting beads.
Why is a hub not typically called a depot?	Hubs are intermediate transfer points that sort and reroute parcels, so despite providing transfer service they are not called depots.
What does the name "NimbusCorp" literally mean?	Inter-hub network — a network connecting hubs.
What is a carrier?	A carrier provides delivery service for parcels; logistics companies (NimbusCorp) and neighborhood pickup shops (via lockers) can all be carriers.
If a customer drops a parcel at a neighborhood shop, who is their carrier?	The shop — the customer's carrier is whoever provides their actual delivery service.
```

---

## Phase 3 — Mechanical Self-Audit (the 7 checks)

Review as a stranger. The draft has **failures** — find and fix them:

1. **Zero yes/no fronts** → ❌ FAIL: "Can one city host multiple hubs?" Fix → ✅ "What can a single city host, and what can it be treated as?" → "Multiple hubs; it can be treated as a single delivery zone."
2. **Opener budget** → count "What is/are X?" openers: 9 of 14 = 64% → ❌ FAIL. Convert 5 to other forms (see fixes below).
3. **Non-redundancy** → "What is a hub?" vs "Can one city host multiple hubs?" answer different questions — ✅ PASS. No other duplicates — ✅ PASS. Deck 14 ≤ map 14 + 0 → ✅ PASS.
4. **Materiality** → "What is a route?" tests a real concept — ✅ PASS. No bare-number or single-word backs — ✅ PASS.
5. **Back length** → all backs ≤ 3 sentences — ✅ PASS.
6. **Fidelity** → every back matches its map row + anchor — ✅ PASS.
7. **Contamination** → no fact from outside the excerpt (no real-world delivery statistics, no invented analogies) — ✅ PASS.

**Applied fixes:**
- Rewrote the one yes/no front (Item 1).
- Converted 5 "What is/are" openers to fill-in-the-blank / scenario / "role" forms (Item 2): e.g. "What is a route?" → "Streets, buildings, and pickup points linked in order form a ___."; "What are end points?" → "Homes, offices, and lockers all share which role in a network?"; "What is a carrier?" → "Who provides a user's delivery service?"; "What are intermediate hubs?" → "Which hubs forward parcels from one route to another — e.g., sorting facilities and transfer points?"; "What are heterogeneous hubs?" → "Hubs that may not be similar to each other are called ___ hubs."

---

## Phase 4 — Final TSV (after recheck: all 7 pass)

```text
What is a hub in a delivery network?	A hub is any facility where parcels are sorted and routed.
What can a single city host, and what can it be treated as?	Multiple hubs; a city with several hubs can be treated as a single delivery zone.
Hubs that may not be similar to each other are called ___ hubs.	Heterogeneous (as opposed to homogeneous hubs, which are similar).
What is a delivery network?	A delivery network enables parcel transfer among hubs; it generally consists of heterogeneous hubs and involves more than two hubs (e.g., a city-wide network).
How does a single delivery differ from a network?	A single delivery involves two hubs and raises vehicle-scheduling issues; a network enables parcel transfer among more than two hubs.
Streets, buildings, and pickup points linked in order form a ___.	Route.
Homes, offices, and lockers all share which role in a network?	They are end points — sinks or sources of parcels.
Which hubs forward parcels from one route to another — e.g., sorting facilities and transfer points?	Intermediate hubs.
How do depots, hubs, vans, and drones relate to each other?	Depots are end points; hubs are transfer points. Vans and drones are both delivery vehicles.
What distinguishes vehicles from routes in parcel transfer?	Vehicles can store and carry parcels, while routes simply connect stops from one end to another — like a thread connecting beads.
Why is a hub not typically called a depot?	Hubs are intermediate transfer points that sort and reroute parcels, so despite providing transfer service they are not called depots.
What does the name "NimbusCorp" literally mean?	Inter-hub network — a network connecting hubs.
Who provides a user's delivery service?	A carrier — logistics companies (NimbusCorp) and neighborhood pickup shops (via lockers) can all be carriers.
If a customer drops a parcel at a neighborhood shop, who is their carrier?	The shop — the customer's carrier is whoever provides their actual delivery service.
```

**Final checks:** 0 yes/no fronts · "What is/are X?" = 2 of 14 ≈ 14% ≤ 25% ✅ · 14 cards ≤ 14 map rows ✅ · no trivia backs ✅ · every back ≤ 3 sentences ✅ · every card anchored ✅ · no contamination ✅.

---

## What this example proves

- **Dedup happens at the map stage** — the "4.2 million parcels" and "one or more routes" rows were dropped *before* any card existed, not caught later.
- **Yes/no and "What is" failures are caught in a separate audit pass** — the draft above deliberately contains them; only the re-read found them.
- **The map's row count is the deck's ceiling** — 14 rows → 14 cards, not 33. A deck bigger than its map is always a dedup failure.
- **The example is content-free for your deck** — NimbusCorp exists only to demonstrate the process. If the attached document is about a different subject, not one NimbusCorp fact may appear in the output (Item 7).
