package net.robinsinghdevgan.dataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph<E extends Comparable<E>> {

    private HashMap<E, Vertex<E>> vertices;
    private List<Edge<E>> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    // Returns object of added Vertex
    public Vertex<E> addVertex(E value) {
        if (vertices.containsKey(value))
            throw new IllegalStateException("Vertex with this value already exists");
        return vertices.put(value, new Vertex<E>(value));
    }

    public void addEdge(E srcVertex, E dstVertex, Double cost) {
        if (vertices.containsKey(dstVertex) && vertices.containsKey(srcVertex)) {
            Edge<E> edge = new Edge<E>(srcVertex, dstVertex, cost);
            edges.add(edge);
            vertices.get(srcVertex).getOutDegreeEdges().add(edge);
            vertices.get(dstVertex).getInDegreeEdges().add(edge);
        } else
            throw new IllegalArgumentException("Such a source or destination vertex does not exist");
    }

    // also removes all edges associated with this vertex
    public Vertex<E> removeVertex(E value) {
        removeEdges(value);
        return vertices.remove(value);
    }

    public void removeEdges(E value) throws IllegalArgumentException {
        if (vertices.containsKey(value)) {
            var outDegreeEdges = vertices.get(value).getOutDegreeEdges();
            var inDegreeEdges = vertices.get(value).getInDegreeEdges();
            for(var edge : outDegreeEdges) {
                edges.remove(edge);
            }
            for(var edge : inDegreeEdges) {
                edges.remove(edge);
            }
        }
        else
            throw new IllegalArgumentException("Such a vertex does not exist");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E v : vertices.keySet()) {
            String vertexName = "{ " + v.toString() + " }\n";
            sb.append(vertexName);
            for (var e : edges) {
                if (e.from == v) {
                    for (int i = 0, len = (vertexName.length() / 2); i < len; i++)
                        sb.append(" ");
                    sb.append("\\");
                    for (int i = 0, len = vertexName.length() / 2; i < len; i++)
                        sb.append("-");
                    sb.append(">" + e.toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

}
