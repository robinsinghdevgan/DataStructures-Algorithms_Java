package net.robinsinghdevgan.dataStructures.Graphs;

public class Edge<E extends Comparable<E>> {
    E from, to;
    Double cost;

    public Edge(E from, E to, Double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{From: " + from + ", To: " + to + ", Cost: " + cost + "}";
    }

    public E getFrom() {
        return from;
    }

    public void setFrom(E from) {
        this.from = from;
    }

    public E getTo() {
        return to;
    }

    public void setTo(E to) {
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        var other = (Edge<E>) obj;
        if (cost == null) {
            if (other.cost != null)
                return false;
        } else if (!cost.equals(other.cost))
            return false;
        if (from == null) {
            if (other.from != null)
                return false;
        } else if (!from.equals(other.from))
            return false;
        if (to == null) {
            if (other.to != null)
                return false;
        } else if (!to.equals(other.to))
            return false;
        return true;
    }

    @Override
    protected Edge<E> clone() throws CloneNotSupportedException {
        return new Edge<E>(this.from, this.to, this.cost);
    }
}