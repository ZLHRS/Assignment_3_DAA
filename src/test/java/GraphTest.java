
import org.example.graph.Graph;
import org.example.graph.GraphReader;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void testGraphReader() throws Exception {
        GraphReader r = new GraphReader();
        List<Graph> graphs = r.readGraphs("data/input.json");
        assertNotNull(graphs);
        assertTrue(graphs.size() > 0, "input.json должен содержать хотя бы один граф");
        Graph g = graphs.get(0);
        assertTrue(g.getVertexCount() > 0);
        assertTrue(g.getEdgeCount() > 0);
    }
}
