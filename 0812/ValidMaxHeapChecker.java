public class ValidMaxHeapChecker {

    // 檢查是否為 Max Heap
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1) return true; // 空陣列或單一元素一定符合

        // 遍歷所有非葉子節點
        for (int i = (n - 2) / 2; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 檢查左子節點
            if (left < n && arr[i] < arr[left]) {
                System.out.println("false (索引 " + left + " 的 " + arr[left] + " 大於父節點索引 " + i + " 的 " + arr[i] + ")");
                return false;
            }

            // 檢查右子節點
            if (right < n && arr[i] < arr[right]) {
                System.out.println("false (索引 " + right + " 的 " + arr[right] + " 大於父節點索引 " + i + " 的 " + arr[i] + ")");
                return false;
            }
        }
        return true;
    }

    // 測試
    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},   // true
            {100, 90, 80, 95, 60, 75, 65},   // false
            {50},                            // true
            {}                               // true
        };

        for (int[] test : testCases) {
            boolean result = isValidMaxHeap(test);
            if (result) {
                System.out.println("true");
            }
        }
    }
}