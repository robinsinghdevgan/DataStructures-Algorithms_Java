package net.robinsinghdevgan.algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchingTest {
    final int[] arrUnsorted = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 100, 200, 50};
    final int[] arrSorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 100, 200};

    @Test
    public void testLinearSearch() {
        assertThat(Searching.linearSearch(arrUnsorted, 50)).isEqualTo(12);
        for (int i = 0; i < arrSorted.length; i++) {
            assertThat(Searching.linearSearch(arrSorted, arrSorted[i])).isEqualTo(i);
        }
    }

    @Test
    public void testBinarySearch() {
        for (int i = 0; i < arrSorted.length; i++) {
            assertThat(Searching.binarySearch(arrSorted, arrSorted[i])).isEqualTo(i);
        }
    }
}
