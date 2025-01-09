package checkers.BoardGUI;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardField extends Circle implements BoardElement {
    
    private Cell field;

    public BoardField(BoardStage boardStage, Cell field) {
        this.field = field;
        this.setColor();
        this.setRadius(15);
    }

    //
    //
    //TODO SET X, SET Y !!!
    // 
    //

    @Override
    public boolean isField() {
            return true;
    }

    @Override
    public CellColor getColor() {
        return field.getColor();
    }

    public boolean isFree() {
        return field.getStatus().equals(CellStatus.FREE);
    }

    public boolean isHomeCell() {
        return field.isHomeCell();
    }

    public boolean isPlayable() {
        return field.isPlayable();
    }

    private void setColor() {
		CellColor color = this.field.getColor();
		
		if(color == CellColor.BLUE)
			this.setFill(Color.LIGHTBLUE);
		else if(color == CellColor.GREEN)
			this.setFill(Color.LIGHTGREEN);
		else if(color == CellColor.ORANGE)
			this.setFill(Color.rgb(253, 221, 148));
		else if(color == CellColor.PURPLE)
			this.setFill(Color.PLUM);
		else if(color == CellColor.RED)
			this.setFill(Color.rgb(234,60,83));
		else if(color == CellColor.YELLOW)
			this.setFill(Color.rgb(255, 253, 124));
		else if(color == CellColor.NONE)
			this.setFill(Color.LIGHTGRAY);
	}

    public Cell getField() {
        return field;
    }

}