class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if (paths == null || paths.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] comp = path.split(" ");
            int len = comp.length;
            String dir = comp[0];
            for (int i = 1; i < len; i++) {
                String[] info = comp[i].split("\\(");
                String fileName = info[0];
                String content = info[1];
                List<String> list = map.getOrDefault(content, new ArrayList<>());
                list.add(dir+"/"+fileName);
                map.put(content, list);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                res.add(map.get(key));
            }
        }
        return res;
    }
}