import java.util.*;

public class AdvancedArrayRecursion {

    public static void main(String[] args) {
        int[] data = {9, 2, 7, 4, 5, 1, 8, 3, 6};
        int[] sortedA = {1, 3, 5, 7};
        int[] sortedB = {2, 4, 6, 8};

        System.out.println("🎯 原始陣列：" + Arrays.toString(data));


        int[] quickSorted = data.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("✅ 快速排序後：" + Arrays.toString(quickSorted));


        int[] merged = mergeRecursive(sortedA, sortedB, 0, 0, new ArrayList<>());
        System.out.println("🔗 合併排序陣列：" + Arrays.toString(merged));

  
        int k = 5;
        int kthSmallest = quickSelect(data.clone(), 0, data.length - 1, k - 1);
        System.out.printf("🔍 第 %d 小的元素：%d\n", k, kthSmallest);

 
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean hasSubset = subsetSum(nums, 0, target);
        System.out.printf("🎯 是否存在子集合總和為 %d？%s\n", target, hasSubset ? "是 ❤️" : "否 ❌");
    }

    
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = arr[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= j && arr[i] <= pivot) i++;
            while (i <= j && arr[j] >= pivot) j--;
            if (i < j) swap(arr, i, j);
        }

        swap(arr, left, j);
        quickSort(arr, left, j - 1);
        quickSort(arr, j + 1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static int[] mergeRecursive(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length && j == b.length)
            return result.stream().mapToInt(Integer::intValue).toArray();

        if (i == a.length)
            result.add(b[j++]);
        else if (j == b.length)
            result.add(a[i++]);
        else if (a[i] < b[j])
            result.add(a[i++]);
        else
            result.add(b[j++]);

        return mergeRecursive(a, b, i, j, result);
    }

    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = partition(arr, left, right);
        if (k == pivot) return arr[k];
        else if (k < pivot) return quickSelect(arr, left, pivot - 1, k);
        else return quickSelect(arr, pivot + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }


    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;


        return subsetSum(arr, index + 1, target - arr[index]) ||
               subsetSum(arr, index + 1, target);
    }
}
