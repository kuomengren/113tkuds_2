import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static class Pair{
        int score, idx;
        Pair(int s,int i){score=s;idx=i;}
    }
    // 使用自製「最小堆」(score↑, idx↑) 取出得到遞增序
    static class MinHeap{
        ArrayList<Pair> h=new ArrayList<>();
        boolean le(Pair a, Pair b){
            if(a.score!=b.score) return a.score<b.score;
            return a.idx<b.idx;
        }
        void push(Pair x){
            h.add(x); int i=h.size()-1;
            while(i>0){
                int p=(i-1)/2; if(le(h.get(p),h.get(i))) break;
                Collections.swap(h,i,p); i=p;
            }
        }
        Pair pop(){
            Pair res=h.get(0);
            Pair last=h.remove(h.size()-1);
            if(!h.isEmpty()){
                h.set(0,last);
                int n=h.size(), i=0;
                while(true){
                    int l=i*2+1,r=i*2+2,b=i;
                    if(l<n && !le(h.get(l),h.get(b))) b=l;
                    if(r<n && !le(h.get(r),h.get(b))) b=r;
                    if(b==i) break; Collections.swap(h,i,b); i=b;
                }
            }
            return res;
        }
        boolean isEmpty(){ return h.isEmpty(); }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        StringTokenizer st=new StringTokenizer(br.readLine());
        MinHeap mh=new MinHeap();
        for(int i=0;i<n;i++) mh.push(new Pair(Integer.parseInt(st.nextToken()), i));
        StringBuilder sb=new StringBuilder();
        ArrayList<Integer> out=new ArrayList<>();
        while(!mh.isEmpty()) out.add(mh.pop().score);
        for(int i=0;i<out.size();i++){ if(i>0) sb.append(' '); sb.append(out.get(i)); }
        System.out.println(sb.toString());
    }
}
/*
 * Time Complexity: O(n log n)
 * 說明：以自製二元最小堆完成 Heapsort，建堆 O(n)，每次彈出 O(log n) 共 n 次，
 * 總成本 O(n log n)。平手以 idx↑ 比較，確保值相同時先出現者優先。
 */
