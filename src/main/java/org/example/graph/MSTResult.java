package org.example.graph;

import java.util.List;

public class MSTResult {
    public List<Edge> edges;
    public double totalCost;

    public MSTResult(List<Edge> edges, double totalCost) {
        this.edges = edges;
        this.totalCost = totalCost;
    }
}
