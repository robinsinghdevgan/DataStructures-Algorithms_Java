package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PrimsTest {

    private Graph<Integer> createGraph() {
        var graph = new Graph<Integer>();
        for (int i = 0; i < 8; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 10.0);
        graph.addEdge(0, 2, 1.0);
        graph.addEdge(0, 3, 4.0);
        graph.addEdge(2, 1, 3.0);
        graph.addEdge(2, 5, 8.0);
        graph.addEdge(2, 3, 2.0);
        graph.addEdge(2, 0, 1.0);
        graph.addEdge(3, 2, 2.0);
        graph.addEdge(3, 5, 2.0);
        graph.addEdge(3, 6, 7.0);
        graph.addEdge(3, 0, 4.0);
        graph.addEdge(5, 2, 8.0);
        graph.addEdge(5, 4, 1.0);
        graph.addEdge(5, 7, 9.0);
        graph.addEdge(5, 6, 6.0);
        graph.addEdge(5, 3, 2.0);
        graph.addEdge(4, 1, 0.0);
        graph.addEdge(4, 5, 1.0);
        graph.addEdge(4, 7, 8.0);
        graph.addEdge(1, 0, 10.0);
        graph.addEdge(1, 2, 3.0);
        graph.addEdge(1, 4, 0.0);
        graph.addEdge(6, 3, 7.0);
        graph.addEdge(6, 5, 6.0);
        graph.addEdge(6, 7, 12.0);
        graph.addEdge(7, 4, 8.0);
        graph.addEdge(7, 5, 9.0);
        graph.addEdge(7, 6, 12.0);
        return graph;
    }

    @Test
    void test1() {
        var graph1 = createGraph();
        log.info(String.valueOf(graph1));

        Integer sourceVertex = 0;
        var obj = new Prims<Integer>();
        assertThat(obj.getMSP(graph1, sourceVertex)).isEqualTo(20.0);

        log.info(String.valueOf(graph1));
    }

    private void addUndirectedEdge(Graph<Integer> g, int from, int to, Double cost) {
        g.addEdge(from, to, cost);
        g.addEdge(to, from, cost);
    }

    @Test
    void test2() {
        var g = new Graph<Integer>();
        for (int i = 0; i < 10; i++) {
            g.addVertex(i);
        }
        addUndirectedEdge(g, 0, 1, 5.0);
        addUndirectedEdge(g, 1, 2, 4.0);
        addUndirectedEdge(g, 2, 9, 2.0);
        addUndirectedEdge(g, 0, 4, 1.0);
        addUndirectedEdge(g, 0, 3, 4.0);
        addUndirectedEdge(g, 1, 3, 2.0);
        addUndirectedEdge(g, 2, 7, 4.0);
        addUndirectedEdge(g, 2, 8, 1.0);
        addUndirectedEdge(g, 9, 8, 0.0);
        addUndirectedEdge(g, 4, 5, 1.0);
        addUndirectedEdge(g, 5, 6, 7.0);
        addUndirectedEdge(g, 6, 8, 4.0);
        addUndirectedEdge(g, 4, 3, 2.0);
        addUndirectedEdge(g, 5, 3, 5.0);
        addUndirectedEdge(g, 3, 6, 11.0);
        addUndirectedEdge(g, 6, 7, 1.0);
        addUndirectedEdge(g, 3, 7, 2.0);
        addUndirectedEdge(g, 7, 8, 6.0);
        log.info(String.valueOf(g));

        Integer sourceVertex = 0;
        var obj = new Prims<Integer>();
        assertThat(obj.getMSP(g, sourceVertex)).isEqualTo(14.0);

        log.info(String.valueOf(g));
    }
}
