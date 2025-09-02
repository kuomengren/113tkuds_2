public class  lt_06_zigzag {

    static class Solution {
        public String convert(String s, int numRows) {
            if (numRows == 1 || s.length() <= numRows) return s;

            StringBuilder[] rows = new StringBuilder[numRows];
            for (int i = 0; i < numRows; i++) {
                rows[i] = new StringBuilder();
            }

            int r = 0;       // 當前 row
            int dir = 1;     // 方向：1 = 往下，-1 = 往上

            for (int i = 0; i < s.length(); i++) {
                rows[r].append(s.charAt(i));
                if (r == 0) dir = 1;                 // 碰到頂 → 往下
                else if (r == numRows - 1) dir = -1; // 碰到底 → 往上
                r += dir;
            }

            StringBuilder ans = new StringBuilder();
            for (StringBuilder sb : rows) ans.append(sb);
            return ans.toString();
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.convert("PAYPALISHIRING", 3));
        // 預期輸出: "PAHNAPLSIIGYIR"

        System.out.println(sol.convert("PAYPALISHIRING", 4));
        // 預期輸出: "PINALSIGYAHRPI"

        System.out.println(sol.convert("A", 1));
        // 預期輸出: "A"
    }
}
