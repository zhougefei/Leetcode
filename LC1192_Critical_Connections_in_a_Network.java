class Solution {
    int rank = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visitRank = new int[n];       Arrays.fill(visitRank, -1);
        int[] lowestRank = new int[n];      Arrays.fill(lowestRank, -1);
        // Make a graph
        List<Integer>[] graph = graphMaker(n, connections);
        for (int i = 0; i < n; i++) {
            if (lowestRank[i] == -1) {
                dfs(i, i, graph, visitRank, lowestRank, res);
            }
        }
        return res;
    }
    
    private List<Integer>[] graphMaker(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> edge : connections) {
            int v1 = edge.get(0);
            int v2 = edge.get(1);
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        return graph;
    }
    
    private void dfs(int curV, int parent, List<Integer>[] graph, int[] visitRank, int[] lowestRank, List<List<Integer>> res) {
        visitRank[curV] = lowestRank[curV] = rank++;
        for (int neighbor : graph[curV]) {
            if (lowestRank[neighbor] == -1) {
                dfs(neighbor, curV, graph, visitRank, lowestRank, res);
                if (lowestRank[neighbor] > visitRank[curV]) {                  
                    res.add(Arrays.asList(neighbor, curV));
                }
                lowestRank[curV] = Math.min(lowestRank[curV], lowestRank[neighbor]);
            } else if (neighbor != parent) {
                lowestRank[curV] = Math.min(lowestRank[curV], visitRank[neighbor]);
            }
        }
    }
}