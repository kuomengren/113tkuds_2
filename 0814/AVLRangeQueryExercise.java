import java.util.*;

public class AVLRangeQueryExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key = k; height = 1; }
    }

    static class AVL {
        Node root;

        public void insert(int key){ root = insert(root, key); }
        public List<Integer> rangeQuery(int min, int max){
            List<Integer> ans = new ArrayList<>();
            rangeQuery(root, min, max, ans);
            return ans;
        }

        private int h(Node n){ return n == null ? 0 : n.height; }
        private void fix(Node n){ n.height = 1 + Math.max(h(n.left), h(n.right)); }
        private int bf(Node n){ return n == null ? 0 : h(n.left)-h(n.right); }

        private Node leftRotate(Node x){
            Node y = x.right, T2 = y.left;
            y.left = x; x.right = T2;
            fix(x); fix(y); return y;
        }
        private Node rightRotate(Node y){
            Node x = y.left, T2 = x.right;
            x.right = y; y.left = T2;
            fix(y); fix(x); return x;
        }
        private Node rebalance(Node n){
            int b = bf(n);
            if (b > 1){
                if (bf(n.left) >= 0) return rightRotate(n);
                else { n.left = leftRotate(n.left); return rightRotate(n); }
            }
            if (b < -1){
                if (bf(n.right) <= 0) return leftRotate(n);
                else { n.right = rightRotate(n.right); return leftRotate(n); }
            }
            return n;
        }
        private Node insert(Node n, int key){
            if (n == null) return new Node(key);
            if (key < n.key) n.left = insert(n.left, key);
            else if (key > n.key) n.right = insert(n.right, key);
            else return n;
            fix(n);
            return rebalance(n);
        }

        private void rangeQuery(Node n, int min, int max, List<Integer> out){
            if (n == null) return;
            if (n.key > min) rangeQuery(n.left, min, max, out);     // 可能還有更小的在左
            if (min <= n.key && n.key <= max) out.add(n.key);       // 命中
            if (n.key < max) rangeQuery(n.right, min, max, out);    // 可能還有更大的在右
        }
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] arr = {15,10,20,8,12,17,25,6,11,16,19,27};
        for (int x : arr) avl.insert(x);
        System.out.println(avl.rangeQuery(11, 20)); // [11,12,15,16,17,19,20]（依中序）
    }
}
