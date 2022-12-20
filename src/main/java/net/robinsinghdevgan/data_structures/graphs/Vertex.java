package net.robinsinghdevgan.data_structures.graphs;

import java.util.ArrayList;
import java.util.List;

public class Vertex<E extends Comparable<E>> {
    private E data;
    private List<Edge<E>> inDegree, outDegree;

    Vertex(E data) {
        this.data = data;
        inDegree = new ArrayList<>();
        outDegree = new ArrayList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<Edge<E>> getInDegreeEdges() {
        return inDegree;
    }

    public void setInDegree(List<Edge<E>> inDegree) {
        this.inDegree = inDegree;
    }

    public List<Edge<E>> getOutDegreeEdges() {
        return outDegree;
    }

    public void setOutDegree(List<Edge<E>> outDegree) {
        this.outDegree = outDegree;
    }
}
