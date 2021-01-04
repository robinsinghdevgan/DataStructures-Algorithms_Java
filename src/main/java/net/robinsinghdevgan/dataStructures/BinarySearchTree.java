package net.robinsinghdevgan.dataStructures;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        Node left, right;
        E data;

        protected Node(E data) {
            this.data = data;
            left = right = null;
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

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        printBinaryTree(buffer, root, 0);
        buffer.append("\n\n");
        return buffer.toString();
    }

    public void printBinaryTree(StringBuilder sb, Node root, int level) {
        if (root == null)
            return;
        printBinaryTree(sb, root.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------" + root.data);
        } else
            sb.append(root.data);
        sb.append("\n");
        printBinaryTree(sb, root.left, level + 1);
    }

    // below line of code is for balancing the bst
    /*
     * This function traverse the skewed binary tree and stores its nodes pointers
     * in vector nodes[]
     */
    void storeBSTNodes(Node root, List<Node> nodes) {
        // Base case
        if (root == null)
            return;

        // Store nodes in Inorder (which is sorted
        // order for BST)
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }

    /* Recursive function to construct binary tree */
    Node buildTreeUtil(List<Node> nodes, int start, int end) {
        // base case
        if (start > end)
            return null;

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        /*
         * Using index in Inorder traversal, construct left and right subtress
         */
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);

        return node;
    }

    // This functions converts an unbalanced BST to
    // a balanced BST
    Node buildTree(Node root) {
        // Store nodes of given BST in sorted order
        List<Node> nodes = new ArrayList<Node>();
        storeBSTNodes(root, nodes);

        // Constucts BST from nodes[]
        int n = nodes.size();
        return buildTreeUtil(nodes, 0, n - 1);
    }

    public void balanceTheTree() {
        root = buildTree(root);
    }
}
