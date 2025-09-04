import java.io.*;
import java.util.*;

/** 北捷刷卡最長無重複片段：滑動視窗 O(n) */
public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) { System.out.println(0); return; }
        int[] last = new int[256];
        Arrays.fill(last, -1);
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (last[c] >= l) l = last[c] + 1;
            last[c] = r;
            ans = Math.max(ans, r - l + 1);
        }
        System.out.println(ans);
    }
}
