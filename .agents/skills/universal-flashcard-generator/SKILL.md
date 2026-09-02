---
name: universal-flashcard-generator
description: 'Trích xuất từ vựng từ mọi loại văn bản đầu vào (danh sách từ, câu, đoạn văn, bài kiểm tra,...) và xuất ra bộ flashcards 6 cột TSV, tương thích với nhiều công cụ AI.'
tags:
- vocab
- flashcards
- toeic
- universal
---

# Universal Flashcard Generator Skill

> Teach the AI agent to act as a universal vocabulary extractor and flashcard creator. The AI will analyze ANY provided text input (word list, sentence, paragraph, article, or test) and output a highly structured, tab-separated flashcard deck ready for importing into spaced-repetition software like Anki.

## Core Rules & Behaviors

1. **Flexible Input Processing:**
   - The AI MUST read and comprehend the provided input text, regardless of its format (unstructured text, lists, paragraphs, etc.).
   - Identify and extract key vocabulary words, idioms, collocations, or important phrases relevant to learning English.

2. **Vocabulary Extraction & Inference:**
   - Extract the most valuable vocabulary from the given text. 
   - If the input is a single word or a short list, provide the flashcards for exactly those words.
   - Do NOT aggressively expand beyond the context of the input unless explicitly requested. Focus on high-quality extraction of what's provided.

3. **Flashcard Structure (6 Columns):**
   Every generated flashcard MUST have exactly 6 fields in the following order:
   1. **Word:** The word, phrase, or idiom extracted from the text.
   2. **Definition:** The Vietnamese translation. Specify the context/usage relevant to how it appeared in the original text (if applicable).
   3. **Pronunciation:** The phonetic transcription (IPA format).
   4. **Part of Speech:** e.g., Noun, Verb, Adj, Adv (if applicable).
   5. **Example:** A realistic example sentence. If the word was used in a specific context in the source text, try to create an example that mirrors or utilizes that context. Otherwise, default to diverse TOEIC-style examples.
   6. **Synonyms:** Relevant synonyms (if applicable).

4. **Formatting Rules (TSV Strict):**
   - **Tab Separated:** The 6 fields of a flashcard MUST be separated by a single `tab` character. Do NOT use spaces, commas, or pipes as the primary separator.
   - **Empty Fields:** If any information (like Part of Speech or Synonyms) is missing or not applicable, leave the field **completely blank** between the tabs. Do NOT write "N/A", "None", or "-".
   - **One Line Per Card:** Each flashcard MUST occupy exactly one single line.
   - **Text Block:** The entire final output of the flashcard deck MUST be enclosed within a single markdown text block: ` ```text ... ``` `. Do not put any headers, conversational fillers, or extra text inside or outside the block.

## Workflow

1. Read the provided text input.
2. Extract the key vocabulary words.
3. Construct the 6-column data for each word, inferring IPA, definitions, and examples as needed.
4. Output the final result strictly in the ````text ```` TSV format.
