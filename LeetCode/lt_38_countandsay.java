/*
# 038. Count and Say

## 🧠 題目說明：
countAndSay(1) = "1"  
countAndSay(n) 是 countAndSay(n-1) 的 Run-Length Encoding 結果。

範例：
countAndSay(4) = RLE("21") ➜ "1211"  
因為 countAndSay(3) = "21" 表示：一個 2、一個 1

---

## 💡 解題邏輯：
1. 初始為 "1"
2. 每次迴圈處理上一層字串，統計連續字元的數量
3. 輸出為：「出現次數 + 字元」組合串起來
4. 執行 n-1 次產生第 n 層

---

## ⏱ 時間複雜度：
O(n * m)，其中 n 是層數，m 為每層字串長度（大約 O(n²)）

---

## 🧪 範例輸出：
countAndSay(1) ➜ "1"  
countAndSay(2) ➜ "11"  
countAndSay(3) ➜ "21"  
countAndSay(4) ➜ "1211"  
countAndSay(5) ➜ "111221"
*/

public class lt_38_countandsay {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試輸出 1~6 層的 Count and Say
        for (int n = 1; n <= 6; n++) {
            String result = sol.countAndSay(n);
            System.out.println("countAndSay(" + n + ") = " + result);
        }
    }
}

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";  // base case

        String result = "1";

        // 從第 2 層開始構造，直到第 n 層
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char prev = result.charAt(0);
            int count = 1;

            for (int j = 1; j < result.length(); j++) {
                char curr = result.charAt(j);

                if (curr == prev) {
                    count++; // 相同數字持續出現
                } else {
                    sb.append(count).append(prev); // 輸出累積的數字
                    prev = curr;
                    count = 1;
                }
            }

            sb.append(count).append(prev); // 最後一組也要處理
            result = sb.toString(); // 更新成新一層的結果
        }

        return result;
    }
}
