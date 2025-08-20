import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    static class Node{
        int time, listId, idx;
        Node(int t,int l,int i){time=t;listId=l;idx=i;}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int K=Integer.parseInt(br.readLine().trim());
        List<int[]> lists=new ArrayList<>();
        for(int k=0;k<K;k++){
            int len=Integer.parseInt(br.readLine().trim());
            int[] arr=new int[len];
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int i=0;i<len;i++) arr[i]=Integer.parseInt(st.nextToken());
            lists.add(arr);
        }
        PriorityQueue<Node> pq=new PriorityQueue<>(Comparator.comparingInt(o->o.time));
        for(int k=0;k<K;k++) if(lists.get(k).length>0) pq.add(new Node(lists.get(k)[0],k,0));
        StringBuilder sb=new StringBuilder();
        boolean first=true;
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            if(!first) sb.append(' '); first=false;
            sb.append(cur.time);
            int ni=cur.idx+1; if(ni<lists.get(cur.listId).length)
                pq.add(new Node(lists.get(cur.listId)[ni],cur.listId,ni));
        }
        System.out.println(sb.toString());
    }
}
