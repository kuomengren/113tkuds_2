import java.util.*;

public class lt_30_substringwithconcatenation {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        // 若輸入不合法，直接返回空結果
        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();              // 每個單字長度
        int wordCount = words.length;                 // 單字總數
        int totalLen = wordLen * wordCount;           // 所有單字拼接後的總長度

        if (s.length() < totalLen) return result;     // 若字串太短，直接回傳空陣列

        // 建立目標單字頻率表
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 從 0 ~ wordLen-1 開始移動視窗（為了處理交錯字元情況）
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> windowMap = new HashMap<>();
            int count = 0;

            // 滑動視窗右指標
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);  // 擷取當前單字
                right += wordLen;

                // 如果是有效單字，加入視窗計數
                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 若超過目標單字出現次數，需調整視窗（left 移動）
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 若剛好符合所有單字數量，加入結果
                    if (count == wordCount) {
                        result.add(left);
                    }

                } else {
                    // 若遇到無效單字，清空視窗並移動指標
                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    // main 測試方法
    public static void main(String[] args) {
        lt_30_substringwithconcatenation solver = new lt_30_substringwithconcatenation();

        String s1 = "barfoothefoobarman";
        String[] w1 = {"foo", "bar"};
        System.out.println(solver.findSubstring(s1, w1)); // ➜ [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] w2 = {"word", "good", "best", "word"};
        System.out.println(solver.findSubstring(s2, w2)); // ➜ []

        String s3 = "barfoofoobarthefoobarman";
        String[] w3 = {"bar", "foo", "the"};
        System.out.println(solver.findSubstring(s3, w3)); // ➜ [6, 9, 12]
    }
}

/*
📘 解題說明：
1. 題目要求在 s 中找出所有子字串，該子字串剛好是 words 中所有單字的排列組合（不重複、可變順序）。
2. 先建立 words 的頻率 map，滑動視窗每次檢查長度為 wordLen * words.length 的區段。
3. 使用左右指標方式調整視窗內容，若某字出現次數超過 target map，即往右滑動。
4. 時間複雜度：O(N * wordLen)，其中 N 為 s 長度，wordLen 為單字長度。
5. 此演算法能處理交錯字元與單字重複等進階情況。
*/