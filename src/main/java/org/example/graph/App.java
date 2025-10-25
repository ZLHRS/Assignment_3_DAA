package org.example.graph;

public class App {
    public static void main(String[] args) {
        String input = "data/input.json";
        String output = "data/output.json";
        if (args.length >= 2) {
            input = args[0];
            output = args[1];
        }
        Benchmark.runAllTests(input, output);
    }
}
