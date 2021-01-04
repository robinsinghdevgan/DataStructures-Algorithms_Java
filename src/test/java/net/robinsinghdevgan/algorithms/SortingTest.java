package net.robinsinghdevgan.algorithms;

import static com.google.common.truth.Truth.assertThat;

//import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortingTest {
    final int[] arr1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 100, 200, 50};
    final int[] arrSorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 100, 200};
    int[] arrUnsorted = null;
    @BeforeAll
    public void setUp() {
        arrUnsorted = arr1.clone();
    }

    @Test
    public void testBubbleSort() {
        Sort.doBubbleSort(arrUnsorted);
        //Arrays.sort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    public void testInsertionSort() {
        Sort.doInsertionSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    public void testSelectionSort() {
        Sort.doSelectionSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    public void testHeapSort() {
        Sort.doHeapSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    public void testCountingSort() {
        Sort.doCountingSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    public void testMergeSort() {
        Sort.doMergeSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }
}
