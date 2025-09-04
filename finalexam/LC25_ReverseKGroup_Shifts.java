import java.io.*;

/** 班表 k 組反轉：原地分段反轉，尾端不足 k 保留 */
public class LC25_ReverseKGroup_Shifts {
    static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String ks=br.readLine(); if(ks==null){ System.out.println(""); return; }
        int k=Integer.parseInt(ks.trim());
        String line=br.readLine(); if(line==null||line.trim().isEmpty()){ System.out.println(""); return; }
        int[] a=parse(line);
        ListNode head=build(a);
        head=reverseKGroup(head,k);
        print(head);
    }
    static ListNode reverseKGroup(ListNode head,int k){
        if(k<=1||head==null) return head;
        ListNode dummy=new ListNode(0); dummy.next=head;
        ListNode groupPrev=dummy;
        while(true){
            ListNode kth=getKth(groupPrev,k);
            if(kth==null) break;
            ListNode groupNext=kth.next;
            // 反轉
            ListNode prev=groupNext, cur=groupPrev.next;
            while(cur!=groupNext){
                ListNode nxt=cur.next;
                cur.next=prev;
                prev=cur; cur=nxt;
            }
            ListNode tmp=groupPrev.next;
            groupPrev.next=kth;
            groupPrev=tmp;
        }
        return dummy.next;
    }
    static ListNode getKth(ListNode start,int k){
        while(start!=null && k>0){ start=start.next; k--; }
        return start;
    }
    static int[] parse(String s){ String[] p=s.trim().split("\\s+"); int[] a=new int[p.length]; for(int i=0;i<p.length;i++) a[i]=Integer.parseInt(p[i]); return a; }
    static ListNode build(int[] a){ ListNode d=new ListNode(0),t=d; for(int x:a){ t.next=new ListNode(x); t=t.next; } return d.next; }
    static void print(ListNode h){ StringBuilder sb=new StringBuilder(); while(h!=null){ sb.append(h.val).append(' '); h=h.next; } System.out.println(sb.toString().trim()); }
}
