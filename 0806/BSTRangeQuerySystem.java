import java.util.*;

public class BSTRangeQuerySystem {

    // 二元搜尋樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        return root;
    }

    // 1️⃣ 範圍查詢（列出所有值在 [min, max] 範圍內的節點）
    public static void rangeQuery(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val < max) rangeQuery(root.right, min, max, result);
    }

    // 2️⃣ 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min)
            return rangeCount(root.right, min, max);
        else if (root.val > max)
            return rangeCount(root.left, min, max);
        else
            return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3️⃣ 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min)
            return rangeSum(root.right, min, max);
        else if (root.val > max)
            return rangeSum(root.left, min, max);
        else
            return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4️⃣ 最接近查詢（回傳最接近 target 的節點值）
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            root = (target < root.val) ? root.left : root.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        // 建立一棵 BST
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        TreeNode root = null;
        for (int val : data) {
            root = insert(root, val);
        }

        int min = 35, max = 75;
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        System.out.println("🔍 範圍查詢 [" + min + ", " + max + "]： " + result);

        int count = rangeCount(root, min, max);
        System.out.println("🔢 範圍內節點數量： " + count);

        int sum = rangeSum(root, min, max);
        System.out.println("🧮 範圍內節點總和： " + sum);

        int target = 65;
        int closest = closestValue(root, target);
        System.out.println("🎯 最接近 " + target + " 的節點值為： " + closest);
    }
}
