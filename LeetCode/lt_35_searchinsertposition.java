/*
# 035. Search Insert Position

## 🧠 題目說明：
給定一個升序整數陣列 `nums`（不含重複值）與一個目標值 `target`，
請回傳：
- 若 `target` 存在 → 回傳其索引。
- 若 `target` 不存在 → 回傳它應插入的位置（保持升序）。

---

## 💡 解題思路（Binary Search）

- 使用 Binary Search 找到第一個 ≥ target 的位置
- 若等於 target 就直接回傳
- 若不等於 → 該位置就是應插入的 index

時間複雜度：O(log n)  
空間複雜度：O(1)

---

## 🧪 測資：
Input: nums = [1,3,5,6], target = 5 ➜ Output: 2  
Input: nums = [1,3,5,6], target = 2 ➜ Output: 1  
Input: nums = [1,3,5,6], target = 7 ➜ Output: 4  
*/

public class lt_35_searchinsertposition {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 3, 5, 6};

        System.out.println("✔️ 插入位置: " + sol.searchInsert(nums, 5)); // 2
        System.out.println("✔️ 插入位置: " + sol.searchInsert(nums, 2)); // 1
        System.out.println("✔️ 插入位置: " + sol.searchInsert(nums, 7)); // 4
    }
}

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Binary Search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到 target
            } else if (nums[mid] < target) {
                left = mid + 1; // 搜右邊
            } else {
                right = mid - 1; // 搜左邊
            }
        }

        // 沒找到，left 是插入點
        return left;
    }
}
