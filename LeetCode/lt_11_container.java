// 題目：11. Container With Most Water
// 給定每根直線高度，找兩根線與 x 軸夾出最大容器水量。
// 解法：雙指針從左右往中間移動；「短板決定水量」，移動較短的一側才有機會增大高度。
// 時間複雜度 O(n)，空間 O(1)

public class lt_11_container {
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            ans = Math.max(ans, h * (r - l));
            // 移動短板
            if (height[l] < height[r]) l++;
            else r--;
        }
        return ans;
    }

    // 測試
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(maxArea(new int[]{1,1})); // 1
    }
}
/*
解題思路：
1. 容器面積 = min(左高,右高) * 寬度。若移動較高的一側，寬度變小但高度上限不會提升，因此無法變更上限。
2. 只能移動較短的一側，嘗試找更高的短板。
3. 線性掃一遍即可。
*/
