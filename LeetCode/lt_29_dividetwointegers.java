import java.util.*;

public class lt_29_dividetwointegers {

    // 主功能函式：實作除法（不使用 *, /, %）
    public int divide(int dividend, int divisor) {
        // 定義 32-bit 整數範圍
        int INT_MAX = Integer.MAX_VALUE; // 2^31 - 1
        int INT_MIN = Integer.MIN_VALUE; // -2^31

        // ✅ 特殊情況：溢位處理
        if (dividend == INT_MIN && divisor == -1) {
            return INT_MAX;
        }

        // ✅ 判斷正負號（同號為正）
        boolean isPositive = (dividend >= 0) == (divisor >= 0);

        // 轉為 long，避免溢位，並取絕對值
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // 用減法 + 左移方式模擬除法
        while (a >= b) {
            long temp = b;
            int multiple = 1;

            // 倍增直到超過 a
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            result += multiple;
        }

        return isPositive ? result : -result;
    }

    // ✅ main 方法：進行測試
    public static void main(String[] args) {
        lt_29_dividetwointegers divider = new lt_29_dividetwointegers();

        System.out.println(divider.divide(10, 3));          // ➜ 3
        System.out.println(divider.divide(7, -3));          // ➜ -2
        System.out.println(divider.divide(-2147483648, -1));// ➜ 2147483647
        System.out.println(divider.divide(-15, 4));         // ➜ -3
        System.out.println(divider.divide(1, 1));           // ➜ 1
    }
}

/*
📘 解題說明：
1. 題目要求實作除法功能，不允許使用 *, /, %。
2. 可透過減法 + 左移位元（模擬乘法）方式加速除法操作。
3. 使用 long 避免 INT_MIN 取絕對值時溢位。
4. 重點為：每次找到 divisor 的最大倍數 ≤ dividend，然後減去並累加倍數。
5. 考慮邊界條件，如 INT_MIN / -1，避免超出 32-bit 整數範圍。
6. 時間複雜度：O(logN)，每次將除數倍增，加速搜尋。
*/
