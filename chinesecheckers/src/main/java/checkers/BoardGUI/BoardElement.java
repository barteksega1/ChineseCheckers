package checkers.BoardGUI;

import checkers.Cell.CellColor;

public interface BoardElement {
    boolean isField();
    CellColor getColor();
}