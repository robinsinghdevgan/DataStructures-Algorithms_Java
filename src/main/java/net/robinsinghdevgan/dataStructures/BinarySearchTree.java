package net.robinsinghdevgan.dataStructures;

public class BinarySearchTree<E extends Comparable<E>> {
    private int nodeCount = 0;
    private Node<E> root = null;

    private class Node<E> {
        Node<E> left, right;
        E data;

        protected Node(Node<E> left, E data, Node<E> right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }

        protected Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    private void inOrderTraversalHelper(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversalHelper(node.left, sb);
            sb.append(node.data + ",");
            inOrderTraversalHelper(node.right, sb);
        }
    }

    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        inOrderTraversalHelper(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public boolean add(E data) {
        var newNode = new Node<E>(data);
        ++nodeCount;
        if (root == null) {
            root = newNode;
            return true;
        } else {
            var iter = root;
            while (iter != null) {
                int compare = iter.data.compareTo(data);
                if (compare > 0) {
                    if (iter.left == null) {
                        iter.left = newNode;
                        return true;
                    }
                    // go left
                    iter = iter.left;
                } else {
                    if (iter.right == null) {
                        iter.right = newNode;
                        return true;
                    }
                    iter = iter.right;
                }
            }
        }
        --nodeCount;
        return false;
    }

    // Computes the height of the tree, O(n)
    public int height() {
        return height(root);
    }

    // Recursive helper method to compute the height of the tree
    private int height(Node<E> node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // Check if this binary tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Get the number of nodes in this binary tree
    public int size() {
        return nodeCount;
    }

    // Helper method to find the leftmost node (which has the smallest value)
    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }
}
