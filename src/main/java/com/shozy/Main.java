package com.shozy;

public class Main {
    public static void main(String[] args) {
        String input = "1 + 2 + 45 + 60";
        Parser parser = new Parser(input);
        Statement statement = parser.parse();
        double result = statement.evaluate();
        System.out.println("Result: " + result);
    }
}