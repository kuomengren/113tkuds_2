// 檔名：Lt_03_LongestSubstring.java

import java.util.HashMap;
import java.util.Map;

public class lt_03_longestsubstring {

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> last = new HashMap<>();
            int left = 0, best = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                if (last.containsKey(c) && last.get(c) >= left) {
                    left = last.get(c) + 1;
                }

                last.put(c, right);
                best = Math.max(best, right - left + 1);
            }
            return best;
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sol.lengthOfLongestSubstring(""));         // 0
        System.out.println(sol.lengthOfLongestSubstring("au"));       // 2
    }
}
