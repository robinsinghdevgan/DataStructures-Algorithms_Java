package net.robinsinghdevgan.dataStructures.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Graph<E extends Comparable<E>> {
    private final HashMap<E, Vertex<E>> vertices;
    private final List<Edge<E>> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    // Returns object of added Vertex
    public void addVertex(E value) {
        if (vertices.containsKey(value)) throw new IllegalStateException(
                "Vertex with this value already exists"
        );
        vertices.put(value, new Vertex<>(value));
    }

    public void addEdge(E srcVertex, E dstVertex, Double cost) {
        if (vertices.containsKey(dstVertex) && vertices.containsKey(srcVertex)) {
            Edge<E> edge = new Edge<>(srcVertex, dstVertex, cost);
            edges.add(edge);
            vertices.get(srcVertex).getOutDegreeEdges().add(edge);
            vertices.get(dstVertex).getInDegreeEdges().add(edge);
        } else throw new IllegalArgumentException(
                "Such a source or destination vertex does not exist"
        );
    }

    // also removes all edges associated with this vertex
    public void removeVertex(E value) {
        removeEdgesTo(value);
        removeEdgesFrom(value);
        vertices.remove(value);
    }

    public void removeEdgesTo(E value) throws IllegalArgumentException {
        if (vertices.containsKey(value)) {
            for (var entry : vertices.entrySet()) {
                var inEdges = entry.getValue().getInDegreeEdges();
                for (int i = 0; i < inEdges.size(); i++) {
                    if (inEdges.get(i).getTo() == value) {
                        this.edges.remove(inEdges.get(i));
                        inEdges.remove(i);
                    }
                }
            }
        } else throw new IllegalArgumentException("Such a vertex does not exist");
    }

    public void removeEdgesFrom(E value) throws IllegalArgumentException {
        if (vertices.containsKey(value)) {
            for (var entry : vertices.entrySet()) {
                var outEdges = entry.getValue().getOutDegreeEdges();
                for (int i = 0; i < outEdges.size(); i++) {
                    if (outEdges.get(i).getFrom() == value) {
                        this.edges.remove(outEdges.get(i));
                        outEdges.remove(i);
                    }
                }
            }
        } else throw new IllegalArgumentException("Such a vertex does not exist");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var v : vertices.entrySet()) {
            String vertexName = "{ " + v.getKey().toString() + " }\n";
            sb.append(vertexName);
            List<Edge<E>> edgesOfThisVertex = v.getValue().getOutDegreeEdges();
            for (var e : edgesOfThisVertex) {
                for (int i = 0, len = (vertexName.length() / 2); i < len; i++) sb.append(" ");
                sb.append("\\");
                for (int i = 0, len = vertexName.length() / 2; i < len; i++) sb.append("-");
                sb.append(">").append(e.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    public Collection<Vertex<E>> vertices() {
        return vertices.values();
    }

    public Vertex<E> getVertex(E vertexValue) {
        return vertices.get(vertexValue);
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public Double getCost(E source, E destination) {
        if (source == destination) return 0.0;
        var src = vertices.get(source);
        for (var e : src.getOutDegreeEdges()) {
            if (e.getTo() == destination) return e.getCost();
        }
        return Double.POSITIVE_INFINITY;
    }

    public int numberOfEdges() {
        return edges.size();
    }

    public void removeEdge(Edge<E> edgeToRemove) {
        for (var v : vertices.entrySet()) {
            List<Edge<E>> edgesOfThisVertex = v.getValue().getOutDegreeEdges();
            List<Integer> removeIndices = new ArrayList<>();
            for (int i = 0; i < edgesOfThisVertex.size(); i++) {
                if (edgesOfThisVertex.get(i).equals(edgeToRemove)) {
                    removeIndices.add(i);
                }
            }
            for (var i : removeIndices) edgesOfThisVertex.remove(i.intValue());
        }
        List<Integer> removeIndices = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).equals(edgeToRemove)) removeIndices.add(i);
        }
        for (var i : removeIndices) edges.remove(i.intValue());
    }
}
