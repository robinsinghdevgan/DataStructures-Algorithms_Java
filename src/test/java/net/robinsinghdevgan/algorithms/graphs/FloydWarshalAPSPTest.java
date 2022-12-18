package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.dataStructures.graphs.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FloydWarshalAPSPTest {

    private Graph<Integer> createGraphWithOnlyPositiveEdgeCosts() {
        var graph = new Graph<Integer>();
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 4.0);
        graph.addEdge(0, 7, 8.0);
        graph.addEdge(1, 2, 8.0);
        graph.addEdge(1, 7, 1.0);
        graph.addEdge(2, 3, 7.0);
        graph.addEdge(2, 8, 2.0);
        graph.addEdge(2, 5, 4.0);
        graph.addEdge(3, 4, 9.0);
        graph.addEdge(3, 5, 1.0);
        graph.addEdge(4, 5, 1.0);
        graph.addEdge(5, 6, 2.0);
        graph.addEdge(6, 7, 1.0);
        graph.addEdge(6, 8, 6.0);
        graph.addEdge(7, 8, 7.0);
        return graph;
    }

    private Graph<Integer> createGraphWithNegativeEdgeCosts() {
        var graph = new Graph<Integer>();
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 4, 1.0);
        graph.addEdge(4, 3, -3.0);
        graph.addEdge(3, 2, 1.0);
        graph.addEdge(1, 5, 4.0);
        graph.addEdge(1, 6, 4.0);
        graph.addEdge(5, 6, 5.0);
        graph.addEdge(6, 7, 4.0);
        graph.addEdge(5, 7, 3.0);
        return graph;
    }

    @Test
    public void test1() {
        var obj = new FloydWarshalAPSP<Integer>();
        var graph = createGraphWithOnlyPositiveEdgeCosts();
        var res = obj.allPairsShortestPath(graph);
        assertThat(res.get(0).get(8)).isEqualTo(12.0);

        graph = createGraphWithNegativeEdgeCosts();
        res = obj.allPairsShortestPath(graph);
        assertThat(res.get(0).get(8)).isEqualTo(Double.POSITIVE_INFINITY);
    }
}
