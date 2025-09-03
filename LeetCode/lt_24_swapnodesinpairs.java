
public class lt_24_swapnodesinpairs {

    // ✅ 定義 ListNode 結構
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // ✅ 主邏輯：迭代交換節點
    public ListNode swapPairs(ListNode head) {
        // 虛擬頭節點：簡化處理邊界情況
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        // 每次看兩個節點是否可交換
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            // ✅ 執行交換（只動指標）
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 移動指標到下一對
            prev = first;
            head = first.next;
        }

        return dummy.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_24_swapnodesinpairs solution = new lt_24_swapnodesinpairs();

        ListNode list1 = buildList(new int[]{1, 2, 3, 4});
        printList(solution.swapPairs(list1)); // [2, 1, 4, 3]

        ListNode list2 = buildList(new int[]{});
        printList(solution.swapPairs(list2)); // []

        ListNode list3 = buildList(new int[]{1});
        printList(solution.swapPairs(list3)); // [1]

        ListNode list4 = buildList(new int[]{1, 2, 3});
        printList(solution.swapPairs(list4)); // [2, 1, 3]
    }

    // 工具：建立 Linked List
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 工具：印出 Linked List
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
1. 使用 dummy 節點幫助處理頭節點被交換的情況。
2. 每次處理兩個節點：first 與 second。
3. 執行交換：prev.next → second，second.next → first，first.next → 原 second.next。
4. 移動 prev 與 head 進入下一輪交換。
5. 時間複雜度 O(n)，空間複雜度 O(1)，原地交換節點。
*/
