import java.io.*;

/** 旋轉陣列搜尋：改良二分 O(log n) */
public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        int n=fs.nextInt(); long target=fs.nextLong();
        long[] a=new long[n]; for(int i=0;i<n;i++) a[i]=fs.nextLong();
        System.out.println(search(a,target));
    }
    static int search(long[] a,long t){
        int l=0,r=a.length-1;
        while(l<=r){
            int m=(l+r)>>>1;
            if(a[m]==t) return m;
            if(a[l]<=a[m]){ // 左半有序
                if(a[l]<=t && t<a[m]) r=m-1;
                else l=m+1;
            }else{ // 右半有序
                if(a[m]<t && t<=a[r]) l=m+1;
                else r=m-1;
            }
        }
        return -1;
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
