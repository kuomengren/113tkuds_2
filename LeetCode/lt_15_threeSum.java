// 題目：15. 3Sum
// 回傳所有不重複三元組 (a,b,c) 使 a+b+c=0。
// 解法：先排序，固定 i，對區間(i+1..n-1) 用雙指針找 two-sum = -nums[i]，並跳過重複元素。
// 時間 O(n^2)，空間 O(1)（不含輸出）

import java.util.*;

public class lt_15_threeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue; // 跳重複的 i
            int target = -nums[i];
            int l = i + 1, r = n - 1;

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 跳過重複的 l、r
                    int lv = nums[l], rv = nums[r];
                    while (l < r && nums[l] == lv) l++;
                    while (l < r && nums[r] == rv) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4})); // [[-1,-1,2],[-1,0,1]]
        System.out.println(threeSum(new int[]{0,1,1}));          // []
        System.out.println(threeSum(new int[]{0,0,0}));          // [[0,0,0]]
    }
}
/*
解題思路：
1. 排序後固定 i，右側用雙指針找和為 -nums[i] 的 pair。
2. 透過跳過重複的 i、l、r，避免答案重複。
*/
