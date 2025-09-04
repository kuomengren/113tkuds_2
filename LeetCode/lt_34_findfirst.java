/*
# 034. Find First and Last Position of Element in Sorted Array

## 🧠 題目說明：
給定一個非遞減排序的整數陣列 `nums`，與一個目標值 `target`。
請找出 `target` 的第一個與最後一個出現位置。
若找不到，回傳 [-1, -1]。

### 條件限制：
- 必須使用 O(log n) 的時間複雜度。
- nums 可能為空。

---

## 💡 解題思路（Binary Search 二次）：
1. 使用 binary search 找出 **第一個出現** 的 index。
2. 再使用 binary search 找出 **最後一個出現** 的 index。
3. 若找不到，回傳 [-1, -1]。

時間複雜度：O(log n)  
空間複雜度：O(1)

---

## 🧪 測資範例：
nums = [5,7,7,8,8,10], target = 8 ➜ Output: [3, 4]  
nums = [5,7,7,8,8,10], target = 6 ➜ Output: [-1, -1]  
nums = [], target = 0 ➜ Output: [-1, -1]
*/

public class lt_34_findfirst {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int[] result = sol.searchRange(nums, target);
        System.out.println("✔️ 目標值出現範圍為: [" + result[0] + ", " + result[1] + "]");
    }
}

// ✅ 核心解法類別
class Solution {

    // 主函式：回傳 target 的第一與最後出現 index
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findBound(nums, target, true);   // 找左邊界
        result[1] = findBound(nums, target, false);  // 找右邊界
        return result;
    }

    // 二分搜尋左右邊界通用函式（isFirst 為 true 表示找左邊界）
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid; // 記錄可能結果
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1; // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }
}
