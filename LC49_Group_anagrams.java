// Brute Force Algorithm    time complexity O(n^2*m)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        boolean[] marked = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (marked[i]) {
                continue;
            }
            List<String> comp = new ArrayList<>();
            comp.add(strs[i]);
            for (int j = i+1; j < strs.length; j++) {
                if (isAnagrams(strs[i], strs[j])) {
                    marked[j] = true;
                    comp.add(strs[j]);
                }
            }
            res.add(comp);
        }
        return res;
    }
    
    private boolean isAnagrams(String p, String q) {
        if (p.length() != q.length()) {
            return false;
        }
        int[] pArr = new int[26];
        int[] qArr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char cp = p.charAt(i);
            char cq = q.charAt(i);
            pArr[cp - 'a']++;
            qArr[cq - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (pArr[i] != qArr[i]) {
                return false;
            }
        }
        return true;
    }
}

// improved algorithm
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cArr = s.toCharArray();
            Arrays.sort(cArr);
            String sortStr = new String(cArr);
            if (!map.containsKey(sortStr)) {
                map.put(sortStr, new ArrayList<String>());
            }
            map.get(sortStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}