class Solution {
    
    public void gameOfLife(int[][] board) {
        Map<Integer, Integer> map = new HashMap<>();
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length, col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map.put(i*col + j, countLiveNeighbor(i, j, board));
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int cur = i*col+j;
                if (board[i][j] == 1) {
                    if (map.get(cur) < 2 || map.get(cur) > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (map.get(cur) == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
    
    private int countLiveNeighbor(int i, int j, int[][] board) {
        int row = board.length, col = board[0].length;
        int[][] dirs = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1} , {0, 1}, {-1, 1}, {-1, 0}}; 
        int count = 0;
        for (int k = 0; k < 8; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if (x < 0 || x >= row || y < 0 || y >= col) {
                continue;
            }
            if (board[x][y] == 1) {
                count++;
            }
        }
        return count;
    }
}