import java.util.*;

public class lt_40_combinationsumii {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> tempList, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > remain) break;
            tempList.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, tempList, res);
            tempList.remove(tempList.size() - 1);
        }
    }

    // ✅ 加入這個 main 方法就能執行並測試
    public static void main(String[] args) {
        lt_40_combinationsumii solver = new lt_40_combinationsumii();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> result = solver.combinationSum2(candidates, target);

        // 輸出結果
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }
}
