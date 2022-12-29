package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PriorityQueueTest {
    static final int LOOPS = 100;
    static final int MAX_SZ = 100;

    static Integer[] genRandArray(int sz) {
        Integer[] lst = new Integer[sz];
        for (int i = 0; i < sz; i++) lst[i] = (int) (Math.random() * MAX_SZ);
        return lst;
    }

    // Generate a list of random numbers
    static List<Integer> genRandList(int sz) {
        List<Integer> lst = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) lst.add((int) (Math.random() * MAX_SZ));
        return lst;
    }

    // Generate a list of unique random numbers
    static List<Integer> genUniqueRandList() {
        List<Integer> lst = new ArrayList<>(PriorityQueueTest.LOOPS);
        for (int i = 0; i < PriorityQueueTest.LOOPS; i++) lst.add(i);
        Collections.shuffle(lst);
        return lst;
    }

    @Test
    void testEmpty() {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        assertThat(q.size()).isEqualTo(0);
        assertThat(q.isEmpty()).isTrue();
        assertThat(q.poll()).isNull();
        assertThat(q.peek()).isNull();
    }

    @Test
    void testHeapProperty() {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Integer[] nums = {3, 2, 5, 6, 7, 9, 4, 8, 1};

        // Try manually creating heap
        for (int n : nums) q.add(n);
        for (int i = 1; i <= 9; i++) assertThat(q.poll()).isEqualTo(i);

        q.clear();

        // Try heapify constructor
        q = new PriorityQueue<>(nums);
        for (int i = 1; i <= 9; i++) assertThat(q.poll()).isEqualTo(i);
    }

    @Test
    void testHeapify() {
        for (int i = 1; i < LOOPS; i++) {
            Integer[] lst = genRandArray(i);
            PriorityQueue<Integer> pq = new PriorityQueue<>(lst);

            java.util.PriorityQueue<Integer> pq2 = new java.util.PriorityQueue<>(i);
            pq2.addAll(Arrays.asList(lst));

            assertThat(pq.isMinHeap(0)).isTrue();
            while (!pq2.isEmpty()) {
                assertThat(pq.poll()).isEqualTo(pq2.poll());
            }
        }
    }

    @Test
    void testClear() {
        PriorityQueue<String> q;
        String[] strs = {"aa", "bb", "cc", "dd", "ee"};
        q = new PriorityQueue<>(strs);
        q.clear();
        assertThat(q.size()).isEqualTo(0);
        assertThat(q.isEmpty()).isTrue();
    }

    @Test
    void testContainment() {
        String[] strs = {"aa", "bb", "cc", "dd", "ee"};
        PriorityQueue<String> q = new PriorityQueue<>(strs);
        q.remove("aa");
        assertThat(q.contains("aa")).isFalse();
        q.remove("bb");
        assertThat(q.contains("bb")).isFalse();
        q.remove("cc");
        assertThat(q.contains("cc")).isFalse();
        q.remove("dd");
        assertThat(q.contains("dd")).isFalse();
        q.clear();
        assertThat(q.contains("ee")).isFalse();
    }

    @Test
    void testContainmentRandomized() {
        for (int i = 0; i < LOOPS; i++) {
            List<Integer> randNums = genRandList(100);
            java.util.PriorityQueue<Integer> PQ = new java.util.PriorityQueue<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (Integer randNum : randNums) {
                pq.add(randNum);
                PQ.add(randNum);
            }

            for (int randVal : randNums) {
                assertThat(pq.contains(randVal)).isEqualTo(PQ.contains(randVal));
                pq.remove(randVal);
                PQ.remove(randVal);
                assertThat(pq.contains(randVal)).isEqualTo(PQ.contains(randVal));
            }
        }
    }

    public void sequentialRemoving(Integer[] in, Integer[] removeOrder) {
        assertThat(in.length).isEqualTo(removeOrder.length);

        PriorityQueue<Integer> pq = new PriorityQueue<>(in);
        java.util.PriorityQueue<Integer> PQ = new java.util.PriorityQueue<>();
        for (int value : in) PQ.offer(value);

        assertThat(pq.isMinHeap(0)).isTrue();

        for (int elem : removeOrder) {
            assertThat(pq.peek()).isEqualTo(PQ.peek());
            assertThat(pq.remove(elem)).isEqualTo(PQ.remove(elem));
            assertThat(pq.size()).isEqualTo(PQ.size());
            assertThat(pq.isMinHeap(0)).isTrue();
        }

        assertThat(pq.isEmpty()).isTrue();
    }

    @Test
    void testRemoving() {
        Integer[] in = {1, 2, 3, 4, 5, 6, 7};
        Integer[] removeOrder = {1, 3, 6, 4, 5, 7, 2};
        sequentialRemoving(in, removeOrder);

        in = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        removeOrder = new Integer[]{7, 4, 6, 10, 2, 5, 11, 3, 1, 8, 9};
        sequentialRemoving(in, removeOrder);

        in = new Integer[]{8, 1, 3, 3, 5, 3};
        removeOrder = new Integer[]{3, 3, 5, 8, 1, 3};
        sequentialRemoving(in, removeOrder);

        in = new Integer[]{7, 7, 3, 1, 1, 2};
        removeOrder = new Integer[]{2, 7, 1, 3, 7, 1};
        sequentialRemoving(in, removeOrder);

        in = new Integer[]{32, 66, 93, 42, 41, 91, 54, 64, 9, 35};
        removeOrder = new Integer[]{64, 93, 54, 41, 35, 9, 66, 42, 32, 91};
        sequentialRemoving(in, removeOrder);
    }

    @Test
    void testRemovingDuplicates() {
        Integer[] in = new Integer[]{2, 7, 2, 11, 7, 13, 2};
        PriorityQueue<Integer> pq = new PriorityQueue<>(in);

        assertThat(pq.peek()).isEqualTo(2);
        pq.add(3);

        assertThat(pq.poll()).isEqualTo(2);
        assertThat(pq.poll()).isEqualTo(2);
        assertThat(pq.poll()).isEqualTo(2);
        assertThat(pq.poll()).isEqualTo(3);
        assertThat(pq.poll()).isEqualTo(7);
        assertThat(pq.poll()).isEqualTo(7);
        assertThat(pq.poll()).isEqualTo(11);
        assertThat(pq.poll()).isEqualTo(13);
    }

    @Test
    void testRandomizedPolling() {
        for (int i = 0; i < LOOPS; i++) {
            List<Integer> randNums = genRandList(i);
            java.util.PriorityQueue<Integer> pq1 = new java.util.PriorityQueue<>();
            PriorityQueue<Integer> pq2 = new PriorityQueue<>();

            // Add all the elements to both priority queues
            for (Integer value : randNums) {
                pq1.offer(value);
                pq2.add(value);
            }

            while (!pq1.isEmpty()) {
                assertThat(pq2.isMinHeap(0)).isTrue();
                assertThat(pq1.size()).isEqualTo(pq2.size());
                assertThat(pq1.peek()).isEqualTo(pq2.peek());
                assertThat(pq1.contains(pq1.peek())).isEqualTo(pq2.contains(pq2.peek()));

                Integer v1 = pq1.poll();
                Integer v2 = pq2.poll();

                assertThat(v1).isEqualTo(v2);
                assertThat(pq1.peek()).isEqualTo(pq2.peek());
                assertThat(pq1.size()).isEqualTo(pq2.size());
                assertThat(pq2.isMinHeap(0));
            }
        }
    }

    @Test
    void testRandomizedRemoving() {
        for (int i = 0; i < LOOPS; i++) {
            List<Integer> randNums = genRandList(i);
            java.util.PriorityQueue<Integer> pq1 = new java.util.PriorityQueue<>();
            PriorityQueue<Integer> pq2 = new PriorityQueue<>();

            // Add all the elements to both priority queues
            for (Integer value : randNums) {
                pq1.offer(value);
                pq2.add(value);
            }

            Collections.shuffle(randNums);
            int index = 0;

            while (!pq1.isEmpty()) {
                int removeNum = randNums.get(index++);

                assertThat(pq2.isMinHeap(0)).isTrue();
                assertThat(pq1.size()).isEqualTo(pq2.size());
                assertThat(pq1.peek()).isEqualTo(pq2.peek());
                pq1.remove(removeNum);
                pq2.remove(removeNum);
                assertThat(pq1.peek()).isEqualTo(pq2.peek());
                assertThat(pq1.size()).isEqualTo(pq2.size());
                assertThat(pq2.isMinHeap(0)).isTrue();
            }
        }
    }

    @Test
    void testPQReusability() {
        List<Integer> SZs = genUniqueRandList();

        java.util.PriorityQueue<Integer> PQ = new java.util.PriorityQueue<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int sz : SZs) {
            pq.clear();
            PQ.clear();

            List<Integer> nums = genRandList(sz);
            for (int n : nums) {
                pq.add(n);
                PQ.add(n);
            }

            Collections.shuffle(nums);

            for (int i = 0; i < sz / 2; i++) {
                // Sometimes add a new number into the PriorityQueue
                if (0.25 < Math.random()) {
                    int randNum = (int) (Math.random() * 10000);
                    PQ.add(randNum);
                    pq.add(randNum);
                }

                int removeNum = nums.get(i);

                assertThat(pq.isMinHeap(0)).isTrue();
                assertThat(PQ.size()).isEqualTo(pq.size());
                assertThat(PQ.peek()).isEqualTo(pq.peek());

                PQ.remove(removeNum);
                pq.remove(removeNum);

                assertThat(PQ.peek()).isEqualTo(pq.peek());
                assertThat(PQ.size()).isEqualTo(pq.size());
                assertThat(pq.isMinHeap(0)).isTrue();
            }
        }
    }
}
