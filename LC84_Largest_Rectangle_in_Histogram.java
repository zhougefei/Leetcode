// Solution 1: Brute force  Time O(n^2)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int max = expand(heights, i);
            res = Math.max(res, max);
        }
        return res;
    }
    
    private int expand(int[] heights, int index) {
        int height = heights[index];
        int left = index, right = index;
        while (left >= 0 && heights[left] >= height) {
            left--;
        }
        while (right < heights.length && heights[right] >= height) {
            right++;
        }
        return height*(right-left-1);
    }
}

// Solution 2 : Divide and Conquer Time O(nlogn)
class Solution {
    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length-1);
    }
    
    private int largestRectangleArea(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        }  else if (start == end) {
            return heights[start];
        }
        int minIndex = getMinIndex(heights, start, end);
        int min = heights[minIndex];
        int maxOnleft = largestRectangleArea(heights, start, minIndex-1);
        int maxOnright = largestRectangleArea(heights, minIndex+1, end);
        return Math.max(Math.max(maxOnleft, maxOnright), min*(end-start+1));
    }
    
    private int getMinIndex(int[] heights, int start, int end) {
        int minValue = heights[start];
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < minValue) {
                minValue = heights[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
// Solution 3: Use two arrays to store the first index with height less than current height Time O(n), Space O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] firstLessOnLeft = new int[len];    firstLessOnLeft[0] = -1;
        int[] firstLessOnRight = new int[len];   firstLessOnRight[len-1] = len;
        for (int i = 1; i < len; i++) {
            int l = i - 1;
            while (l >= 0 && heights[l] >= heights[i]) {
                l = firstLessOnLeft[l];
            }
            firstLessOnLeft[i] = l;
        }
        for (int i = len - 2; i >= 0; i--) {
            int r = i + 1;
            while (r < len && heights[r] >= heights[i]) {
                r = firstLessOnRight[r];
            }
            firstLessOnRight[i] = r;
        }
        int res= 0;
        for (int i = 0; i < heights.length; i++) {
            int curMax = heights[i] * (firstLessOnRight[i] - firstLessOnLeft[i] - 1);
            res = Math.max(res, curMax);
        }
        return res;
    }
}