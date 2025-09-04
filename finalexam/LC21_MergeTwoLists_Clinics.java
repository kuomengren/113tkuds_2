import java.io.*;

/** 合併兩已排序鏈結串列：O(n+m) */
public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        long val; ListNode next;
        ListNode(long v){ val=v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), m = fs.nextInt();
        ListNode a = readList(fs, n), b = readList(fs, m);
        ListNode merged = merge(a, b);
        printList(merged);
    }

    static ListNode readList(FastScanner fs, int len) throws Exception {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int i = 0; i < len; i++) { cur.next = new ListNode(fs.nextLong()); cur = cur.next; }
        return dummy.next;
    }

    static ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0), t = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { t.next = a; a = a.next; }
            else { t.next = b; b = b.next; }
            t = t.next;
        }
        t.next = (a != null) ? a : b;
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
        long nextLong() throws Exception{ return Long.parseLong(next()); }
    }
}
