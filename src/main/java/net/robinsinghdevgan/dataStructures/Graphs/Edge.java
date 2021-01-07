package net.robinsinghdevgan.dataStructures.Graphs;

public class Edge <VertexDataType extends Comparable<VertexDataType>>{
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