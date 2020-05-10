class TimeMap {

    Map<String, List<Pair>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            List<Pair> list = new ArrayList<>();
            list.add(new Pair(timestamp, value));
            map.put(key, list);
        } else {
            map.get(key).add(new Pair(timestamp, value));
        }
    }
    
    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {
            List<Pair> list = map.get(key);
            int low = 0, high = list.size()-1;
            int maxPreLocation = 0;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid).timestamp == timestamp) {
                    return list.get(mid).value;
                } else if (list.get(mid).timestamp < timestamp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            maxPreLocation = high;
            return maxPreLocation < 0 ? "" : list.get(maxPreLocation).value;    
        } else {
            return "";
        }
    }
}

class Pair {
    String value;
    int timestamp;
    public Pair (int timestamp, String value) {
        this.value = value;
        this.timestamp = timestamp;
    }
}