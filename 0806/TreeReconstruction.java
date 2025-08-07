import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. 根據前序 + 中序重建二元樹
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inorderMap);
    }

    private static TreeNode buildPreIn(int[] pre, int preL, int preR,
                                       int[] in, int inL, int inR,
                                       Map<Integer, Integer> map) {
        if (preL > preR || inL > inR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        int rootIdx = map.get(pre[preL]);
        int leftSize = rootIdx - inL;

        root.left = buildPreIn(pre, preL + 1, preL + leftSize,
                               in, inL, rootIdx - 1, map);
        root.right = buildPreIn(pre, preL + leftSize + 1, preR,
                                in, rootIdx + 1, inR, map);
        return root;
    }

    // ✅ 2. 根據後序 + 中序重建二元樹
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1,
                           inorder, 0, inorder.length - 1, inorderMap);
    }

    private static TreeNode buildPostIn(int[] post, int postL, int postR,
                                        int[] in, int inL, int inR,
                                        Map<Integer, Integer> map) {
        if (postL > postR || inL > inR) return null;
        TreeNode root = new TreeNode(post[postR]);
        int rootIdx = map.get(post[postR]);
        int leftSize = rootIdx - inL;

        root.left = buildPostIn(post, postL, postL + leftSize - 1,
                                in, inL, rootIdx - 1, map);
        root.right = buildPostIn(post, postL + leftSize, postR - 1,
                                 in, rootIdx + 1, inR, map);
        return root;
    }

    // ✅ 3. 根據層序結果建立完全二元樹（僅數組還原完全樹）
    public static TreeNode buildFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode curr = queue.poll();
            if (levelOrder[i] != null) {
                curr.left = new TreeNode(levelOrder[i]);
                queue.offer(curr.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                curr.right = new TreeNode(levelOrder[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // ✅ 4. 驗證重建結果（以中序遍歷印出）
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        Integer[] levelOrder = {3, 9, 20, null, null, 15, 7};

        TreeNode fromPreIn = buildFromPreIn(preorder, inorder);
        TreeNode fromPostIn = buildFromPostIn(postorder, inorder);
        TreeNode fromLevelOrder = buildFromLevelOrder(levelOrder);

        System.out.print("🔁 Inorder (from Pre + In): ");
        printInorder(fromPreIn);
        System.out.print("\n🔁 Inorder (from Post + In): ");
        printInorder(fromPostIn);
        System.out.print("\n🔁 Inorder (from LevelOrder): ");
        printInorder(fromLevelOrder);
    }
}
