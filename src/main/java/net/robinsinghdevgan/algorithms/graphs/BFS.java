package net.robinsinghdevgan.algorithms.graphs;

import lombok.extern.slf4j.Slf4j;
import net.robinsinghdevgan.data_structures.graphs.Graph;
import net.robinsinghdevgan.data_structures.QueueUsingSinglyLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

//@Slf4j
@Slf4j
public class BFS<V extends Comparable<V>> {
    //private static final Logger log = LogManager.getLogger("BFS");

    // returns cost to search target from source
    public Double search(Graph<V> graph, V source, V target) {
        if (source == target) return 0.0;

        //keep track of all the vertices visited
        var visited = new HashSet<V>();

        //Use queue to go the next vertex, and track the cost.
        var q = new QueueUsingSinglyLinkedList<V>();
        var costQ = new QueueUsingSinglyLinkedList<Double>();
        costQ.offer(0.0);
        q.offer(source);

        Double cost = 0.0;
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        while (!q.isEmpty()) {
            log.debug(String.valueOf(q));

            cost += costQ.poll();

            V vertexValue = q.poll();

            //if already visited this vertex on the path, skip this vertex
            if (visited.contains(vertexValue)) continue;

            //keep track of the vertices visited
            visited.add(vertexValue);

            sb.append(vertexValue).append(" -> ");

            if (vertexValue == target) {
                sb.append(target).append("\n");
                found = true;
                break;
            }
            var v = graph.getVertex(vertexValue);

            //Add every vertex reachable from this vertex to the queue
            for (var e : v.getOutDegreeEdges()) {
                costQ.offer(e.getCost());
                q.offer(e.getTo());
            }
        }
        log.debug(String.valueOf(sb));
        return found ? cost : Double.NEGATIVE_INFINITY;
    }
}
