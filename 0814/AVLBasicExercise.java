import java.util.*;

public class AVLBasicExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { this.key = k; this.height = 1; }
    }

    static class AVL {
        Node root;

        // 公用 API
        public void insert(int key) { root = insert(root, key); }
        public boolean contains(int key) { return contains(root, key); }
        public int height() { return height(root); }
        public boolean isAVL() { return isAVL(root).ok; }

        // ---- 內部實作 ----
        private boolean contains(Node n, int key) {
            while (n != null) {
                if (key == n.key) return true;
                n = (key < n.key) ? n.left : n.right;
            }
            return false;
        }

        private int height(Node n) { return n == null ? 0 : n.height; }

        private int bf(Node n) { return (n == null) ? 0 : height(n.left) - height(n.right); }

        private void fix(Node n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            fix(y);
            fix(x);
            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            fix(x);
            fix(y);
            return y;
        }

        private Node rebalance(Node n, int keyJustInserted) {
            int balance = bf(n);
            if (balance > 1) { // left heavy
                if (keyJustInserted < n.left.key) { // LL
                    return rightRotate(n);
                } else { // LR
                    n.left = leftRotate(n.left);
                    return rightRotate(n);
                }
            }
            if (balance < -1) { // right heavy
                if (keyJustInserted > n.right.key) { // RR
                    return leftRotate(n);
                } else { // RL
                    n.right = rightRotate(n.right);
                    return leftRotate(n);
                }
            }
            return n;
        }

        private Node insert(Node n, int key) {
            if (n == null) return new Node(key);
            if (key < n.key) n.left = insert(n.left, key);
            else if (key > n.key) n.right = insert(n.right, key);
            else return n; // 不插入重複鍵

            fix(n);
            return rebalance(n, key);
        }

        // 驗證是否為 AVL：同時計算高度與檢查平衡
        private static class Check {
            boolean ok; int h;
            Check(boolean ok, int h){ this.ok = ok; this.h = h; }
        }
        private Check isAVL(Node n) {
            if (n == null) return new Check(true, 0);
            Check L = isAVL(n.left), R = isAVL(n.right);
            boolean ok = L.ok && R.ok && Math.abs(L.h - R.h) <= 1;
            int h = 1 + Math.max(L.h, R.h);
            return new Check(ok, h);
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] data = {10, 20, 30, 40, 50, 25};
        for (int x : data) avl.insert(x);

        System.out.println("高度: " + avl.height());             // 應為平衡高度
        System.out.println("含 25? " + avl.contains(25));        // true
        System.out.println("含 99? " + avl.contains(99));        // false
        System.out.println("是有效 AVL? " + avl.isAVL());        // true
    }
}
