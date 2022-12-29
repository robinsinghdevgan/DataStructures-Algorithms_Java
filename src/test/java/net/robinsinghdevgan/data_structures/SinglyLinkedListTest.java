package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class SinglyLinkedListTest {
    final int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

    @Test
    void testToString() {
        SinglyLinkedList<Integer> sll;
        sll = testCollectionToList();
        log.info(String.valueOf(sll.toString()));
    }

    SinglyLinkedList<Integer> testCollectionToList() {
        SinglyLinkedList<Integer> sll;
        sll = new SinglyLinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        assertTrue(sll.addAll(list));
        return sll;
    }

    @Test
    void removeItem() {
        SinglyLinkedList<Integer> sll;
        sll = testCollectionToList();
        sll.remove(0);
        assertEquals("{1,2,3,4,5,6,7}", sll.toString());
        sll.remove(6);
        assertEquals("{1,2,3,4,5,6}", sll.toString());
        sll.remove(3);
        assertEquals("{1,2,3,5,6}", sll.toString());
    }

    @org.junit.jupiter.api.Test
    void getItem() {
        final SinglyLinkedList<Integer> sll = testCollectionToList();
        for (int i = 0; i < 8; i++) {
            log.info("i: " + i + " -> " + sll.get(i));
            assertEquals(i, sll.get(i));
        }
        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> sll.get(100)
        );
        log.info(String.valueOf(exception.getMessage()));
    }

    @Test
    void checkFirstAndLast() {
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        sll.add(1);
        assertEquals(1, sll.getFirst());
        assertEquals(1, sll.getLast());
        for (int i = 2; i < 10; ++i) {
            sll.add(i);
            assertEquals(1, sll.getFirst());
            assertEquals(i, sll.getLast());
        }
        for (int i = 9; i >= 2; --i) {
            assertEquals(1, sll.getFirst());
            assertEquals(i, sll.getLast());
            sll.unlinkLast();
        }
        assertEquals(1, sll.getFirst());
        assertEquals(1, sll.getLast());

        sll.unlinkLast();
        assertNull(sll.getFirst());
        assertNull(sll.getLast());
    }

    @Test
    void reverse() {
        SinglyLinkedList<Integer> sll;
        sll = testCollectionToList();
        SinglyLinkedList<Integer> newList = sll.reverse();
        log.info(String.valueOf(newList.toString()));
    }
}
