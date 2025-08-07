import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {4, 5, 2, 4, 1, 2, 8, 5, 9};
        int[] sortedA = {1, 3, 5, 7, 9};
        int[] sortedB = {2, 4, 6, 8, 10};

        System.out.println("🎯 原始陣列 array1: " + Arrays.toString(array1));

        // 1️⃣ 移除重複元素
        int[] uniqueArray = removeDuplicates(array1);
        System.out.println("✅ 移除重複元素後: " + Arrays.toString(uniqueArray));

        // 2️⃣ 合併兩個已排序的陣列
        int[] mergedArray = mergeSortedArrays(sortedA, sortedB);
        System.out.println("🔗 合併後的排序陣列: " + Arrays.toString(mergedArray));

        // 3️⃣ 出現頻率最高的元素
        int mostFrequent = findMostFrequentElement(array1);
        System.out.println("🔥 出現頻率最高的元素: " + mostFrequent);

        // 4️⃣ 分割成兩個總和相近的子陣列
        System.out.println("🔀 分割成總和相近的兩組：");
        splitArray(array1);
    }

    // 1️⃣ 移除重複元素
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // 2️⃣ 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 3️⃣ 找出出現頻率最高的元素
    public static int findMostFrequentElement(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        int mostFrequent = array[0];

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        return mostFrequent;
    }

    // 4️⃣ 分割成兩個總和最接近的子陣列（貪婪法）
    public static void splitArray(int[] array) {
        Arrays.sort(array); // 先排序
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        int sum1 = 0, sum2 = 0;

        // 從大到小分配，使兩邊加總接近
        for (int i = array.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                part1.add(array[i]);
                sum1 += array[i];
            } else {
                part2.add(array[i]);
                sum2 += array[i];
            }
        }

        System.out.println("  ▶ 子陣列 1：" + part1 + "，總和：" + sum1);
        System.out.println("  ▶ 子陣列 2：" + part2 + "，總和：" + sum2);
    }
}
