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
