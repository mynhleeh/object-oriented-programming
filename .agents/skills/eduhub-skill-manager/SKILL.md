---
name: eduhub-skill-manager
description: Guide an agent through using the eduhub CLI to discover, inspect, install,
  package, publish, and verify Codex skills on an EduHub registry. Use when the user
  asks to install a skill from EduHub, upload or publish a local skill to EduHub,
  test EduHub skill installation, manage registry login, or explain the correct EduHub
  CLI workflow for skills.
tags:
- eduhub
---

# EduHub Skill Manager

Use this skill when working with EduHub-hosted Codex skills. Prefer the `eduhub` CLI and verify each network action with the live registry instead of relying on memory.

## Ground Rules

- Treat registry state as live data: run the CLI/API command that confirms the current state before saying something is installed or published.
- Do not embed access tokens in files, commits, package contents, screenshots, or final answers.
- If a token is pasted in chat or terminal history, use it only for the requested action and recommend rotation afterward.
- Use `--no-input` for automation. Use interactive prompts only when a human can answer them.
- Preserve third-party attribution and licenses when publishing skills that copy or adapt open-source content.
- Never publish GPL/AGPL or unlicensed source text unless the user explicitly confirms compatibility and authorization.

## Login

Check authentication first:

```bash
eduhub whoami
```

If not logged in, use browser login:

```bash
eduhub login --registry https://eduhub.deeptutor.info
```

For CI or a user-provided token, store it through the CLI rather than writing it into the skill:

```bash
eduhub login --registry https://eduhub.deeptutor.info --token <token>
```

If the user provides a different registry, pass `--registry <url>` or set `EDUHUB_REGISTRY` for all subsequent commands.

## Discover And Install Skills

Search before installing:

```bash
eduhub search <query>
eduhub skill inspect <slug>
```

Install a skill for Codex into the Codex skills directory:

```bash
eduhub install <slug> --workdir "${CODEX_HOME:-$HOME/.codex}" --dir skills
```

Install into a project-local skills directory when the user wants a repo-scoped copy:

```bash
eduhub install <slug> --workdir "$PWD" --dir skills
```

After installation, verify the target folder contains `SKILL.md`. For Codex session discovery, tell the user that a new Codex session may be needed if the runtime does not hot-load newly installed skills.

## Prepare A Skill For Publishing

Before publishing, inspect the folder:

- The directory must contain `SKILL.md`.
- `SKILL.md` frontmatter must include `name` and `description`.
- The `description` must state what the skill does and when it should trigger.
- Remove placeholder files, local secrets, build artifacts, caches, and unrelated docs.
- Include `ATTRIBUTION.md` when content is copied or adapted from a permissively licensed upstream project.
- Keep the package minimal: include only resources the skill needs at runtime.

Recommended tracks:

- `academics`: student-facing study, tutoring, writing, practice, assessment.
- `companions`: motivation, planning, reflection, coaching, wellbeing support.
- `skills-interests`: coding, music, public speaking, hobbies, practical skills.
- `educators`: teacher/admin tooling, lesson planning, rubrics, classroom design, skill-management workflows.

Recommended languages: `zh`, `en`, `ja`, or `other`.

## Publish Workflow

Run a dry run first:

```bash
eduhub skill publish ./my-skill --dry-run
```

If the command prompts, answer `track` and `language` from the menu. For non-interactive usage, pass them explicitly:

```bash
eduhub --no-input skill publish ./my-skill \
  --track educators \
  --language en \
  --tags agent,codex,skills,eduhub \
  --changelog "Initial release"
```

If the package is ready, publish without `--dry-run`:

```bash
eduhub --no-input skill publish ./my-skill \
  --track educators \
  --language en \
  --tags agent,codex,skills,eduhub \
  --changelog "Initial release"
```

Use `--version <semver>` for updates. Do not overwrite meaningfully different behavior under the same version.

## Verify A Publish

After publishing, verify the registry and installation path:

```bash
eduhub skill inspect <slug>
eduhub install <slug> --workdir /private/tmp/eduhub-install-check --dir skills
test -f /private/tmp/eduhub-install-check/skills/<slug>/SKILL.md
```

If testing in a shared temp directory, remove only the temp directory that you created.

Report the final slug, version, registry, and verification commands. If any step fails, report the exact failing command and the registry response, then stop before making further destructive changes.
