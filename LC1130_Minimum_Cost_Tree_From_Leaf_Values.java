// Solution 1  DP: Time O(N^3)   Space O(N^2)
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len-1; i++) {
            dp[i][i+1] = arr[i] * arr[i+1];
        }
        for (int d = 1; d <= len-1; d++) {
            for (int i = 0; i < len-d; i++) {
                int val = Integer.MAX_VALUE;
                int j = i + d;
                for (int k = i; k < j; k++) {
                    val = Math.min(val, getMax(arr, i, k)*getMax(arr, k+1, j)+dp[i][k]+dp[k+1][j]);
                }
                dp[i][j] = val;
            }
        }
        return dp[0][len-1];
    }
    
    private int getMax(int[] arr, int i, int j) {
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            max = Math.max(max, arr[k]);
        }
        return max;
    }
}

// Solution 2  Montonous Stack:  Time O(N),  Space: O(N)
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int cur : arr) {
            while (stack.peek() <= cur) {
                int mid = stack.pop();
                res += mid * Math.min(cur, stack.peek());
            }
            stack.push(cur);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}

