import java.util.*;

public class AVLRotationExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key = k; height = 1; }
    }

    static class Rotator {
        private int h(Node n){ return n == null ? 0 : n.height; }
        private void fix(Node n){ n.height = 1 + Math.max(h(n.left), h(n.right)); }

        public Node rightRotate(Node y){
            Node x = y.left, T2 = x.right;
            x.right = y; y.left = T2;
            fix(y); fix(x);
            return x;
        }

        public Node leftRotate(Node x){
            Node y = x.right, T2 = y.left;
            y.left = x; x.right = T2;
            fix(x); fix(y);
            return y;
        }
    }

    // 建立不平衡情況並套旋轉
    public static void main(String[] args) {
        Rotator R = new Rotator();

        // LL：插入 30,20,10 時，30 為根且左重
        Node LL = new Node(30);
        LL.left = new Node(20);
        LL.left.left = new Node(10);
        fixAll(LL);
        System.out.println("LL 前根: " + LL.key); // 30
        LL = R.rightRotate(LL);
        System.out.println("LL 後根: " + LL.key); // 20

        // RR：插入 10,20,30
        Node RR = new Node(10);
        RR.right = new Node(20);
        RR.right.right = new Node(30);
        fixAll(RR);
        System.out.println("RR 前根: " + RR.key); // 10
        RR = R.leftRotate(RR);
        System.out.println("RR 後根: " + RR.key); // 20

        // LR：插入 30,10,20
        Node LR = new Node(30);
        LR.left = new Node(10);
        LR.left.right = new Node(20);
        fixAll(LR);
        System.out.println("LR 前根: " + LR.key); // 30
        LR.left = R.leftRotate(LR.left);
        LR = R.rightRotate(LR);
        System.out.println("LR 後根: " + LR.key); // 20

        // RL：插入 10,30,20
        Node RL = new Node(10);
        RL.right = new Node(30);
        RL.right.left = new Node(20);
        fixAll(RL);
        System.out.println("RL 前根: " + RL.key); // 10
        RL.right = R.rightRotate(RL.right);
        RL = R.leftRotate(RL);
        System.out.println("RL 後根: " + RL.key); // 20
    }

    // 小工具：自底向上修正高度
    private static int fixAll(Node n){
        if (n == null) return 0;
        int L = fixAll(n.left), R = fixAll(n.right);
        n.height = 1 + Math.max(L, R);
        return n.height;
    }
}
