
import java.util.*;

public class lt_17_lettercombination {

    // 數字與字母的對應關係（index 對應 0~9）
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        // 特殊情況：空字串直接回傳空結果
        if (digits == null || digits.length() == 0) return result;

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    // 回溯函式
    private void backtrack(List<String> result, StringBuilder path, String digits, int index) {
        // 當組合長度與輸入長度一致時，加入結果
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        // 取得目前數字對應的字母字串
        String letters = KEYPAD[digits.charAt(index) - '0'];

        // 遍歷對應的每個字母
        for (char c : letters.toCharArray()) {
            path.append(c); // 做選擇
            backtrack(result, path, digits, index + 1); // 繼續遞迴
            path.deleteCharAt(path.length() - 1); // 撤銷選擇
        }
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_17_lettercombination solution = new lt_17_lettercombination();

        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("2"));
    }
}

/*
解題思路：
1. 題目本質為「多層迴圈組合問題」→ 適合用回溯法處理。
2. 建立數字到字母的對應表 KEYPAD（例如 '2' → "abc"）。
3. 使用 backtrack 遞迴構造每個位置可能的字母組合。
4. 每層遞迴代表 digits 的一位數字，每次嘗試所有對應字母。
5. 遞迴結束條件為 index == digits.length()。
6. 時間複雜度：O(3ⁿ~4ⁿ)，n 為 digits 長度（最多為 4）。
*/
