package net.robinsinghdevgan.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackUsingSinglyLinkedListTest {

    private StackUsingSinglyLinkedList<String> s;
    
    @BeforeAll
    public void setup() {
        s = new StackUsingSinglyLinkedList<String>();
    }

    @Test
    public void test1() {
        assertEquals(true, s.isEmpty());
        assertEquals(0, s.getSize());
        s.push("a");
        assertEquals(false, s.isEmpty());
        assertEquals("a", s.peek());
        assertEquals("a", s.pop());
        assertEquals(true, s.isEmpty());
    }

    @Test
    //@Disabled
    public void test2() {
        //s = new StackUsingSinglyLinkedList<String>();
        assertEquals(true, s.isEmpty());
        String[] x = { "a", "b", "c", "d" };
        for (int i = 0; i < x.length; i++) {
            s.push(x[i]);
            assertEquals(i+1, s.getSize());
            assertEquals(x[i], s.peek());
        }

        for (int i = x.length - 1; i >= 0; i--) {
            assertEquals(x[i], s.pop());
        }
        assertEquals(true, s.isEmpty());
    }

    @Test()
    public void test3() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            s.pop();
        });
    }
}
