package net.robinsinghdevgan.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Graph<VertexDataType extends Comparable<VertexDataType>> {

    private class Edge {
        VertexDataType from, to;
        Double cost;

        public Edge(VertexDataType from, VertexDataType to, Double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{To: " + to + ", Cost: " + cost + "}";
        }
    }

    private List<VertexDataType> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<VertexDataType>();
        edges = new ArrayList<Edge>();
    }

    // Returns index of added Vertex
    public int addVertex(VertexDataType value) {
        if (vertices.contains(value))
            throw new IllegalStateException("Vertex with this value already exists");
        if (vertices.add(value))
            return vertices.size() - 1;
        return -1;
    }

    public void addEdge(int srcIndex, int dstIndex, Double cost) {
        if (srcIndex < 0 || dstIndex < 0 || srcIndex >= vertices.size() || dstIndex >= vertices.size())
            throw new IllegalArgumentException("Supplied Source Index or Destination Index does not exist");
        VertexDataType sourceNode = vertices.get(srcIndex);
        VertexDataType destinationNode = vertices.get(dstIndex);
        edges.add(new Edge(sourceNode, destinationNode, cost));
    }

    // Returns index of Vertex with given VertexDataType
    public int getIndex(VertexDataType value) {
        return vertices.indexOf(value);
    }

    public VertexDataType get(int index) {
        return vertices.get(index);
    }

    // also removes all edges associated with this vertex
    public boolean removeUsingVertexName(VertexDataType valueOfNode) {
        return vertices.remove(valueOfNode) && removeEdges(valueOfNode);
    }

    private boolean removeEdges(VertexDataType valueOfNode)
            throws UnsupportedOperationException, IndexOutOfBoundsException {
        List<Integer> listOfEdgesToRemove = new ArrayList<Integer>();
        for (int i = 0; i < edges.size(); i++) {
            Edge VertexDataType = edges.get(i);
            if (VertexDataType.from == valueOfNode || VertexDataType.to == valueOfNode) {
                listOfEdgesToRemove.add(i);
            }
        }
        int reducedSize = 0;
        for (int i : listOfEdgesToRemove) {
            edges.remove(i - reducedSize++);
        }
        return true;
    }

    public boolean removeUsingVertexIndex(int index) throws UnsupportedOperationException, IndexOutOfBoundsException {
        VertexDataType value = vertices.get(index);
        vertices.remove(index);
        return removeEdges(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (VertexDataType v : vertices) {
            String vertexName = "{ " + v.toString() + " }\n";
            sb.append(vertexName);
            for (Edge VertexDataType : edges) {
                if (VertexDataType.from == v) {
                    for (int i = 0, len = (vertexName.length() / 2); i < len; i++)
                        sb.append(" ");
                    sb.append("\\");
                    for (int i = 0, len = vertexName.length() / 2; i < len; i++)
                        sb.append("-");
                    sb.append(">" + VertexDataType.toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

}
