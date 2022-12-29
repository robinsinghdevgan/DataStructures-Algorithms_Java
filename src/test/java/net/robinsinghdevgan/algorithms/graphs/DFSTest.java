package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class DFSTest {

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
        var graph = createGraph();
        log.info(String.valueOf(graph));
        var obj = new DFS<Integer>();
        var cost = obj.search(graph, 0, 8);
        assertThat(cost).isEqualTo(15.0);
        log.info(String.valueOf(cost));

        cost = obj.search(graph, 0, 6);
        assertThat(cost).isEqualTo(34.0);
        log.info(String.valueOf(cost));

        cost = obj.search(graph, 0, 7);
        assertThat(cost).isEqualTo(8.0);
        log.info(String.valueOf(cost));

        cost = obj.search(graph, 0, 3);
        assertThat(cost).isEqualTo(50.0);
        log.info(String.valueOf(cost));
    }
}
