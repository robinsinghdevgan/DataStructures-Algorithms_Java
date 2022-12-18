package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.dataStructures.graphs.Edge;
import net.robinsinghdevgan.dataStructures.graphs.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BellmanFordSSSP<E extends Comparable<E>> {

    private List<String> reconstructPath(
            HashMap<E, Double> distance,
            HashMap<E, E> prev,
            E dest
    ) {
        List<String> path = new ArrayList<>();
        if (
                distance.get(dest) == 0.0 || distance.get(dest) == Double.NEGATIVE_INFINITY
        ) return path;
        var at = prev.get(dest);
        while (at != null) {
            path.add(at.toString());
            at = prev.get(at);
        }
        Collections.reverse(path);
        return path;
    }

    public HashMap<E, String> getShortestPath(Graph<E> graph, E source) {
        int V = graph.numberOfVertices();
        int iterations = V - 1;

        var distCost = new HashMap<E, Double>();
        var prev = new HashMap<E, E>();
        for (var vertex : graph.vertices()) {
            distCost.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }
        distCost.put(source, 0.0);

        // For each vertex, apply relaxation for all the edges
        for (int i = 0; i < iterations; i++)
            for (Edge<E> edge : graph.getEdges())
                if (
                        distCost.get(edge.getFrom()) + edge.getCost() < distCost.get(edge.getTo())
                ) {
                    distCost.put(edge.getTo(), distCost.get(edge.getFrom()) + edge.getCost());
                    prev.put(edge.getTo(), edge.getFrom());
                }

        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        for (int i = 0; i < iterations; i++)
            for (Edge<E> edge : graph.getEdges())
                if (
                        distCost.get(edge.getFrom()) + edge.getCost() < distCost.get(edge.getTo())
                ) {
                    distCost.put(edge.getTo(), Double.NEGATIVE_INFINITY);
                    prev.put(edge.getTo(), edge.getFrom());
                }

        // Return the array containing the shortest distance to every node
        var result = new HashMap<E, String>();
        for (var dist : distCost.entrySet()) {
            result.put(
                    dist.getKey(),
                    reconstructPath(distCost, prev, dist.getKey()) +
                            " and Costs: " +
                            dist.getValue().toString()
            );
        }
        return result;
    }
}
