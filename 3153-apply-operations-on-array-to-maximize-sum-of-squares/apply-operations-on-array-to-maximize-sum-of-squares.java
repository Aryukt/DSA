
import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1000000007L;
        int[] bitCount = new int[31];

        // Count occurrences of each bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Construct k maximum numbers
        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (bitCount[bit] > 0) {
                    x |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + x * x % MOD) % MOD;
        }

        return (int) ans;
    }
}
