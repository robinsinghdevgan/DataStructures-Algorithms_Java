package net.robinsinghdevgan.dataStructures;

import java.util.EmptyStackException;

public class StackUsingArray<E> {
    private int size = 0;
    private int top = -1;
    private Object[] array = null;

    public StackUsingArray(int size) {
        this.size = size;
        array = new Object[size];
    }

    public StackUsingArray() {
        size = 15;
        array = new Object[size];
    }

    public E push(E item) throws IndexOutOfBoundsException{
        try{
            array[++top] = item;
        }catch (IndexOutOfBoundsException ex){
            throw new StackOverflowError(ex.toString());
        }
        return item;
    }

    public boolean isFull() {
        return top == size-1;
    }

    public E pop() {
        if(this.isEmpty())
            throw new EmptyStackException();
        return element(top--);
    }

    public E peek() {
        if(this.isEmpty())
            throw new EmptyStackException();
        return element(top);
    }

    // Safe because push(T) is type checked.
    @SuppressWarnings("unchecked")
    private E element(int index) {
        return (E)array[index];
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
