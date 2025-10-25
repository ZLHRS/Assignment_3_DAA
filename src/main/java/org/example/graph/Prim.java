package org.example.graph;

import java.util.*;

public class Prim {
    private final Graph graph;
    private int operationCount = 0;

    public Prim(Graph graph) {
        this.graph = graph;
    }

    public MSTResult findMST() {
        operationCount = 0;
        if (graph.getVertexCount() == 0) return new MSTResult(new ArrayList<>(), 0.0);
        if (!graph.isConnected()) return new MSTResult(new ArrayList<>(), Double.POSITIVE_INFINITY);

        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String start = graph.getVertices().iterator().next();
        visited.add(start);
        for (Edge e : graph.getNeighbors(start)) {
            pq.offer(e);
            operationCount++;
        }

        double total = 0.0;
        while (!pq.isEmpty() && visited.size() < graph.getVertexCount()) {
            operationCount++;
            Edge e = pq.poll();
            if (visited.contains(e.to)) continue;
            visited.add(e.to);
            mst.add(e);
            total += e.weight;
            for (Edge nx : graph.getNeighbors(e.to)) {
                if (!visited.contains(nx.to)) {
                    pq.offer(nx);
                    operationCount++;
                }
            }
        }

        return new MSTResult(mst, total);
    }

    public int getOperationCount() {
        return operationCount;
    }
}
