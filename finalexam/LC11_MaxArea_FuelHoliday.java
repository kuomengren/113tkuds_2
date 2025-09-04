import java.io.*;

/** 最大容器（輸出帶寬）：雙指針 O(n) */
public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) h[i] = fs.nextLong();
        int L = 0, R = n - 1;
        long ans = 0;
        while (L < R) {
            long area = (long)(R - L) * Math.min(h[L], h[R]);
            ans = Math.max(ans, area);
            if (h[L] < h[R]) L++;
            else R--;
        }
        System.out.println(ans);
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
