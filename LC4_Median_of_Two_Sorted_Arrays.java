class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int left = 0, right = n1;
        while (left <= right) {
            int partitionX = left + (right-left)/2;
            int partitionY = (n1+n2+1) / 2 - partitionX;
        
            int maxInLA = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX-1];
            int minInRA = partitionX == n1 ? Integer.MAX_VALUE : nums1[partitionX];
            int maxInLB = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY-1];
            int minInRB = partitionY == n2 ? Integer.MAX_VALUE : nums2[partitionY];
        
            if (maxInLA <= minInRB && maxInLB <= minInRA) {
                if ((n1+n2) % 2 == 0) {
                    return (double) (Math.max(maxInLA, maxInLB) + Math.min(minInRA, minInRB))/2;
                } else {
                    return (double) Math.max(maxInLA, maxInLB);
                }
            } else if (maxInLB > minInRA) {
                left = partitionX+1;
            } else {
                right = partitionX-1;
            }
        }
        return 0.0;
    }
}