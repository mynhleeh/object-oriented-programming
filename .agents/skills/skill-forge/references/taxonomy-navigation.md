# Taxonomy & Navigation

The repo's **intent-based classification system**, distilled from `README.md`. This is the mental model a meta-skill uses to decide *where* a generated artifact belongs and *which* source has the highest epistemic weight to cite.

> **Why this file exists**: The meta-skill needs to (a) classify a resource into the right axis/folder, (b) route citations by trust level. This is the distilled reference for the **NAVIGATE / ORGANIZE** mode.

---

## The 3 Axes — Intent-Based Taxonomy

| Axis | Decision Question | Folder |
|---|---|---|
| **INSTRUCTIONS** | Will I feed this directly to AI to execute? | `instructions/` |
| **KNOWLEDGE** | Will I read this to learn about AI/prompting? | `knowledge/` |
| **BLUEPRINTS** | Is this a design, blueprint, or mental model? | `blueprints/` |

### Decision Tree

```
Q1: Will I feed this directly to an AI agent to execute?
  → Yes → instructions/
  → No  → Q2

Q2: Will I read this to learn about AI or prompting?
  → Yes → knowledge/
  → No  → Q3

Q3: Is this a system design, architecture, or mental model?
  → Yes → blueprints/
```

---

## Resource Classification Table

| Resource type | Axis | Location |
|---|---|---|
| Skill files (`SKILL.md`) | INSTRUCTIONS | `instructions/skills/` |
| System prompts | INSTRUCTIONS | `instructions/prompts/` |
| Rules files (`AGENTS.md`, `.cursorrules`) | INSTRUCTIONS | `instructions/rules/` |
| Automation scripts | INSTRUCTIONS | `instructions/scripts/` |
| How-to guides, tutorials | KNOWLEDGE | `knowledge/guides/` |
| Academic / technical papers | KNOWLEDGE | `knowledge/papers/` |
| Blog posts, saved articles | KNOWLEDGE | `knowledge/articles/` |
| System architecture diagrams | BLUEPRINTS | `blueprints/architectures/` |
| Mental models, frameworks | BLUEPRINTS | `blueprints/concepts/` |

---

## Dynamic Lifecycle Split (3 Tiers)

Sub-folders are created **only when a folder contains 2 or more different tiers**.

| Tier | Purpose | Contains |
|---|---|---|
| `active/` | Currently used, maintained | Self-created resources in use |
| `archive/` | Retired — historical only | Self-created, no longer used |
| `external/` | Third-party, submodules | External repos, cloned resources |

### MECE Boundary
- `active/` and `archive/` → **only self-created** resources.
- `external/` → **only third-party** resources.
- When an `external/` resource is no longer needed → **remove/untrack the submodule** (do NOT move to `archive/`).

---

## Knowledge Source Hierarchy (Citation Priority)

When citing or referencing content, apply this order. **Higher tiers carry greater epistemic weight** — always prefer the highest available tier.

| Priority | Type | Location | Signal |
|---|---|---|---|
| Tier 1 | Academic papers, technical reports | `knowledge/papers/` | Peer-reviewed, cited, reproducible |
| Tier 2 | Official documentation, specs | `blueprints/architectures/` (design/architecture specs); else `knowledge/guides/` with a `specs/` subfolder | Authoritative |
| Tier 3 | Curated guides, tutorials | `knowledge/guides/` | Practitioner-verified |
| Tier 4 | Articles, blog posts | `knowledge/articles/` | Community knowledge |
| Tier 5 | External submodules | `instructions/*/external/` | Third-party curated |
| Tier 6 | Active working files | `instructions/*/active/` | Operational, not evidence |
| Tier 7 | Archive | `instructions/*/archive/` | Historical, may be outdated |

> **Note on Tier 5 vs Tier 6**: External submodules rank above active files (broader scope, though unverified). Neither supports factual claims when Tier 1–4 exist.

> **Tier 2 vs Tier 3 (shared folder):** `knowledge/guides/` holds both official specs (Tier 2) and curated guides (Tier 3). When both exist for the same topic, the Tier 2 source wins for factual claims; Tier 3 wins for methodology. Within the folder, prefix official specs with `specs/` to keep the tiers distinguishable.

---

## Trust & Citation Rules

```
ALWAYS cite source tier when referencing content.
PREFER knowledge/papers/ over any other source for factual claims.
PREFER knowledge/guides/ over instructions/prompts/ for methodology.
TREAT instructions/*/external/ as higher priority than active/archive context.
TREAT instructions/*/active/ as operational context, NOT as evidence.
DO NOT reference archive/ unless explicitly asked for historical context.
DO NOT treat submodule content as peer-reviewed or verified, despite being external.
```

---

## Agent Execution Protocol (Navigation)

When any agent operates with this repo in context:

```
Step 1: Read this README — establish repo scope.
Step 2: Classify the request: INSTRUCT / LEARN / DESIGN → route to folder.
Step 3: Consult the hierarchy before citing any resource.
Step 4: Prioritize Tier 1–3 for factual/methodological claims.
Step 5: Cross-reference YAML frontmatter for semantic matching.
Step 6: Execute using the highest-tier applicable source.
```

---

## How the Meta-Skill Uses This

- **Classify** a requested artifact → decide its target folder (e.g., a generated skill → `instructions/skills/active/`).
- **Route citations** → in EXPLAIN/CONVERT modes, cite the source tier of the reference used.
- **Organize** → in NAVIGATE/ORGANIZE mode, place a resource and decide active/archive/external split.
