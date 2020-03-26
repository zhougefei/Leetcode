class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] maxProAfterFirst = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProAfterFirst[i] = Math.max(maxProAfterFirst[i-1], prices[i]-min);
        }
        int maxOnRight = 0;
        int maxPro = 0;
        for (int i = prices.length-1; i >= 0; i--) {
            maxOnRight = Math.max(maxOnRight, prices[i]);
            int maxProAfterSecond = maxOnRight - prices[i];
            maxPro = Math.max(maxPro, maxProAfterFirst[i]+maxProAfterSecond);
        }
        return maxPro;
    }
}