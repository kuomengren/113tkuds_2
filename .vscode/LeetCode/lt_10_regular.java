
public class lt_10_regular {

    static class Solution {
        public boolean isMatch(String s, String p) {
            Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
            return dfs(0, 0, s, p, memo);
        }
        private boolean dfs(int i, int j, String s, String p, Boolean[][] memo) {
            if (memo[i][j] != null) return memo[i][j];
            boolean ans;
            if (j == p.length()) {
                ans = (i == s.length());
            } else {
                boolean firstMatch = (i < s.length()) &&
                        (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    ans = dfs(i, j + 2, s, p, memo) || (firstMatch && dfs(i + 1, j, s, p, memo));
                } else {
                    ans = firstMatch && dfs(i + 1, j + 1, s, p, memo);
                }
            }
            memo[i][j] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isMatch("aa", "a"));    // false
        System.out.println(sol.isMatch("aa", "a*"));   // true
        System.out.println(sol.isMatch("ab", ".*"));   // true
        System.out.println(sol.isMatch("aab", "c*a*b"));// true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
