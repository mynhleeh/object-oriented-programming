---
name: paper-reading-notes
description: Extract key information from academic papers (abstract, methodology,
  results, conclusions, limitations) and generate structured reading notes in Markdown.
  Use this skill when a user asks to read, analyze, summarize, or take notes on a
  research paper, when preparing for a literature review, or when organizing readings
  for systematic comparison. Optionally integrates with Zotero for note attachment.
  当用户需要精读论文、提取论文关键信息、生成结构化阅读笔记、 或为文献综述做准备时触发此 skill。
tags:
- eduhub
---

# Paper Reading Notes / 论文精读笔记

> A structured workflow for deep-reading academic papers and producing
> reusable Markdown notes. / 一套用于深度阅读学术论文并生成可复用
> Markdown 笔记的结构化工作流。

---

## When to Use / 何时使用

Use this skill when the user:

- Provides a paper (PDF, DOI, URL, or pasted text) and wants a structured reading note.
- Asks to "read", "analyze", "summarize", or "take notes on" a paper.
- Is preparing for a literature review and needs consistent note formats across multiple papers.
- Wants to compare papers side by side using a shared template.
- Asks to save reading notes to Zotero.

当用户提供论文（PDF、DOI、URL 或粘贴文本）并希望获得结构化阅读笔记时，或
在准备文献综述、需要统一笔记格式以便跨论文比较时，使用此 skill。

---

## Workflow / 工作流程

### Step 1 — Identify the Paper / 确认论文

1. Confirm the input source: PDF file path, DOI, URL, or pasted text.
2. If a PDF is provided, read it to extract full text and figures.
3. If a DOI/URL is provided, fetch metadata (title, authors, journal, year, DOI).
4. If text is pasted directly, proceed with what is available.
5. Ask the user for any missing context (e.g., their research focus, why they are reading this paper).

**确认输入来源**：PDF 文件路径、DOI、URL 或粘贴文本。如有缺失信息，向用户确认。

### Step 2 — Extract Metadata / 提取元数据

Capture the following bibliographic fields:

| Field | Description |
|-------|-------------|
| Title | Full paper title |
| Authors | All authors with affiliations if available |
| Year | Publication year |
| Venue | Journal or conference name |
| DOI | Digital Object Identifier |
| Keywords | Author-provided keywords |
| Type | Research article / Review / Case study / Conference paper / Other |

### Step 3 — Extract Key Sections / 提取核心内容

Read the paper and extract information into these structured sections:

#### 3.1 Research Question / 研究问题
- What problem does the paper address?
- Why is this problem important?
- 论文要解决什么问题？为什么重要？

#### 3.2 Methodology / 研究方法
- Study design (experimental, observational, meta-analysis, etc.)
- Participants / sample / data source
- Key variables and measurements
- Statistical methods or analytical approach
- 研究设计、样本/数据来源、关键变量、统计/分析方法

#### 3.3 Key Findings / 主要发现
- List 3–5 most important results.
- Include specific numbers, effect sizes, or qualitative themes.
- Flag any surprising or counterintuitive results.
- 列出 3-5 项最重要的结果，附具体数据和效应量。

#### 3.4 Conclusions / 结论
- What do the authors conclude?
- How do they interpret the findings?
- 作者的结论和对结果的解读

