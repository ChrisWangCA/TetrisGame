/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;
import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class T extends Tetromino {
    public T() {
        //Initialization position
        cells[0] = new Cell(0,4, Tetris.T);
        cells[1] = new Cell(0,3, Tetris.T);
        cells[2] = new Cell(0,5, Tetris.T);
        cells[3] = new Cell(1,4, Tetris.T);


        // total four rotate states
        rotates = new Rotate[4];
        //Initialize relative coordinates
        rotates[0] = new Rotate(0,0,0,-1,0,1,1,0);
        rotates[1] = new Rotate(0,0,-1,0,1,0,0,-1);
        rotates[2] = new Rotate(0,0,0,1,0,-1,-1,0);
        rotates[3] = new Rotate(0,0,1,0,-1,0,0,1);
    }
}
