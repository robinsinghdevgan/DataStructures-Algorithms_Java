package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.dataStructures.graphs.Graph;

import java.util.*;

public class DijkstraSSSP<Vertex extends Comparable<Vertex>> {

    private List<String> reconstructPath(
            HashMap<Vertex, Double> distance,
            HashMap<Vertex, Vertex> prev,
            Vertex dest
    ) {
        List<String> path = new ArrayList<>();
        if (distance.get(dest) == 0.0) return path;
        Vertex at = prev.get(dest);
        while (at != null) {
            path.add(at.toString());
            at = prev.get(at);
        }
        Collections.reverse(path);
        return path;
    }

    // DijkstraSingleSourceShortestPath
    public HashMap<Vertex, String> getShortestPath(Graph<Vertex> graph, Vertex source) {
        //Let V be the number of vertices
        int V = graph.numberOfVertices();

        /* The priority-queue */
        //Let 'pq' be the priority queue of total size 'V', and is of type [source, destination-cost]
        var pq = new PriorityQueue<>(
                V,
                new DistanceToVertexFromSource()
        );
        //The distance to source is 0.0, add this to queue
        pq.offer(new DistanceToVertexFromSource(source, 0.0));

        /* The distance-map */
        //Create a map to store distance to each vertex from the source
        var mapOfDistanceToVertex = new HashMap<Vertex, Double>();
        //The distance of source to source is 0.0
        mapOfDistanceToVertex.put(source, 0.0);
        //In the beginning, the distance to every vertex apart from the source is unknown, therefore POSITIVE_INFINITY
        for (var vertex : graph.vertices()) {
            mapOfDistanceToVertex.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }

        var mapOfConnections = new HashMap<Vertex, Vertex>();

        while (!pq.isEmpty()) {
            DistanceToVertexFromSource pqe = pq.poll();
            Vertex vertexValue = pqe.source;
            Double distOfv = pqe.cost;
            net.robinsinghdevgan.dataStructures.graphs.Vertex<Vertex> v = graph.getVertex(
                    vertexValue
            );
            var listOfEdges = v.getOutDegreeEdges();
            for (var edge : listOfEdges) {
                //calculate new distance
                double newDist = distOfv + edge.getCost();

                //if newDist is cheaper than older distance
                if (newDist < mapOfDistanceToVertex.get(edge.getTo())) {
                    // update distance-map with the new distance to this node
                    mapOfDistanceToVertex.put(edge.getTo(), newDist);

                    //update connection to this node with this edge
                    mapOfConnections.put(edge.getTo(), edge.getFrom());

                    // update PQ with the new distance to this node
                    pq.offer(new DistanceToVertexFromSource(edge.getTo(), newDist));
                }
            }
        }
        var result = new HashMap<Vertex, String>();
        for (var dist : mapOfDistanceToVertex.entrySet()) {
            result.put(
                    dist.getKey(),
                    reconstructPath(mapOfDistanceToVertex, mapOfConnections, dist.getKey()) +
                            " and Costs: " +
                            dist.getValue().toString()
            );
        }
        return result;
    }

    class DistanceToVertexFromSource implements Comparator<DistanceToVertexFromSource> {
        Vertex source;
        Double cost;

        public DistanceToVertexFromSource(Vertex source, double d) {
            this.source = source;
            this.cost = d;
        }

        public DistanceToVertexFromSource() {
            // for supplying to PriorityQueue
        }

        @Override
        public int compare(DistanceToVertexFromSource o1, DistanceToVertexFromSource o2) {
            return o1.cost.compareTo(o2.cost);
        }
    }
}
