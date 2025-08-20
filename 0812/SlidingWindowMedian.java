import java.util.*;

public class SlidingWindowMedian {
    // 小的一半 (Max Heap)
    private PriorityQueue<Integer> small;
    // 大的一半 (Min Heap)
    private PriorityQueue<Integer> large;
    // 延遲刪除的紀錄表
    private Map<Integer, Integer> delayed;
    private int smallSize = 0;
    private int largeSize = 0;

    public SlidingWindowMedian() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
        delayed = new HashMap<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
            smallSize--;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            largeSize--;
            if (num == large.peek()) {
                prune(large);
            }
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        // small 比 large 多超過 1
        if (smallSize > largeSize + 1) {
            large.offer(small.poll());
            smallSize--;
            largeSize++;
            prune(small);
        }
        // large 比 small 多
        else if (largeSize > smallSize) {
            small.offer(large.poll());
            largeSize--;
            smallSize++;
            prune(large);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
            heap.poll();
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return (double) small.peek();
        } else {
            return ((double) small.peek() + (double) large.peek()) / 2.0;
        }
    }

    // 測試
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] res1 = swm.medianSlidingWindow(nums1, 3);
        System.out.println(Arrays.toString(res1)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        double[] res2 = swm.medianSlidingWindow(nums2, 2);
        System.out.println(Arrays.toString(res2)); // [1.5, 2.5, 3.5]
    }
}