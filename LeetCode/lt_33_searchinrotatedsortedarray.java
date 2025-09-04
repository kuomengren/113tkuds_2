/*
# 033. Search in Rotated Sorted Array

## 🧠 題目說明
給定一個被旋轉過的升序整數陣列 nums，並給定一個目標值 target，請在陣列中找到 target 的索引。
若不存在則回傳 -1。要求時間複雜度為 O(log n)。

---

## 💡 解題思路（改良版 Binary Search）

1. 初始化左右指標 left、right。
2. 使用 binary search。
3. 判斷哪一邊是有序的：
   - 如果左邊有序且 target 落在該區間，就繼續搜尋左邊。
   - 否則搜尋右邊。
4. 如果右邊有序，則反向處理。

---

## 🧪 範例測資：
nums = [4,5,6,7,0,1,2], target = 0  → Output: 4  
nums = [4,5,6,7,0,1,2], target = 3  → Output: -1  
nums = [1], target = 0              → Output: -1
*/

public class lt_33_searchinrotatedsortedarray {

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int result = sol.search(nums, target);
        System.out.println("✔️ 搜尋結果 index 為: " + result); // Output: 4
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // ✅ 改良版 binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到了
            if (nums[mid] == target) return mid;

            // 左半邊是有序的
            if (nums[left] <= nums[mid]) {
                // target 在左半邊
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 右半邊是有序的
            else {
                // target 在右半邊
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // 沒找到
        return -1;
    }
}
