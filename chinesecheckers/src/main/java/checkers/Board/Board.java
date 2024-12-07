package checkers.Board;

import java.util.Arrays;

public class Board {
    private final char[][] board;

    public Board() {
        board = new char[17][25]; // Przybliżona reprezentacja planszy
        initializeBoard();
    }

    private void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, '.'); // Puste pola oznaczone kropkami
        }
        // Dodaj konfigurację startową pionków (symbol 'A', 'B', itd. dla graczy)
        for (int i = 0; i < 10; i++) {
            board[0][i] = 'A'; // Przykład gracza A
            board[16][24 - i] = 'B'; // Przykład gracza B
        }
    }

    public void display() {
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        // Prosta walidacja ruchu
        return board[startX][startY] != '.' && board[endX][endY] == '.';
    }

    public void makeMove(int startX, int startY, int endX, int endY) {
        char piece = board[startX][startY];
        board[startX][startY] = '.';
        board[endX][endY] = piece;
    }
}
