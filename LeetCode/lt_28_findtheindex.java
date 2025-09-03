public class lt_28_findtheindex {

    // 主方法：實作 strStr() 找子字串第一次出現的位置
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 邊界情況：若 needle 是空字串，依題意回傳 0（雖然題目說不會發生）
        if (m == 0) return 0;

        // 主迴圈：從 haystack 的每個起點 i 嘗試比對 needle
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++; // 逐字比對
            }
            if (j == m) {
                return i; // 全部比對成功，回傳起始 index
            }
        }

        return -1; // 若沒有找到，回傳 -1
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_28_findtheindex solution = new lt_28_findtheindex();

        System.out.println(solution.strStr("sadbutsad", "sad"));     // ➜ 0
        System.out.println(solution.strStr("leetcode", "leeto"));    // ➜ -1
        System.out.println(solution.strStr("abc", "c"));             // ➜ 2
        System.out.println(solution.strStr("abc", ""));              // ➜ 0（特例）
    }
}

/*
📘 解題說明：
1. 題目要求：找出 needle 第一次出現在 haystack 中的位置，若沒找到回傳 -1。
2. 解法為「暴力滑動比對」：
   - 外層 i 為起點，最多跑到 n - m（剩下的長度要夠）
   - 內層 j 比對 needle 每個字元，若全部吻合就回傳該起點 i
3. 若都找不到則回傳 -1。
4. 時間複雜度：O(n * m)，其中 n 為 haystack 長度，m 為 needle 長度。
5. 題目有定義：若 needle 是空字串，需回傳 0（但 LeetCode 通常保證不會發生）
*/
