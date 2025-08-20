
import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    static boolean isMax;

    static void heapifyDown(long[] a, int n, int i){
        while(true){
            int l = i*2+1, r = i*2+2, best = i;
            if(l<n && cmp(a[l], a[best])) best = l;
            if(r<n && cmp(a[r], a[best])) best = r;
            if(best==i) break;
            long t=a[i]; a[i]=a[best]; a[best]=t;
            i = best;
        }
    }
    static boolean cmp(long x, long y){ // return true if x should be above y
        return isMax ? x>y : x<y;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine().trim();
        isMax = type.equalsIgnoreCase("max");
        int n = Integer.parseInt(br.readLine().trim());
        long[] a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) a[i]=Long.parseLong(st.nextToken());
        for(int i=n/2-1;i>=0;i--) heapifyDown(a,n,i);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){ if(i>0) sb.append(' '); sb.append(a[i]); }
        System.out.println(sb);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆對每個節點做一次下濾，實際總成本為 Σ O(height) ≈ O(n)。
 * 與逐一 insert 的 O(n log n) 相比，自底向上只需要線性時間。
 */
