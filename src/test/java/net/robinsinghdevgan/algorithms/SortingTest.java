package net.robinsinghdevgan.algorithms;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class SortingTest {
    final int[] arr1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 100, 200, 50};
    final int[] arrSorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 100, 200};
    int[] arrUnsorted = null;

    @BeforeAll
    public void setUp() {
        arrUnsorted = arr1.clone();
    }

    @Test
    void testBubbleSort() {
        Sort.doBubbleSort(arrUnsorted);
        // Arrays.sort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testInsertionSort() {
        Sort.doInsertionSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testSelectionSort() {
        Sort.doSelectionSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testHeapSort() {
        Sort.doHeapSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testCountingSort() {
        Sort.doCountingSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testMergeSort() {
        Sort.doMergeSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testQuickSort() {
        Sort.doQuickSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }

    @Test
    void testBSTSort() {
        Sort.doBSTSort(arrUnsorted);
        assertThat(arrUnsorted).isEqualTo(arrSorted);
        assertThat(arrUnsorted).isNotEqualTo(arr1);
    }
}
