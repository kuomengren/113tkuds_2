
import java.util.*;

public class lt_18_4sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums); // 先排序

        int n = nums.length;

        // 外層第一層：固定第一個數
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重

            // 第二層：固定第二個數
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重

                int left = j + 1;
                int right = n - 1;

                // 雙指標
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 移動 left 並跳過重複值
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;

                        // 移動 right 並跳過重複值
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    } else if (sum < target) {
                        left++; // 太小往右找
                    } else {
                        right--; // 太大往左找
                    }
                }
            }
        }

        return result;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_18_4sum solution = new lt_18_4sum();

        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println("Result 1: " + solution.fourSum(nums1, target1));

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Result 2: " + solution.fourSum(nums2, target2));
    }
}

/*
解題思路：
1. 將陣列排序後，使用四層結構：兩層 for + 雙指標掃描（類似 3Sum）。
2. 固定前兩個數字 i、j，接著使用雙指標從 j+1 到尾端找出總和符合 target。
3. 若 sum == target，加入答案，並跳過重複值避免重複組合。
4. 若 sum < target，left++；若 sum > target，right--。
5. 注意使用 long 處理總和，避免整數溢位。
6. 時間複雜度為 O(n³)，空間複雜度為 O(1)。
*/
