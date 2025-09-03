
public class lt_25_reversekgroup {

    // ✅ ListNode 結構定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // ✅ 主函式：每 k 個節點翻轉一次
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // 虛擬頭節點，便於操作
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {
            // 檢查是否還有足夠的 k 個節點
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break;

            ListNode groupNext = kth.next;

            // 翻轉當前這組 [groupPrev.next, ..., kth]
            ListNode prev = kth.next;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 連接已翻轉部分與前後段
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }

        return dummy.next;
    }

    // ✅ 找出第 k 個節點（若不足 k 個，返回 null）
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    // ✅ 測試
    public static void main(String[] args) {
        lt_25_reversekgroup solution = new lt_25_reversekgroup();

        ListNode list1 = buildList(new int[]{1,2,3,4,5});
        printList(solution.reverseKGroup(list1, 2)); // [2,1,4,3,5]

        ListNode list2 = buildList(new int[]{1,2,3,4,5});
        printList(solution.reverseKGroup(list2, 3)); // [3,2,1,4,5]

        ListNode list3 = buildList(new int[]{1,2});
        printList(solution.reverseKGroup(list3, 3)); // [1,2] -> 不足 k，保留原狀
    }

    // 工具：建立鏈結串列
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 工具：印出鏈結串列
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
1. 使用 dummy 頭節點 + groupPrev 控制每組翻轉起點。
2. 用 getKthNode 找每組的結尾 kth（若不足 k 個節點則跳出）。
3. 翻轉這 k 個節點區間的指標。
4. 把翻轉結果接回前後節點，並移動 groupPrev。
5. 時間複雜度 O(n)，空間 O(1)，不使用遞迴與額外資料結構。
*/
