
public class lt_04_median {

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
            int m = nums1.length, n = nums2.length;
            int totalLeft = (m + n + 1) / 2;

            int lo = 0, hi = m;
            while (lo <= hi) {
                int i = (lo + hi) / 2;
                int j = totalLeft - i;

                int Aleft  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
                int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];
                int Bleft  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
                int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

                if (Aleft <= Bright && Bleft <= Aright) {
                    if (((m + n) & 1) == 1) return Math.max(Aleft, Bleft);
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                } else if (Aleft > Bright) {
                    hi = i - 1;
                } else {
                    lo = i + 1;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));       // 2.0
        System.out.println(s.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));     // 2.5
        System.out.println(s.findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));     // 0.0
        System.out.println(s.findMedianSortedArrays(new int[]{}, new int[]{1}));          // 1.0
        System.out.println(s.findMedianSortedArrays(new int[]{2}, new int[]{1,3,4,5}));   // 3.0
    }
}