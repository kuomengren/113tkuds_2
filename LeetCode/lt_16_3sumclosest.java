
import java.util.Arrays;

public class lt_16_3sumclosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        int closest = nums[0] + nums[1] + nums[2]; // 初始化最接近的總和

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            // 雙指標向中間靠攏
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 若此組合更接近 target，就更新 closest
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                // 根據總和與 target 的比較移動指標
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 完美相等，直接回傳
                }
            }
        }

        return closest;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_16_3sumclosest solution = new lt_16_3sumclosest();

        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Closest sum: " + solution.threeSumClosest(nums1, target1)); // 2

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Closest sum: " + solution.threeSumClosest(nums2, target2)); // 0
    }
}

/*
解題思路：
1. 將陣列排序後，固定一個數 nums[i]，搭配左右指標掃描剩下的數。
2. 每次計算 sum = nums[i] + nums[left] + nums[right]，若更接近 target，更新結果。
3. 根據 sum 與 target 的大小調整左右指標（若 sum 太小則 left++，太大則 right--）。
4. 若恰好 sum == target，表示已達最佳解可直接回傳。
5. 時間複雜度為 O(n²)，空間複雜度為 O(1)。
*/
