package net.robinsinghdevgan.dataStructures;

public class QueueUsingArray<E> implements Queue<E> {
    private int front, rear;
    private Object[] array;

    public QueueUsingArray(int capacity) {
        array = new Object[capacity + 1];
        front = 0;
        rear = 0;
    }

    @Deprecated
    public QueueUsingArray() {
        final int qsize = 100;
        array = new Object[qsize];
        front = 0;
        rear = 0;
    }

    @Override
    public void offer(E elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        array[rear++] = elem;
        rear = adjustIndex(rear, array.length);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, array.length);
        return (E) array[front++];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, array.length);
        return (E) array[front];
    }

    @Override
    public int size() {
        return adjustIndex(rear + array.length - front, array.length);
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (front + array.length - rear) % array.length == 1;
    }

    private int adjustIndex(int index, int size) {
        return index >= size
                ? index - size // go around the array
                : index; // return the index, no going around the array required
    }
}
