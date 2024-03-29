package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import net.robinsinghdevgan.data_structures.StackUsingSinglyLinkedList;

import java.util.HashSet;

@Slf4j
public class DFS<E extends Comparable<E>> {

    // returns cost to search target from source
    public Double search(Graph<E> graph, E source, E target) {
        if (source == target) return 0.0;

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
            log.info(String.valueOf(stackVertex));

            cost += stackCost.pop();

            E vertexValue = stackVertex.pop();
            if (visited.contains(vertexValue)) continue;
            visited.add(vertexValue);

            if (vertexValue == target) {
                sb.append(target).append("\n");
                found = true;
                break;
            }
            var v = graph.getVertex(vertexValue);
            sb.append(vertexValue).append(" -> ");

            for (var e : v.getOutDegreeEdges()) {
                stackCost.push(e.getCost());
                stackVertex.push(e.getTo());
            }
        }
        log.info(String.valueOf(sb));
        return found ? cost : Double.NEGATIVE_INFINITY;
    }
}
