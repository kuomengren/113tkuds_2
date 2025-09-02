import java.util.HashMap;

public class lt_01_twosum {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            // 若你的 JDK < 8，請把 new HashMap<>() 換成 new HashMap<Integer, Integer>()
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] { map.get(complement), i };
                }
                map.put(nums[i], i);
            }
            return new int[] {}; // 題目保證有解，理論上不會到這
        }
    }

    // 本機測試
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans1 = s.twoSum(new int[]{2,7,11,15}, 9);
        int[] ans2 = s.twoSum(new int[]{3,2,4}, 6);
        int[] ans3 = s.twoSum(new int[]{3,3}, 6);

        System.out.println(ans1[0] + "," + ans1[1]); // 0,1
        System.out.println(ans2[0] + "," + ans2[1]); // 1,2
        System.out.println(ans3[0] + "," + ans3[1]); // 0,1
    }
}
