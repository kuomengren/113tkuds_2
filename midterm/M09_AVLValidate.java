import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    static class Node{ long v; Node l,r; Node(long v){this.v=v;} }
    static Node build(Long[] a,int i){
        if(i>=a.length||a[i]==null) return null;
        Node n=new Node(a[i]);
        n.l=build(a,2*i+1); n.r=build(a,2*i+2);
        return n;
    }
    static boolean isBST(Node x,long min,long max){
        if(x==null) return true;
        if(!(min < x.v && x.v < max)) return false;
        return isBST(x.l,min,x.v) && isBST(x.r,x.v,max);
    }
    static int checkAVL(Node x){ // 回傳高度；不合法回傳 -INF
        if(x==null) return 0;
        int lh=checkAVL(x.l); if(lh<=-1_000_000) return lh;
        int rh=checkAVL(x.r); if(rh<=-1_000_000) return rh;
        if(Math.abs(lh-rh)>1) return -1_000_000;
        return Math.max(lh,rh)+1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        StringTokenizer st=new StringTokenizer(br.readLine());
        Long[] a=new Long[n];
        for(int i=0;i<n;i++){ long v=Long.parseLong(st.nextToken()); a[i]=(v==-1)?null:v; }
        Node root=build(a,0);
        if(!isBST(root,Long.MIN_VALUE,Long.MAX_VALUE)){ System.out.println("Invalid BST"); return; }
        if(checkAVL(root)<=-1_000_000){ System.out.println("Invalid AVL"); return; }
        System.out.println("Valid");
    }
}
/*
 * Time Complexity: O(n)
 * 說明：檢查 BST 與 AVL 各為一次 DFS；每個節點被訪問常數次，總成本 O(n)。
 * 空間 O(h) 為遞迴深度（h 為樹高，最差 O(n)，平衡時 O(log n)）。
 */
