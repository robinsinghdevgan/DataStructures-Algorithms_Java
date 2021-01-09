package net.robinsinghdevgan.algorithms.Graphs;

import java.util.HashSet;

import net.robinsinghdevgan.dataStructures.StackUsingSinglyLinkedList;
import net.robinsinghdevgan.dataStructures.Graphs.Graph;

public class DFS<E extends Comparable<E>> {
    // returns cost to search target from source
    public Double search(Graph<E> graph, E source, E target) {
        if (source == target)
            return 0.0;

        var visited = new HashSet<E>();

        var stackVertex = new StackUsingSinglyLinkedList<E>();
        var stackCost = new StackUsingSinglyLinkedList<Double>();
        stackCost.push(0.0);
        stackVertex.push(source);

        Double cost = 0.0;
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        while (!stackVertex.isEmpty()) {
            System.out.println(stackVertex.toString());

            cost += stackCost.pop();

            E vertexValue = stackVertex.pop();
            if (visited.contains(vertexValue))
                continue;
            visited.add(vertexValue);

            if (vertexValue == target) {
                sb.append(target + "\n");
                found = true;
                break;
            }
            var v = graph.getVertex(vertexValue);
            sb.append(vertexValue + " -> ");

            for (var e : v.getOutDegreeEdges()) {
                stackCost.push(e.getCost());
                stackVertex.push(e.getTo());
            }
        }
        System.out.println(sb.toString());
        return found ? cost : Double.NEGATIVE_INFINITY;
    }
}
