package net.robinsinghdevgan.algorithms;

import java.util.ArrayList;

/*
References: 
[1] https://github.com/adnanaziz/EPIJudge/blob/master/epi_judge_java_solutions/epi/SubstringMatch.java
[2] https://www.geeksforgeeks.org/algorithms-gq/pattern-searching/
*/
public class StringPatternSearch {

    public static ArrayList<Integer> naiveSearch(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        int M = pat.length();
        int N = txt.length();

        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {
            int j;

            /*
             * For current index i, check for pattern match
             */
            for (j = 0; j < M; j++) if (txt.charAt(i + j) != pat.charAt(j)) break;

            if (j == M) { // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                System.out.println("NaiveSearch : Pattern found at index " + i);
                result.add(i);
            }
        }
        return result;
    }

    public static ArrayList<Integer> searchKMP(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int[] lps = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("KMP: Found pattern " + "at index " + (i - j));
                result.add(i - j);
                j = lps[j - 1];
            }
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0) j = lps[j - 1];
                else i = i + 1;
            }
        }
        return result;
    }

    private static void computeLPSArray(String pat, int M, int[] lps) {
        ArrayList<Integer> result = new ArrayList<>();
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { // (pat[i] != pat[len])
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
                    // Also, note that we do not increment
                    // i here
                } else { // if (len == 0)
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Rabin-Karp
    static ArrayList<Integer> searchRabinKarp(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        if (pat.length() > txt.length()) {
            System.out.println("pat is not a substring of txt.");
        }

        final int BASE = 26;
        int tHash = 0, sHash = 0; // Hash codes for the substring of txt and pat.
        int powerS = 1; // BASE^|pat-1|.
        for (int i = 0; i < pat.length(); i++) {
            powerS = i > 0 ? powerS * BASE : 1;
            tHash = tHash * BASE + txt.charAt(i);
            sHash = sHash * BASE + pat.charAt(i);
        }
        boolean found = false;
        for (int i = pat.length(); i < txt.length(); i++) {
            // Checks the two substrings are actually equal or not, to protect
            // against hash collision.
            if (tHash == sHash && txt.startsWith(pat, i - pat.length())) {
                found = true;
                System.out.println("RabinKarp: Pattern found at: " + (i - pat.length())); // Found a match.
                result.add(i - pat.length());
            }

            // Uses rolling hash to compute the new hash code.
            tHash -= txt.charAt(i - pat.length()) * powerS;
            tHash = tHash * BASE + txt.charAt(i);
        }
        // Tries to match pat and txt.substring(txt.length() - pat.length()).
        if (tHash == sHash && txt.endsWith(pat)) {
            found = true;
            System.out.println("RabinKarp: Pattern found at: " + (txt.length() - pat.length()));
            result.add(txt.length() - pat.length());
        }
        if (!found) {
            System.out.println("pat is not a substring of txt.");
        }
        return result;
    }

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABABABABDABACDABABCABABCABABABABDABA";
        String pat = "ABABCABAB";
        naiveSearch(pat, txt);
        searchKMP(pat, txt);
        searchRabinKarp(pat, txt);
    }
}
