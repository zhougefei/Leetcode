class Solution {
    public int maxArea(int[] height) {
        int ol = 0, or = height.length-1;
        int l = ol, r = or;
        int maxA = 0;
        while (l < r) {
            maxA = Math.max(maxA, (r-l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) {
                while (height[l] <= height[ol] && l < height.length-1) {
                    l++;
                }
                ol = l;
            } else {
                while (height[r] <= height[or] && r > 0) {
                    r--;
                }
                or = r;
            }
        }
        return maxA;
    }
}