#### 3.5 Limitations / 局限性
- Stated limitations (authors' own)
- Unstated limitations you notice (methodological, sample, generalizability)
- 作者自述的局限 + 你发现的额外局限

#### 3.6 Relevance to User / 与用户研究的关联
- How does this paper connect to the user's research interests?
- What can be borrowed (methods, frameworks, data)?
- This section should be personalized — ask the user if context is unclear.
- 此论文与用户研究方向的关联，可借鉴之处

### Step 4 — Generate Structured Note / 生成结构化笔记

Produce a Markdown note using the template below. Save it as a `.md` file
named `{first-authorlastname}_{year}_{short-title}.md`.

```markdown
# {Paper Title}

> **Authors:** {authors}
> **Year:** {year} | **Venue:** {venue} | **DOI:** {doi}
> **Keywords:** {keywords}
> **Read on:** {date}

---

## Research Question / 研究问题

{content}

## Methodology / 研究方法

{content}

## Key Findings / 主要发现

1. {finding 1}
2. {finding 2}
3. {finding 3}

## Conclusions / 结论

{content}

## Limitations / 局限性

- **Stated:** {author-acknowledged limitations}
- **Observed:** {your observations}

## Relevance / 与我的关联

{personalized content}

## Quotes & Page References / 关键引述与页码

> "{quote}" (p. {page})

## Tags

#paper-reading #{domain} #{method-type}
```

### Step 5 — Quality Check / 质量检查

Before finalizing the note, verify:

- [ ] All metadata fields are filled or marked as "N/A" with reason.
- [ ] Key findings include specific data points, not vague summaries.
- [ ] Limitations include both stated and observed.
- [ ] The relevance section is personalized, not generic.
- [ ] Quotes have page numbers or section references.
- [ ] The note is saved as a properly named `.md` file.

**检查清单**：元数据完整、发现含具体数据、局限含自述+观察、关联个性化、引述有页码、文件命名规范。

### Step 6 — Zotero Integration (Optional) / Zotero 集成（可选）

If the user uses Zotero and wants to attach the note:

1. Search for the paper in Zotero by title or DOI.
2. If found, attach the Markdown note as a child note.
3. If not found, offer to create a new item with the extracted metadata.
4. Add relevant tags from the note to the Zotero item.

If Zotero MCP tools are available, use `zotero_search_items` to find the paper
and `zotero_create_note` to attach the reading note.

**如用户使用 Zotero**：按标题/DOI 搜索文献，将阅读笔记作为子笔记附加，并同步标签。

---

## Anti-Patterns / 常见错误

- **Do not** produce a vague summary without specific data points or effect sizes.
- **Do not** skip the limitations section — it is critical for critical reading.
- **Do not** generate a generic relevance section — always tie it to the user's specific research.
- **Do not** copy-paste large blocks of the paper verbatim. Use quotes with page references for key passages only.
- **Do not** fabricate data or statistics. If a number is unclear, mark it as "[verify]" and flag it to the user.
- **Do not** skip the quality check step.

**避免**：模糊摘要、跳过局限性、通用化关联、大段复制原文、编造数据、跳过质量检查。

---

## Multi-Paper Comparison / 多论文比较

When the user asks to compare multiple papers:

1. Generate individual notes for each paper using the template above.
2. Create a comparison table with rows = papers, columns = key dimensions:
   - Research question
   - Methodology
   - Key findings
   - Limitations
   - Relevance
3. Highlight agreements and contradictions across papers.
4. Identify research gaps.

**多论文比较**：先逐篇生成笔记，再创建比较表格，标出共识与矛盾，识别研究空白。

---

## Output Format / 输出格式

- Primary output: a Markdown `.md` file saved to the user's preferred directory.
- If the user does not specify a directory, default to the current workspace.
- File naming: `{first-authorlastname}_{year}_{short-title}.md` (e.g., `li_2024_mindfulness-exercise-metabolism.md`).
- Always present the note to the user for review after generation.

**输出**：Markdown 文件，命名格式为 `第一作者姓_年份_简短标题.md`，生成后呈现给用户审阅。

---

## Language Handling / 语言处理

- If the paper is in English, extract in English and provide Chinese annotations for key terms.
- If the paper is in Chinese, extract in Chinese and provide English annotations for key terms.
- Always include bilingual section headers as shown in the template.
- Respect the user's preferred output language if they specify one.

**语言处理**：英文论文用英文提取并附中文关键术语注释，中文论文反之。始终使用双语标题。

---

## Validation Checklist / 验证清单

Before reporting completion to the user:

1. The note file exists and is valid Markdown.
2. All template sections are present (not empty).
3. Key findings contain specific data.
4. Limitations include both stated and observed.
5. The relevance section references the user's actual research context.
6. File is properly named and saved.
7. (If applicable) Zotero note was attached successfully.

**完成前验证**：文件存在且格式正确、所有部分已填写、发现含具体数据、局限含双来源、关联个性化、文件命名正确、Zotero 集成成功（如适用）。
