import java.util.*;

public class BinaryTreeBasicOperations {

    // 二元樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 建立一棵測試樹
        TreeNode root = mockBinaryTree();

        // 1️⃣ 計算總和與平均
        int sum = sumTree(root);
        int count = countNodes(root);
        double avg = (double) sum / count;
        System.out.println("🧮 總和：" + sum + "，節點數：" + count + "，平均值：" + avg);

        // 2️⃣ 最大值與最小值
        int max = findMax(root);
        int min = findMin(root);
        System.out.println("🔍 最大值：" + max + "，最小值：" + min);

        // 3️⃣ 樹的寬度
        int width = maxWidth(root);
        System.out.println("🌳 最大寬度（單層節點最多數量）：" + width);

        // 4️⃣ 是否為完全二元樹
        boolean isComplete = isCompleteBinaryTree(root);
        System.out.println("📏 是否為完全二元樹？" + (isComplete ? "是 ✅" : "否 ❌"));
    }

    // 模擬二元樹
    public static TreeNode mockBinaryTree() {
        // 範例樹結構如下：
        //        10
        //       /  \
        //      5    15
        //     / \   /
        //    3   7 12
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        return root;
    }

    // 1️⃣ 總和
    public static int sumTree(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumTree(root.left) + sumTree(root.right);
    }

    // 1️⃣ 節點數
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 2️⃣ 找最大值
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    // 2️⃣ 找最小值
    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3️⃣ 計算最大寬度（BFS）
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }

        return maxWidth;
    }

    // 4️⃣ 是否為完全二元樹（BFS 判斷）
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean foundNull = false;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                foundNull = true;
            } else {
                if (foundNull) return false;
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }

        return true;
    }
}
