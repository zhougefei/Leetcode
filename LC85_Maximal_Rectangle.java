class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int[] newArray = new int[col];
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == '1') {
                newArray[j] = 1;
            } else {
                newArray[j] = 0;
            }
        }
        int res = maxRectArea(newArray);
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    newArray[j]++;
                } else {
                    newArray[j] = 0;
                }    
            }
            res = Math.max(res, maxRectArea(newArray));
        }
        return res;
    }
    
    private int maxRectArea(int[] heights){
        int res = 0;
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len; i++) {
            int cur = (i ==len) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= cur) {
                int curHeight = heights[stack.pop()];
                int maxWidth = stack.isEmpty() ? i : i - stack.peek() - 1;
                int curMax = maxWidth * curHeight;
                res = Math.max(res, curMax);
            }
            stack.push(i);
        }
        return res;
    }
}