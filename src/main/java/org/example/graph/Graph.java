package org.example.graph;

import java.util.*;

public class Graph {
    private final int id;
    private final Map<String, List<Edge>> adj = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int id) {
        this.id = id;
    }

    public void addEdge(String from, String to, double weight) {

        adj.putIfAbsent(from, new ArrayList<>());
        adj.putIfAbsent(to, new ArrayList<>());


        Edge e = new Edge(from, to, weight);
        edges.add(e);


        adj.get(from).add(e);
        adj.get(to).add(new Edge(to, from, weight));
    }

    public int getId() {
        return id;
    }

    public Set<String> getVertices() {
        return Collections.unmodifiableSet(adj.keySet());
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Edge> getNeighbors(String node) {
        return adj.getOrDefault(node, Collections.emptyList());
    }

    public int getVertexCount() {
        return adj.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public boolean isConnected() {
        if (adj.isEmpty()) return true;
        Set<String> visited = new HashSet<>();
        Deque<String> dq = new ArrayDeque<>();
        String start = adj.keySet().iterator().next();
        dq.add(start);
        visited.add(start);
        while (!dq.isEmpty()) {
            String u = dq.poll();
            for (Edge e : getNeighbors(u)) {
                if (!visited.contains(e.to)) {
                    visited.add(e.to);
                    dq.add(e.to);
                }
            }
        }
        return visited.size() == adj.size();
    }
}
