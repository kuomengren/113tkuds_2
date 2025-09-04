import java.io.*;
import java.util.*;

/** 括號格式檢查：Stack O(n) */
public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) { System.out.println(true); return; }

        Deque<Character> st = new ArrayDeque<>();
        Map<Character, Character> mp = Map.of(
            ')','(', ']','[', '}','{'
        );
        for (char c : s.toCharArray()) {
            if (c=='('||c=='['||c=='{') st.push(c);
            else {
                if (st.isEmpty() || st.peek()!=mp.getOrDefault(c,'#')) {
                    System.out.println(false); return;
                }
                st.pop();
            }
        }
        System.out.println(st.isEmpty());
    }
}
