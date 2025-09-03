
import java.util.*;

public class lt_22_generateparentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    // 回溯函式
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // 終止條件：長度達到 2 * n
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // 可以加左括號的情況（還沒用完）
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }

        // 可以加右括號的情況（目前右括號數 < 左括號數）
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_22_generateparentheses solution = new lt_22_generateparentheses();

        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(1));
    }
}

/*
解題思路：
1. 使用回溯法（Backtracking）從空字串開始構造。
2. 若目前左括號 < n，就可以加 '('。
3. 若目前右括號 < 左括號，就可以加 ')'。
4. 當字串長度達到 2n 時，即為合法結果。
5. 每次 append 都必須記得做回溯（delete 最後一個字元）。
6. 時間複雜度為 O(2^n)，實際生成數為第 n 個卡塔蘭數（Catalan number）。
*/
