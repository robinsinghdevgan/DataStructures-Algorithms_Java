package net.robinsinghdevgan.dataStructures;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private Node first;

    private int size;
    private Node last;

    public SinglyLinkedList() {
        size = 0;
        first = last = null;
    }

    public boolean add(T data) {
        Node newElement;
        try {
            newElement = new Node(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        if (last != null) {
            last.next = newElement;
        } else {
            first = last = newElement;
        }
        return true;
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }

        @Override
        @SuppressWarnings({"unchecked"})
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node;
            try {
                node = (Node) o;
                return getData().equals(node.getData());
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(getData());
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
