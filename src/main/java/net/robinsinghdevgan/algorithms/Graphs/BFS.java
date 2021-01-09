package net.robinsinghdevgan.algorithms.Graphs;

import java.util.HashSet;

import net.robinsinghdevgan.dataStructures.QueueUsingSinglyLinkedList;
import net.robinsinghdevgan.dataStructures.Graphs.Graph;

public class BFS<E extends Comparable<E>> {
    // returns cost to search target from source
    public Double search(Graph<E> graph, E source, E target) {
        if (source == target)
            return 0.0;

        var visited = new HashSet<E>();

        var q = new QueueUsingSinglyLinkedList<E>();
        var costQ = new QueueUsingSinglyLinkedList<Double>();
        costQ.offer(0.0);
        q.offer(source);

        Double cost = 0.0;
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        while (!q.isEmpty()) {
            System.out.println(q.toString());

            cost += costQ.poll();

            E vertexValue = q.poll();
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
                costQ.offer(e.getCost());
                q.offer(e.getTo());
            }
        }
        System.out.println(sb.toString());
        return found ? cost : Double.NEGATIVE_INFINITY;
    }
}
