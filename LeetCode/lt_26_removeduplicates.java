public class lt_26_removeduplicates {

    // 主方法：移除排序陣列中重複元素（就地修改）
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0; // 特例處理：空陣列

        int i = 0; // 慢指標：記錄不重複元素的最後位置

        // 快指標 j：從第 1 個元素開始往後掃
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;               // 找到新元素，慢指標往右移
                nums[i] = nums[j]; // 把新元素放到前面
            }
        }

        return i + 1; // 回傳不重複的元素數量（index + 1）
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_26_removeduplicates solution = new lt_26_removeduplicates();

        int[] nums1 = {1, 1, 2};
        int k1 = solution.removeDuplicates(nums1);
        printResult(nums1, k1); // ➜ k = 2, nums = [1, 2]

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solution.removeDuplicates(nums2);
        printResult(nums2, k2); // ➜ k = 5, nums = [0, 1, 2, 3, 4]
    }

    // 工具方法：列印結果陣列（前 k 個有效元素）
    public static void printResult(int[] nums, int k) {
        System.out.print("k = " + k + ", nums = [");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i]);
            if (i < k - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

/*
📘 解題說明：
1. 題目：給定排序陣列，移除重複元素，使每個元素最多出現一次。
2. 限制：須在原地修改陣列，不能使用額外空間。
3. 解法：使用雙指標（慢指標 i, 快指標 j）
   - i 指向不重複元素的最後一位
   - j 每次找到新元素時就把它覆蓋到 i+1 的位置
4. 回傳值為不重複元素的個數，即 i + 1。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
