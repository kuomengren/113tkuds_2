import java.io.*;

/** 延誤等級首末定位：雙 lower_bound O(log n) */
public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        int n=fs.nextInt(); long target=fs.nextLong();
        long[] a=new long[n]; for(int i=0;i<n;i++) a[i]=fs.nextLong();
        int l=lower(a,target);
        if(l==n || a[l]!=target){ System.out.println("-1 -1"); return; }
        int r=lower(a,target+1)-1;
        System.out.println(l+" "+r);
    }
    static int lower(long[] a,long x){
        int l=0,r=a.length;
        while(l<r){
            int m=(l+r)>>>1;
            if(a[m]>=x) r=m; else l=m+1;
        }
        return l;
    }
    static class FastScanner{
        private final InputStream in; private final byte[] buf=new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1; } return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString(); }
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
        long nextLong() throws Exception{ return Long.parseLong(next()); }
    }
}
