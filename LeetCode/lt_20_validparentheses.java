
import java.util.*;

public class lt_20_validparentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // 每個字元逐一處理
        for (char c : s.toCharArray()) {
            // 遇到開括號就放入堆疊
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            // 遇到閉括號，檢查是否與頂端配對
            else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // 最後堆疊應該為空，代表所有括號都有正確關閉
        return stack.isEmpty();
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_20_validparentheses solution = new lt_20_validparentheses();

        System.out.println(solution.isValid("()"));       // true
        System.out.println(solution.isValid("()[]{}"));   // true
        System.out.println(solution.isValid("(]"));       // false
        System.out.println(solution.isValid("([])"));     // true
        System.out.println(solution.isValid("([)]"));     // false
    }
}

/*
解題思路：
1. 使用 Stack（堆疊）記錄左括號。
2. 每遇到一個右括號，就檢查堆疊頂端是否為對應的左括號。
3. 若對應錯誤或堆疊為空則為非法字串。
4. 全部處理完後 stack 應為空才代表全部配對完成。
5. 時間複雜度 O(n)，空間複雜度 O(n)。
*/
