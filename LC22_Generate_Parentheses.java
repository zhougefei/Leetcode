// Solution 1 : StringBuilder is similar to a++;
//              After a backtrack is finished we need to a--   
//             if (open < n) {
 //                backtrack(res, a++, open+1, close, n);
//                 a--;
//             }
//             if (close < open) {
//                 backtrack(res, a++, open, close+1, n);
//                 a--;
//             }          
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        backtrack(res, new StringBuilder() , 0, 0, n);
        return res;
    }
    private void backtrack(List<String> res, StringBuilder cur, int open, int close, int n) {
        if (open == n && close == n) {
            res.add(cur.toString());
            return;
        }
        if (open < n) {
            backtrack(res, cur.append("("), open+1, close, n);
            cur.setLength(cur.length()-1);
        }
        if (close < open) {
            backtrack(res, cur.append(")"), open, close+1, n);
            cur.setLength(cur.length()-1);
        }
    }
}

// Solution 2 : String is similar to a+1;
//              After a backtrack is finished we do not need to a-1  
//             if (open < n) {
//                 backtrack(res, a+1, open+1, close, n);
//             }
//             if (close < open) {
//                 backtrack(res, a-1, open, close+1, n);
//             }  
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        backtrack(res, "" , 0, 0, n);
        return res;
    }
    private void backtrack(List<String> res, String cur, int open, int close, int n) {
        if (open == n && close == n) {
            res.add(cur);
            return;
        }
        if (open < n) {
            backtrack(res, cur+"(", open+1, close, n);
        }
        if (close < open) {
            backtrack(res, cur+")", open, close+1, n);
        }
    }
}    