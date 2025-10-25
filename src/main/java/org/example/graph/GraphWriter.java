package org.example.graph;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class GraphWriter {

    public void writeResults(JsonArray resultsArray, String outputPath) throws IOException {
        JsonObject root = new JsonObject();
        root.add("results", resultsArray);
        try (FileWriter fw = new FileWriter(outputPath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(root, fw);
        }
    }
}
