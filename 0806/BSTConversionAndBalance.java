import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. 將 BST 轉換為排序的雙向鏈結串列
    static class DLLNode {
        int val;
        DLLNode prev, next;
        DLLNode(int val) { this.val = val; }
    }

    static DLLNode bstToSortedDoublyLinkedList(TreeNode root) {
        return convertBSTToDLL(root)[0]; // return head
    }

    // [head, tail]
    private static DLLNode[] convertBSTToDLL(TreeNode root) {
        if (root == null) return new DLLNode[]{null, null};

        DLLNode[] left = convertBSTToDLL(root.left);
        DLLNode[] right = convertBSTToDLL(root.right);

        DLLNode curr = new DLLNode(root.val);

        if (left[1] != null) {
            left[1].next = curr;
            curr.prev = left[1];
        }

        if (right[0] != null) {
            curr.next = right[0];
            right[0].prev = curr;
        }

        return new DLLNode[]{
            left[0] != null ? left[0] : curr,
            right[1] != null ? right[1] : curr
        };
    }

    // ✅ 2. 將排序陣列轉換為平衡的 BST
    static TreeNode sortedArrayToBST(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int m = (l + r) / 2;
        TreeNode root = new TreeNode(arr[m]);
        root.left = buildBST(arr, l, m - 1);
        root.right = buildBST(arr, m + 1, r);
        return root;
    }

    // ✅ 3. 檢查 BST 是否平衡，並計算平衡因子（左高 - 右高）
    static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int lh = checkBalance(node.left);
        int rh = checkBalance(node.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    static int getBalanceFactor(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ✅ 4. 將 BST 中每個節點值改為所有大於等於該節點值的總和
    static int total = 0;
    static void convertToGreaterTree(TreeNode root) {
        total = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        total += node.val;
        node.val = total;
        reverseInorder(node.left);
    }

    // 工具：中序列印 BST
    static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 工具：列印雙向鏈表
    static void printDLL(DLLNode head) {
        DLLNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // 測試主程式
    public static void main(String[] args) {
        // 原始 BST 範例
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(10);

        System.out.print("🔁 Inorder (Original BST): ");
        printInorder(root);
        System.out.println();

        // 1. 轉換為排序雙向鏈表
        DLLNode head = bstToSortedDoublyLinkedList(root);
        System.out.print("🔁 Doubly Linked List: ");
        printDLL(head);

        // 2. 建立平衡 BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sortedArray);
        System.out.print("✅ Balanced BST (Inorder): ");
        printInorder(balanced);
        System.out.println("\n是否平衡：" + isBalanced(balanced));
        System.out.println("Root 平衡因子：" + getBalanceFactor(balanced));

        // 4. 轉換為 Greater Tree
        convertToGreaterTree(root);
        System.out.print("💰 Greater Tree (Inorder): ");
        printInorder(root);
    }
}
