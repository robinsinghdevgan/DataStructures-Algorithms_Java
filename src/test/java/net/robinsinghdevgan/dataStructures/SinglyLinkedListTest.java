package net.robinsinghdevgan.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SinglyLinkedListTest {

    SinglyLinkedList<Integer> sll = null;
    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        var arr = new int[]{0,1,2,3,4,5,6,7};
        sll = new SinglyLinkedList<Integer>();
        for (int i:arr){
            sll.add(i);
        }
        System.out.println("=========List setup=====");
        System.out.println(sll.toString());
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
        sll = null;
    }
    
    @org.junit.jupiter.api.Test
    void getItem() {
        for(int i = 0; i < 8; i++) {
            System.out.println("i: " + i + " -> " + sll.get(i));
            assertEquals(i, sll.get(i));
        }
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> sll.get(100));
        System.out.println(exception.getMessage());
    }
}