package net.robinsinghdevgan.dataStructures;

/**
 * @author Robin Singh Devgan
 * @param <E> the type of element held int the queue
 */
public interface Queue<E> {
    public void offer(E elem);

    public E poll();

    public E peek();

    public int size();

    public boolean isEmpty();
}