/**
 * @author Chris Wang
 * @version July 11, 2022
 */
package shape;

import cell.Cell;
import tetris.Tetris;
import tetromino.Tetromino;

public class I extends Tetromino {
    public I() {
        //Initialization position
        cells[0] = new Cell(0,4,Tetris.I);
        cells[1] = new Cell(0,3,Tetris.I);
        cells[2] = new Cell(0,5,Tetris.I);
        cells[3] = new Cell(0,6,Tetris.I);

        // total two rotate states
        rotates = new Rotate[2];
        //Initialize relative coordinates
        rotates[0] = new Rotate(0,0,0,-1,0,1,0,2);
        rotates[1] = new Rotate(0,0,-1,0,1,0,2,0);


    }


}
