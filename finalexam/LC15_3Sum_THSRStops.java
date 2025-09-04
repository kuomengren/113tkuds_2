import java.io.*;
import java.util.*;

/** 三數和為 0：排序 + 兩指針 O(n^2)，嚴格去重 */
public class LC15_3Sum_THSRStops {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();
        Arrays.sort(a);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] == a[i-1]) continue;
            if (a[i] > 0) break;
            int l = i + 1, r = n - 1;
            while (l < r) {
                long sum = a[i] + a[l] + a[r];
                if (sum == 0) {
                    out.append(a[i]).append(' ').append(a[l]).append(' ').append(a[r]).append('\n');
                    long lv = a[l], rv = a[r];
                    while (l < r && a[l] == lv) l++;
                    while (l < r && a[r] == rv) r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        System.out.print(out.toString());
    }

    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1;} return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString();}
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
        long nextLong() throws Exception{ return Long.parseLong(next()); }
    }
}
