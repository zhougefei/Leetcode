class Solution {
    private final String[] LESSTHAN20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                res.insert(0, THOUSANDS[i] + " ");
                res.insert(0, helper(num % 1000));
            }
            i++;
            num /= 1000;
        }
        return res.toString().trim();
    }
    
    private String helper(int n) {
        if (n == 0) {
            return "";
        } else if (n < 20) {
            return LESSTHAN20[n] + " ";
        } else if (n < 100) {
            return TENS[n/10] + " " + helper(n % 10);
        } else {
            return LESSTHAN20[n/100] + " Hundred " + helper(n % 100);
        }
    }
}