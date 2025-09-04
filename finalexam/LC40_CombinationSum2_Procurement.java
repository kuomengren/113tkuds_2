import java.io.*;
import java.util.*;

/** Combination Sum II：每數只能用一次，需去重（同層跳重複） */
public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> ans=new ArrayList<>();
    static int[] a; static int n, T;
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        n=fs.nextInt(); T=fs.nextInt();
        a=new int[n]; for(int i=0;i<n;i++) a[i]=fs.nextInt();
        Arrays.sort(a);
        dfs(0, T, new ArrayList<>());
        print();
    }
    static void dfs(int start,int rem,List<Integer> path){
        if(rem==0){ ans.add(new ArrayList<>(path)); return; }
        for(int i=start;i<n;i++){
            if(i>start && a[i]==a[i-1]) continue; // 同層去重
            int x=a[i];
            if(x>rem) break;
            path.add(x);
            dfs(i+1, rem-x, path);
            path.remove(path.size()-1);
        }
    }
    static void print(){
        StringBuilder sb=new StringBuilder();
        for(List<Integer> L:ans){
            for(int i=0;i<L.size();i++){ if(i>0) sb.append(' '); sb.append(L.get(i)); }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
    static class FastScanner{
        private final InputStream in; private final byte[] buf=new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1; } return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString(); }
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
