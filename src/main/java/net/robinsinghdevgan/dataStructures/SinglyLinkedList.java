package net.robinsinghdevgan.dataStructures;

import java.util.*;

public class SinglyLinkedList<T> implements List<T> {
    private Node first;

    private int size;
    private Node last;

    public SinglyLinkedList() {
        size = 0;
        first = last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node n = (Node) o;
        Node iterator = first;
        while (iterator.hasNext()) {
            if (n == iterator)
                return true;
            iterator = iterator.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node iterator = first;
        while (iterator.hasNext()) {
            array[index++] = iterator.getData();
            iterator = iterator.getNext();
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int index = 0;
        Node iterator = first;
        while (iterator.hasNext()) {
            array[index++] = (T) iterator.getData();
            iterator = iterator.next();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node iterator = first;
        while (iterator.hasNext()) {
            sb.append(iterator.getData().toString() + " => ");
            iterator = iterator.next();
        }
        return sb.toString();
    }

    @Override
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
            last = newElement;
        } else {
            first = last = newElement;
        }
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node n;
        try {
            n = (Node) o;
            Node iterator = first;
            while (iterator.hasNext() && iterator.next() != n) {
                iterator = iterator.getNext();
            }
            if (iterator().hasNext())
                iterator.setNext(iterator.getNext().getNext());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int i) {
        if(size() <= i) {
            throw new IndexOutOfBoundsException("Supplied index is out of bounds.");
        }
        int index = 0;
        Node iterator = first;
        T res = null;
        while (iterator != null) {
            if(index++ == i)
                res = iterator.data;
            iterator = iterator.next();
        }
        return res;
    }

    @Override
    public T set(int i, T t) {
        return null;
    }

    @Override
    public void add(int i, T t) {

    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }

    private class Node implements Iterator<Node> {
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

        @Override
        public boolean hasNext() {
            return getNext() != null;
        }

        @Override
        public Node next() {
            return next;
        }
    }

}
