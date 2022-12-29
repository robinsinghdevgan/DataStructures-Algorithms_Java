package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class GraphTest {

    private Graph<Integer> createGraph() {
        var graph = new Graph<Integer>();
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i);
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
    void test1() {
        var graph1 = createGraph();
        log.info(String.valueOf(graph1));
        assertThat(graph1.numberOfVertices()).isEqualTo(9);
        graph1.removeVertex(8);
        log.info(String.valueOf(graph1));
        assertThat(graph1.numberOfVertices()).isEqualTo(8);
    }
}
