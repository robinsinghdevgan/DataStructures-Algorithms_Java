package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class QueueTest {
    private final List<Queue<Integer>> queues = new ArrayList<>();

    @BeforeAll
    public void setup() {
        //queues.add(new QueueUsingArray<Integer>(2));
        queues.add(new QueueUsingSinglyLinkedList<>());
    }

    @Test
    void testEmptyQueue() {
        for (var queue : queues) {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }
    }

    @Test
    void testExhaustively() {
        for (Queue<Integer> queue : queues) {
            assertTrue(queue.isEmpty());
            queue.offer(1);
            assertFalse(queue.isEmpty());
            queue.offer(2);
            assertEquals(2, queue.size());
            assertEquals(1, (int) queue.peek());
            assertEquals(2, queue.size());
            assertEquals(1, (int) queue.poll());
            assertEquals(1, queue.size());
            assertEquals(2, (int) queue.peek());
            assertEquals(1, queue.size());
            assertEquals(2, (int) queue.poll());
            assertEquals(0, queue.size());
            assertTrue(queue.isEmpty());
        }
    }

    @Test
    void testOffer() {
        for (Queue<Integer> queue : queues) {
            queue.offer(2);
            assertEquals(1, queue.size());
            assertEquals(2, (int) queue.poll());
        }
    }

    @Test
    void testPeek() {
        for (Queue<Integer> queue : queues) {
            queue.offer(2);
            assertEquals(2, (int) queue.peek());
            assertEquals(1, queue.size());
            assertEquals(2, (int) queue.poll());
        }
    }

    @Test
    void testPeekOnEmpty() {
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    for (var queue : queues) {
                        queue.peek();
                    }
                }
        );
    }

    @Test
    void testPoll() {
        for (Queue<Integer> queue : queues) {
            queue.offer(2);
            assertEquals(2, (int) queue.poll());
            assertEquals(0, queue.size());
        }
    }

    @Test
    void testPollOnEmpty() {
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    for (var queue : queues) {
                        queue.poll();
                    }
                }
        );
    }
}
