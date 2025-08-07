public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int total = 0;
        int max = scores[0];
        int min = scores[0];
        int aboveAverageCount = 0;

        // 成績等第人數統計
        int countA = 0; // 90~100
        int countB = 0; // 80~89
        int countC = 0; // 70~79
        int countD = 0; // 60~69
        int countF = 0; // <60

        // 計算總分、最高、最低、等第分布
        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;

            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        double average = (double) total / scores.length;

        // 計算高於平均的人數
        for (int score : scores) {
            if (score > average) {
                aboveAverageCount++;
            }
        }

        // 輸出結果
        System.out.println("🎓 成績統計報表 🎓");
        System.out.println("---------------------");
        System.out.printf("所有成績：");
        for (int score : scores) {
            System.out.printf("%d ", score);
        }
        System.out.println();
        System.out.println("---------------------");
        System.out.printf("總分：%d\n", total);
        System.out.printf("平均：%.2f\n", average);
        System.out.printf("最高分：%d\n", max);
        System.out.printf("最低分：%d\n", min);
        System.out.printf("高於平均的人數：%d 人\n", aboveAverageCount);
        System.out.println("---------------------");
        System.out.println("📊 各等第人數：");
        System.out.printf("A (90~100)：%d 人\n", countA);
        System.out.printf("B (80~89) ：%d 人\n", countB);
        System.out.printf("C (70~79) ：%d 人\n", countC);
        System.out.printf("D (60~69) ：%d 人\n", countD);
        System.out.printf("F (<60)   ：%d 人\n", countF);
    }
}
