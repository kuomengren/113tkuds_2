
public class lt_19_removeNthFromEnd {

    // ✅ 定義 ListNode 類別
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // ✅ 主解法：一次遍歷使用雙指標
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虛擬頭節點，方便處理刪除頭節點情況
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 設定快慢指標
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先前進 n+1 步（讓 slow 指向要刪除的前一個）
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 一起前進，直到 fast 到尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        lt_19_removeNthFromEnd solution = new lt_19_removeNthFromEnd();

        ListNode head1 = buildList(new int[]{1, 2, 3, 4, 5});
        printList(solution.removeNthFromEnd(head1, 2)); // 輸出 [1, 2, 3, 5]

        ListNode head2 = buildList(new int[]{1});
        printList(solution.removeNthFromEnd(head2, 1)); // 輸出 []

        ListNode head3 = buildList(new int[]{1, 2});
        printList(solution.removeNthFromEnd(head3, 1)); // 輸出 [1]
    }

    // 工具方法：根據陣列建立 Linked List
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 工具方法：列印 Linked List
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}

/*
解題思路：
1. 使用 dummy 節點避免處理頭節點刪除的特殊情況。
2. 設定兩個指標 fast 與 slow，先讓 fast 前進 n+1 步。
3. 然後 fast 與 slow 一起前進，直到 fast 到尾端，slow 即在目標節點前一個。
4. 刪除 slow.next 即可。
5. 時間複雜度 O(n)，空間複雜度 O(1)，為一次遍歷解法。
*/
