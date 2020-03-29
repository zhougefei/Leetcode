class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1 || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peek() < i-k+1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i-k+1 >= 0) {
                res[i-k+1] = nums[deque.peek()];
            }
        }
        return res;
    }
}