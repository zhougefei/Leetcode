// Two arrays to record the left greater and right greater index;
class Solution {
    public int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[] firstLessOnL = new int[len];    firstLessOnL[0] = -1;
        int[] firstLessOnR = new int[len];    firstLessOnR[len-1] = len;
        for (int i = 1; i < len; i++) {
            int l = i - 1;
            while (l >= 0 && A[l] >= A[i]) {
                l = firstLessOnL[l];
            }
            firstLessOnL[i] = l;
        }
        for (int i = len - 2; i >= 0; i--) {
            int r = i + 1;
            while (r < len && A[r] > A[i]) {
                r = firstLessOnR[r];
            }    
            firstLessOnR[i] = r;
        }
        int res = 0;
        int M = (int) (Math.pow(10, 9)+7);
        for (int i = 0; i < len; i++) {
            res = res % M + (A[i] * (i - firstLessOnL[i]) * (firstLessOnR[i] - i)) % M;
        }
        return  res;
    }
}