
public class lt_05_longestpalindromic {

    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) return s;
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expand(s, i, i);
                int len2 = expand(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start + 1) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }
        private int expand(String s, int l, int r) {
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { l--; r++; }
            return r - l - 1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestPalindrome("babad")); // bab / aba
        System.out.println(sol.longestPalindrome("cbbd"));  // bb
        System.out.println(sol.longestPalindrome("a"));     // a
        System.out.println(sol.longestPalindrome("ac"));    // a or c
    }
}