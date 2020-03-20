// DFS Solution
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    
    private void dfs(char[][] grid, int i , int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
}

// BFS Solution
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    
    private void bfs(char[][] grid, int i, int j){
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i*grid[0].length + j);
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int row = cur / grid[0].length;
            int col = cur % grid[0].length;
            for (int k = 0; k < 4; k++) {
                int x = row + dirs[k][0];
                int y = col + dirs[k][1];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
                    continue;
                }
                queue.add(x*grid[0].length + y);
                grid[x][y] = '0';
            }
        }
    }
}

//  Union Find Solution
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        UF uf = new UF(grid);
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dirs[k][0];
                        int y = j + dirs[k][1];
                        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == '0') {
                            continue;
                        }
                        uf.union(i*col+j, x*col+y);
                    }
                }
            }
        }
        return uf.count;
    }
}

class UF {
    int[] id;
    int[] size;
    int count = 0;
    public UF(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        id = new int[row*col];
        size = new int[row*col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                id[i*col+j] = i*col+j;
                size[i*col+j] = 1;
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
    }
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }
    public void union (int p, int q) {
        int rootp = find(p);
        int rootq = find(q);
        if (rootp == rootq) {
            return;
        }
        if (size[rootp] < size[rootq]) {
            id[rootp] = rootq;
            size[rootq] += size[rootp];
            size[rootp] = 0;
        } else {
            id[rootq] = rootp;
            size[rootp] += size[rootq];
            size[rootq] = 0;
        }
        count--;
    }
}