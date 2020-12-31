package net.robinsinghdevgan.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SinglyLinkedListTest {

    final int[] arr = new int[]{0,1,2,3,4,5,6,7};
        
    @org.junit.jupiter.api.BeforeAll
    void setUp() {
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
    }

    @Test
    void testToString() {
        SinglyLinkedList<Integer> sll = null;
        sll = testCollectionToList();
        System.out.println(sll.toString());
    }
    
    @org.junit.jupiter.api.Test
    SinglyLinkedList<Integer> testCollectionToList() {
        SinglyLinkedList<Integer> sll = null;
        sll = new SinglyLinkedList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i:arr){
            list.add(i);
        }
        assertTrue(sll.addAll(list));
        //System.out.println("=========List setup=====");
        //System.out.println(sll.toString());
        return sll;
    }

    @Test
    void removeItem() {
        SinglyLinkedList<Integer> sll = null;
        sll = testCollectionToList();
        sll.remove(0);
        assertEquals("{1,2,3,4,5,6,7}", sll.toString());
        sll.remove(6);
        assertEquals("{1,2,3,4,5,6}", sll.toString());
        sll.remove(3);
        assertEquals("{1,2,3,5,6}", sll.toString());
    }

    @org.junit.jupiter.api.Test()
    void getItem() {
        final SinglyLinkedList<Integer> sll = testCollectionToList();
        for(int i = 0; i < 8; i++) {
            System.out.println("i: " + i + " -> " + sll.get(i));
            assertEquals(i, sll.get(i));
        }
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> sll.get(100));
        System.out.println(exception.getMessage());
    }

    @Test
    void checkFirstAndLast() {
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
        sll.add(1);
        assertEquals(1, sll.getFirst());
        assertEquals(1, sll.getLast());
        for(int i=2;i<10;++i){
            sll.add(i);
            assertEquals(1, sll.getFirst());
            assertEquals(i, sll.getLast());
        }
        for(int i=9;i>=2;--i){
            assertEquals(1, sll.getFirst());
            assertEquals(i, sll.getLast());
            sll.unlinkLast();
        }
    }
}