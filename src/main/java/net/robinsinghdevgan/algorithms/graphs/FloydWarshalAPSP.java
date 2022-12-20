package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.data_structures.graphs.Graph;

import java.util.HashMap;

public class FloydWarshalAPSP<E extends Comparable<E>> {

    public HashMap<E, HashMap<E, Double>> allPairsShortestPath(Graph<E> graph) {
        var costMatrix = new HashMap<E, HashMap<E, Double>>();
        /*
         * Initialize the cost matrix
         */
        var listOfVertices = graph.vertices();

        for (var v : listOfVertices) {
            costMatrix.put(v.getData(), new HashMap<>());
            var costMapOfV = costMatrix.get(v.getData());
            for (var w : listOfVertices)
                costMapOfV.put(
                        w.getData(),
                        graph.getCost(v.getData(), w.getData())
                );
        }

        /*
         * Add all vertices one by one to the set of intermediate vertices. ---> Before
         * start of an iteration, we have the shortest distances between all pairs of
         * vertices such that the shortest distances consider only the vertices in set
         * {0, 1, 2, .. k-1} as intermediate vertices. ----> After the end of an
         * iteration, vertex no. k is added to the set of intermediate vertices and the
         * set becomes {0, 1, 2, .. k}
         */
        for (var K : listOfVertices) {
            // Pick all vertices as source one by one
            var k = K.getData();
            for (var I : listOfVertices) {
                // Pick all vertices as destination for the
                // above picked source
                var i = I.getData();
                for (var J : listOfVertices) {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    var j = J.getData();
                    if (
                            costMatrix.get(i).get(k) + costMatrix.get(k).get(j) < costMatrix.get(i).get(j)
                    ) costMatrix.get(i).put(j, costMatrix.get(i).get(k) + costMatrix.get(k).get(j));
                }
            }
        }

        // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        // to every edge that is part of or reaches into a negative cycle.
        for (var K : listOfVertices) {
            // Pick all vertices as source one by one
            var k = K.getData();
            for (var I : listOfVertices) {
                // Pick all vertices as destination for the
                // above picked source
                var i = I.getData();
                for (var J : listOfVertices) {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    var j = J.getData();
                    if (
                            costMatrix.get(i).get(k) + costMatrix.get(k).get(j) < costMatrix.get(i).get(j)
                    ) costMatrix.get(i).put(j, Double.NEGATIVE_INFINITY);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var row : costMatrix.entrySet()) {
            sb.append(row.getKey().toString()).append(" => | ");
            for (var col : row.getValue().entrySet()) {
                var s = col.getValue().toString();
                if (s.equals("Infinity")) sb.append(" Inf" + " | ");
                else if (
                        s.equals("-Infinity")
                ) sb.append("-Inf" + " | ");
                else sb.append(" ").append(s).append(" | ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        return costMatrix;
    }
}
