import java.util.*;

public class MeetingRoomScheduler {

    // 第一部分：計算最少需要多少個會議室
    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        // 按開始時間排序
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Min Heap (存會議結束時間)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            // 如果最早結束的會議已經結束，可以釋放一個會議室
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll();
            }
            // 新會議加入
            minHeap.offer(meeting[1]);
        }

        return minHeap.size(); // heap 大小即為需要的會議室數量
    }

    // 第二部分：在有限會議室數量下，安排會議使總會議時間最大
    public static int maxMeetingTimeWithNRooms(int[][] meetings, int N) {
        // 按結束時間排序 (區間排程的貪心法)
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        // 如果只有 1 間會議室，退化為經典的最大不重疊區間問題
        if (N == 1) {
            int totalTime = 0;
            int lastEnd = -1;
            for (int[] meeting : meetings) {
                if (meeting[0] >= lastEnd) {
                    totalTime += meeting[1] - meeting[0];
                    lastEnd = meeting[1];
                }
            }
            return totalTime;
        }

        // 如果會議室數 > 1，可以同時安排多場
        // 用一個 minHeap 追蹤每個會議室的結束時間
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        int totalTime = 0;

        for (int[] meeting : meetings) {
            // 清除已經空出的會議室
            while (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= meeting[0]) {
                roomEndTimes.poll();
            }

            if (roomEndTimes.size() < N) {
                // 還有空會議室
                roomEndTimes.offer(meeting[1]);
                totalTime += meeting[1] - meeting[0];
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,30},{5,10},{15,20}};
        System.out.println("需要會議室: " + minMeetingRooms(m1)); // 2

        int[][] m2 = {{9,10},{4,9},{4,17}};
        System.out.println("需要會議室: " + minMeetingRooms(m2)); // 2

        int[][] m3 = {{1,5},{8,9},{8,9}};
        System.out.println("需要會議室: " + minMeetingRooms(m3)); // 2

        int[][] m4 = {{1,4},{2,3},{4,6}};
        System.out.println("1 間會議室最大總時間: " + maxMeetingTimeWithNRooms(m4, 1)); // 5
    }
}
