import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name; long qty; int idx;
        Item(String n,long q,int i){name=n;qty=q;idx=i;}
    }
    // Min-Heap by (qty↑, name↑, idx↑) – 只保留 Top-K
    static void push(List<Item> h, Item v){
        h.add(v);
        int i=h.size()-1;
        while(i>0){
            int p=(i-1)/2;
            if(le(h.get(p), h.get(i))) break;
            Collections.swap(h,i,p); i=p;
        }
    }
    static Item pop(List<Item> h){
        Item res=h.get(0);
        Item last=h.remove(h.size()-1);
        if(!h.isEmpty()){
            h.set(0,last);
            int n=h.size(), i=0;
            while(true){
                int l=i*2+1,r=i*2+2,b=i;
                if(l<n && le(h.get(l), h.get(b))==false) b=l;
                if(r<n && le(h.get(r), h.get(b))==false) b=r;
                if(b==i) break; Collections.swap(h,i,b); i=b;
            }
        }
        return res;
    }
    static boolean le(Item a, Item b){ // a <= b by (qty,name,idx)
        if(a.qty!=b.qty) return a.qty<b.qty;
        int c=a.name.compareTo(b.name);
        if(c!=0) return c<0;
        return a.idx<b.idx;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()), K=Integer.parseInt(st.nextToken());
        List<Item> heap=new ArrayList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            String name=st.nextToken();
            long qty=Long.parseLong(st.nextToken());
            Item it=new Item(name,qty,i);
            if(heap.size()<K) push(heap,it);
            else{
                if(le(heap.get(0), it)) { // 新的更大
                    pop(heap); push(heap,it);
                }
            }
        }
        // 取出由大到小
        List<Item> ans=new ArrayList<>();
        while(!heap.isEmpty()) ans.add(pop(heap));
        ans.sort((a,b)->{
            if(b.qty!=a.qty) return Long.compare(b.qty,a.qty);
            int c=a.name.compareTo(b.name);
            if(c!=0) return c;
            return Integer.compare(a.idx,b.idx);
        });
        StringBuilder sb=new StringBuilder();
        for(Item x:ans) sb.append(x.name).append(' ').append(x.qty).append('\n');
        System.out.print(sb.toString());
    }
}
/*
 * Time Complexity: O(n log K)
 * 說明：每筆資料最多造成一次 heap push/pop，堆大小固定為 K，單次操作 O(log K)；
 * 因此總成本 O(n log K)，當 K ≪ n 時遠優於排序 O(n log n)。
 */
