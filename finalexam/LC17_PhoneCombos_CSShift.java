import java.io.*;

/** 電話鍵盤組合：回溯 */
public class LC17_PhoneCombos_CSShift {
    private static final String[] MAP = {
        "",    "",    "abc",  "def",
        "ghi", "jkl", "mno",  "pqrs",
        "tuv", "wxyz"
    };
    static String digits;
    static StringBuilder path = new StringBuilder();
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        digits = br.readLine();
        if (digits == null || digits.isEmpty()) { System.out.print(""); return; }
        dfs(0);
        System.out.print(out.toString());
    }

    static void dfs(int idx) {
        if (idx == digits.length()) {
            out.append(path).append('\n'); return;
        }
        int d = digits.charAt(idx) - '0';
        String letters = MAP[d];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(idx + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
