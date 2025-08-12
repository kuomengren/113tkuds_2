import java.util.*;

/**
 * Sliding window：
 * - 平均：用 Queue + 總和
 * - 中位數：兩堆 + 懶刪
 * - 最小/最大：TreeMap 當作多重集合
 */
public class MovingAverageStream {
    private final int k;
    private final Deque<Integer> q = new ArrayDeque<>();
    private long sum = 0;

    // median 結構
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private Map<Integer,Integer> delayed = new HashMap<>();
    private int maxSize=0, minSize=0;

    // min/max 結構
    private TreeMap<Integer,Integer> multiset = new TreeMap<>();

    public MovingAverageStream(int size){ this.k=size; }

    public double next(int val) {
        q.offerLast(val);
        sum += val;
        addMedian(val);
        multiset.put(val, multiset.getOrDefault(val,0)+1);

        if (q.size() > k) {
            int old = q.pollFirst();
            sum -= old;
            removeMedian(old);
            int c = multiset.get(old);
            if (c==1) multiset.remove(old); else multiset.put(old, c-1);
        }
        int denom = Math.min(q.size(), k);
        return denom==0 ? 0.0 : sum * 1.0 / denom;
    }

    public double getMedian() {
        if (q.isEmpty()) throw new NoSuchElementException("window empty");
        if (maxSize > minSize) return maxHeap.peek();
        return ((long)maxHeap.peek() + (long)minHeap.peek()) / 2.0;
    }

    public int getMin() {
        if (multiset.isEmpty()) throw new NoSuchElementException("window empty");
        return multiset.firstKey();
    }

    public int getMax() {
        if (multiset.isEmpty()) throw new NoSuchElementException("window empty");
        return multiset.lastKey();
    }

    // ---- median helpers ----
    private void addMedian(int num){
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) { maxHeap.offer(num); maxSize++; }
        else { minHeap.offer(num); minSize++; }
        balance();
    }
    private void removeMedian(int num){
        delayed.put(num, delayed.getOrDefault(num,0)+1);
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) { maxSize--; if (num==maxHeap.peek()) prune(maxHeap); }
        else { minSize--; if (!minHeap.isEmpty() && num==minHeap.peek()) prune(minHeap); }
        balance();
    }
    private void prune(PriorityQueue<Integer> heap){
        while(!heap.isEmpty()){
            int x = heap.peek();
            int c = delayed.getOrDefault(x, 0);
            if (c==0) break;
            if (c==1) delayed.remove(x); else delayed.put(x, c-1);
            heap.poll();
        }
    }
    private void balance(){
        if (maxSize > minSize + 1) { minHeap.offer(maxHeap.poll()); maxSize--; minSize++; prune(maxHeap); }
        else if (maxSize < minSize) { maxHeap.offer(minHeap.poll()); minSize--; maxSize++; prune(minHeap); }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
