package net.robinsinghdevgan.algorithms.Graphs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import net.robinsinghdevgan.dataStructures.Graphs.Graph;
import net.robinsinghdevgan.dataStructures.Graphs.Vertex;

public class ShortestPath<E extends Comparable<E>> {
    // public class Dijkstra<E extends Comparable<E>> {
    class PQEntry implements Comparator<PQEntry> {
        E source;
        Double cost;

        public PQEntry(E source, double d) {
            this.source = source;
            this.cost = d;
        }

        public PQEntry() {
            // for supplying to PriorityQueue
        }

        @Override
        public int compare(PQEntry o1, PQEntry o2) {
            return o1.cost.compareTo(o2.cost);
        }
    }

    // DijkstraSingleSourceShortestPath
    public HashMap<E, Double> DijkstraSSPP(Graph<E> graph, E source) {
        int V = graph.numberOfVertices();
        var pq = new PriorityQueue<PQEntry>(V, new PQEntry());
        pq.offer(new PQEntry(source, 0.0));
        var distance = new HashMap<E, Double>();
        for (var vertex : graph.vertices()) {
            distance.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }
        distance.put(source, 0.0);

        while (!pq.isEmpty()) {
            PQEntry pqe = pq.poll();
            E vertexValue = pqe.source;
            Double distOfv = pqe.cost;
            Vertex<E> v = graph.getVertex(vertexValue);
            var listOfEdges = v.getOutDegreeEdges();
            for (var e : listOfEdges) {
                Double newDist = distOfv + e.getCost();
                if (newDist < distance.get(e.getTo())) {
                    distance.put(e.getTo(), newDist);
                    pq.offer(new PQEntry(e.getTo(), newDist));
                }
            }
        }
        System.out.println(
                "Shortest distance of vertex: '" + source + "' to all the vertices in the graph is as follows:");
        for (var dist : distance.entrySet()) {
            System.out.println("Vertex: " + dist.getKey() + ", distance: " + dist.getValue());
        }
        return distance;
    }

}
