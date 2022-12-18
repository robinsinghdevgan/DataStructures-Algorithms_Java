package net.robinsinghdevgan.dataStructures;

/**
 * @param <E> the type of element held int the queue
 * @author Robin Singh Devgan
 */
public interface Queue<E> {
    void offer(E elem);

    E poll();

    E peek();

    int size();

    boolean isEmpty();
}
