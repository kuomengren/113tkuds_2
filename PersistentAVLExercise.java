import java.util.*;

public class PersistentAVLExercise {

    static final class Node {
        final int key, height;
        final Node left, right;
        Node(int key, Node left, Node right){
            this.key = key; this.left = left; this.right = right;
            this.height = 1 + Math.max(h(left), h(right));
        }
        static int h(Node n){ return n==null?0:n.height; }
        static int bf(Node n){ return n==null?0: h(n.left)-h(n.right); }
        static int h(Node l, Node r){ return 1 + Math.max(h(l), h(r)); }
    }

    static class PersistentAVL {
        private final List<Node> versions = new ArrayList<>();

        public PersistentAVL(){
            versions.add(null); // 版本0：空樹
        }

        public int latestVersion(){ return versions.size()-1; }
        public Node rootOf(int version){ return versions.get(version); }

        public int insertNewVersion(int baseVersion, int key){
            Node base = versions.get(baseVersion);
            Node nr = insert(base, key);
            versions.add(nr);
            return latestVersion();
        }

        public boolean contains(int version, int key){
            Node n = versions.get(version);
            while (n != null){
                if (key == n.key) return true;
                n = (key < n.key) ? n.left : n.right;
            }
            return false;
        }

        // ---- 不可變操作：回傳新節點 ----
        private Node insert(Node n, int key){
            if (n == null) return new Node(key, null, null);
            if (key < n.key){
                Node newLeft = insert(n.left, key);
                Node updated = new Node(n.key, newLeft, n.right);
                return rebalance(updated);
            } else if (key > n.key){
                Node newRight = insert(n.right, key);
                Node updated = new Node(n.key, n.left, newRight);
                return rebalance(updated);
            } else {
                return n; // 不插重複
            }
        }

        private Node rightRotate(Node y){
            Node x = y.left;
            Node T2 = x.right;
            Node newY = new Node(y.key, T2, y.right);
            return new Node(x.key, x.left, newY);
        }

        private Node leftRotate(Node x){
            Node y = x.right;
            Node T2 = y.left;
            Node newX = new Node(x.key, x.left, T2);
            return new Node(y.key, newX, y.right);
        }

        private Node rebalance(Node n){
            int b = Node.bf(n);
            if (b > 1){
                if (Node.bf(n.left) >= 0) return rightRotate(n); // LL
                Node newLeft = leftRotate(n.left);               // LR
                Node updated = new Node(n.key, newLeft, n.right);
                return rightRotate(updated);
            }
            if (b < -1){
                if (Node.bf(n.right) <= 0) return leftRotate(n); // RR
                Node newRight = rightRotate(n.right);            // RL
                Node updated = new Node(n.key, n.left, newRight);
                return leftRotate(updated);
            }
            return n;
        }
    }

    public static void main(String[] args) {
        PersistentAVL pavl = new PersistentAVL();
        int v0 = 0;
        int v1 = pavl.insertNewVersion(v0, 10);
        int v2 = pavl.insertNewVersion(v1, 20);
        int v3 = pavl.insertNewVersion(v2, 5);
        int v4 = pavl.insertNewVersion(v3, 4);

        System.out.println("v0 含 10? " + pavl.contains(v0, 10)); // false
        System.out.println("v2 含 20? " + pavl.contains(v2, 20)); // true
        System.out.println("v3 含 5? "  + pavl.contains(v3, 5));  // true
        System.out.println("v2 含 5? "  + pavl.contains(v2, 5));  // false（版本獨立）
        System.out.println("最新版本 = v" + pavl.latestVersion()); // 4
    }
}
