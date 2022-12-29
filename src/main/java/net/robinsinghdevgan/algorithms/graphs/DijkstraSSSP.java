package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;

import java.util.*;

@Slf4j
public class DijkstraSSSP<V extends Comparable<V>> {

    private List<String> reconstructPath(
            HashMap<V, Double> distance,
            HashMap<V, V> mapOfConnections,
            V dest
    ) {
        List<String> path = new ArrayList<>();
        /* if the distance to reach the destination vertex is 0,
            or in other words, the 'dest' supplied is the 'source' parameter to 'getShortestPath' method,
            return empty path
         */
        if (distance.get(dest) == 0.0) return path;

        //get the node which is connected to the destination
        V connectedVertex = mapOfConnections.get(dest);
        while (connectedVertex != null) {
            //Add this connected vertex to path
            path.add(connectedVertex.toString());
            //get the node which is connected to this vertex
            connectedVertex = mapOfConnections.get(connectedVertex);
        }
        //Reverse the path list, to get directions from start to destination
        Collections.reverse(path);
        return path;
    }

    // DijkstraSingleSourceShortestPath
    public Map<V, String> getShortestPath(Graph<V> graph, V source) {

        int numberOfVertices = graph.numberOfVertices();

        /* The priority-queue */
        //Let 'pq' be the priority queue of total size 'numberOfVertices', and is of type [source, destination-cost]
        var pq = new PriorityQueue<>(
                numberOfVertices,
                new DistanceToVertexFromSource()
        );
        //The distance to source is 0.0, add this to queue
        pq.offer(new DistanceToVertexFromSource(source, 0.0));

        /* The distance-map */
        //Create a map to store distance to each vertex from the source
        var mapOfDistanceToVertex = new HashMap<V, Double>();

        //In the beginning, the distance to every vertex apart from the source is unknown, therefore POSITIVE_INFINITY
        for (var vertex : graph.vertices()) {
            mapOfDistanceToVertex.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }
        //The distance of source to source is 0.0
        mapOfDistanceToVertex.put(source, 0.0);

        var mapOfConnections = new HashMap<V, V>();

        while (!pq.isEmpty()) {
            DistanceToVertexFromSource pqe = pq.poll();
            V vertexValue = pqe.source;
            Double distOfv = pqe.cost;
            net.robinsinghdevgan.data_structures.graphs.Vertex<V> v = graph.getVertex(
                    vertexValue
            );
            var listOfEdges = v.getOutDegreeEdges();
            for (var edge : listOfEdges) {

                V startingVertexOfThisEdge = edge.getFrom();
                V endingVertexOfThisEdge = edge.getTo();

                //calculate new distance
                double newDist = distOfv + edge.getCost();

                //if newDist is cheaper than older distance
                if (newDist < mapOfDistanceToVertex.get(endingVertexOfThisEdge)) {
                    // update distance-map with the new distance to this node
                    mapOfDistanceToVertex.put(endingVertexOfThisEdge, newDist);

                    //update connection to this node with this edge
                    mapOfConnections.put(endingVertexOfThisEdge, startingVertexOfThisEdge);

                    // update PQ with the new distance to this node
                    pq.offer(new DistanceToVertexFromSource(endingVertexOfThisEdge, newDist));
                }
            }
        }
        //Build and return the result
        var result = new HashMap<V, String>();
        for (var entry : mapOfDistanceToVertex.entrySet()) {
            V vertexToReach = entry.getKey();
            Double costToReachTheVertex = entry.getValue();
            result.put(
                    entry.getKey(),
                    reconstructPath(mapOfDistanceToVertex, mapOfConnections, vertexToReach) +
                            " and Costs: " +
                            costToReachTheVertex
            );
        }
        return result;
    }

    class DistanceToVertexFromSource implements Comparator<DistanceToVertexFromSource> {
        V source;
        Double cost;

        public DistanceToVertexFromSource(V source, double d) {
            this.source = source;
            this.cost = d;
        }

        public DistanceToVertexFromSource() {
            // for supplying to PriorityQueue
        }

        @Override
        public int compare(DistanceToVertexFromSource o1, DistanceToVertexFromSource o2) {
            //return -1 if o1 < o2, 0 if o1==o2 and 1 if o1 > o2
            return o1.cost.compareTo(o2.cost);
        }
    }
}
