import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node{
        int val; char color; // 'B' or 'R'; -1 代表 null（視為黑）
        Node(int v,char c){val=v;color=c;}
    }
    static int n;
    static int[] vals;
    static char[] cols;

    static boolean isBlack(int i){ return (i>=n || vals[i]==-1) || cols[i]=='B'; }
    static boolean isRed(int i){ return (i<n && vals[i]!=-1 && cols[i]=='R'); }

    static int blackHeight(int i){
        if(i>=n || vals[i]==-1) return 1; // NIL as black with height 1
        int l=2*i+1, r=2*i+2;
        int bl=blackHeight(l); if(bl<0) return -1;
        int br=blackHeight(r); if(br<0) return -1;
        if(bl!=br) return -1;
        return bl + (cols[i]=='B'?1:0);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine().trim());
        vals=new int[n]; cols=new char[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int v=Integer.parseInt(st.nextToken());
            String c=st.nextToken();
            vals[i]=v;
            cols[i]=(v==-1)?'B':c.charAt(0);
        }
        // Property 1: root is black
        if(n>0 && vals[0]!=-1 && cols[0]!='B'){ System.out.println("RootNotBlack"); return; }

        // Property 2: no red-red adjacency (report first index i if violated)
        for(int i=0;i<n;i++){
            if(vals[i]==-1) continue;
            if(cols[i]=='R'){
                int l=2*i+1, r=2*i+2;
                if((l<n && vals[l]!=-1 && cols[l]=='R') || (r<n && vals[r]!=-1 && cols[r]=='R')){
                    System.out.println("RedRedViolation at index "+i);
                    return;
                }
            }
        }
        // Property 3: black height equal on all paths
        if(blackHeight(0)<0){ System.out.println("BlackHeightMismatch"); return; }
        System.out.println("RB Valid");
    }
}
