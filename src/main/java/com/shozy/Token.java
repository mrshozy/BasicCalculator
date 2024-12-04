package com.shozy;

public class Token {
    public enum TokenType {
        EOF,
        Number,
        Mul,
        Div,
        Add,
        Sub,
        Dot,
    }

    String text;
    TokenType token;

    public Token(String text, TokenType token) {
        this.text = text;
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public TokenType getTokenType() {
        return token;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() throws IllegalArgumentException {
        switch (token) {
            case Number:
                try {
                    if (text.contains(".")) {
                        return (T) Double.valueOf(text);
                    } else {
                        return (T) Integer.valueOf(text);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format: " + text);
                }
            case Mul:
            case Div:
            case Add:
            case Sub:
            case Dot:
                return (T) text;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return text + " " + token;
    }
}