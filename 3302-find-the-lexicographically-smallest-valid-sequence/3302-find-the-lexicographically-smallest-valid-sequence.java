import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] ans = new int[m];

        // last[j] = position in word1 that can be used
        // to match word2[j] while matching the suffix.
        int[] last = new int[m];

        Arrays.fill(last, -1);

        // Build last[] from right to left
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }

            i--;
        }

        // Now greedily build the answer
        boolean mismatchUsed = false;

        i = 0;
        j = 0;

        while (i < n && j < m) {

            // Normal matching character
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            }

            // Use our one allowed mismatch
            else if (!mismatchUsed &&
                    (j == m - 1 || i < last[j + 1])) {

                ans[j] = i;
                j++;

                mismatchUsed = true;
            }

            i++;
        }

        // Couldn't form word2
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}