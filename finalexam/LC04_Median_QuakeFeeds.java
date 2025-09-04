import java.io.*;
import java.util.*;

/** 雙已排序數列中位數：對短陣列二分 O(log min(n,m)) */
public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), m = fs.nextInt();
        double[] A = new double[n], B = new double[m];
        for (int i = 0; i < n; i++) A[i] = Double.parseDouble(fs.next());
        for (int i = 0; i < m; i++) B[i] = Double.parseDouble(fs.next());
        if (n > m) { // 讓 A 為短
            double[] t = A; A = B; B = t;
            int tt = n; n = m; m = tt;
        }
        int totalLeft = (n + m + 1) / 2;
        int lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = totalLeft - i;

            double Aleft  = (i == 0) ? -1e300 : A[i-1];
            double Aright = (i == n) ?  1e300 : A[i];
            double Bleft  = (j == 0) ? -1e300 : B[j-1];
            double Bright = (j == m) ?  1e300 : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if (((n + m) & 1) == 1) {
                    System.out.printf(Locale.US, "%.1f%n", Math.max(Aleft, Bleft));
                } else {
                    double v = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                    System.out.printf(Locale.US, "%.1f%n", v);
                }
                return;
            } else if (Aleft > Bright) hi = i - 1;
            else lo = i + 1;
        }
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1<<16];
        private int ptr=0,len=0;
        FastScanner(InputStream is){ in=is; }
        private int read() throws IOException{
            if(ptr>=len){ len=in.read(buffer); ptr=0; if(len<=0) return -1; }
            return buffer[ptr++];
        }
        String next() throws IOException{
            StringBuilder sb=new StringBuilder(); int c;
            while((c=read())<=32 && c!=-1);
            if(c==-1) return null;
            do { sb.append((char)c); } while((c=read())>32);
            return sb.toString();
        }
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
