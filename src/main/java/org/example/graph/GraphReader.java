package org.example.graph;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class GraphReader {
    public List<Graph> readGraphs(String filename) throws IOException {
        List<Graph> graphs = new ArrayList<>();
        try (Reader r = new FileReader(filename)) {
            JsonObject root = JsonParser.parseReader(r).getAsJsonObject();
            JsonArray arr = root.getAsJsonArray("graphs");
            for (JsonElement ge : arr) {
                JsonObject go = ge.getAsJsonObject();
                int id = go.get("id").getAsInt();
                Graph g = new Graph(id);
                JsonArray edges = go.getAsJsonArray("edges");
                for (JsonElement ee : edges) {
                    JsonObject eo = ee.getAsJsonObject();
                    String from = eo.get("from").getAsString();
                    String to = eo.get("to").getAsString();
                    double weight = eo.get("weight").getAsDouble();
                    g.addEdge(from, to, weight);
                }
                graphs.add(g);
            }
        }
        return graphs;
    }
}
