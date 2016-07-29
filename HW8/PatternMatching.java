import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Your implementations of various pattern matching algorithms.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class PatternMatching {

    /**
     * Brute Force Algorithm compares a pattern with the text for each possible
     * shift of pattern with respect to the text.
     *
     * Runtime: O(nm) where n is the size of text and m is the size of pattern
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> bruteForce(CharSequence pattern,
        CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or have"
                    + " length 0");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        List<Integer> matches = new ArrayList<>();
        if  (pattern.length() > text.length()) {
            return matches;
        }

        int i = 0;
        int j = 0;
        int n = text.length() - 1;
        int m = pattern.length() - 1;

        while (i <= n) {
            if (j == 0 && i > n - m) {
                break;
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m) {
                    matches.add(i - j);
                    i = i - j + 1;
                    j = 0;
                } else {
                    i++;
                    j++;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        return matches;
    }

    /**
     * Boyer Moore algorithm that uses a last table. Works better with large
     * alphabets.
     *
     * Runtime : O(nm + s) where n is the size of text, m is the size of pattern
     * and s is the size of alphabet
     *
     * NOTE: Make sure to implement {@code buildLastTable} before 
     * implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * if text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
        CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or have"
                    + " length 0");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }


        List<Integer> matches = new ArrayList<>();
        if  (pattern.length() > text.length()) {
            return matches;
        }
        Map<Character, Integer> lastTable = buildLastTable(pattern);


        int n = pattern.length() - 1;
        int m = pattern.length() - 1;

        while (n < text.length()) {
            char tc = text.charAt(n);
            char pc = pattern.charAt(m);

            if (tc != pc) {
                n += pattern.length() - Math.min(m,
                        lastTable.getOrDefault(tc, -1) + 1);
                m = pattern.length() - 1;
            } else {
                if (m != 0) {
                    n--;
                    m--;
                } else {
                    matches.add(n);
                    n += pattern.length();
                    m = pattern.length() - 1;
                }

            }
        }

        return matches;
    }
    /**
     * Builds last occurrence table for the Boyer Moore algorithm.
     *
     * NOTE : each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x
     * and you will have to check for that in your BoyerMoore
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        Map<Character, Integer> lastTable = new HashMap<>();

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }

        for (int i = 0; i < pattern.length(); i++) {
            lastTable.put(pattern.charAt(i), i);
        }
        return lastTable;
    }

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Runtime: O(m+n) where n is the size of the text and m is the size of the
     * pattern
     *
     * NOTE: Make sure to implement {@code buildFailureTable} before 
     * implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0 or
     * if text is null
     * @param pattern the pattern you are trying to match
     * @param text the body of text where you search for the pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }

        List<Integer> list = new java.util.ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m == 0) {
            return list;
        }
        int[] fail = buildFailureTable(pattern);
        int j = 0;
        int i = 0;
        while (j < n) {
            if (i + m - j > n) {
                break;
            }
            if (text.charAt(j) == pattern.charAt(i)) {
                if (i == m - 1) {
                    list.add(j - m + 1);
                    j++;
                    i = fail[i];
                } else {
                    j++;
                    i++;
                }
            } else if (i > 0) {
                i = fail[i - 1];
            } else {
                j++;
            }
        }
        return list;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * A given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building failure table for
     * @return integer array of size text.length that you are building a failure
     * table for
     */
    public static int[] buildFailureTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }

        int m = pattern.length();
        int[] fail = new int[m];
        int j = 1;
        int i = 0;
        while (j < m) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                fail[j] = i + 1;
                j++;
                i++;
            } else if (i > 0) {
                i = fail[i - 1];
            } else {
                j++;
            }
        }
        return fail;
    }

}
