package checkers.BoardGUI;

import checkers.Cell.CellColor;

/**
 * Represents an element on the board.
 */
public interface BoardElement {

    /**
     * Checks if the element is a field.
     *
     * @return true if the element is a field, false otherwise
     */
    boolean isField();

    /**
     * Gets the color of the element.
     *
     * @return the color of the element
     */
    CellColor getColor();
}