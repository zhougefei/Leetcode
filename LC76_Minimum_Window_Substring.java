class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = createMap(t);
        int left = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        int counter = 0;
        for (int right = 0; right < s.length(); right++) {
            char ri = s.charAt(right);
            Integer freL = map.get(ri);
            if (freL == null) {
                continue;
            }
            map.put(ri, freL - 1);
            if (freL == 1) {
                counter++;
            }
            while (counter == map.size()) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    start = left;
                }
                char le = s.charAt(left++);
                Integer freR = map.get(le);
                if (freR == null) {
                    continue;
                }
                map.put(le, freR + 1);
                if (freR == 0) {
                    counter--;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start+minLen);
    }
    
    private Map<Character, Integer> createMap(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            Integer count = map.get(c);
            if (count == null) {
                map.put(c, 1);
            } else {
                map.put(c, count+1);
            }
        }
        return map;
    }
}