
import org.example.graph.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PrimKruskalTest {

    @Test
    void testMSTCostsEqual() throws Exception {
        GraphReader r = new GraphReader();
        List<Graph> graphs = r.readGraphs("data/input.json");
        for (Graph g : graphs) {
            Prim prim = new Prim(g);
            Kruskal kruskal = new Kruskal(g);
            MSTResult p = prim.findMST();
            MSTResult k = kruskal.findMST();
            if (Double.isInfinite(p.totalCost) && Double.isInfinite(k.totalCost)) continue;
            assertEquals(p.totalCost, k.totalCost, 1e-6, "Стоимость MST должна совпадать");
            assertEquals(g.getVertexCount() - 1, p.edges.size(), "MST должен содержать V-1 ребро (Prim)");
            assertEquals(g.getVertexCount() - 1, k.edges.size(), "MST должен содержать V-1 ребро (Kruskal)");
        }
    }
}
