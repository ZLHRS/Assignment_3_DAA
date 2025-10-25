package org.example.graph;

import java.util.*;

public class Kruskal {
    private final Graph graph;
    private int operationCount = 0;

    public Kruskal(Graph graph) {
        this.graph = graph;
    }

    static class DSU {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();
        private int opsFind = 0;

        public String find(String x) {
            opsFind++;
            parent.putIfAbsent(x, x);
            rank.putIfAbsent(x, 0);
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(String a, String b) {
            a = find(a);
            b = find(b);
            if (a.equals(b)) return;
            int ra = rank.getOrDefault(a,0);
            int rb = rank.getOrDefault(b,0);
            if (ra < rb) parent.put(a, b);
            else if (ra > rb) parent.put(b, a);
            else {
                parent.put(b, a);
                rank.put(a, ra + 1);
            }
        }
    }

    public MSTResult findMST() {
        operationCount = 0;
        if (graph.getVertexCount() == 0) return new MSTResult(new ArrayList<>(), 0.0);
        if (!graph.isConnected()) return new MSTResult(new ArrayList<>(), Double.POSITIVE_INFINITY);

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        operationCount += edges.size() * (int)Math.ceil(Math.log(edges.size() + 1)); // rough count for sorting comparisons

        DSU dsu = new DSU();
        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        for (Edge e : edges) {
            operationCount++;
            String a = dsu.find(e.from);
            String b = dsu.find(e.to);
            operationCount += 2;
            if (!a.equals(b)) {
                dsu.union(a, b);
                mst.add(e);
                total += e.weight;
                operationCount++;
            }
            if (mst.size() == graph.getVertexCount() - 1) break;
        }


        operationCount += dsu.opsFind;

        return new MSTResult(mst, total);
    }

    public int getOperationCount() {
        return operationCount;
    }
}
