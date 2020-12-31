package net.robinsinghdevgan.dataStructures;

import java.util.EmptyStackException;

public class StackUsingSinglyLinkedList<E> {
    private int size = 0;
    private int top = -1;
    private SinglyLinkedList<E> list = null;

    public StackUsingSinglyLinkedList() {
        list = new SinglyLinkedList<E>();
    }

    public E push(E item) throws StackOverflowError{
        list.add(item);
        ++top;
        ++size;
        return item;
    }

    public boolean isFull() {
        return top == size-1;
    }

    public E pop() {
        if(this.isEmpty())
            throw new EmptyStackException();
        --top;
        --size;
        return list.unlinkLast();
    }

    public E peek() {
        if(this.isEmpty())
            throw new EmptyStackException();
        return list.getLast();
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
    
}
