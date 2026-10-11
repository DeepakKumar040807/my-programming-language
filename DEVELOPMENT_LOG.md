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

## Day 3 — Token Objects and Token Storage

### Goal

Convert the lexer output from simple printed strings into actual stored tokens containing both the token type and the original value written by the user.

### What I Implemented

* Created a `Token` structure to store:

  * **Token type** — what the lexer recognized.
  * **Value/Lexeme** — the actual text written in the source code.
* Changed the lexer to store tokens in a `List<Token>`.
* Replaced the previous map-based token storage because a `Map` could not preserve repeated tokens such as multiple `;` symbols.
* Used a `for-each` loop to access and display every stored token.
* Tested the lexer with all currently defined keywords, identifiers, and statement terminators.

### Example

For:

`int age;`

the lexer now stores:

```text
INT = int
IDENTIFIER = age
END = ;
```

### Important Learning

A lexer must preserve the **order and repetition** of tokens. Therefore, a list of token objects is more appropriate than a map.

Each token now represents:

```text
Token
├── Type
└── Value
```

### Current Status

The lexer successfully recognizes and stores all features defined so far, with both token type and source value preserved.

### Next Step

Begin designing the parser that will consume the stored `List<Token>` and start understanding the structure of the program.

## Day 4 — Parser Development Begins

**Status: In Progress — Parser**

### Work Completed
- Started developing `Parser001` to validate the tokens generated by `Lexer003`.
- Implemented the first grammar rule for variable declarations: `DATA_TYPE IDENTIFIER END`.
- Added support for the `int`, `float`, `string`, and `boolean` data types.
- Implemented checks for data types, identifiers, and statement terminators.
- Added error messages that identify which required components are missing.
- Used conditional logic and a bitmask to determine the appropriate error message.
- Tested valid declarations and several invalid inputs, including missing identifiers, data types, and terminators.

### Current Progress
The lexer generates tokens, and the parser now uses those tokens to validate basic variable declarations. Initial test cases are producing the expected results.

### Current Focus
Parser development is **still in progress**. I am working on improving syntax validation, handling invalid token sequences, and preparing the parser to support more language constructs.

### Next Steps
- Improve handling of invalid syntax and unexpected tokens.
- Test multiple declarations and additional edge cases.
- Add grammar rules for variable assignments using `let`.
- Gradually extend parsing to conditional statements, loops, and functions.
