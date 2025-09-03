
import java.util.*;

public class lt_23_mergek_sortedlists {

    // ✅ ListNode 定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    // ✅ 主邏輯：使用 PriorityQueue（最小堆）
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 定義優先隊列：按 ListNode 的 val 排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 將所有非空的串列頭放入 min-heap
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        // 虛擬頭節點
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;

            // 若當前節點還有下一個，就加入 pq
            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_23_mergek_sortedlists solution = new lt_23_mergek_sortedlists();

        ListNode[] lists1 = {
            buildList(new int[]{1, 4, 5}),
            buildList(new int[]{1, 3, 4}),
            buildList(new int[]{2, 6})
        };
        printList(solution.mergeKLists(lists1)); // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6

        ListNode[] lists2 = {};
        printList(solution.mergeKLists(lists2)); // 空

        ListNode[] lists3 = {buildList(new int[]{})};
        printList(solution.mergeKLists(lists3)); // 空
    }

    // 工具方法：建構 Linked List
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 工具方法：列印 Linked List
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
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
1. 使用 PriorityQueue（最小堆）將所有串列的頭節點放入隊列。
2. 每次從隊列中取出最小值節點，加入合併結果串列中。
3. 若該節點還有下一個，將其下一節點加入 PriorityQueue。
4. 重複直到隊列為空。
5. 時間複雜度 O(N log k)，其中 N 是所有節點總數，k 是串列數量。
6. 空間複雜度 O(k)，因為 PriorityQueue 同時最多儲存 k 個節點。
*/
