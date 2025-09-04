import java.io.*;

/** 刪除倒數第 N 節點：快慢指針 O(n) */
public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int i = 0; i < n; i++) { cur.next = new ListNode(fs.nextInt()); cur = cur.next; }
        int k = fs.nextInt();

        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast.next != null) { fast = fast.next; slow = slow.next; }
        // slow 在待刪前一個
        slow.next = (slow.next==null) ? null : slow.next.next;

        // 輸出
        StringBuilder out = new StringBuilder();
        cur = dummy.next;
        while (cur != null) { out.append(cur.val).append(' '); cur = cur.next; }
        System.out.println(out.toString().trim());
    }

    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16];
        private int p=0,l=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException{ if(p>=l){ l=in.read(buf); p=0; if(l<=0) return -1;} return buf[p++]; }
        String next() throws IOException{ StringBuilder sb=new StringBuilder(); int c; while((c=read())<=32 && c!=-1); if(c==-1) return null; do{ sb.append((char)c);}while((c=read())>32); return sb.toString();}
        int nextInt() throws Exception{ return Integer.parseInt(next()); }
    }
}
