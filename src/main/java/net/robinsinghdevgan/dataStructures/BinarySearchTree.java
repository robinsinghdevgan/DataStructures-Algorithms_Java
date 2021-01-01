package net.robinsinghdevgan.dataStructures;

public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        Node left, right;
        E data;

        protected Node(E data) {
            this.data = data;
            left = right = null;
        }

        protected Node(Node left, E data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }
    }

    private int nodeCount = 0;

    private Node root = null;

    public boolean add(E data) {
        var newNode = new Node(data);
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

    // returns true is the element exists in the tree
    public boolean contains(E elem) {
        return contains(root, elem);
    }

    // private recursive method to find an element in the tree
    private boolean contains(Node node, E elem) {

        // Base case: reached bottom, value not found
        if (node == null)
            return false;

        int cmp = elem.compareTo(node.data);

        // Dig into the left subtree because the value we're
        // looking for is smaller than the current value
        if (cmp < 0)
            return contains(node.left, elem);

        // Dig into the right subtree because the value we're
        // looking for is greater than the current value
        else if (cmp > 0)
            return contains(node.right, elem);

        // We found the value we were looking for
        else
            return true;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private Node findMax(Node node) {
        if (node == null)
            return null;
        while (node.right != null)
            node = node.right;
        return node;
    }

    // Helper method to find the leftmost node (which has the smallest value)
    private Node findMin(Node node) {
        if (node == null)
            return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Computes the height of the tree, O(n)
    public int height() {
        return height(root);
    }

    // Recursive helper method to compute the height of the tree
    private int height(Node node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        inOrderTraversalHelper(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    private void inOrderTraversalHelper(Node node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversalHelper(node.left, sb);
            sb.append(node.data + ",");
            inOrderTraversalHelper(node.right, sb);
        }
    }

    // Check if this binary tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Remove the node with supplied data
    public boolean remove(E elem) {
        // Make sure the node we want to remove
        // actually exists before we remove it
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    // Helper for remove(E elem)
    private Node remove(Node node, E elem) {
        if (node == null)
            return null;
        if (node.data.equals(elem)) {
            // This is the case with only a right subtree or
            // no subtree at all. In this situation just
            // swap the node we wish to remove with its right child.
            if (node.left == null) {
                return node.right;
            }
            // This is the case with only a left subtree or
            // no subtree at all. In this situation just
            // swap the node we wish to remove with its left child.
            else if (node.right == null) {
                return node.left;
            } else {
                var maxNodeInLeftSubTree = findMax(node.left); // max in left tree is going to be the new root
                node.data = maxNodeInLeftSubTree.data;
                node.left = remove(node.left, elem);
            }
        } else if (root.data.compareTo(elem) > 0)

        {
            node.left = remove(node.left, elem);
        } else {
            node.right = remove(node.right, elem);
        }
        return node;
    }

    // Get the number of nodes in this binary tree
    public int size() {
        return nodeCount;
    }
}
