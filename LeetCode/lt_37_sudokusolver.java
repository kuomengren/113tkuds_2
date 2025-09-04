/*
# 037. Sudoku Solver

## 🧠 題目說明：
給一個未完成的 9x9 數獨盤面，請幫它「就地填數」使其變成合法數獨。

### 數獨規則：
1. 每行只能出現一次 1-9
2. 每列只能出現一次 1-9
3. 每個 3x3 的區塊只能出現一次 1-9
4. 空格用 '.' 表示

---

## 💡 解題思路（Backtracking 回溯法）

1. 從左上角開始找空格（'.'）
2. 對這格嘗試填入 1~9
3. 若該數合法（不衝突），就繼續往下一格遞迴
4. 若之後無解，則回溯（Backtrack）還原成 '.'，再試下一個數字
5. 若整張盤都填完 → 回傳即可（題目保證有解）

時間複雜度：O(9^(空格數))  
空間複雜度：O(1)（直接修改輸入陣列）

---

## 🧪 範例輸入（已給解）
[
 ["5","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 ["9","8",".",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]
]
*/

public class lt_37_sudokusolver {
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

        sol.solveSudoku(board);

        System.out.println("✔️ 解出的數獨盤如下：");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    // ✅ 回溯主邏輯：嘗試填每個空格
    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // 找到空格就嘗試填數
                if (board[row][col] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c; // 填入
                            if (backtrack(board)) return true; // 繼續遞迴
                            board[row][col] = '.'; // 回溯還原
                        }
                    }
                    return false; // 9 個數都試過都不行，這層失敗
                }
            }
        }
        return true; // 沒有空格了，代表解完了
    }

    // ✅ 驗證填入的數字是否符合數獨規則
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 同一列
            if (board[row][i] == c) return false;
            // 同一行
            if (board[i][col] == c) return false;
            // 同一個 3x3 區塊
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}
