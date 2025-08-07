import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // ✅ 1. 找出所有根到葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path)); // 到葉節點
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    // ✅ 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    // ✅ 3. 找出總和最大的根到葉路徑
    static class MaxSumPath {
        int maxSum = Integer.MIN_VALUE;
        List<Integer> maxPath = new ArrayList<>();
    }

    public static List<Integer> maxRootToLeafPath(TreeNode root) {
        MaxSumPath tracker = new MaxSumPath();
        findMaxPath(root, new ArrayList<>(), 0, tracker);
        return tracker.maxPath;
    }

    private static void findMaxPath(TreeNode node, List<Integer> path, int sum, MaxSumPath tracker) {
        if (node == null) return;
        path.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum > tracker.maxSum) {
                tracker.maxSum = sum;
                tracker.maxPath = new ArrayList<>(path);
            }
        } else {
            findMaxPath(node.left, path, sum, tracker);
            findMaxPath(node.right, path, sum, tracker);
        }
        path.remove(path.size() - 1);
    }

    // ✅ 4. 計算任意兩節點間最大路徑和（直徑問題）
    static int maxPathSumGlobal;

    public static int maxPathSum(TreeNode root) {
        maxPathSumGlobal = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSumGlobal;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));
        int totalPath = node.val + leftGain + rightGain;
        maxPathSumGlobal = Math.max(maxPathSumGlobal, totalPath);
        return node.val + Math.max(leftGain, rightGain); // 回傳單邊最大
    }

    // 🧪 測試主程式
    public static void main(String[] args) {
        /*
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \      \
            7    2      1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("✅ 所有根到葉路徑：");
        for (List<Integer> path : allRootToLeafPaths(root)) {
            System.out.println(path);
        }

        int target = 22;
        System.out.println("🔍 是否存在總和為 " + target + " 的根到葉路徑？ " + hasPathSum(root, target));

        System.out.println("🌟 最大總和的根到葉路徑：" + maxRootToLeafPath(root));

        System.out.println("🌐 任意兩節點最大路徑和（直徑）：" + maxPathSum(root));
    }
}
