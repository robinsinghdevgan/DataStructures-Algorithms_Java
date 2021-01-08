package net.robinsinghdevgan.algorithms.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import net.robinsinghdevgan.dataStructures.Graphs.Graph;
import net.robinsinghdevgan.dataStructures.Graphs.Vertex;

public class DijkstraSSSP<E extends Comparable<E>> {
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

    private List<String> reconstructPath(HashMap<E, Double> distance, HashMap<E, E> prev, E dest) {
        List<String> path = new ArrayList<>();
        if (distance.get(dest) == 0.0)
            return path;
        var at = prev.get(dest);
        while (at != null) {
            path.add(at.toString());
            at = prev.get(at);
        }
        Collections.reverse(path);
        return path;
    }

    // DijkstraSingleSourceShortestPath
    public HashMap<E, String> getShortestPath(Graph<E> graph, E source) {
        int V = graph.numberOfVertices();
        var pq = new PriorityQueue<PQEntry>(V, new PQEntry());
        pq.offer(new PQEntry(source, 0.0));
        var distance = new HashMap<E, Double>();
        var prev = new HashMap<E, E>();
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
                    prev.put(e.getTo(), e.getFrom());
                    pq.offer(new PQEntry(e.getTo(), newDist));
                }
            }
        }
        var result = new HashMap<E, String>();
        for (var dist : distance.entrySet()) {
            result.put(dist.getKey(),
                    reconstructPath(distance, prev, dist.getKey()) + " and Costs: " + dist.getValue().toString());
        }
        return result;
    }

}
