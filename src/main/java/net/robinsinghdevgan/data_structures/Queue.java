package net.robinsinghdevgan.data_structures;

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
