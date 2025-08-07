import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ✅ 一、定義多路樹節點結構
    static class MultiNode {
        String value;
        List<MultiNode> children;

        MultiNode(String val) {
            this.value = val;
            this.children = new ArrayList<>();
        }
    }

    // ✅ 二、深度優先走訪 DFS（遞迴）
    public static void dfs(MultiNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiNode child : node.children) {
            dfs(child);
        }
    }

    // ✅ 三、廣度優先走訪 BFS（佇列）
    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiNode node = queue.poll();
            System.out.print(node.value + " ");
            for (MultiNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // ✅ 四、計算多路樹高度
    public static int getHeight(MultiNode root) {
        if (root == null) return 0;
        int maxHeight = 0;
        for (MultiNode child : root.children) {
            maxHeight = Math.max(maxHeight, getHeight(child));
        }
        return maxHeight + 1;
    }

    // ✅ 五、計算每個節點的度數
    public static void printDegrees(MultiNode root) {
        if (root == null) return;
        System.out.println("節點：" + root.value + "，度數：" + root.children.size());
        for (MultiNode child : root.children) {
            printDegrees(child);
        }
    }

    // ✅ 六、簡單決策樹（猜數字遊戲）
    static class DecisionNode {
        String question;
        DecisionNode yes, no;

        DecisionNode(String q) {
            this.question = q;
        }
    }

    public static void simulateDecisionTree(DecisionNode root, Scanner scanner) {
        if (root == null) return;
        if (root.yes == null && root.no == null) {
            System.out.println("猜測結果：➡️ " + root.question);
            return;
        }
        System.out.print(root.question + " (y/n)：");
        String ans = scanner.nextLine().trim().toLowerCase();
        if (ans.equals("y")) simulateDecisionTree(root.yes, scanner);
        else simulateDecisionTree(root.no, scanner);
    }

    // ✅ 主程式：測試所有功能
    public static void main(String[] args) {
        // ➤ 建立多路樹
        MultiNode root = new MultiNode("A");
        MultiNode b = new MultiNode("B");
        MultiNode c = new MultiNode("C");
        MultiNode d = new MultiNode("D");
        MultiNode e = new MultiNode("E");
        MultiNode f = new MultiNode("F");

        root.children.add(b);
        root.children.add(c);
        b.children.add(d);
        b.children.add(e);
        c.children.add(f);

        System.out.println("🔁 DFS：");
        dfs(root); // A B D E C F

        System.out.println("\n🔁 BFS：");
        bfs(root); // A B C D E F

        System.out.println("\n📏 樹高：" + getHeight(root)); // 3
        System.out.println("🌿 每個節點度數：");
        printDegrees(root);

        // ➤ 模擬簡單決策樹（猜數字）
        DecisionNode q1 = new DecisionNode("是偶數嗎？");
        q1.yes = new DecisionNode("大於5嗎？");
        q1.no = new DecisionNode("小於5嗎？");

        q1.yes.yes = new DecisionNode("你猜的是 8！");
        q1.yes.no = new DecisionNode("你猜的是 6！");
        q1.no.yes = new DecisionNode("你猜的是 3！");
        q1.no.no = new DecisionNode("你猜的是 5！");

        System.out.println("\n🧠 決策樹：猜數字 2~10");
        Scanner scanner = new Scanner(System.in);
        simulateDecisionTree(q1, scanner);
    }
}
