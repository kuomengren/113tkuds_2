import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("🎮 井字遊戲開始！");
        printBoard();

        while (!gameEnded) {
            System.out.printf("輪到玩家 %c，請輸入 row 和 col（0~2）: ", currentPlayer);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!isValidMove(row, col)) {
                System.out.println("⛔ 此位置無效，請重新輸入！");
                continue;
            }

            placeMark(row, col, currentPlayer);
            printBoard();

            if (checkWinner(currentPlayer)) {
                System.out.printf("🎉 玩家 %c 獲勝！\n", currentPlayer);
                gameEnded = true;
            } else if (isBoardFull()) {
                System.out.println("🤝 平手！棋盤已滿。");
                gameEnded = true;
            } else {
                switchPlayer();
            }
        }

        scanner.close();
    }

    // 初始化棋盤
    public static void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // 顯示棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 放置棋子
    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == ' ';
    }

    public static void placeMark(int row, int col, char player) {
        board[row][col] = player;
    }

    // 切換玩家
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 檢查勝利
    public static boolean checkWinner(char player) {
        // 行、列檢查
        for (int i = 0; i < 3; i++) {
            if (
                (board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            ) return true;
        }

        // 對角線
        if (
            (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) return true;

        return false;
    }

    // 是否平手
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}
