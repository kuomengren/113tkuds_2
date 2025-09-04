import java.util.Arrays;

/*
# 031. Next Permutation

## 🧠 題目說明
給定一個整數陣列 nums，請找出「下一個排列」(next permutation)，
也就是字典順序上比目前排列大的最小排列。

如果沒有更大的排列（例如 [3,2,1]），就將陣列重新排序為最小排列（升序）。

要求：
- 必須使用 in-place 操作
- 僅使用常數額外空間

---

## 💡 解題思路（經典三步驟）
1. 從右往左找出第一個下降點 i，使得 nums[i] < nums[i + 1]
2. 再從右往左找出第一個大於 nums[i] 的數字 j，交換 i 與 j
3. 最後將 i+1 到陣列尾部反轉（原為降序 → 升序）

---

## 🧪 測資範例
Input: [1, 2, 3] → Output: [1, 3, 2]  
Input: [3, 2, 1] → Output: [1, 2, 3]  
Input: [1, 1, 5] → Output: [1, 5, 1]
*/

public class lt_31_nextpermutation {

    public static void main(String[] args) {
        // 🧪 測資：最常見的排列遞增情境
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.out.println("✔️ 下一個排列為：" + Arrays.toString(nums)); // Output: [1, 3, 2]
    }

    /**
     * 主邏輯函式：計算下一個排列（in-place）
     * @param nums 輸入的整數陣列
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;

        // ✅ Step 1：從右往左找第一個下降點 nums[i] < nums[i + 1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // ✅ Step 2：從後往前找第一個比 nums[i] 大的數字並交換
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // ✅ Step 3：將 i+1 到 n-1 之間的數字反轉（讓右半邊變成最小升序）
        reverse(nums, i + 1, n - 1);
    }

    // 工具函式：交換 nums[i] 與 nums[j]
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 工具函式：反轉 nums 的一個區間 [start, end]
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
