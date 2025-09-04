/*
# 036. Valid Sudoku

## 🧠 題目說明：
判斷一個 9x9 的數獨盤面是否有效：
1️⃣ 每一行（row）不可出現重複數字  
2️⃣ 每一列（column）不可出現重複數字  
3️⃣ 每個 3x3 區塊不可出現重複數字  
🔸 空格以 '.' 表示，無需驗證  
🔸 數獨不一定要可解，只需檢查目前盤面是否有效  

---

## 💡 解題思路：
使用 HashSet 分別記錄：
- 每列是否有重複
- 每行是否有重複
- 每個 3x3 區塊是否有重複

每行每列每區塊掃一次即可。

---

## ⏱ 時間複雜度：O(1)
因為固定是 9x9 大小 → 操作次數最多 81 次  
空間複雜度：O(1)

---

## 🧪 範例測資（輸出 true）
[
 ['5','3','.','.','7','.','.','.','.'],
 ['6','.','.','1','9','5','.','.','.'],
 ['9','8','.','.','.','.','.','6','.'],
 ['8','.','.','.','6','.','.','.','3'],
 ['4','.','.','8','.','3','.','.','1'],
 ['7','.','.','.','2','.','.','.','6'],
 ['.','6','.','.','.','.','2','8','.'],
 ['.','.','.','4','1','9','.','.','5'],
 ['.','.','.','.','8','.','.','7','9']
]
*/

import java.util.HashSet;

public class lt_36_validsudoku {
    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'9','8','.','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        boolean result = sol.isValidSudoku(board);
        System.out.println("✔️ 數獨是否合法？: " + result); // true
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 逐行、逐列、逐區塊檢查
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rowSet = new HashSet<>();
            HashSet<Character> colSet = new HashSet<>();
            HashSet<Character> blockSet = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                // ✅ 檢查 row
                char rowChar = board[i][j];
                if (rowChar != '.') {
                    if (rowSet.contains(rowChar)) return false;
                    rowSet.add(rowChar);
                }

                // ✅ 檢查 col
                char colChar = board[j][i];
                if (colChar != '.') {
                    if (colSet.contains(colChar)) return false;
                    colSet.add(colChar);
                }

                // ✅ 檢查 3x3 區塊（box）
                int rowIndex = 3 * (i / 3) + (j / 3); // box 的 row 座標
                int colIndex = 3 * (i % 3) + (j % 3); // box 的 col 座標
                char blockChar = board[rowIndex][colIndex];
                if (blockChar != '.') {
                    if (blockSet.contains(blockChar)) return false;
                    blockSet.add(blockChar);
                }
            }
        }

        return true; // 都沒重複就是有效數獨
    }
}
