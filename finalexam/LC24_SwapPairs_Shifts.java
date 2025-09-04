import java.io.*;

/** 班表兩兩交換：成對交換相鄰節點 */
public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line=br.readLine();
        if(line==null||line.trim().isEmpty()){ System.out.println(""); return; }
        int[] arr = parseLine(line);
        ListNode head = build(arr);
        head = swapPairs(head);
        print(head);
    }
    static ListNode swapPairs(ListNode head){
        ListNode dummy=new ListNode(0); dummy.next=head;
        ListNode prev=dummy;
        while(prev.next!=null && prev.next.next!=null){
            ListNode a=prev.next, b=a.next;
            a.next=b.next; b.next=a; prev.next=b;
            prev=a;
        }
        return dummy.next;
    }
    static int[] parseLine(String s){
        String[] p=s.trim().split("\\s+");
        int[] a=new int[p.length];
        for(int i=0;i<p.length;i++) a[i]=Integer.parseInt(p[i]);
        return a;
    }
    static ListNode build(int[] a){
        ListNode d=new ListNode(0), t=d; for(int x:a){ t.next=new ListNode(x); t=t.next; } return d.next;
    }
    static void print(ListNode h){
        StringBuilder sb=new StringBuilder();
        while(h!=null){ sb.append(h.val).append(' '); h=h.next; }
        System.out.println(sb.toString().trim());
    }
}
