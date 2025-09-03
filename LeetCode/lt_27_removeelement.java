public class lt_27_removeelement {

    // 主函式：移除指定元素 val，並就地覆蓋原陣列
    public int removeElement(int[] nums, int val) {
        int i = 0; // 慢指標，記錄下一個有效值要放置的位置

        // 快指標 j：逐一檢查每個元素
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j]; // 將有效值往前放
                i++;               // 慢指標右移
            }
        }

        return i; // 回傳剩下不為 val 的元素個數
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_27_removeelement solution = new lt_27_removeelement();

        int[] nums1 = {3, 2, 2, 3};
        int k1 = solution.removeElement(nums1, 3);
        printResult(nums1, k1); // ➜ k = 2, nums = [2, 2]

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = solution.removeElement(nums2, 2);
        printResult(nums2, k2); // ➜ k = 5, nums = [0, 1, 3, 0, 4]（順序不限）
    }

    // 輔助函式：列印前 k 個結果值
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
1. 題目要求從陣列中移除所有值為 val 的元素，並回傳剩下的元素數量。
2. 限制：必須在原地修改陣列，不能額外使用空間。
3. 解法：使用快慢指標
   - 快指標 j：掃描整個陣列
   - 慢指標 i：只記錄有效元素（非 val）
4. 每次遇到 nums[j] != val，就將 nums[j] 放到 nums[i] 的位置，然後 i++。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
