package com.shozy;

public class Lexer {
    String data;
    int count;

    public Lexer(String data) {
        this.data = data.replaceAll(" ", "");
        this.count = 0;
    }

    public Token nextToken() {
        if (count == data.length()) {
            return new Token("\0", Token.TokenType.EOF);
        }
        int start = this.count;
        int end = this.tokenRange();
        String text = this.data.substring(start, end);
        Token.TokenType type = this.tokenType(text);
        return new Token(text, type);
    }

    public int tokenRange() {
        char ch = data.charAt(count);
        if (Character.isDigit(ch)) {
            while (count < data.length() && (Character.isDigit(data.charAt(count)) || data.charAt(count) == '.')) {
                count++;
            }
        } else if (ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '.') {
            count++;
        } else {
            throw new IllegalArgumentException("Unexpected character: " + ch);
        }
        return count;
    }

    public Token.TokenType tokenType(String text) {
        if (text.matches("\\d+\\.?\\d*")) {
            return Token.TokenType.Number;
        }
        return switch (text) {
            case "*" -> Token.TokenType.Mul;
            case "/" -> Token.TokenType.Div;
            case "+" -> Token.TokenType.Add;
            case "-" -> Token.TokenType.Sub;
            case "." -> Token.TokenType.Dot;
            default -> throw new IllegalArgumentException("Unknown token: " + text);
        };
    }
}