// 題目39: Combination Sum
import java.util.*;

public class lt_39_combinationsum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> result) {
        // 如果總和超過目標就直接回傳
        if (remain < 0) return;

        // 如果剛好湊成 target，加到結果中
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 嘗試所有從 start 開始的候選數字（可以重複使用）
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]); // 選擇
            backtrack(candidates, remain - candidates[i], i, path, result); // 遞迴
            path.remove(path.size() - 1); // 回溯
        }
    }

    // ✅ 測試用 main 方法（可自行移除）
    public static void main(String[] args) {
        lt_39_combinationsum solver = new lt_39_combinationsum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> results = solver.combinationSum(candidates, target);
        System.out.println(results); // Output: [[2, 2, 3], [7]]
    }
}
