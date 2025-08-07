import java.util.*;

public class TreeMirrorAndSymmetry {

    // 定義二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1️⃣ 判斷是否為對稱二元樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    // 2️⃣ 鏡像轉換（遞迴交換左右子樹）
    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 3️⃣ 比較兩棵樹是否互為鏡像
    public static boolean areMirror(TreeNode t1, TreeNode t2) {
        return isMirror(t1, t2);
    }

    // 4️⃣ 判斷 subtree 是否為 root 的子樹
    public static boolean isSubtree(TreeNode root, TreeNode subtree) {
        if (subtree == null) return true;
        if (root == null) return false;
        if (isSameTree(root, subtree)) return true;
        return isSubtree(root.left, subtree) || isSubtree(root.right, subtree);
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
                isSameTree(t1.left, t2.left) &&
                isSameTree(t1.right, t2.right);
    }

    // 中序印出樹的結構
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 測試用
    public static void main(String[] args) {
        // 建立一棵對稱樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹？ " + isSymmetric(root)); // true

        System.out.print("原始樹中序： ");
        printInOrder(root);
        System.out.println();

        TreeNode mirrored = mirror(root);
        System.out.print("鏡像後中序： ");
        printInOrder(mirrored);
        System.out.println();

        System.out.println("鏡像與原本互為鏡像？ " + areMirror(root, mirrored)); // true

        // 建立子樹 subtree
        TreeNode subtree = new TreeNode(2);
        subtree.left = new TreeNode(4);
        subtree.right = new TreeNode(3);
        System.out.println("subtree 是否為主樹的子樹？ " + isSubtree(root, subtree)); // true
    }
}
