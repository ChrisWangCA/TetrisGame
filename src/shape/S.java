/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;
import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class S extends Tetromino {
    public S() {
        //Initialization position
        cells[0] = new Cell(0,4, Tetris.S);
        cells[1] = new Cell(0,5, Tetris.S);
        cells[2] = new Cell(1,3, Tetris.S);
        cells[3] = new Cell(1,4, Tetris.S);


        // total two rotate states
        rotates = new Rotate[2];
        //Initialize relative coordinates
        rotates[0] = new Rotate(0,0,0,1,1,-1,1,0);
        rotates[1] = new Rotate(0,0,1,0,-1,-1,0,-1);

    }
}
