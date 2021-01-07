package net.robinsinghdevgan.dataStructures.Graphs;

public class Edge <E extends Comparable<E>>{
    E from, to;
    Double cost;

    public Edge(E from, E to, Double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{To: " + to + ", Cost: " + cost + "}";
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
}