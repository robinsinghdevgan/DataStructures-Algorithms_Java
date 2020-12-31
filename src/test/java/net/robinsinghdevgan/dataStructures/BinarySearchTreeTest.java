package net.robinsinghdevgan.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BinarySearchTreeTest {
    @Test
    public void test1() {
        int[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 9999, 88, -10055 };
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        assertEquals(tree.size(), arr.length);
        assertEquals(11, tree.height());
        assertEquals("{-10055,1,2,3,4,5,6,7,8,9,10,88,9999}", tree.inOrderTraversal());
    }
}
