package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Edge;
import net.robinsinghdevgan.data_structures.graphs.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

@Slf4j
public class Prims<T extends Comparable<T>> {

    PriorityQueue<Edge<T>> pq;
    HashSet<T> visited;
    HashSet<Edge<T>> mstEdges;

    private void addEdges(Graph<T> graph, T node) {
        visited.add(node);
        for (var e : graph.getVertex(node).getOutDegreeEdges()) {
            if (!visited.contains(e.getTo())) pq.offer(e);
        }
    }

    // modifies graph to become MST
    // and returns cost of MST
    public Double getMSP(Graph<T> graph, T source) {
        int V = graph.numberOfVertices();
        int E = graph.numberOfEdges();

        if (E < V - 1) throw new IllegalArgumentException(
                "The graph does not have all nodes connected"
        );

        // create priority queue
        pq = new PriorityQueue<>(E, new EdgeComparator());
        visited = new HashSet<>();
        mstEdges = new HashSet<>();
        addEdges(graph, source);

        int m = V - 1; // number of edges in mst
        int edgeCount = 0;
        Double mstCost = 0.0;

        while (!pq.isEmpty() && edgeCount != m) {
            var e = pq.poll();
            if (visited.contains(e.getTo())) continue;

            addEdges(graph, e.getTo());
            ++edgeCount;
            mstEdges.add(e);
            mstCost += e.getCost();
        }
        var edges = graph.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            if (!mstEdges.contains(edges.get(i)))
                graph.removeEdge(edges.get(i));
        }

        log.info(String.valueOf("MST Cost: " + mstCost));
        for (var e : mstEdges) {
            log.info(String.valueOf("MST Edge: " + e));
        }
        return mstCost;
    }

    class EdgeComparator implements Comparator<Edge<T>> {

        @Override
        public int compare(Edge<T> o1, Edge<T> o2) {
            return o1.getCost().compareTo(o2.getCost());
        }
    }
}
