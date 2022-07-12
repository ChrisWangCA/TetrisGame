/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;
import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class Z extends Tetromino {
    public Z() {
        //Initialization position
        cells[0] = new Cell(1,4, Tetris.Z);
        cells[1] = new Cell(0,3, Tetris.Z);
        cells[2] = new Cell(0,4, Tetris.Z);
        cells[3] = new Cell(1,5, Tetris.Z);

        // total two rotate states
        rotates = new Rotate[2];
        //Initialize relative coordinates
        rotates[0] = new Rotate(0,0,-1,-1,-1,0,0,1);
        rotates[1] = new Rotate(0,0,-1,1,0,1,1,0);
    }
}
