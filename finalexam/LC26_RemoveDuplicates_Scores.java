import java.io.*;

/** 去重學生成績單：就地壓縮已排序陣列 */
public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        Integer nObj=fs.nextIntNullable(); if(nObj==null){ System.out.println(0); System.out.println(); return; }
        int n=nObj;
        int[] nums=new int[n];
        for(int i=0;i<n;i++) nums[i]=fs.nextInt();
        int len = removeDuplicates(nums, n);
        System.out.println(len);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<len;i++){ if(i>0) sb.append(' '); sb.append(nums[i]); }
        System.out.println(sb.toString());
    }
    static int removeDuplicates(int[] a,int n){
        if(n==0) return 0;
        int w=1;
        for(int i=1;i<n;i++){
            if(a[i]!=a[w-1]) a[w++]=a[i];
        }
        return w;
    }
    /* utils */
    static class FastScanner{
        private final InputStream in; private final byte[] buf=new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1; } return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString(); }
        Integer nextIntNullable() throws Exception{ String s=next(); return s==null?null:Integer.parseInt(s); }
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
