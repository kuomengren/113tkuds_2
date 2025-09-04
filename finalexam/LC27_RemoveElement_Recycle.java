import java.io.*;

/** 回收站清單移除指定元素：覆寫保留非 val */
public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        int n=fs.nextInt(); int val=fs.nextInt();
        int[] a=new int[n]; for(int i=0;i<n;i++) a[i]=fs.nextInt();
        int w=0; for(int x:a) if(x!=val) a[w++]=x;
        System.out.println(w);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<w;i++){ if(i>0) sb.append(' '); sb.append(a[i]); }
        System.out.println(sb.toString());
    }
    static class FastScanner{
        private final InputStream in; private final byte[] buf=new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1; } return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString(); }
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
