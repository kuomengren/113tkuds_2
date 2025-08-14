import java.util.*;

public class AVLLeaderboardSystem {

    // 排序鍵：分數高者在前（compareTo 回傳負值表示"更小"→前面）
    static class Key implements Comparable<Key> {
        int score;
        int playerId;
        Key(int score, int playerId){ this.score = score; this.playerId = playerId; }
        @Override public int compareTo(Key o) {
            if (this.score != o.score) return Integer.compare(o.score, this.score); // DESC
            return Integer.compare(this.playerId, o.playerId); // tie: id ASC
        }
        @Override public String toString(){ return "(" + playerId + ":" + score + ")"; }
    }

    static class Node {
        Key key;
        int height, size;
        Node left, right;
        Node(Key k){ key = k; height = 1; size = 1; }
    }

    static class OrderStatisticAVL {
        Node root;

        private int h(Node n){ return n==null?0:n.height; }
        private int sz(Node n){ return n==null?0:n.size; }
        private void fix(Node n){
            n.height = 1 + Math.max(h(n.left), h(n.right));
            n.size   = 1 + sz(n.left) + sz(n.right);
        }
        private int bf(Node n){ return n==null?0: h(n.left)-h(n.right); }

        private Node rightRotate(Node y){
            Node x = y.left, T2 = x.right;
            x.right = y; y.left = T2;
            fix(y); fix(x); return x;
        }
        private Node leftRotate(Node x){
            Node y = x.right, T2 = y.left;
            y.left = x; x.right = T2;
            fix(x); fix(y); return y;
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

        public void insert(Key k){ root = insert(root, k); }
        private Node insert(Node n, Key k){
            if (n == null) return new Node(k);
            int cmp = k.compareTo(n.key);
            if (cmp < 0) n.left = insert(n.left, k);
            else if (cmp > 0) n.right = insert(n.right, k);
            else return n; // 相同鍵不重複插入（理論上不會因 playerId 唯一）
            fix(n);
            return rebalance(n);
        }

        public void delete(Key k){ root = delete(root, k); }
        private Node minNode(Node n){ while(n.left!=null) n=n.left; return n; }
        private Node delete(Node n, Key k){
            if (n == null) return null;
            int cmp = k.compareTo(n.key);
            if (cmp < 0) n.left = delete(n.left, k);
            else if (cmp > 0) n.right = delete(n.right, k);
            else {
                if (n.left == null || n.right == null) {
                    n = (n.left != null) ? n.left : n.right;
                } else {
                    Node succ = minNode(n.right);
                    n.key = succ.key;
                    n.right = delete(n.right, succ.key);
                }
            }
            if (n == null) return null;
            fix(n);
            return rebalance(n);
        }

        // rank：該鍵在「高分→低分」中的名次（第一名 = 1）
        public int rank(Key k){ return rank(root, k); }
        private int rank(Node n, Key k){
            if (n == null) return -1; // 不存在
            int cmp = k.compareTo(n.key);
            if (cmp < 0) { // k 在左子樹方向（更前面）
                return rank(n.left, k);
            } else if (cmp > 0) {
                int r = rank(n.right, k);
                if (r == -1) return -1;
                return sz(n.left) + 1 + r;
            } else {
                return sz(n.left) + 1;
            }
        }

        // select：取第 k 名（k>=1）
        public Key select(int k){
            if (k <= 0 || k > sz(root)) return null;
            return select(root, k);
        }
        private Key select(Node n, int k){
            int leftSize = sz(n.left);
            if (k == leftSize + 1) return n.key;
            if (k <= leftSize) return select(n.left, k);
            return select(n.right, k - leftSize - 1);
        }

        public List<Key> topK(int k){
            List<Key> ans = new ArrayList<>();
            topK(root, k, ans);
            return ans;
        }
        private void topK(Node n, int k, List<Key> out){
            if (n == null || out.size() >= k) return;
            topK(n.left, k, out);            // 左子：更高分
            if (out.size() < k) out.add(n.key);
            topK(n.right, k, out);
        }
    }

    static class Leaderboard {
        private final OrderStatisticAVL tree = new OrderStatisticAVL();
        private final Map<Integer, Integer> currScore = new HashMap<>();

        // 新增或更新玩家分數
        public void addOrUpdateScore(int playerId, int score){
            if (currScore.containsKey(playerId)) {
                int old = currScore.get(playerId);
                tree.delete(new Key(old, playerId));
            }
            currScore.put(playerId, score);
            tree.insert(new Key(score, playerId));
        }

        // 取得玩家目前名次（1 = 第一名；不存在回傳 -1）
        public int getRank(int playerId){
            Integer sc = currScore.get(playerId);
            if (sc == null) return -1;
            return tree.rank(new Key(sc, playerId));
        }

        // 取得前 K 名 (playerId, score) 列表
        public List<int[]> topK(int k){
            List<Key> keys = tree.topK(k);
            List<int[]> ans = new ArrayList<>();
            for (Key x : keys) ans.add(new int[]{x.playerId, x.score});
            return ans;
        }

        // 取第 k 名（1-based）
        public int[] selectK(int k){
            Key kx = tree.select(k);
            if (kx == null) return null;
            return new int[]{kx.playerId, kx.score};
        }
    }

    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();
        lb.addOrUpdateScore(1, 1200);
        lb.addOrUpdateScore(2, 1500);
        lb.addOrUpdateScore(3, 1500); // 與 2 同分→以 playerId 升序排序
        lb.addOrUpdateScore(4, 900);

        System.out.println("Top3:");
        for (int[] p : lb.topK(3)) System.out.println(Arrays.toString(p));
        // 期望：[2,1500], [3,1500], [1,1200]

        System.out.println("player 1 rank = " + lb.getRank(1)); // 3
        lb.addOrUpdateScore(1, 1700);
        System.out.println("player 1 rank (after update) = " + lb.getRank(1)); // 1
        System.out.println("第2名 = " + Arrays.toString(lb.selectK(2)));
    }
}
