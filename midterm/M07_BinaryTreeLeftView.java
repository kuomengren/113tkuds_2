import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {
    static class Node{ int val; Node l,r; Node(int v){val=v;} }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        StringTokenizer st=new StringTokenizer(br.readLine());
        Integer[] arr=new Integer[n];
        for(int i=0;i<n;i++){
            int v=Integer.parseInt(st.nextToken());
            arr[i]=(v==-1)?null:v;
        }
        Node root=build(arr,0);
        List<Integer> left=new ArrayList<>();
        if(root!=null){
            Queue<Node> q=new ArrayDeque<>();
            q.add(root);
            while(!q.isEmpty()){
                int sz=q.size();
                for(int i=0;i<sz;i++){
                    Node cur=q.poll();
                    if(i==0) left.add(cur.val);
                    if(cur.l!=null) q.add(cur.l);
                    if(cur.r!=null) q.add(cur.r);
                }
            }
        }
        StringBuilder sb=new StringBuilder("LeftView:");
        for(int i=0;i<left.size();i++) sb.append(i==0?" ":" ").append(left.get(i));
        System.out.println(sb);
    }
    static M07_BinaryTreeLeftView.Node build(Integer[] a,int i){
        if(i>=a.length||a[i]==null) return null;
        Node n=new Node(a[i]);
        n.l=build(a,2*i+1); n.r=build(a,2*i+2);
        return n;
    }
}
