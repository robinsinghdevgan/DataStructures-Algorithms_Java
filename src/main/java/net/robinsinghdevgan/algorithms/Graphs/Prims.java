package net.robinsinghdevgan.algorithms.Graphs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import net.robinsinghdevgan.dataStructures.Graphs.Edge;
import net.robinsinghdevgan.dataStructures.Graphs.Graph;

public class Prims<T extends Comparable<T>> {
    class EdgeComparator implements Comparator<Edge<T>> {
        @Override
        public int compare(Edge<T> o1, Edge<T> o2) {
            return o1.getCost().compareTo(o2.getCost());
        }
    }

    PriorityQueue<Edge<T>> pq;
    HashSet<T> visited;
    HashSet<Edge<T>> mstEdges;

    private void addEdges(Graph<T> graph, T node) {
        visited.add(node);
        for (var e : graph.getVertex(node).getOutDegreeEdges()) {
            if (!visited.contains(e.getTo()))
                pq.offer(e);
        }
    }

    // modifies graph to become MST
    // and returns cost of MST
    public Double getMSP(Graph<T> graph, T source) {
        int V = graph.numberOfVertices();
        int E = graph.numberOfEdges();

        if (E < V - 1)
            throw new IllegalArgumentException("The graph does not have all nodes connected");

        // create priority queue
        pq = new PriorityQueue<>(E, new EdgeComparator());
        visited = new HashSet<T>();
        mstEdges = new HashSet<Edge<T>>();
        addEdges(graph, source);

        int m = V - 1; // number of edges in mst
        int edgeCount = 0;
        Double mstCost = 0.0;

        while (!pq.isEmpty() && edgeCount != m) {
            var e = pq.poll();
            if (visited.contains(e.getTo()))
                continue;

            addEdges(graph, e.getTo());
            ++edgeCount;
            mstEdges.add(e);
            mstCost += e.getCost();
        }
        var edges = graph.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            if (mstEdges.contains(edges.get(i)) == false)
                graph.removeEdge(edges.get(i));
        }

        
        System.out.println("MST Cost: " + mstCost);
        for(var e : mstEdges) {
            System.out.println("MST Edge: " + e);
        }
        return mstCost;
    }
}
