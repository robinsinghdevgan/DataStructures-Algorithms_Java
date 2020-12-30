package net.robinsinghdevgan.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.robinsinghdevgan.dataStructures.StackUsingArray;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackUsingArrayTest {

    private StackUsingArray<String> s;
    private final int size = 15;

    @BeforeAll
    public void setup() {
        s = new StackUsingArray<String>(size);
    }

    @Test
    public void test1() {
        assertEquals(true, s.isEmpty());
        assertEquals(15, s.getSize());
        s.push("a");
        assertEquals(false, s.isEmpty());
        assertEquals("a", s.peek());
        assertEquals("a", s.pop());
        assertEquals(true, s.isEmpty());
    }

    @Test
    public void test2() {
        String[] x = { "a", "b", "c", "d" };
        for (int i = 0; i < x.length; i++) {
            s.push(x[i]);
            assertEquals(size, s.getSize());
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
