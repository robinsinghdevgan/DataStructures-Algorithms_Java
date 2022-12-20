package net.robinsinghdevgan.algorithms.graphs;

import net.robinsinghdevgan.data_structures.graphs.Edge;
import net.robinsinghdevgan.data_structures.graphs.Graph;

import java.util.*;

public class Kruskal<T extends Comparable<T>> {

    HashMap<T, Integer> indexMap;
    HashSet<Edge<T>> mstEdges;

    // and returns cost of MST
    public Double getMSP(Graph<T> graph) {
        indexMap = new HashMap<>();
        mstEdges = new HashSet<>();

        int i = 0;
        for (var v : graph.vertices()) indexMap.put(v.getData(), i++);
        var mstCost = getMSPHelper(graph.getEdges(), graph.numberOfVertices());

        var edges = graph.getEdges();
        for (i = 0; i < edges.size(); i++) {
            if (!mstEdges.contains(edges.get(i))) graph.removeEdge(edges.get(i));
        }

        System.out.println("MST Cost: " + mstCost);
        for (var e : mstEdges) {
            System.out.println("MST Edge: " + e);
        }

        return mstCost;
    }

    private Double getMSPHelper(List<Edge<T>> edges, int V) {
        if (edges == null) return null;

        Double sum = 0.0;
        edges.sort(new EdgeComparator());

        UnionFind uf = new UnionFind(V);

        for (var edge : edges) {
            // Skip this edge getTo() avoid creating a cycle in MST
            if (
                    uf.connected(indexMap.get(edge.getFrom()), indexMap.get(edge.getTo()))
            ) continue;

            // Include this edge
            uf.union(indexMap.get(edge.getFrom()), indexMap.get(edge.getTo()));
            sum += edge.getCost();
            mstEdges.add(edge);

            // Optimization getTo() stop early if we found
            // a MST that includes all the nodes
            if (uf.size(0) == V) break;
        }

        // Make sure we have a MST that includes all the nodes
        if (uf.size(0) != V) return null;

        return sum;
    }

    // Union find data structure
    static class UnionFind {
        private final int[] id;
        private final int[] sz;

        public UnionFind(int n) {
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int p) {
            int root = p;
            while (root != id[root]) root = id[root];
            while (p != root) { // Do path compression
                int next = id[p];
                id[p] = root;
                p = next;
            }
            return root;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int size(int p) {
            return sz[find(p)];
        }

        public void union(int p, int q) {
            int root1 = find(p);
            int root2 = find(q);
            if (root1 == root2) return;
            if (sz[root1] < sz[root2]) {
                sz[root2] += sz[root1];
                id[root1] = root2;
            } else {
                sz[root1] += sz[root2];
                id[root2] = root1;
            }
        }
    }

    class EdgeComparator implements Comparator<Edge<T>> {

        @Override
        public int compare(Edge<T> o1, Edge<T> o2) {
            return o1.getCost().compareTo(o2.getCost());
        }
    }
}
