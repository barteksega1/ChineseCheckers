package checkers.BoardGUI;

import checkers.Cell.CellColor;

public interface BoardElement {
	boolean isField();
	//boolean isPiece(); probably useless?
	CellColor getColor();
}