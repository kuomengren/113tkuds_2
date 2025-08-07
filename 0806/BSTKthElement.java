import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val, size;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1; // 每個節點初始子樹大小為 1
        }
    }

    // ➕ 插入節點（更新 size）
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    // ➖ 刪除節點（標準 BST 刪除＋更新 size）
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }
        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    private static TreeNode findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    // 🧮 第 k 小元素
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException("樹是空的！");
        int leftSize = getSize(root.left);
        if (k <= leftSize) return kthSmallest(root.left, k);
        else if (k == leftSize + 1) return root.val;
        else return kthSmallest(root.right, k - leftSize - 1);
    }

    // 🧮 第 k 大元素（利用 size - k + 1 即可）
    public static int kthLargest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException("樹是空的！");
        int totalSize = getSize(root);
        return kthSmallest(root, totalSize - k + 1);
    }

    // 🧮 找出第 k ~ j 小的區間元素
    public static List<Integer> rangeKtoJ(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderWithRange(root, result, k, j, new int[]{0});
        return result;
    }

    private static void inOrderWithRange(TreeNode root, List<Integer> result, int k, int j, int[] count) {
        if (root == null) return;
        inOrderWithRange(root.left, result, k, j, count);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            result.add(root.val);
        }
        if (count[0] >= j) return;
        inOrderWithRange(root.right, result, k, j, count);
    }

    private static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    // 🧪 測試主程式
    public static void main(String[] args) {
        TreeNode root = null;
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        for (int val : data) {
            root = insert(root, val);
        }

        System.out.println("第 1 小元素：" + kthSmallest(root, 1)); // 20
        System.out.println("第 3 小元素：" + kthSmallest(root, 3)); // 40
        System.out.println("第 2 大元素：" + kthLargest(root, 2));   // 70

        System.out.println("第 2 到第 5 小的區間：");
        List<Integer> range = rangeKtoJ(root, 2, 5);
        System.out.println(range); // [30, 40, 50, 60]

        System.out.println("刪除 50 後，查第 3 小：");
        root = delete(root, 50);
        System.out.println(kthSmallest(root, 3)); // 40 → 依刪除後可能會變

        System.out.println("插入 35 後，查第 3 小：");
        root = insert(root, 35);
        System.out.println(kthSmallest(root, 3)); // 35
    }
}
