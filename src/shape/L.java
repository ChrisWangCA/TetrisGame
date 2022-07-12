/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;
import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class L extends Tetromino {
    public L() {
        //Initialization position
        cells[0] = new Cell(0,4, Tetris.L);
        cells[1] = new Cell(0,3, Tetris.L);
        cells[2] = new Cell(0,5, Tetris.L);
        cells[3] = new Cell(1,3, Tetris.L);

        // total four rotate states
        rotates = new Rotate[4];
        //Initialize relative coordinates
        rotates[0] = new Rotate(0,0,0,-1,0,1,1,-1);
        rotates[1] = new Rotate(0,0,-1,0,1,0,-1,-1);
        rotates[2] = new Rotate(0,0,0,1,0,-1,-1,1);
        rotates[3] = new Rotate(0,0,1,0,-1,0,1,1);

    }


}
