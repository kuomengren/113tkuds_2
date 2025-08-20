import java.util.PriorityQueue;
import java.util.Collections;

public class KthSmallestElement {

    // 方法1：使用大小為K的 Max Heap
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大值，保持大小為K
            }
        }
        return maxHeap.peek();
    }

    // 方法2：使用 Min Heap，提取K次
    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    // 測試效能
    public static void testPerformance(int[] arr, int k) {
        long start, end;

        start = System.nanoTime();
        int ans1 = kthSmallestUsingMaxHeap(arr, k);
        end = System.nanoTime();
        System.out.println("方法1（Max Heap, 大小為K）結果：" + ans1 + "，耗時：" + (end - start) + " ns");

        start = System.nanoTime();
        int ans2 = kthSmallestUsingMinHeap(arr, k);
        end = System.nanoTime();
        System.out.println("方法2（Min Heap, 提取K次）結果：" + ans2 + "，耗時：" + (end - start) + " ns");
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int[] arr2 = {1};
        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("測試案例1：");
        testPerformance(arr1, 3); // 答案應該是 7

        System.out.println("\n測試案例2：");
        testPerformance(arr2, 1); // 答案應該是 1

        System.out.println("\n測試案例3：");
        testPerformance(arr3, 4); // 答案應該是 3
    }
}