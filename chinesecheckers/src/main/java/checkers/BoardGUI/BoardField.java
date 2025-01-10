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
        this.setRadius(15); // Ustawienie odpowiedniego rozmiaru pola
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
        if (field.isHomeCell()) {
            CellColor color = this.field.getColor();
            switch (color) {
                case BLUE:
                    this.setFill(Color.LIGHTBLUE);
                    break;
                case GREEN:
                    this.setFill(Color.LIGHTGREEN);
                    break;
                case ORANGE:
                    this.setFill(Color.rgb(253, 221, 148));
                    break;
                case PURPLE:
                    this.setFill(Color.PLUM);
                    break;
                case RED:
                    this.setFill(Color.rgb(234,60,83));
                    break;
                case YELLOW:
                    this.setFill(Color.rgb(255, 253, 124));
                    break;
                default:
                    this.setFill(Color.LIGHTGRAY);
                    break;
            }
        } else if (field.getStatus()!=CellStatus.ILLEGAL) {
            this.setFill(Color.LIGHTGRAY); // Domyślny kolor dla pól bez przypisanego koloru
        }
    }

    public Cell getField() {
        return field;
    }

}