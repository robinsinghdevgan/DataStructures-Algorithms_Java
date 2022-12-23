package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.data_structures.graphs.Graph;

import java.util.HashMap;
import java.util.Map;

public class FloydWarshalAPSP<V extends Comparable<V>> {

    public Map<V, HashMap<V, Double>> allPairsShortestPath(Graph<V> graph) {
        
        /*
         * Initialize the cost matrix
         */
        var costMatrix = new HashMap<V, HashMap<V, Double>>();
        var listOfVertices = graph.vertices();

        for (var v : listOfVertices) {
            //against every vertex 'v', add a Map object
            costMatrix.put(v.getData(), new HashMap<>());
            //Let 'costMapOfV' be the new Map object created in the above line
            var costMapOfV = costMatrix.get(v.getData());
            //For every vertex 'w', initialize the cost to reach vertex 'w' from vertex 'v'
            for (var w : listOfVertices) {
                var costToReachW = graph.getCost(v.getData(), w.getData());
                costMapOfV.put(
                        w.getData(),
                        costToReachW
                );
            }
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
                    var costToTravelFromIToK = costMatrix.get(i).get(k);
                    var costToTravelFromKToJ = costMatrix.get(k).get(j);
                    var costToTravelFromIToJ = costMatrix.get(i).get(j);
                    if (costToTravelFromIToK + costToTravelFromKToJ < costToTravelFromIToJ)
                        costMatrix.get(i).put(j, costToTravelFromIToK + costToTravelFromKToJ);
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
                    var costToTravelFromIToK = costMatrix.get(i).get(k);
                    var costToTravelFromKToJ = costMatrix.get(k).get(j);
                    var costToTravelFromIToJ = costMatrix.get(i).get(j);
                    if (costToTravelFromIToK + costToTravelFromKToJ < costToTravelFromIToJ)
                        costMatrix.get(i).put(j, Double.NEGATIVE_INFINITY);
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
