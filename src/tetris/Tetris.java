package tetris;

import cell.Cell;
import tetromino.Tetromino;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Chris Wang
 * Tetris class
 * Game body code and logic code
 */

public class Tetris extends JPanel {
    //Falling cubes
    private Tetromino currentCube = Tetromino.random();
    //Cube that will fall
    private Tetromino nextCube = Tetromino.random();
    //Game area
    private Cell[][] wall = new Cell[18][9];
    //Variable Pixels
    private static final int PIXEL = 43;

    // Score pool
    int[] scorePool = {0, 100, 300, 600, 1000};
    // Game score
    private int totalScore = 0;
    //Number of eliminated rows
    private int totalLine = 0;

    // Variable for three status: progress, pause, end
    public static final int PROGRESS = 0;
    public static final int PAUSE = 1;
    public static final int END = 2;
    // Variable for current game status
    private int currentStatus;

    // Array for displaying the status
    String[] showStatus = {"P[pause]", "C[continue]", "S[restart]"};


    //Load square image
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage T;
    public static BufferedImage Z;

    // background image
    public static BufferedImage background;

    //Load images path
    static {
        try {
            I = ImageIO.read(new File("src/images/I.png"));
            J = ImageIO.read(new File("src/images/J.png"));
            L = ImageIO.read(new File("src/images/L.png"));
            O = ImageIO.read(new File("src/images/O.png"));
            S = ImageIO.read(new File("src/images/S.png"));
            T = ImageIO.read(new File("src/images/T.png"));
            Z = ImageIO.read(new File("src/images/Z.png"));
            background = ImageIO.read(new File("src/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        //Move the coordinate axes
        g.translate(22, 15);
        //The main area of the game
        paintWall(g);
        //Drawing the falling cubes
        paintCurrentCube(g);
        //Drawing the next falling cubes
        paintNext(g);
        //Drawing game score
        paintScore(g);
        //Drawing the game status
        paintStatus(g);


    }

    public void start() {
        currentStatus = PROGRESS;
        KeyListener kl = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    // If press down key move down
                    case KeyEvent.VK_DOWN:
                        dropAction();
                        break;
                    // If press left key shift to left
                    case KeyEvent.VK_LEFT:
                        moveLeftAction();
                        break;
                    // If press right key shift to right
                    case KeyEvent.VK_RIGHT:
                        moveRightAction();
                        break;
                    // If press up key transfer shapes
                    case KeyEvent.VK_UP:
                        rotateRightAction();
                        break;
                    // If press space key invoking instant drop
                    case KeyEvent.VK_SPACE:
                        instantDrop();
                        break;
                    // If press p pause game
                    case KeyEvent.VK_P:
                        //Determine the game's states
                        if (currentStatus == PROGRESS) {
                            currentStatus = PAUSE;
                        }
                        break;
                    // If press c continue the game
                    case KeyEvent.VK_C:
                        //Determine the game's states
                        if (currentStatus == PAUSE) {
                            currentStatus = PROGRESS;
                        }
                        break;
                    // If press s restart the game
                    case KeyEvent.VK_S:
                        // Restart the game, clear all score and lines
                        currentStatus = PROGRESS;
                        wall = new Cell[18][9];
                        currentCube = Tetromino.random();
                        nextCube = Tetromino.random();
                        totalScore = 0;
                        totalLine = 0;
                        break;
                }
            }
        };

        //Set window to focus
        this.addKeyListener(kl);
        this.requestFocus();

        while (true){
            // Determine the game states is progress,automatically drop one frame every 0.5 seconds
            if (currentStatus == PROGRESS) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Determine if it can fall down
                if (canDrop()) {
                    currentCube.dropDown();
                } else {
                    //Embedded into the wall
                    toWall();
                    //Eliminate lines
                    eliLines();
                    //Determine if the game is over
                    if (isGameOver()) {
                        currentStatus = END;
                    } else {
                        currentCube = nextCube;
                        nextCube = Tetromino.random();
                    }
                }
            }
            //Repaint surface
            repaint();
        }
    }

    //Press key drop
    public void dropAction() {
        if (canDrop()) {
            currentCube.dropDown();
        } else {
            //Embed the cube into the wall
            toWall();
            //Determine if the number of rows can be eliminated
            eliLines();
            //Determine if the game is over
            if (isGameOver()) {
                currentStatus = END;
            } else {
                //When the game doesn't end, continue to generate new cube
                currentCube = nextCube;
                nextCube = Tetromino.random();
            }
        }
    }

    //Instant drop
    public void instantDrop() {
        while (true) {
            if (canDrop()) {
                currentCube.dropDown();
            } else {
                break;
            }
        }
        toWall();
        eliLines();
        if (isGameOver()) {
            currentStatus = END;
        } else {
            currentCube = nextCube;
            nextCube = Tetromino.random();
        }
    }

    //clockwise rotation
    public void rotateRightAction() {
        currentCube.rotateRight();
        if (outOfBounds() || coincide()) {
            currentCube.rotateLeft();
        }
    }

    //Embed the cube into the wall
    private void toWall() {
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int column = cell.getColumn();
            wall[row][column] = cell;
        }
    }

    //Drawing the game status
    private void paintStatus(Graphics g) {
        if (currentStatus == PROGRESS) {
            g.drawString(showStatus[0], 500, 660);
        } else if (currentStatus == PAUSE) {
            g.drawString(showStatus[1], 500, 660);
        } else if (currentStatus == END) {
            g.drawString(showStatus[2], 500, 660);
            g.setColor(Color.red);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
            g.drawString("GAMEOVER!", 30, 400);
        }
    }

    //Drawing game score
    private void paintScore(Graphics g) {
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        g.drawString("SCORE: " + totalScore, 500, 248);
        g.drawString("LINES: " + totalLine, 500, 430);
    }

    //Drawing the next falling cubes
    private void paintNext(Graphics g) {
        Cell[] cells = nextCube.cells;
        for (Cell cell : cells) {
            int x = cell.getColumn() * PIXEL + 370;
            int y = cell.getRow() * PIXEL + 25;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    //Drawing the falling cubes
    private void paintCurrentCube(Graphics g) {
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int x = cell.getColumn() * PIXEL;
            int y = cell.getRow() * PIXEL;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintWall(Graphics g) {
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                int x = j * PIXEL;
                int y = i * PIXEL;
                Cell cell = wall[i][j];
                /*
                Determine if there is a cube in the current cell,
                if not then draw a rectangle,
                if so then add it to the wall
                 */
                if (cell == null) {
                    g.drawRect(x, y, PIXEL, PIXEL);
                } else {
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    //Determine if the cube is outside the range of rows
    public boolean outOfBounds() {
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int column = cell.getColumn();
            int row = cell.getRow();
            if (row < 0 || row > wall.length - 1 || column < 0 || column > wall[0].length - 1) {
                return true;
            }
        }
        return false;
    }

    //Determine if the cubes overlap
    public boolean coincide() {
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int column = cell.getColumn();
            if (wall[row][column] != null) {
                return true;
            }
        }
        return false;
    }

    //Press the key once to move left once
    public void moveLeftAction() {
        currentCube.moveLeft();
        if (outOfBounds() || coincide()) {
            currentCube.moveRight();
        }
    }

    //Press the key once to move right once
    public void moveRightAction() {
        currentCube.moveRight();
        if (outOfBounds() || coincide()) {
            currentCube.moveLeft();
        }
    }

    //Determine if it can fall
    public boolean canDrop() {
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int column = cell.getColumn();
            //Does it reach the bottom
            if (row == wall.length - 1) {
                return false;
            } else if (wall[row + 1][column] != null) {
                return false;
            }
        }
        return true;
    }

    //Determine if the current row is full
    public boolean isFullLine(int row) {
        Cell[] cells = wall[row];
        for (Cell cell : cells) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }

    //Eliminate rows
    public void eliLines() {
        //Count the number of rows currently eliminated
        int line = 0;
        Cell[] cells = currentCube.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            if (isFullLine(row)) {
                line++;
                for (int i = row; i > 0; i--) {
                    System.arraycopy(wall[i - 1], 0, wall[i], 0, wall[0].length);
                }
                wall[0] = new Cell[9];
            }
        }
        //Calculate the score
        totalScore += scorePool[line];
        //Total number of rows
        totalLine += line;

    }

    //Determine if the game is over
    public boolean isGameOver() {
        Cell[] cells = nextCube.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int column = cell.getColumn();
            if (wall[row][column] != null) {
                return true;
            }
        }
        return false;
    }



}
