
public class lt_21_mergetwosortedlists {

    // ✅ 鏈結串列節點定義
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // ✅ 主邏輯：迭代合併兩個排序 LinkedList
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // 虛擬頭節點
        ListNode current = dummy;

        // 只要兩邊都還有節點，就比較大小取較小接到新串列
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 任一串列剩餘節點直接接上（因為已排序）
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_21_mergetwosortedlists solution = new lt_21_mergetwosortedlists();

        ListNode list1 = buildList(new int[]{1, 2, 4});
        ListNode list2 = buildList(new int[]{1, 3, 4});
        printList(solution.mergeTwoLists(list1, list2)); // Output: [1,1,2,3,4,4]

        ListNode list3 = buildList(new int[]{});
        ListNode list4 = buildList(new int[]{});
        printList(solution.mergeTwoLists(list3, list4)); // Output: []

        ListNode list5 = buildList(new int[]{});
        ListNode list6 = buildList(new int[]{0});
        printList(solution.mergeTwoLists(list5, list6)); // Output: [0]
    }

    // 工具：陣列轉 ListNode
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // 工具：列印 ListNode
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
1. 使用虛擬頭節點 dummy 方便處理頭指標。
2. 比較兩串列當前節點的值，較小者接到結果串列中。
3. 每次取一個節點並前進指標。
4. 最後若有剩餘節點，直接接到結果串列尾部（已排序）。
5. 時間複雜度 O(m + n)，空間複雜度 O(1)，僅修改指標，不創建新節點。
*/
