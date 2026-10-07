# Programming Language — Development Log

## Day 1 — Lexer Prototype

### Goal

Build the first working prototype of the lexer.

### What I built

* Created a Java lexer prototype.
* Added a `HashMap` for recognizing keywords.
* Added a list to store identifiers.
* Added basic symbol recognition.
* Implemented pointer-based scanning using `start` and `curr`.
* Added semicolon (`;`) as a statement terminator represented by `END`.

### Initial language keywords

* `int`
* `float`
* `string`
* `boolean`
* `let`

### Result

The first prototype was able to distinguish basic keywords, identifiers, and the semicolon terminator.

---

## Day 2 — Expanding Lexer Recognition

### Goal

Make the lexer recognize more language elements and handle multiple statements.

### What I built

* Expanded the keyword map.
* Added keywords for:

  * `if`
  * `else`
  * `while`
  * `function`
  * `return`
  * `true`
  * `false`
  * `and`
  * `or`
  * `not`
  * `printd`
  * `inputd`
* Created a separate `symbolsMap`.
* Added `ProcessKeyWord()`.
* Added `ProcessIdentifiers()`.
* Added `ProcessSymbol()`.
* Added support for multiple statements separated by `;`.
* Added handling for the final character of the input.

### Testing

Tested inputs such as:

```text
int age;
int age; float price; string name;
```

and a complete set of keywords.

### Current issue discovered

While testing edge cases, I found that multiple spaces can cause an empty string to be processed as an identifier.

For example:

```text
int  age;
```

can produce an empty identifier between the two spaces.

This will be addressed in the next lexer revision.

### Current milestone

The lexer can currently recognize:

* Keywords
* Identifiers
* Semicolon symbols
* Multiple statements
* The final character of the input

The next revision will focus on making the scanner more robust against edge cases rather than adding many new features.

# Development Log

## Day 2 — Lexer Development

### Goal

Expand the lexer so it can recognize the basic keywords, identifiers, and statement-ending symbols defined for the language.

### What I Implemented

* Added keyword recognition for:

  * `int`, `float`, `string`, `boolean`
  * `let`
  * `if`, `else`, `while`
  * `function`, `return`
  * `true`, `false`
  * `and`, `or`, `not`
  * `printd`, `inputd`
* Added `;` as the statement terminator.
* Added identifier handling for user-defined names such as `age`, `price`, and `name`.
* Separated keyword, identifier, and symbol processing into different methods.
* Improved handling of spaces between tokens.
* Added handling for the final token at the end of the input.
* Tested multiple statements in a single input string.

### Testing

Tested the lexer using all currently defined keywords, identifiers, and statement terminators together.

Example input:
`int age; float price; string name; boolean active; let if else while function return true false and or not printd inputd;`

The lexer correctly produced the expected token names for every currently defined feature.

### Problems I Encountered

* The final token was not always processed because the scanning loop reached the end of the input.
* Multiple spaces could create an empty identifier.
* The last character needed separate handling when it was a keyword, identifier, or symbol.
* I learned that a lexer must process the token **before** a boundary such as whitespace or `;`, and then process the boundary itself if it represents a symbol.

### Current Status

The lexer now handles all features I have defined so far.

### Next Step

Convert the current printed lexer output into actual `Token` objects so that the lexer can produce structured output for the parser.

