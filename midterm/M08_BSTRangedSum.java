import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
    static class Node{ long v; Node l,r; Node(long v){this.v=v;} }
    static Node build(Long[] a,int i){
        if(i>=a.length || a[i]==null) return null;
        Node n=new Node(a[i]);
        n.l=build(a,2*i+1); n.r=build(a,2*i+2);
        return n;
    }
    static long L,R;
    static long dfs(Node x){
        if(x==null) return 0;
        if(x.v<L) return dfs(x.r);
        if(x.v>R) return dfs(x.l);
        return x.v + dfs(x.l) + dfs(x.r);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        StringTokenizer st=new StringTokenizer(br.readLine());
        Long[] a=new Long[n];
        for(int i=0;i<n;i++){ long v=Long.parseLong(st.nextToken()); a[i]=(v==-1)?null:v; }
        st=new StringTokenizer(br.readLine());
        L=Long.parseLong(st.nextToken()); R=Long.parseLong(st.nextToken());
        Node root=build(a,0);
        System.out.println("Sum: "+dfs(root));
    }
}
