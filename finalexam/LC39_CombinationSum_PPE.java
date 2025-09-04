import java.io.*;
import java.util.*;

/** Combination Sum I：數值可重複使用，回溯 + 剪枝 */
public class LC39_CombinationSum_PPE {
    static List<List<Integer>> ans=new ArrayList<>();
    static int[] cand; static int n, T;
    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        n=fs.nextInt(); T=fs.nextInt();
        cand=new int[n]; for(int i=0;i<n;i++) cand[i]=fs.nextInt();
        Arrays.sort(cand);
        dfs(0, T, new ArrayList<>());
        print();
    }
    static void dfs(int i,int rem,List<Integer> path){
        if(rem==0){ ans.add(new ArrayList<>(path)); return; }
        if(i==n || rem<0) return;
        // 取多次 cand[i]
        int x=cand[i];
        for(int k=0; k*x<=rem; k++){
            // push k 次
            for(int t=0;t<k;t++) path.add(x);
            dfs(i+1, rem - k*x, path);
            for(int t=0;t<k;t++) path.remove(path.size()-1);
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
