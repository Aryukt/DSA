class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        long[] square = new long[n];

        // Store squares
        for (int i = 0; i < n; i++) {
            square[i] = (long) nums[i] * nums[i];
        }

        // Sort squares
        Arrays.sort(square);

        long ans = 0;

        // Number of positive positions
        int positive = (n + 1) / 2;

        // Add the largest squares
        for (int i = n - 1; i >= n - positive; i--) {
            ans += square[i];
        }

        // Subtract the remaining squares
        for (int i = 0; i < n - positive; i++) {
            ans -= square[i];
        }

        return ans;
    }
}
