
# Calculator

A simple arithmetic calculator implemented in Java. It supports basic operations such as addition, subtraction, multiplication, and division using a custom lexer, parser, and evaluator.

## Features

- **Lexer**: Tokenizes the input expression into meaningful units (numbers and operators).
- **Parser**: Converts the tokenized input into a syntax representation (`Statement` object).
- **Evaluator**: Uses Reverse Polish Notation (RPN) to evaluate the arithmetic expression with proper operator precedence.

## Supported Operations

| Operator | Description         |
|----------|---------------------|
| `+`      | Addition            |
| `-`      | Subtraction         |
| `*`      | Multiplication      |
| `/`      | Division            |

## How It Works

1. **Lexical Analysis**:
    - The `Lexer` splits the input string into tokens such as numbers and operators.
    - Example: `"3 + 5 * 2"` → Tokens: `[3, +, 5, *, 2]`.

2. **Parsing**:
    - The `Parser` processes tokens and wraps them into a `Statement` object.

3. **Evaluation**:
    - The `Statement` class evaluates the arithmetic expression using:
        - Shunting Yard Algorithm to convert tokens into Reverse Polish Notation (RPN).
        - A stack-based approach to compute the result of the RPN expression.

## Example Usage

```java
public class Main {
    public static void main(String[] args) {
        String input = "3 + 5 * 2 - 8 / 4";
        Parser parser = new Parser(input);
        Statement statement = parser.parse();
        double result = statement.evaluate();
        System.out.println("Result: " + result);  // Output: 10.0
    }
}
```

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── shozy
│   │               ├── Token.java        // Token representation (type and value)
│   │               ├── Lexer.java        // Tokenizes the input string
│   │               ├── Parser.java       // Parses tokens into a statement
│   │               ├── Statement.java    // Evaluates the expression
│   │               └── Main.java         // Example usage of the calculator
│   └── test
│       └── java
│           └── CalculatorTests.java      // Unit tests for the calculator
├── README.md                             // Project documentation
├── LICENSE                               // License file
├── pom.xml                               // Maven build file
├── .gitignore                            // Git ignore file
└── .github
    └── workflows
        └── calculator-test.yml           // GitHub Actions workflow for testing

```


## Future Improvements

- Add support for parentheses to handle nested expressions.
- Improve error handling for invalid inputs (e.g., division by zero, unexpected characters).
- Extend support to more operations (e.g., exponentiation, modulo).
- Add a command-line interface for user input.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
