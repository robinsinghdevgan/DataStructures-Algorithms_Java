package net.robinsinghdevgan.dataStructures;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private class Node<T>{
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return getData().equals(node.getData());
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

    private int size;
    private Node<T> first;
    private Node<T> last;

    public SinglyLinkedList() {
        size = 0;
        first = last = null;
    }


}
