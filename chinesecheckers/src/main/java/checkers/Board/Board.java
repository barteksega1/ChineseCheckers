package checkers.Board;

import java.util.ArrayList;
import java.util.List;

import checkers.Board.Cell.Cell;

public class Board {
    private List<Cell> cells; // Lista pól
    private int rows;         // Liczba wierszy
    private int cols;         // Liczba kolumn

    // Konstruktor z określeniem rozmiaru planszy
    public Board(int triangle_size) {
        int game_size = 2 * triangle_size; // dla przyjecia ze puste pola to nieaktywne pola
        cells = new ArrayList<>();
        initializeBoard(game_size); // Inicjalizacja planszy
    }

    private void initializeBoard(int game_size) {
        rows = game_size * 2 + 1;  // Liczba wierszy
        cols = game_size * 3 + 2;  // Liczba kolumn
        for (int i = 0; i < rows * cols; i++) {
            cells.add(new Cell());
        }

        // Zaznaczenie aktywnych pól w kształcie gwiazdy
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isInsideStar(i, j, cols, game_size)) {
                    getCell(i, j).setOccupied(false); // Puste pole
                }
                else {
                    getCell(i, j).setOccupied(true); // Pole zajęte
                    getCell(i, j).setPlayer("X"); // Ustawienie kropki w komórkach aktywnych
                }
            }
        }

        // Ustawienie komórek nieaktywnych (X)
        setInactiveCells();
    }

    // Sprawdzamy, czy dane pole znajduje się w obrębie gwiazdy
    private boolean isInsideStar(int i, int j, int cols, int game_size) {
        // placeholder
        return true;
    }

    // Ustawienie komórek nieaktywnych (X)
    private void setInactiveCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) { 
                    getCell(i, j).setOccupied(true); // Oznaczamy komórkę jako nieaktywną 
                    getCell(i, j).setPlayer("X"); // Ustawiamy "X" w komórkach nieaktywnych
                }
            }
        }
    }

    // Zwrócenie komórki na podstawie indeksów
    private Cell getCell(int x, int y) {
        int index = x * cols + y; // Indeks komórki w tablicy 1D
        return cells.get(index);
    }

    // Wyświetlanie planszy
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = getCell(i, j);
                // Zabezpieczamy przed null-em
                String player = (cell.getPlayer() != null) ? cell.getPlayer() : "";
                if (player.equals("X")) {
                    System.out.print("  "); // Pokaż "X" w komórkach nieaktywnych
                } else {
                    System.out.print("O "); // Pokaż "." w komórkach aktywnych
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board(4);  // Tworzymy planszę z 5 pionami dla każdego gracza
        board.printBoard();
    }
}
