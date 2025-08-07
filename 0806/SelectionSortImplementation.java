import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] data1 = {64, 25, 12, 22, 11};
        int[] data2 = data1.clone(); // 給 Bubble Sort 比較用

        System.out.println("📌 原始陣列: " + Arrays.toString(data1));

        System.out.println("\n🔷 選擇排序過程:");
        selectionSort(data1);

        System.out.println("\n🔶 氣泡排序過程:");
        bubbleSort(data2);
    }

    // ✅ 選擇排序實作
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪：%s\n", i + 1, Arrays.toString(arr));
        }

        System.out.println("📊 選擇排序完成！");
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }

    // ✅ 氣泡排序實作（比較用）
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.printf("第 %d 輪：%s\n", i + 1, Arrays.toString(arr));
            if (!swapped) break;
        }

        System.out.println("📊 氣泡排序完成！");
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }
}
