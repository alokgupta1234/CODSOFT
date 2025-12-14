import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private static final char PLAYER = 'O';
    private static final char COMPUTER = 'X';
    private static final char EMPTY = ' ';

    private final char[] board;
    private final Scanner input;

    public TicTacToe() {
        board = new char[9];
        input = new Scanner(System.in);
        for (int i = 0; i < board.length; i++) {
            board[i] = EMPTY;
        }
    }

    private void showBoard() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
            if (i < 6) {
                System.out.println("--+---+--");
            }
        }
        System.out.println();
    }

    private List<Integer> getFreeCells() {
        List<Integer> cells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY) {
                cells.add(i);
            }
        }
        return cells;
    }

    private boolean hasWon(char mark) {
        int[][] winSets = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] set : winSets) {
            if (board[set[0]] == mark &&
                board[set[1]] == mark &&
                board[set[2]] == mark) {
                return true;
            }
        }
        return false;
    }

    private boolean gameTied() {
        for (char cell : board) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private int evaluate(boolean maximizing, int alpha, int beta) {
        if (hasWon(COMPUTER)) return 1;
        if (hasWon(PLAYER)) return -1;
        if (gameTied()) return 0;

        if (maximizing) {
            int best = Integer.MIN_VALUE;
            for (int cell : getFreeCells()) {
                board[cell] = COMPUTER;
                best = Math.max(best, evaluate(false, alpha, beta));
                board[cell] = EMPTY;
                alpha = Math.max(alpha, best);
                if (beta <= alpha) break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int cell : getFreeCells()) {
                board[cell] = PLAYER;
                best = Math.min(best, evaluate(true, alpha, beta));
                board[cell] = EMPTY;
                beta = Math.min(beta, best);
                if (beta <= alpha) break;
            }
            return best;
        }
    }

    private int chooseBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int cell : getFreeCells()) {
            board[cell] = COMPUTER;
            int score = evaluate(false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            board[cell] = EMPTY;

            if (score > bestScore) {
                bestScore = score;
                move = cell;
            }
        }
        return move;
    }

    private void playerMove() {
        while (true) {
            System.out.print("Choose a position (1-9): ");
            try {
                int pos = input.nextInt() - 1;
                if (pos >= 0 && pos < 9 && board[pos] == EMPTY) {
                    board[pos] = PLAYER;
                    return;
                }
                System.out.println("Invalid position.");
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Enter a valid number.");
            }
        }
    }

    private void computerMove() {
        int move = chooseBestMove();
        if (move != -1) {
            board[move] = COMPUTER;
        }
    }

    public void startGame() {
        System.out.println("Tic Tac Toe");
        System.out.println("Player: O | Computer: X");
        showBoard();

        while (true) {
            playerMove();
            showBoard();

            if (hasWon(PLAYER)) {
                System.out.println("You win!");
                break;
            }
            if (gameTied()) {
                System.out.println("Game tied.");
                break;
            }

            computerMove();
            System.out.println("Computer move:");
            showBoard();

            if (hasWon(COMPUTER)) {
                System.out.println("Computer wins!");
                break;
            }
            if (gameTied()) {
                System.out.println("Game tied.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe().startGame();
    }
}
