package cell;
import java.awt.image.BufferedImage;

/**
 * @author Chris Wang
 * Cell class
 * Attributes: rows, columns, images of each small square
 * Method: Shift one frame left, shift one frame right, shift one frame down
 */

public class Cell {
    private int row;
    private int column;
    private BufferedImage image;

    //Shift to left
    public void left(){
        column--;
    }

    //Shift to right
    public void right(){
        column++;
    }

    //Shift down
    public void drop(){
        row++;
    }


    public Cell(int row, int column, BufferedImage image) {
        this.row = row;
        this.column = column;
        this.image = image;
    }

    public Cell() {
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        if (column != cell.column) return false;
        return image.equals(cell.image);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
