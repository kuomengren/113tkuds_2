import java.io.*;
import java.util.*;

/** 高鐵連假加班車 Two Sum：O(n) HashMap 解 */
public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long target = fs.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        Map<Long, Integer> need = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long x = a[i];
            if (need.containsKey(x)) {
                System.out.println(need.get(x) + " " + i);
                return;
            }
            need.put(target - x, i);
        }
        System.out.println("-1 -1");
    }

    /* ---------- utils ---------- */
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is){ in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer); ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ' && c != -1);
            if (c == -1) return null;
            do { sb.append((char)c); } while ((c = read()) > ' ');
            return sb.toString();
        }
        int nextInt() throws Exception { return Integer.parseInt(next()); }
        long nextLong() throws Exception { return Long.parseLong(next()); }
    }
}
