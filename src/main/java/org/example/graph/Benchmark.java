package org.example.graph;

import com.google.gson.*;
import java.util.*;
import java.io.*;

public class Benchmark {

    public static void runAllTests(String inputPath, String outputPath) {
        try {
            GraphReader reader = new GraphReader();
            List<Graph> graphs = reader.readGraphs(inputPath);
            JsonArray resultsArray = new JsonArray();

            for (Graph g : graphs) {
                JsonObject graphResult = new JsonObject();
                graphResult.addProperty("graph_id", g.getId());

                JsonObject inputStats = new JsonObject();
                inputStats.addProperty("vertices", g.getVertexCount());
                inputStats.addProperty("edges", g.getEdgeCount());
                graphResult.add("input_stats", inputStats);


                Prim prim = new Prim(g);
                long startP = System.nanoTime();
                MSTResult pRes = prim.findMST();
                long endP = System.nanoTime();
                double primTime = (endP - startP) / 1_000_000.0;

                JsonObject primObj = new JsonObject();
                primObj.add("mst_edges", edgesToJsonArray(pRes.edges));
                primObj.addProperty("total_cost", pRes.totalCost);
                primObj.addProperty("operations_count", prim.getOperationCount());
                primObj.addProperty("execution_time_ms", primTime);
                graphResult.add("prim", primObj);


                Kruskal kruskal = new Kruskal(g);
                long startK = System.nanoTime();
                MSTResult kRes = kruskal.findMST();
                long endK = System.nanoTime();
                double kruskalTime = (endK - startK) / 1_000_000.0;

                JsonObject kruskalObj = new JsonObject();
                kruskalObj.add("mst_edges", edgesToJsonArray(kRes.edges));
                kruskalObj.addProperty("total_cost", kRes.totalCost);
                kruskalObj.addProperty("operations_count", kruskal.getOperationCount());
                kruskalObj.addProperty("execution_time_ms", kruskalTime);
                graphResult.add("kruskal", kruskalObj);

                resultsArray.add(graphResult);
            }

            GraphWriter writer = new GraphWriter();
            writer.writeResults(resultsArray, outputPath);

            System.out.println("Benchmark finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonArray edgesToJsonArray(List<Edge> edges) {
        JsonArray arr = new JsonArray();
        for (Edge e : edges) {
            JsonObject o = new JsonObject();
            o.addProperty("from", e.from);
            o.addProperty("to", e.to);
            o.addProperty("weight", e.weight);
            arr.add(o);
        }
        return arr;
    }
}
