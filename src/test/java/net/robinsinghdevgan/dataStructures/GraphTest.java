package net.robinsinghdevgan.dataStructures;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class GraphTest {
    
    private Graph<Character> createGraph() {
        var graph = new Graph<Character>();
        char c = 'A';
        for (int i = 0; i < 9; i++) {
            graph.addVertex(c++);
        }
        graph.addEdge(0, 1, 4.5);
        graph.addEdge(0, 7, 8.5);
        graph.addEdge(1, 2, 8.5);
        graph.addEdge(1, 7, 1.51);
        graph.addEdge(2, 3, 7.5);
        graph.addEdge(2, 8, 2.5);
        graph.addEdge(2, 5, 4.5);
        graph.addEdge(3, 4, 9.5);
        graph.addEdge(3, 5, 1.54);
        graph.addEdge(4, 5, 1.50);
        graph.addEdge(5, 6, 2.5);
        graph.addEdge(6, 7, 1.5);
        graph.addEdge(6, 8, 6.5);
        graph.addEdge(7, 8, 7.5);
        return graph;
    }
    @Test
    public void test1() {
        var graph1 = createGraph();
        Graph<Character> graph2 = createGraph();
        graph1.removeUsingVertexIndex(8);
        graph1.removeUsingVertexIndex(0);
        graph2.removeUsingVertexName('I');
        graph2.removeUsingVertexName('A');

        assertEquals(graph1.toString(), graph2.toString());
    }
}
