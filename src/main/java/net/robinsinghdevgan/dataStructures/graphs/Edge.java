package net.robinsinghdevgan.dataStructures.graphs;

public class Edge<Vertex extends Comparable<Vertex>> {
    Vertex from, to;
    Double cost;

    public Edge(Vertex from, Vertex to, Double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{From: " + from + ", To: " + to + ", Cost: " + cost + "}";
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        var other = (Edge<Vertex>) obj;
        if (cost == null) {
            if (other.cost != null) return false;
        } else if (!cost.equals(other.cost)) return false;
        if (from == null) {
            if (other.from != null) return false;
        } else if (!from.equals(other.from)) return false;
        if (to == null) {
          return other.to == null;
        } else return to.equals(other.to);
    }

    @Override
    protected Edge<Vertex> clone() throws CloneNotSupportedException {
        Edge<Vertex> vertexEdge = (Edge<Vertex>) super.clone();
        return new Edge<>(this.from, this.to, this.cost);
    }
}
