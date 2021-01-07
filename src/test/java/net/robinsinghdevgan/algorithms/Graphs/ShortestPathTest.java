package net.robinsinghdevgan.algorithms.Graphs;

import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.robinsinghdevgan.dataStructures.Graphs.Graph;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShortestPathTest {
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
    public void test1() {
        var graph1 = createGraph();
        System.out.println(graph1.toString());

        var checkMap = new HashMap<Integer, Double>();
        checkMap.put(0, 0.0);
        checkMap.put(1, 4.0);
        checkMap.put(2, 12.0);
        checkMap.put(3, 19.0);
        checkMap.put(4, 28.0);
        checkMap.put(5, 16.0);
        checkMap.put(6, 18.0);
        checkMap.put(7, 5.0);
        checkMap.put(8, 12.0);

        var obj = new ShortestPath<Integer>();
        var distanceMap = obj.DijkstraSSPP(graph1, 0);
        assertThat(checkMap).isEqualTo(distanceMap);
    }
}
