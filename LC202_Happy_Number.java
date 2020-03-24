class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = digitSum(slow);
            fast = digitSum(fast);
            if (fast == 1  || fast == 7) {
                return true;
            }
            fast = digitSum(fast);
            if (fast == 1  || fast == 7) {
                return true;
            }
        } while (slow != fast); 
        return false;
    }
    
    private int digitSum(int n) {
        int sum = 0;
        while (n != 0) {
            int temp = n % 10;
            sum += temp * temp;
            n /= 10;
        }
        return sum;
    }
}