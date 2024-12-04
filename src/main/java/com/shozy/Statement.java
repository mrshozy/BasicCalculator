package com.shozy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final List<Token> tokens;

    public Statement(List<Token> tokens) {
        this.tokens = tokens;
    }

    public double evaluate() {
        List<Token> rpn = toRPN(tokens);
        return evaluateRPN(rpn);
    }

    private List<Token> toRPN(List<Token> tokens) {
        List<Token> output = new ArrayList<>();
        ArrayDeque<Token> operators = new ArrayDeque<>();

        for (Token token : tokens) {
            switch (token.getTokenType()) {
                case Number:
                    output.add(token);
                    break;
                case Add:
                case Sub:
                case Mul:
                case Div:
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                        output.add(operators.pop());
                    }
                    operators.push(token);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    private int precedence(Token token) {
        return switch (token.getTokenType()) {
            case Mul, Div -> 2;
            case Add, Sub -> 1;
            default -> throw new IllegalArgumentException("Unexpected operator: " + token);
        };
    }

    private double evaluateRPN(List<Token> rpn) {
        ArrayDeque<Double> stack = new ArrayDeque<>();

        for (Token token : rpn) {
            switch (token.getTokenType()) {
                case Number:
                    stack.push(Double.valueOf(token.getText()));
                    break;
                case Add:
                    stack.push(stack.pop() + stack.pop());
                    break;
                case Sub:
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a - b);
                    break;
                case Mul:
                    stack.push(stack.pop() * stack.pop());
                    break;
                case Div:
                    double divisor = stack.pop();
                    double dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected token in RPN: " + token);
            }
        }
        if (stack.isEmpty()) {
            return Double.NaN;
        }
        return stack.pop();
    }
}