package net.robinsinghdevgan.algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringPatternSearchTest {

    @Test
    public void test1() {
        String txt = "ABABDABACDABABCABABABABDABACDABABCABABCABABABABDABA";
        String pat = "ABABCABAB";
        final List<Integer> result = Arrays.asList(10, 29, 34);
        assertThat(StringPatternSearch.naiveSearch(pat, txt)).isEqualTo(result);
        assertThat(StringPatternSearch.searchKMP(pat, txt)).isEqualTo(result);
        assertThat(StringPatternSearch.searchRabinKarp(pat, txt)).isEqualTo(result);
    }
}
