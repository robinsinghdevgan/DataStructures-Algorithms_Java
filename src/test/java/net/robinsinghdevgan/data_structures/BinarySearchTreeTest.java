package net.robinsinghdevgan.data_structures;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class BinarySearchTreeTest {

    @Test
    void test1() {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 9999, 88, -10055};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int j : arr) {
            tree.add(j);
        }
        assertEquals(tree.size(), arr.length);
        assertEquals(11, tree.height());
        log.info(String.valueOf(tree));
        tree.balanceTheTree();
        log.info(String.valueOf(tree));
    }

    @Test
    void test2() {
        int[] arr = {5, 6, 3, 2, 1, 9999, 88, -10055};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int j : arr) {
            tree.add(j);
        }
        assertEquals("{-10055,1,2,3,5,6,88,9999}", tree.inOrderTraversal());
        assertEquals(-10055, tree.findMin());
        assertEquals(9999, tree.findMax());
        assertTrue(tree.remove(9999));
        assertEquals("{-10055,1,2,3,5,6,88}", tree.inOrderTraversal());
        assertTrue(tree.remove(-10055));
        assertEquals("{1,2,3,5,6,88}", tree.inOrderTraversal());
        tree.balanceTheTree();
        log.info(String.valueOf(tree));
    }
}
