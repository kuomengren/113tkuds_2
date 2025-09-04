import java.util.Stack;

/*
# 032. Longest Valid Parentheses

## 🧠 題目說明：
給一個只包含 '(' 和 ')' 的字串，找出其中最長的合法括號子字串長度。

---

## 💡 解題思路（使用 Stack）：
1. 初始化 stack，並放入基準點 -1。
2. 遇到 '(' → push index。
3. 遇到 ')' → pop 並計算合法區間長度（若 stack 為空，代表無效 → push 當前 index 當作新的基準）。
4. 每次匹配成功就更新 maxLength。

時間複雜度：O(n)
空間複雜度：O(n)

---

## 🧪 測資範例：
Input: s = "(()"        → Output: 2  
Input: s = ")()())"     → Output: 4  
Input: s = ""           → Output: 0
*/

public class lt_32_longestvalidparentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = ")()())";
        int result = sol.longestValidParentheses(s);
        System.out.println("✔️ 最長合法括號長度為：" + result); // Output: 4
    }
}

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);  // 初始基準點
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);  // 無效括號，設為新基準
                } else {
                    int len = i - stack.peek();
                    maxLength = Math.max(maxLength, len);
                }
            }
        }

        return maxLength;
    }
}