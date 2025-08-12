import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length == 0 || K == 0) return 0;

        // 用 max heap 存所有可獲利交易
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int buy = -1; // 買入價
        for (int i = 0; i < prices.length - 1; i++) {
            // 找買點
            if (buy == -1 && prices[i] < prices[i + 1]) {
                buy = prices[i];
            }

            // 找賣點
            if (buy != -1 && prices[i] > prices[i + 1]) {
                maxHeap.offer(prices[i] - buy);
                buy = -1;
            }
        }

        // 處理最後一天可能的賣點
        if (buy != -1 && prices[prices.length - 1] > buy) {
            maxHeap.offer(prices[prices.length - 1] - buy);
        }

        // 取最大的 K 筆交易
        int profit = 0;
        for (int i = 0; i < K && !maxHeap.isEmpty(); i++) {
            profit += maxHeap.poll();
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}, 2)); // 2
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3}, 2)); // 7
        System.out.println(maxProfit(new int[]{1,2,3,4,5}, 2)); // 4
    }
}