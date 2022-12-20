package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.data_structures.graphs.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DijkstraSSSPTest {

    private Graph<Integer> createGraph() {
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

    @Test
    void test1() {
        var graph1 = createGraph();
        System.out.println(graph1);

        var checkMap = new HashMap<Integer, String>();
        checkMap.put(0, "[] and Costs: 0.0");
        checkMap.put(1, "[0] and Costs: 4.0");
        checkMap.put(2, "[0, 1] and Costs: 12.0");
        checkMap.put(3, "[0, 1, 2] and Costs: 19.0");
        checkMap.put(4, "[0, 1, 2, 3] and Costs: 28.0");
        checkMap.put(5, "[0, 1, 2] and Costs: 16.0");
        checkMap.put(6, "[0, 1, 2, 5] and Costs: 18.0");
        checkMap.put(7, "[0, 1] and Costs: 5.0");
        checkMap.put(8, "[0, 1, 7] and Costs: 12.0");

        Integer sourceVertex = 0;
        var obj = new DijkstraSSSP<Integer>();
        var distanceMap = obj.getShortestPath(graph1, sourceVertex);
        assertThat(distanceMap).isEqualTo(checkMap);

        for (var entry : distanceMap.entrySet()) {
            System.out.println(
                    "Path from: '" +
                            sourceVertex +
                            "' to: " +
                            entry.getKey() +
                            " : " +
                            entry.getValue()
            );
        }
    }
}
