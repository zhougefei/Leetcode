class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] maxProAtCur = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= prices[i-1]) {
                maxProAtCur[i] = maxProAtCur[i-1];
            } else {
                maxProAtCur[i] = maxProAtCur[i-1] + prices[i] - prices[i-1];
            }
        }
        return maxProAtCur[prices.length-1];
    }
}