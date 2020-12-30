package net.robinsinghdevgan.dataStructures;

import java.util.*;

public class SinglyLinkedList<E> implements List<E> {
    private static class Node<E> implements Iterator<Node<E>> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        @SuppressWarnings({ "unchecked" })
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Node<E> node;
            try {
                node = (Node<E>) o;
                return getData().equals(node.getData());
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getData());
        }

        @Override
        public boolean hasNext() {
            return getNext() != null;
        }

        public Iterator<Node<E>> iterator() {
            return this;
        }

        @Override
        public Node<E> next() {
            return next;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + ", next=" + next + '}';
        }
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned = null;
        private Node<E> next;
        private int nextIndex;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : getNode(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }

        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.data = e;
        }

        public void add(E e) {
            Node<E> newElement;
            try {
                newElement = new Node<E>(e);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            if (last != null) {
                last.setNext(newElement);
                last = newElement;
            } else {
                first = last = newElement;
            }
            ++size;
            nextIndex++;
        }

        @Override
        public boolean hasPrevious() {
            if (lastReturned == first)
                return false;
            return true;
        }

        @Override
        public E previous() {
            if (lastReturned == first)
                return null;
            var prev = first;
            while (prev.next != lastReturned) {
                prev = prev.next;
            }
            lastReturned = prev;
            return prev.data;
        }
    }

    private Node<E> first, last;

    private int size;

    public SinglyLinkedList() {
        size = 0;
        first = last = null;
    }

    @Override
    public void add(int i, E t) {
        var oldNode = this.getNode(i);
        var nextOfOldNode = oldNode.next();
        Node<E> newNode = new Node<E>(t, nextOfOldNode);
        oldNode.setNext(newNode);
    }

    @Override
    public boolean add(E data) {
        Node<E> newElement;
        try {
            newElement = new Node<E>(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        if (last != null) {
            last.setNext(newElement);
            last = newElement;
        } else {
            first = last = newElement;
        }
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.size() == 0)
            return false;
        var iterOfCollection = collection.iterator();
        while (iterOfCollection.hasNext()) {
            E data = (E) iterOfCollection.next();
            this.add(data);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        SinglyLinkedList<E> newList = new SinglyLinkedList<E>();
        newList.addAll(collection);
        Node<E> ithNode = getNode(i);
        Node<E> nextOfIthNode = ithNode.getNext();
        ithNode.setNext(newList.getFirst());
        newList.getLast().setNext(nextOfIthNode);
        return false;
    }

    @Override
    public void clear() {
        Node<E> iterator = first, prev = null;
        while (iterator != null) {
            prev = iterator;
            iterator = iterator.getNext();
            if (prev != null) {
                prev = null;
            }
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings({ "unchecked" }) Node<E> n = (Node<E>) o;
        Node<E> iterator = first;
        while (iterator != null) {
            if (n == iterator)
                return true;
            iterator = iterator.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (var item : collection) {
            if (!this.contains(item))
                return false;
        }
        return true;
    }

    @Override
    public E get(int i) {
        if (size() <= i) {
            throw new IndexOutOfBoundsException("Supplied index is out of bounds.");
        }
        int index = 0;
        Node<E> iterator = first;
        E res = null;
        while (iterator != null) {
            if (index++ == i)
                return iterator.data;
            iterator = iterator.next();
        }
        return res;
    }

    private Node<E> getFirst() {
        return first;
    }

    private Node<E> getLast() {
        return last;
    }

    public Node<E> getNode(int i) throws IndexOutOfBoundsException {
        if (size() <= i) {
            throw new IndexOutOfBoundsException("Supplied index is out of bounds.");
        }
        int index = 0;
        Node<E> iterator = first;
        Node<E> res = null;
        while (iterator != null) {
            if (index++ == i) {
                res = iterator;
                break;
            }
            iterator = iterator.next();
        }
        return res;
    }

    @Override
    public int indexOf(Object o) {
        @SuppressWarnings({ "unchecked" })
        Node<E> n = (Node<E>) o;
        int idx = 0;
        Node<E> iterator = first;
        boolean found = false;
        while (iterator != null) {
            if (n == iterator) {
                found = true;
                break;
            }
            iterator = iterator.getNext();
            ++idx;
        }
        return found ? idx : -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListItr(0);
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public int lastIndexOf(Object o) {
        var n = first;
        int i = 0, res = -1;
        while (n != null) {
            if (n == (Node<E>) o)
                res = i;
            ++i;
        }
        return res;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new ListItr(i);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        if (i == 0) {
            if (first != null && first.hasNext()) {
                E currValue = first.getData();
                first = first.getNext();
                return currValue;
            } else
                return null;
        } else if (i == size() - 1) {
            Node<E> iter = first;
            while (iter.hasNext()) {
                iter.iterator().next();
            }
            E currValue = last.getData();
            last = null;
            last = iter;
            return currValue;
        }
        Node<E> prevEoNodeEoRemove = getNode(i - 1);
        Node<E> nodeEoRemove = prevEoNodeEoRemove.next();
        E currValue = nodeEoRemove.getData();
        prevEoNodeEoRemove.setNext(nodeEoRemove.next());
        nodeEoRemove = null;
        return currValue;
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public boolean remove(Object o) {
        Node<E> n;
        try {
            n = (Node<E>) o;
            Node<E> iterator = first;
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
    public boolean removeAll(Collection<?> collection) {
        for (Object object : collection) {
            if (this.remove(object) == false)
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean listChanged = false;
        for (Object object : collection) {
            if (this.contains(object) == false) {
                this.remove(object);
                listChanged = true;
            }
        }
        return listChanged;
    }

    @Override
    public E set(int i, E t) throws IndexOutOfBoundsException {
        var oldNode = this.getNode(i);
        E oldData = oldNode.getData();
        oldNode.setData(t);
        return oldData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int i, int i1) throws IndexOutOfBoundsException {
        SinglyLinkedList<E> newList = new SinglyLinkedList<E>();
        Node<E> start = this.getNode(i), end = this.getNode(i1);
        Node<E> iter = start;
        while (iter != end) {
            newList.add(iter.getData());
        }
        return newList;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> iterator = first;
        while (iterator != null) {
            array[index++] = iterator.getData();
            iterator = iterator.getNext();
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.data;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Node<E> iterator = first;
        while (iterator.next() != null) {
            sb.append(iterator.getData().toString() + ",");
            iterator = iterator.next();
        }
        sb.append(iterator.getData().toString() + "}");
        return sb.toString();
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(Node<E> x) {

        final E element = x.data;
        final Node<E> next = x.next;

        if (x == first) {
            first = first.next;
            x = null;
            size--;
            return element;
        }

        var iter = first;

        while (iter.next != x) {
            iter = iter.next();
        }
        iter.setNext(next);
        x.data = null;
        size--;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    E unlinkLast() {
        return unlink(last);
    }
}
