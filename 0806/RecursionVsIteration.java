import java.util.*;

public class RecursionVsIteration {

    public static void main(String[] args) {
        // 1️⃣ 二項式係數 C(n, k)
        int n = 20, k = 10;
        System.out.println("🔢 二項式係數 C(" + n + ", " + k + ")");

        long start = System.nanoTime();
        long recC = combinationRecursive(n, k);
        long end = System.nanoTime();
        System.out.println("遞迴：結果 = " + recC + "，時間 = " + (end - start) + " ns");

        start = System.nanoTime();
        long iteC = combinationIterative(n, k);
        end = System.nanoTime();
        System.out.println("迭代：結果 = " + iteC + "，時間 = " + (end - start) + " ns");

        // 2️⃣ 陣列乘積
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("\n✖️ 陣列乘積 " + Arrays.toString(arr));

        start = System.nanoTime();
        int recP = arrayProductRecursive(arr, 0);
        end = System.nanoTime();
        System.out.println("遞迴：結果 = " + recP + "，時間 = " + (end - start) + " ns");

        start = System.nanoTime();
        int iteP = arrayProductIterative(arr);
        end = System.nanoTime();
        System.out.println("迭代：結果 = " + iteP + "，時間 = " + (end - start) + " ns");

        // 3️⃣ 元音字母數量
        String text = "Recursion and Iteration are fun!";
        System.out.println("\n🔤 字串元音統計: \"" + text + "\"");

        start = System.nanoTime();
        int recV = countVowelsRecursive(text.toLowerCase(), 0);
        end = System.nanoTime();
        System.out.println("遞迴：結果 = " + recV + "，時間 = " + (end - start) + " ns");

        start = System.nanoTime();
        int iteV = countVowelsIterative(text.toLowerCase());
        end = System.nanoTime();
        System.out.println("迭代：結果 = " + iteV + "，時間 = " + (end - start) + " ns");

        // 4️⃣ 括號配對檢查
        String brackets = "((a+b)*(c-d))";
        System.out.println("\n() 括號配對檢查: \"" + brackets + "\"");

        start = System.nanoTime();
        boolean recB = checkParenthesesRecursive(brackets, 0, 0);
        end = System.nanoTime();
        System.out.println("遞迴：配對正確？" + recB + "，時間 = " + (end - start) + " ns");

        start = System.nanoTime();
        boolean iteB = checkParenthesesIterative(brackets);
        end = System.nanoTime();
        System.out.println("迭代：配對正確？" + iteB + "，時間 = " + (end - start) + " ns");
    }

    // 1️⃣ 二項式係數
    public static long combinationRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combinationRecursive(n - 1, k - 1) + combinationRecursive(n - 1, k);
    }

    public static long combinationIterative(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result *= n - (k - i);
            result /= i;
        }
        return result;
    }

    // 2️⃣ 陣列乘積
    public static int arrayProductRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * arrayProductRecursive(arr, index + 1);
    }

    public static int arrayProductIterative(int[] arr) {
        int product = 1;
        for (int val : arr)
            product *= val;
        return product;
    }

    // 3️⃣ 字串元音計算
    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = s.charAt(index);
        int count = "aeiou".indexOf(c) != -1 ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ("aeiou".indexOf(c) != -1)
                count++;
        }
        return count;
    }

    // 4️⃣ 括號配對檢查
    public static boolean checkParenthesesRecursive(String s, int index, int balance) {
        if (balance < 0) return false;
        if (index == s.length()) return balance == 0;

        char c = s.charAt(index);
        if (c == '(')
            return checkParenthesesRecursive(s, index + 1, balance + 1);
        else if (c == ')')
            return checkParenthesesRecursive(s, index + 1, balance - 1);
        else
            return checkParenthesesRecursive(s, index + 1, balance);
    }

    public static boolean checkParenthesesIterative(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}
