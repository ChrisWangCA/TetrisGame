package tetromino;

import cell.Cell;
import shape.*;

/**
 * @author Chris Wang
 * Tetromina class
 * Attributes: cell array to create four squares
 * Method: Shift one frame left, shift one frame right, shift one frame down,Randomly generate four grids
 */

public class Tetromino {
    public Cell[] cells = new Cell[4];
    //Rotating states
    protected Rotate[] rotates;
    //Rotating number
    protected int count = 10000;


    /*
    Inner class for rotating states
     */
    public class Rotate{
        int row0,col0,row1,col1,row2,col2,row3,col3;

        public Rotate(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }

        public Rotate() {
        }

        @Override
        public String toString() {
            return "rotate{" +
                    "row0=" + row0 +
                    ", col0=" + col0 +
                    ", row1=" + row1 +
                    ", col1=" + col1 +
                    ", row2=" + row2 +
                    ", col2=" + col2 +
                    ", row3=" + row3 +
                    ", col3=" + col3 +
                    '}';
        }

        public int getRow0() {
            return row0;
        }

        public void setRow0(int row0) {
            this.row0 = row0;
        }

        public int getCol0() {
            return col0;
        }

        public void setCol0(int col0) {
            this.col0 = col0;
        }

        public int getRow1() {
            return row1;
        }

        public void setRow1(int row1) {
            this.row1 = row1;
        }

        public int getCol1() {
            return col1;
        }

        public void setCol1(int col1) {
            this.col1 = col1;
        }

        public int getRow2() {
            return row2;
        }

        public void setRow2(int row2) {
            this.row2 = row2;
        }

        public int getCol2() {
            return col2;
        }

        public void setCol2(int col2) {
            this.col2 = col2;
        }

        public int getRow3() {
            return row3;
        }

        public void setRow3(int row3) {
            this.row3 = row3;
        }

        public int getCol3() {
            return col3;
        }

        public void setCol3(int col3) {
            this.col3 = col3;
        }
    }

    //Clockwise rotation
    public void rotateRight(){
        // If is O means return;
        if (rotates.length==0){
            return;
        }
        //Rotate count+1
        count++;
        Rotate r = rotates[count % rotates.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int column = cell.getColumn();
        cells[1].setRow(row+r.row1);
        cells[1].setColumn(column+r.col1);
        cells[2].setRow(row+r.row2);
        cells[2].setColumn(column+r.col2);
        cells[3].setRow(row+r.row3);
        cells[3].setColumn(column+r.col3);
    }

    //Counterclockwise rotation
    public void rotateLeft(){
        //Rotate count -1
        count--;
        Rotate r = rotates[count % rotates.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int column = cell.getColumn();
        cells[1].setRow(row+r.row1);
        cells[1].setColumn(column+r.col1);
        cells[2].setRow(row+r.row2);
        cells[2].setColumn(column+r.col2);
        cells[3].setRow(row+r.row3);
        cells[3].setColumn(column+r.col3);
    }


    //Shift to left
    public void moveLeft(){
        for (Cell cell:cells){
            cell.left();
        }
    }

    //Shift to right
    public void moveRight(){
        for (Cell cell:cells){
            cell.right();
        }
    }

    //Shift down
    public void dropDown(){
        for (Cell cell:cells){
            cell.drop();
        }
    }

    //Randomly generate four grids
    public static Tetromino random(){
        int num = (int)(Math.random()*7);
        Tetromino tetromino = null;
        switch (num){
            case 0:
                tetromino = new I();
                break;
            case 1:
                tetromino = new J();
                break;
            case 2:
                tetromino = new L();
                break;
            case 3:
                tetromino = new O();
                break;
            case 4:
                tetromino = new S();
                break;
            case 5:
                tetromino = new T();
                break;
            case 6:
                tetromino = new Z();
                break;
        }
        return tetromino;
    }
}
