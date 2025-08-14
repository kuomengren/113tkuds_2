import java.util.*;

public class AVLDeleteExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key = k; height = 1; }
    }

    static class AVL {
        Node root;

        public void insert(int key){ root = insert(root, key); }
        public void delete(int key){ root = delete(root, key); }
        public boolean contains(int key){ return contains(root, key); }
        public boolean isAVL(){ return isAVL(root).ok; }

        private int h(Node n){ return n == null ? 0 : n.height; }
        private int bf(Node n){ return n == null ? 0 : h(n.left) - h(n.right); }
        private void fix(Node n){ n.height = 1 + Math.max(h(n.left), h(n.right)); }

        private Node rightRotate(Node y){
            Node x = y.left, T2 = x.right;
            x.right = y; y.left = T2;
            fix(y); fix(x);
            return x;
        }
        private Node leftRotate(Node x){
            Node y = x.right, T2 = y.left;
            y.left = x; x.right = T2;
            fix(x); fix(y);
            return y;
        }

        private Node rebalance(Node n){
            int balance = bf(n);
            if (balance > 1){
                if (bf(n.left) >= 0) return rightRotate(n);      // LL
                else { n.left = leftRotate(n.left); return rightRotate(n);} // LR
            }
            if (balance < -1){
                if (bf(n.right) <= 0) return leftRotate(n);      // RR
                else { n.right = rightRotate(n.right); return leftRotate(n);} // RL
            }
            return n;
        }

        private Node insert(Node n, int key){
            if (n == null) return new Node(key);
            if (key < n.key) n.left = insert(n.left, key);
            else if (key > n.key) n.right = insert(n.right, key);
            else return n; // 不插重複

            fix(n);
            return rebalance(n);
        }

        private Node minNode(Node n){
            while (n.left != null) n = n.left;
            return n;
        }

        private Node delete(Node n, int key){
            if (n == null) return null;
            if (key < n.key) n.left = delete(n.left, key);
            else if (key > n.key) n.right = delete(n.right, key);
            else {
                // 0或1子
                if (n.left == null || n.right == null) {
                    n = (n.left != null) ? n.left : n.right;
                } else {
                    // 2子：用後繼（右子最小）
                    Node succ = minNode(n.right);
                    n.key = succ.key;
                    n.right = delete(n.right, succ.key);
                }
            }
            if (n == null) return null; // 被刪成空，直接回傳
            fix(n);
            return rebalance(n);
        }

        private boolean contains(Node n, int key){
            while (n != null) {
                if (key == n.key) return true;
                n = (key < n.key) ? n.left : n.right;
            }
            return false;
        }

        private static class Check {
            boolean ok; int h;
            Check(boolean ok, int h){ this.ok = ok; this.h = h; }
        }
        private Check isAVL(Node n){
            if (n == null) return new Check(true, 0);
            Check L = isAVL(n.left), R = isAVL(n.right);
            boolean ok = L.ok && R.ok && Math.abs(L.h - R.h) <= 1;
            int h = 1 + Math.max(L.h, R.h);
            return new Check(ok, h);
        }
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] arr = {20,10,30,5,15,25,40,3,7,13,17};
        for (int x : arr) avl.insert(x);
        System.out.println("初始 AVL? " + avl.isAVL());

        avl.delete(3);   // 刪葉
        avl.delete(25);  // 刪單子
        avl.delete(10);  // 刪兩子
        System.out.println("刪除後 AVL? " + avl.isAVL());
        System.out.println("含 10? " + avl.contains(10)); // false
    }
}
