import java.util.*;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1️⃣ 字串排列組合
        System.out.print("🔄 輸入字串產生排列：");
        String input = sc.nextLine();
        System.out.println("所有排列組合：");
        permute("", input);

        // 2️⃣ 字串匹配
        System.out.print("\n🔍 輸入主字串：");
        String text = sc.nextLine();
        System.out.print("🔍 輸入要找的子字串：");
        String pattern = sc.nextLine();
        int index = match(text, pattern, 0);
        System.out.printf("結果：%s\n", index == -1 ? "未找到" : "匹配於索引 " + index);

        // 3️⃣ 移除重複字符
        System.out.print("\n❌ 輸入字串移除重複字元：");
        String dup = sc.nextLine();
        System.out.println("結果：" + removeDuplicates(dup, 0, new HashSet<>()));

        // 4️⃣ 所有子字串組合
        System.out.print("\n🧮 輸入字串列出所有子字串：");
        String sub = sc.nextLine();
        System.out.println("所有子字串：");
        generateSubstrings(sub, 0, "");
    }

    // 1️⃣ 字串排列組合
    public static void permute(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permute(
                prefix + remaining.charAt(i),
                remaining.substring(0, i) + remaining.substring(i + 1)
            );
        }
    }

    // 2️⃣ 遞迴字串匹配：回傳第一次匹配的位置
    public static int match(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) return -1;
        if (text.substring(index, index + pattern.length()).equals(pattern)) return index;
        return match(text, pattern, index + 1);
    }

    // 3️⃣ 移除重複字符（保留第一次）
    public static String removeDuplicates(String s, int index, Set<Character> seen) {
        if (index == s.length()) return "";
        char c = s.charAt(index);
        if (seen.contains(c)) {
            return removeDuplicates(s, index + 1, seen);
        } else {
            seen.add(c);
            return c + removeDuplicates(s, index + 1, seen);
        }
    }

    // 4️⃣ 列出所有子字串
    public static void generateSubstrings(String s, int index, String current) {
        if (index == s.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }

        // 包含當前字元
        generateSubstrings(s, index + 1, current + s.charAt(index));

        // 不包含當前字元
        generateSubstrings(s, index + 1, current);
    }
}
