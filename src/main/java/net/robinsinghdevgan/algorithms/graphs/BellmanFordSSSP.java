package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Edge;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import net.robinsinghdevgan.data_structures.graphs.Vertex;

import java.util.*;

@Slf4j
public class BellmanFordSSSP<V extends Comparable<V>> {

    private List<String> reconstructPath(
            HashMap<V, Double> distance,
            HashMap<V, V> mapOfConnections,
            V dest
    ) {
        List<String> path = new ArrayList<>();
        if (
                distance.get(dest) == 0.0 || distance.get(dest) == Double.NEGATIVE_INFINITY
        ) return path;
        V connectedVertex = mapOfConnections.get(dest);
        while (connectedVertex != null) {
            path.add(connectedVertex.toString());
            connectedVertex = mapOfConnections.get(connectedVertex);
        }
        Collections.reverse(path);
        return path;
    }

    public Map<V, String> getShortestPath(Graph<V> graph, V source) {
        int numberOfVertices = graph.numberOfVertices();
        int iterations = numberOfVertices - 1;

        var distCost = new HashMap<V, Double>();

        //In the beginning, the distance to every vertex apart from the source is unknown, therefore POSITIVE_INFINITY
        for (Vertex<V> vertex : graph.vertices()) {
            distCost.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }
        //The distance of source to source is 0.0
        distCost.put(source, 0.0);

        var mapOfConnections = new HashMap<V, V>();

        // For each vertex, apply relaxation for all the edges
        for (int i = 0; i < iterations; i++) {
            for (Edge<V> edge : graph.getEdges()) {

                V startingVertexOfThisEdge = edge.getFrom();
                V endingVertexOfThisEdge = edge.getTo();
                //calculate new distance
                Double newCalculatedDistance = distCost.get(startingVertexOfThisEdge) + edge.getCost();

                //if newCalculatedDistance is cheaper than older distance
                if (newCalculatedDistance < distCost.get(endingVertexOfThisEdge)) {
                    // update distance-map with the new distance to this node
                    distCost.put(endingVertexOfThisEdge, newCalculatedDistance);
                    //update connection to this node with this edge
                    mapOfConnections.put(endingVertexOfThisEdge, startingVertexOfThisEdge);
                }
            }
        }

        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        for (int i = 0; i < iterations; i++) {
            for (Edge<V> edge : graph.getEdges()) {
                Double newCalculatedDistance = distCost.get(edge.getFrom()) + edge.getCost();
                if (newCalculatedDistance < distCost.get(edge.getTo())) {
                    distCost.put(edge.getTo(), Double.NEGATIVE_INFINITY);
                    mapOfConnections.put(edge.getTo(), edge.getFrom());
                }
            }
        }

        // Return the array containing the shortest distance to every node
        var result = new HashMap<V, String>();
        for (var dist : distCost.entrySet()) {
            result.put(
                    dist.getKey(),
                    reconstructPath(distCost, mapOfConnections, dist.getKey()) +
                            " and Costs: " +
                            dist.getValue().toString()
            );
        }
        return result;
    }
}
