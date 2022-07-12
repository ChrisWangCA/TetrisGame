/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;
import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class O extends Tetromino {
    public O() {
        //Initialization position
        cells[0] = new Cell(0,4, Tetris.O);
        cells[1] = new Cell(0,5, Tetris.O);
        cells[2] = new Cell(1,4, Tetris.O);
        cells[3] = new Cell(1,5, Tetris.O);

        // total two rotate states
        rotates = new Rotate[0];
    }
}
