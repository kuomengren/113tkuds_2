import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // ✅ 1. 驗證是否為 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) &&
               validate(node.right, node.val, max);
    }

    // ✅ 2. 修復有兩個節點錯誤的 BST
    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        inorderFind(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private void inorderFind(TreeNode root) {
        if (root == null) return;
        inorderFind(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorderFind(root.right);
    }

    // ✅ 3. 找出不合法的節點數量（需移除才能成 BST）
    public static int minRemovalsToMakeBST(TreeNode root) {
        return dfs(root).removals;
    }

    static class Result {
        boolean isBST;
        int removals;
        long min, max;

        Result(boolean isBST, int removals, long min, long max) {
            this.isBST = isBST;
            this.removals = removals;
            this.min = min;
            this.max = max;
        }
    }

    private static Result dfs(TreeNode node) {
        if (node == null)
            return new Result(true, 0, Long.MAX_VALUE, Long.MIN_VALUE);

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        if (left.isBST && right.isBST &&
            node.val > left.max && node.val < right.min) {
            return new Result(
                true,
                left.removals + right.removals,
                Math.min(node.val, left.min),
                Math.max(node.val, right.max)
            );
        }

        // 若不是 BST，則需刪除目前子樹（+1）
        return new Result(false, 1 + left.removals + right.removals, 0, 0);
    }

    // ✅ 顯示中序（方便觀察修復結果）
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 🧪 測試主程式
    public static void main(String[] args) {
        // 範例：錯誤 BST（節點 2 與 6 交換）
        /*
                  4
                 / \
                2   6
               / \   \
              1   3   5
         */

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(6); // 錯
        root.right = new TreeNode(2); // 錯
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(5);

        System.out.println("✅ 是否為有效 BST: " + isValidBST(root));

        // 找出不合法節點數量
        System.out.println("❌ 需要移除節點數: " + minRemovalsToMakeBST(root));

        // 修復 BST
        BSTValidationAndRepair fixer = new BSTValidationAndRepair();
        fixer.recoverTree(root);

        System.out.println("🔁 修復後中序輸出：");
        inorderPrint(root);
        System.out.println();

        System.out.println("✅ 修復後為有效 BST: " + isValidBST(root));
    }
}
