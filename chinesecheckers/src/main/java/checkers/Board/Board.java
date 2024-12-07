package checkers.Board;

import java.util.ArrayList;
import java.util.List;

import checkers.Board.Cell.Cell;

public class Board {
    private List<Cell> cells; // Lista pól
    private int rows;         // Liczba wierszy
    private int cols;         // Liczba kolumn

    // Konstruktor z określeniem rozmiaru planszy
    public Board(int size) {
        cells = new ArrayList<>();
        initializeBoard(size); // Inicjalizacja planszy
    }

    private void initializeBoard(int size) {
        rows = size * 2 + 1;  // Liczba wierszy
        cols = size * 3 + 2;  // Liczba kolumn
        for (int i = 0; i < rows * cols; i++) {
            cells.add(new Cell());
        }

        // Zaznaczenie aktywnych pól w kształcie gwiazdy
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isInsideStar(i, j)) {
                    getCell(i, j).setOccupied(false); // Puste pole
                }
            }
        }
    }

    // Sprawdzamy, czy dane pole znajduje się w obrębie gwiazdy
    private boolean isInsideStar(int x, int y) {
        // Placeholder, do zaimplementowania
        return true;
    }

    // Zwrócenie komórki na podstawie indeksów
    private Cell getCell(int x, int y) {
        int index = x * cols + y; // Indeks komórki w tablicy 1D
        return cells.get(index);
    }

    // Wyświetlanie planszy z przesunięciem w kolumnach nieparzystych
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Dodanie przesunięcia o 1 spację przed kolumnami nieparzystymi
                if (i % 2 == 0) {
                    System.out.print(". "); // Zwykła komórka
                } else {
                    System.out.print(" ."); // Komórka przesunięta o spację
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board(5);  // Tworzymy planszę z 5 pionami dla każdego gracza
        board.printBoard();
    }
}
