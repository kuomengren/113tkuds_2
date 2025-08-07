public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 4},
            {2, 5},
            {3, 6}
        };

        System.out.println("🧮 原始矩陣 A:");
        printMatrix(matrixA);

        System.out.println("\n🧮 原始矩陣 B:");
        printMatrix(matrixB);

        System.out.println("\n➕ A + B（矩陣加法）:");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("\n✖️ A × C（矩陣乘法）:");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        System.out.println("\n🔄 A 的轉置矩陣:");
        printMatrix(transposeMatrix(matrixA));

        System.out.println("\n🔍 A 的最大與最小值:");
        int[] minMax = findMinMax(matrixA);
        System.out.printf("最大值：%d，最小值：%d\n", minMax[1], minMax[0]);
    }

    // ✅ 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = a[i][j] + b[i][j];

        return result;
    }

    // ✅ 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += a[i][k] * b[k][j];

        return result;
    }

    // ✅ 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                transposed[j][i] = matrix[i][j];

        return transposed;
    }

    // ✅ 找最大與最小值
    public static int[] findMinMax(int[][] matrix) {
        int min = matrix[0][0];
        int max = matrix[0][0];

        for (int[] row : matrix)
            for (int value : row) {
                if (value < min) min = value;
                if (value > max) max = value;
            }

        return new int[]{min, max};
    }

    // ✅ 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row)
                System.out.printf("%4d", value);
            System.out.println();
        }
    }
}
