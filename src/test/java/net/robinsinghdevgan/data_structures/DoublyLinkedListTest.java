package net.robinsinghdevgan.data_structures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoublyLinkedListTest {
    final int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

    @Test
    public void testToString() {
        DoublyLinkedList<Integer> sll;
        sll = testCollectionToList();
        System.out.println(sll.toString());
    }

    @org.junit.jupiter.api.Test
    DoublyLinkedList<Integer> testCollectionToList() {
        DoublyLinkedList<Integer> sll;
        sll = new DoublyLinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        assertTrue(sll.addAll(list));
        return sll;
    }

    @Test
    public void removeItem() {
        DoublyLinkedList<Integer> sll;
        sll = testCollectionToList();
        sll.remove(0);
        assertEquals("{1,2,3,4,5,6,7}", sll.toString());
        sll.remove(6);
        assertEquals("{1,2,3,4,5,6}", sll.toString());
        sll.remove(3);
        assertEquals("{1,2,3,5,6}", sll.toString());
    }

    @Test
    public void getItem() {
        final DoublyLinkedList<Integer> sll = testCollectionToList();
        for (int i = 0; i < 8; i++) {
            System.out.println("i: " + i + " -> " + sll.get(i));
            assertEquals(i, sll.get(i));
        }
        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> sll.get(100)
        );
        System.out.println(exception.getMessage());
    }
}
