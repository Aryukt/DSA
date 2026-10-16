import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] square;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        HashMap<Integer, Integer> last = new HashMap<>();

        long answer = 0;

        for (int r = 0; r < n; r++) {

            int prev = last.getOrDefault(nums[r], -1);

            /*
             * nums[r] is a new distinct value for subarrays
             * starting from prev+1 through r.
             */
            update(1, 0, n - 1, prev + 1, r, 1);

            last.put(nums[r], r);

            /*
             * square[1] =
             * sum of distinctCount(l,r)^2
             * for every l.
             */
            answer = (answer + square[1]) % MOD;
        }

        return (int) answer;
    }

    void update(int node, int l, int r,
                int ql, int qr, long val) {

        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        if (ql <= mid) {
            update(node * 2, l, mid, ql, qr, val);
        }

        if (qr > mid) {
            update(node * 2 + 1, mid + 1, r, ql, qr, val);
        }

        pull(node);
    }

    void apply(int node, int l, int r, long val) {

        long len = r - l + 1;

        /*
         * Suppose old distinct count = x.
         *
         * New count = x + val
         *
         * (x + val)^2
         * = x^2 + 2*x*val + val^2
         */

        square[node] =
            (square[node]
            + 2L * val % MOD * sum[node] % MOD
            + val * val % MOD * len) % MOD;

        sum[node] =
            (sum[node] + val * len) % MOD;

        lazy[node] =
            (lazy[node] + val) % MOD;
    }

    void push(int node, int l, int r) {

        if (lazy[node] == 0 || l == r)
            return;

        int mid = (l + r) / 2;

        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void pull(int node) {

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        square[node] =
            (square[node * 2] + square[node * 2 + 1]) % MOD;
    }
}