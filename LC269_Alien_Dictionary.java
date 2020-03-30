public class Solution {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = createGraph(words);
        int[] indegree = getIndegree(graph);
        String order = topoSort(graph, indegree);
        return order.length() == graph.size() ? order : "";
    }
    
    private Map<Character, Set<Character>> createGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                graph.put(word.charAt(j), new HashSet<>());
            }
        }
        
        for (int i = 0; i < words.length-1; i++) {
            char[] curr = words[i].toCharArray();
            char[] next = words[i+1].toCharArray();
            int len = Math.min(curr.length, next.length);
            for (int j = 0; j < len; j++) {
                if (curr[j] != next[j]) {
                    if (!graph.get(curr[j]).contains(next[j])) {
                        graph.get(curr[j]).add(next[j]);
                    }
                    break;
                }
            }
        }
        return graph;
    }
    
    private int[] getIndegree(Map<Character, Set<Character>> graph) {
        int[] indegree = new int[26];
        for (char c : graph.keySet()) {
            for (char neighbor : graph.get(c)) {
                indegree[neighbor-'a']++;
            }
        }
        return indegree;
    }
    
    private String topoSort(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder res = new StringBuilder();
        Queue<Character> queue = new PriorityQueue<>();
        for (char c : graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res.append(c);
            for (char neighbor : graph.get(c)) {
                indegree[neighbor-'a']--;
                if (indegree[neighbor-'a'] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return res.toString();
    }
}