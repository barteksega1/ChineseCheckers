package checkers.BoardGUI;

import checkers.Cell.Cell;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Cell.HomeCell;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a field on the board.
 */
public class BoardField extends StackPane implements BoardElement {
    private final Cell field;

    /**
     * Constructs a BoardField with the specified board stage and cell.
     *
     * @param boardStage the board stage
     * @param field the cell representing the field
     */
    public BoardField(BoardStage boardStage, Cell field) {
        this.field = field;
        Circle circle = new Circle(15); // Set the radius of the circle
        circle.setStrokeWidth(3); // Set stroke width to make it thicker
        setColor(circle);

        this.getChildren().add(circle);
    }

    /**
     * Constructs a BoardField with the specified board stage, cell, row, and column.
     *
     * @param boardStage the board stage
     * @param field the cell representing the field
     * @param row the row of the field
     * @param column the column of the field
     */
    public BoardField(BoardStage boardStage, Cell field, int row, int column) {
        this.field = field;
        Circle circle = new Circle(12); // Set the radius of the circle
        circle.setStrokeWidth(3); // Set stroke width to make it thicker
        setColor(circle);

        Label coordinates = new Label(row + "," + column);
        coordinates.setStyle("-fx-font-size: 8; -fx-text-fill: black;");

        this.getChildren().addAll(circle, coordinates);
    }

    /**
     * Constructs a BoardField with the specified board stage, cell, row, column, and game size.
     *
     * @param boardStage the board stage
     * @param field the cell representing the field
     * @param row the row of the field
     * @param column the column of the field
     * @param gameSize the size of the game
     */
    public BoardField(BoardStage boardStage, Cell field, int row, int column, int gameSize) {
        this.field = field;
        double radius = 30 / (gameSize / 3); // Adjust the radius based on game size
        Circle circle = new Circle(radius);
        circle.setStrokeWidth(3); // Set stroke width to make it thicker
        setColor(circle);

        Label coordinates = new Label(row + "," + column);
        coordinates.setStyle("-fx-font-size: " + (8 + gameSize / 2) + "; -fx-text-fill: black;"); // Adjust font size based on game size

        this.getChildren().addAll(circle, coordinates);
    }

    /**
     * Constructs a BoardField with the specified board stage, cell, row, column, and cell size.
     *
     * @param boardStage the board stage
     * @param field the cell representing the field
     * @param row the row of the field
     * @param column the column of the field
     * @param cellSize the size of the cell
     */
    public BoardField(BoardStage boardStage, Cell field, int row, int column, double cellSize) {
        this.field = field;
        double radius = cellSize / 2; // Adjust the radius based on cell size
        Circle circle = new Circle(radius);
        circle.setStrokeWidth(3); // Set stroke width to make it thicker
        setColor(circle);

        Label coordinates = new Label(row + "," + column);
        coordinates.setStyle("-fx-font-size: " + (cellSize / 4) + "; -fx-text-fill: black;"); // Adjust font size based on cell size

        this.getChildren().addAll(circle, coordinates);
    }

    @Override
    public boolean isField() {
        return true;
    }

    @Override
    public CellColor getColor() {
        return field.getColor();
    }

    /**
     * Checks if the field is free.
     *
     * @return true if the field is free, false otherwise
     */
    public boolean isFree() {
        return field.getStatus().equals(CellStatus.FREE);
    }

    /**
     * Checks if the field is a home cell.
     *
     * @return true if the field is a home cell, false otherwise
     */
    public boolean isHomeCell() {
        return field.isHomeCell();
    }

    /**
     * Checks if the field is playable.
     *
     * @return true if the field is playable, false otherwise
     */
    public boolean isPlayable() {
        return field.isPlayable();
    }

    /**
     * Sets the color of the circle based on the field's status and color.
     *
     * @param circle the circle to set the color for
     */
    private void setColor(Circle circle) {
        if (field.isHomeCell()) {
            CellColor homeColor = ((HomeCell) field).getHomeColor();
            switch (homeColor) {
                case BLUE:
                    circle.setStroke(Color.DARKBLUE);
                    break;
                case GREEN:
                    circle.setStroke(Color.DARKGREEN);
                    break;
                case ORANGE:
                    circle.setStroke(Color.rgb(204, 153, 102)); // Darker orange
                    break;
                case PURPLE:
                    circle.setStroke(Color.DARKMAGENTA);
                    break;
                case RED:
                    circle.setStroke(Color.DARKRED);
                    break;
                case YELLOW:
                    circle.setStroke(Color.GOLDENROD);
                    break;
                default:
                    circle.setStroke(Color.DARKGRAY);
                    break;
            }
        } else if (field.getStatus() != CellStatus.ILLEGAL) {
            circle.setStroke(Color.BLACK); // Overlay with black for normal cells
        }

        if (field.getStatus() == CellStatus.OCCUPIED) {
            CellColor pieceColor = field.getColor();
            switch (pieceColor) {
                case BLUE:
                    circle.setFill(Color.LIGHTBLUE);
                    break;
                case GREEN:
                    circle.setFill(Color.LIGHTGREEN);
                    break;
                case ORANGE:
                    circle.setFill(Color.rgb(253, 221, 148));
                    break;
                case PURPLE:
                    circle.setFill(Color.PLUM);
                    break;
                case RED:
                    circle.setFill(Color.rgb(234, 60, 83));
                    break;
                case YELLOW:
                    circle.setFill(Color.rgb(255, 253, 124));
                    break;
                default:
                    circle.setFill(Color.LIGHTGRAY);
                    break;
            }
        } else {
            circle.setFill(Color.TRANSPARENT); // Transparent fill for non-occupied cells
        }
    }

    /**
     * Gets the cell representing the field.
     *
     * @return the cell representing the field
     */
    public Cell getField() {
        return field;
    }
}