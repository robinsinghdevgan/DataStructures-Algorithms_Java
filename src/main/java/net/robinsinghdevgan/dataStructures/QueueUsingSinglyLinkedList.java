package net.robinsinghdevgan.dataStructures;

public class QueueUsingSinglyLinkedList<E> implements Queue<E> {
    private final SinglyLinkedList<E> list;

    public QueueUsingSinglyLinkedList() {
        list = new SinglyLinkedList<>();
    }

    @Override
    public void offer(E elem) {
        list.add(elem);
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.unlinkFirst();
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.getFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
