package net.robinsinghdevgan.dataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

    private class Itr implements Iterator<E> {
        private Node<E> lastReturned = null, next = null;
        private int nextIndex;

        Itr() {
            next = first == null ? null : getNode(0);
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return next == null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
        
    }
    private class ListIter implements ListIterator<E> {
        private Node<E> lastReturned = null, next = null;
        private int nextIndex;

        ListIter() {
            next = first == null ? null : getNode(0);
            nextIndex = 0;
        }

        ListIter(int index) {
            next = index >= size ? null : getNode(index);
            nextIndex = index;
        }

        @Override
        public void add(E e) {
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
        }

        @Override
        public boolean hasNext() {
            return next == null;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            //check if we're at the last element
            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.data;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
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

        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.data = e;
        }

    }

    private static class Node<E> {
        Node<E> prev, next;
        E data;

        Node(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> first = null, last = null;

    private int size = 0;
    
    @Override
    public boolean add(E e) {
        Node<E> newNode;
        try {
            newNode = new Node<E>(e);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        if (first == null){
            first = last = newNode;
        }else{
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of bounds");
        if(index == 0){
            linkFirst(element);
        }else if(index == size - 1){
            linkLast(element);
        }
        var iter = first;
        for(int i = 0; i < index; i++){
            iter = iter.next;
        }
        //iter at one node ahead to index
        linkBefore(element, iter);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        try {  
            for(E object : c){
                linkLast(object);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        try {
            for(E object : c){
                add(index++, object);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    @Override
    public void clear() {
        removeRange(0, size());
    }


    @Override
    public boolean contains(Object o) {
        @SuppressWarnings({ "unchecked" }) Node<E> n = (Node<E>) o;
        Node<E> iterator = first;
        while (iterator != null) {
            if (n == iterator)
                return true;
            iterator = iterator.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object object : c){
            if(!contains(object))
                return false;
        }
        return true;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        var iter = first;
        for(int i = 0; i < index; i++){
            iter = iter.next;
        }
        return iter.data;
    }

    Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public int indexOf(Object o) {
        var iter = first;
        for(int i = 0; i < size; i++){
            if(iter == (Node<E>) o)
                return i;
            iter = iter.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public int lastIndexOf(Object o) {
        var iter = last;
        for(int i = size-1; i >= 0; i--){
            if(iter == (Node<E>) o)
                return i;
            iter = iter.prev;
        }
        return -1;
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * Links e as last element.
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public E remove(int index) {
        var iter = first;
        for(int i = 0; i < index; i++){
            iter = iter.next;
        }
        var result = iter.data;
        unlink(iter);
        return result;
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public boolean remove(Object o) {
        var iter = first;
        for(int i = 0; i < size; i++){
            if(iter == (Node<E>) o){
                unlink(iter);
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object object : c){
            if(!remove(object))
                return false;
        }
        return true;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        ListIterator<E> it = listIterator(fromIndex);
        for (int i=0, n=toIndex-fromIndex; i<n; i++) {
            it.next();
            it.remove();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean listChanged = false;
        for (Object object : c) {
            if (this.contains(object) == false) {
                this.remove(object);
                listChanged = true;
            }
        }
        return listChanged;
    }

    @Override
    public E set(int index, E element) {
        Node<E> nodeToSet = getNode(index);
        E previousValue = nodeToSet.data;
        nodeToSet.data = element;
        return previousValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        DoublyLinkedList<E> newSubList = new DoublyLinkedList<E>();
        Node<E> start = this.getNode(fromIndex), end = this.getNode(toIndex);
        Node<E> iter = start;
        while (iter != end) {
            newSubList.add(iter.data);
        }
        return newSubList;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> iterator = first;
        while (iterator != null) {
            array[index++] = iterator.data;
            iterator = iterator.next;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
        a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
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
        while (iterator.next != null) {
            sb.append(iterator.data.toString() + ",");
            iterator = iterator.next;
        }
        sb.append(iterator.data.toString() + "}");
        return sb.toString();
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.data;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

}
