import java.util.*;

class HeapNode {
    int value;      // 當前值
    int arrayIndex; // 來自第幾個陣列
    int elementIndex; // 該陣列中的索引

    public HeapNode(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        // 1. 先把每個陣列的第一個元素放入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new HeapNode(arrays[i][0], i, 0));
            }
        }

        // 2. 不斷從 heap 取最小值，並把該陣列的下一個元素放進 heap
        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    // 測試
    public static void main(String[] args) {
        int[][] test1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] test2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] test3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(test1)); // [1, 1, 2, 3, 4, 4, 5, 6]
        System.out.println(mergeKSortedArrays(test2)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(mergeKSortedArrays(test3)); // [0, 1]
    }
}