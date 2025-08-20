import java.util.ArrayList;

class Task {
    String name;
    int priority;
    long timestamp; // 用於相同優先級時保持先來先執行

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }
}

public class PriorityQueueWithHeap {
    private ArrayList<Task> heap;
    private long counter = 0; // 確保 FIFO 順序

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
    }

    // 添加任務
    public void addTask(String name, int priority) {
        heap.add(new Task(name, priority, counter++));
        heapifyUp(heap.size() - 1);
    }

    // 執行最高優先級任務
    public String executeNext() {
        if (isEmpty()) throw new IllegalStateException("沒有任務可以執行");
        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top.name;
    }

    // 查看下一個要執行的任務
    public String peek() {
        if (isEmpty()) throw new IllegalStateException("任務佇列為空");
        return heap.get(0).name;
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        boolean found = false;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).name.equals(name)) {
                heap.get(i).priority = newPriority;
                // 修改後需要重新調整 heap
                heapifyUp(i);
                heapifyDown(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到任務：" + name);
        }
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 往上調整（插入或提高優先級）
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // 往下調整（刪除或降低優先級）
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap.get(left), heap.get(largest)) > 0) {
                largest = left;
            }
            if (right < size && compare(heap.get(right), heap.get(largest)) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // 比較任務（優先級大優先，其次時間早優先）
    private int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return a.priority - b.priority; // 優先級大者優先
        }
        return (b.timestamp > a.timestamp) ? 1 : -1; // 先來的優先
    }

    // 交換兩個元素
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println(pq.executeNext()); // 緊急修復
        System.out.println(pq.executeNext()); // 更新
        System.out.println(pq.executeNext()); // 備份
    }
}
