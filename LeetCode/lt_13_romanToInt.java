// 題目：13. Roman to Integer
// 將羅馬數字轉為整數（輸入保證合法 1..3999）。
// 解法：從左到右，若當前值 < 右邊值，代表是減法對（如 IV），則減去；否則加上。
// 時間 O(n)，空間 O(1)

public class lt_13_romanToInt {
    private static int val(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

    public static int romanToInt(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int v = val(s.charAt(i));
            if (i + 1 < n && v < val(s.charAt(i + 1))) ans -= v; // 減法對
            else ans += v;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));     // 3
        System.out.println(romanToInt("LVIII"));   // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }
}
/*
解題思路：
1. 檢查 s[i] 與 s[i+1]。若左 < 右，表示為減法對，將左值減掉；否則加上。
2. 合法輸入不需要特別做錯誤處理。
*/
