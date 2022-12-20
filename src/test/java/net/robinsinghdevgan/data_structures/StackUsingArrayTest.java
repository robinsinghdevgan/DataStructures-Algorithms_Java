package net.robinsinghdevgan.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackUsingArrayTest {
    private final int size = 15;
    private StackUsingArray<String> s;

    @BeforeAll
    public void setup() {
        s = new StackUsingArray<>(size);
    }

    @Test
    public void test1() {
        assertTrue(s.isEmpty());
        assertEquals(15, s.getSize());
        s.push("a");
        assertFalse(s.isEmpty());
        assertEquals("a", s.peek());
        assertEquals("a", s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    public void test2() {
        String[] x = {"a", "b", "c", "d"};
        for (String value : x) {
            s.push(value);
            assertEquals(size, s.getSize());
            assertEquals(value, s.peek());
        }

        for (int i = x.length - 1; i >= 0; i--) {
            assertEquals(x[i], s.pop());
        }
        assertTrue(s.isEmpty());
    }

    @Test
    public void test3() {
        Assertions.assertThrows(
                EmptyStackException.class,
                () -> s.pop()
        );
    }
}
