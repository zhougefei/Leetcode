// Understandable algorithm
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A.length != B.length) {
            return -1;
        }
        int a1 = numberOfSwapWithTarget(A[0], A, B);
        int a2 = numberOfSwapWithTarget(A[0], B, A);
        int n1 = Math.min(a1, a2);
        int b1 = numberOfSwapWithTarget(B[0], A, B);
        int b2 = numberOfSwapWithTarget(B[0], B, A);
        int n2 = Math.min(b1, b2);
        int n = Math.min(n1, n2);
        return n == Integer.MAX_VALUE ? -1 : n;
    }
    
    private int numberOfSwapWithTarget(int target, int[] upper, int[] lower) {
        int n = upper.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (upper[i] == target) {
                continue;
            } else if (lower[i] == target) {
                count++;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return count;
    }
}

// Sharp intuition algorithm
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A.length != B.length) {
            return -1;
        }
        int n = A.length;
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] repetition = new int[7];
        for (int i = 0; i < n; i++) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i]) {
                repetition[A[i]]++;
            }
        }
        for (int i = 1; i <= 6; i++) {
            if (countA[i] + countB[i] - repetition[i] == n) {
                return n - Math.max(countA[i], countB[i]);
            }
        }
        return -1;
    }
}


