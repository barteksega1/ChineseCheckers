package checkers.BoardGUI;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardField extends Circle implements BoardElement {
    
    private final Cell field;

    public BoardField(BoardStage boardStage, Cell field) {
        this.field = field;
        this.setColor();
        this.setRadius(15); // Ustawienie odpowiedniego rozmiaru pola
        this.setStrokeWidth(3); // Set stroke width to make it thicker
    }

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
            CellColor homeColor = ((HomeCell) field).getHomeColor();
            switch (homeColor) {
                case BLUE:
                    this.setStroke(Color.DARKBLUE);
                    break;
                case GREEN:
                    this.setStroke(Color.DARKGREEN);
                    break;
                case ORANGE:
                    this.setStroke(Color.rgb(204, 153, 102)); // Darker orange
                    break;
                case PURPLE:
                    this.setStroke(Color.DARKMAGENTA);
                    break;
                case RED:
                    this.setStroke(Color.DARKRED);
                    break;
                case YELLOW:
                    this.setStroke(Color.GOLDENROD);
                    break;
                default:
                    this.setStroke(Color.DARKGRAY);
                    break;
            }
        } else if (field.getStatus() != CellStatus.ILLEGAL) {
            this.setStroke(Color.BLACK); // Overlay with black for normal cells
        }

        if (field.getStatus() == CellStatus.OCCUPIED) {
            CellColor pieceColor = field.getColor();
            switch (pieceColor) {
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
                    this.setFill(Color.rgb(234, 60, 83));
                    break;
                case YELLOW:
                    this.setFill(Color.rgb(255, 253, 124));
                    break;
                default:
                    this.setFill(Color.LIGHTGRAY);
                    break;
            }
        } else {
            this.setFill(Color.TRANSPARENT); // Transparent fill for non-occupied cells
        }
    }

    public Cell getField() {
        return field;
    }

}