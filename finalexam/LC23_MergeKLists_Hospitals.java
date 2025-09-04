import java.io.*;
import java.util.*;

/** 合併 k 條已排序串列：最小堆 O(N log k) */
public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        long val; ListNode next;
        ListNode(long v){ val=v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int k = fs.nextInt();
        List<ListNode> lists = new ArrayList<>(k);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 每行一串，以 -1 結束；可能直接 -1（空串）
        for (int i = 0; i < k; i++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) line = br.readLine(); // 跳過空行
            if (line == null) { lists.add(null); continue; }
            String[] parts = line.trim().split("\\s+");
            ListNode dummy = new ListNode(0), cur = dummy;
            for (String s : parts) {
                long v = Long.parseLong(s);
                if (v == -1) break;
                cur.next = new ListNode(v); cur = cur.next;
            }
            lists.add(dummy.next);
        }

        ListNode merged = mergeK(lists.toArray(new ListNode[0]));
        printList(merged);
    }

    static ListNode mergeK(ListNode[] lists){
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingLong(x->x.val));
        for (ListNode h : lists) if (h != null) pq.offer(h);
        ListNode dummy = new ListNode(0), t = dummy;
        while (!pq.isEmpty()) {
            ListNode x = pq.poll();
            t.next = x; t = t.next;
            if (x.next != null) pq.offer(x.next);
        }
        return dummy.next;
    }

    static void printList(ListNode h){
        StringBuilder sb = new StringBuilder();
        while (h != null) { sb.append(h.val).append(' '); h = h.next; }
        System.out.println(sb.toString().trim());
    }

    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1;} return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString();}
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
