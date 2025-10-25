package org.example.graph;

public class Edge implements Comparable<Edge> {
    public final String from;
    public final String to;
    public final double weight;

    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\":\"%s\",\"to\":\"%s\",\"weight\":%.2f}", from, to, weight);
    }
}
