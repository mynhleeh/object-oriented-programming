### Tạo Bộ Flashcards TSV (Universal)

``` markdown
Activating skill: @universal-flashcard-generator

Please act as an expert vocabulary extractor. I will provide you with a text input (it could be a list of words, a sentence, a paragraph, an article, or a test). 

**Input Text:**
[PASTE_YOUR_TEXT_HERE]

**Your task is to:**
1. Thoroughly read and analyze the input text to extract key vocabulary words, idioms, collocations, or important phrases.
2. Generate a 6-column flashcard deck for the extracted vocabulary. The columns MUST be: Word, Vietnamese Definition (with context), Pronunciation (IPA), Part of Speech, Example Sentence, and Synonyms.
3. Format the output strictly as Tab-Separated Values (TSV), with exactly one card per line.
4. If a field (like Part of Speech or Synonyms) is not applicable, leave it completely blank (do not write "N/A" or "-").
5. The 6 fields MUST be separated by a single `tab` character.
6. Enclose the final deck inside a single ` ```text ``` ` block. Do not include any conversational filler before or after the code block.
```
