class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Map<String, List<String>> map = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, map, dict);
    }
    
    private List<String> dfs(String s, Map<String, List<String>> map, Set<String> dict) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (dict.contains(s)) {
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i, s.length());
            if (dict.contains(left) && dfs(right, map, dict) != null) {
                for (String comp : dfs(right, map, dict)) {
                    res.add(left+" "+comp);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}