import java.util.Scanner;

public class RecursiveMathCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🧮 遞迴數學計算器 🧮");

        // 1️⃣ 組合數 C(n, k)
        System.out.print("\n請輸入 n 和 k 計算組合數 C(n, k): ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.printf("C(%d, %d) = %d\n", n, k, combination(n, k));

        // 2️⃣ 卡塔蘭數 Catalan(n)
        System.out.print("\n請輸入 n 計算卡塔蘭數 C(n): ");
        int c = sc.nextInt();
        System.out.printf("Catalan(%d) = %d\n", c, catalan(c));

        // 3️⃣ 漢諾塔移動步數
        System.out.print("\n請輸入 n 計算漢諾塔的最少步數: ");
        int h = sc.nextInt();
        System.out.printf("Hanoi(%d) = %d\n", h, hanoi(h));

        // 4️⃣ 判斷回文數
        System.out.print("\n請輸入一個數字判斷是否為回文數: ");
        int num = sc.nextInt();
        System.out.printf("%d 是回文數嗎？ %s\n", num, isPalindrome(num) ? "是 ❤️" : "不是 ❌");

        sc.close();
    }

    // 1️⃣ 計算 C(n, k) = C(n-1, k-1) + C(n-1, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2️⃣ 卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n <= 1)
            return 1;

        int result = 0;
        for (int i = 0; i < n; i++)
            result += catalan(i) * catalan(n - 1 - i);
        return result;
    }

    // 3️⃣ 漢諾塔移動步數
    public static int hanoi(int n) {
        if (n == 1)
            return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    // 4️⃣ 判斷是否為回文數（遞迴方式）
    public static boolean isPalindrome(int number) {
        return number == reverseNumber(number, 0);
    }

    // 補助函式：反轉數字
    public static int reverseNumber(int n, int reversed) {
        if (n == 0)
            return reversed;
        return reverseNumber(n / 10, reversed * 10 + n % 10);
    }
}