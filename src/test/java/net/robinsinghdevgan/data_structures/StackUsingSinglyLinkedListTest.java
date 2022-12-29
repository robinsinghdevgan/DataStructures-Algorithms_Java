package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class StackUsingSinglyLinkedListTest {
    private StackUsingSinglyLinkedList<String> s;

    @BeforeAll
    public void setup() {
        s = new StackUsingSinglyLinkedList<>();
    }

    @Test
    void test1() {
        assertTrue(s.isEmpty());
        assertEquals(0, s.getSize());
        s.push("a");
        assertFalse(s.isEmpty());
        assertEquals("a", s.peek());
        assertEquals("a", s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    //@Disabled
    void test2() {
        //s = new StackUsingSinglyLinkedList<String>();
        assertTrue(s.isEmpty());
        String[] x = {"a", "b", "c", "d"};
        for (int i = 0; i < x.length; i++) {
            s.push(x[i]);
            assertEquals(i + 1, s.getSize());
            assertEquals(x[i], s.peek());
        }

        for (int i = x.length - 1; i >= 0; i--) {
            assertEquals(x[i], s.pop());
        }
        assertTrue(s.isEmpty());
    }

    @Test
    void test3() {
        Assertions.assertThrows(
                EmptyStackException.class,
                () -> s.pop()
        );
    }
}
