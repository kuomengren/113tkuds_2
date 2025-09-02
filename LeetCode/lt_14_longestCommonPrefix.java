// 題目：14. Longest Common Prefix
// 找出多字串的最長共同前綴。
// 解法 A（實作）：先拿第一個字串為基底，逐步與下一個做 LCP 縮減；若變空字串即可提前結束。
// 時間 O(總字元數)，空間 O(1)

public class lt_14_longestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length && !prefix.isEmpty(); i++) {
            prefix = lcp(prefix, strs[i]);
        }
        return prefix;
    }

    private static String lcp(String a, String b) {
        int i = 0, m = Math.min(a.length(), b.length());
        while (i < m && a.charAt(i) == b.charAt(i)) i++;
        return a.substring(0, i);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"})); // "fl"
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));     // ""
    }
}
/*
解題思路：
1. 兩兩縮減：prefix = LCP(prefix, 下一字串)。
2. 任一輪為空字串，後續不可能再變長，提早結束。
*/
