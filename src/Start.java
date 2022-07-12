import tetris.Tetris;

import javax.swing.*;

/**
 * @author Chris Wang
 * @version July 11, 2022
 * The entrance of the program
 */
public class Start {
    public static void main(String[] args) {
        // Window object
        JFrame frame = new JFrame("TETRIS GAME");
        // Game interface
        Tetris panel = new Tetris();
        // Add panel to window
        frame.add(panel);
        // Set visible
        frame.setVisible(true);
        // Set size
        frame.setSize(810, 840);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Entrance of the game
        panel.start();
    }
}
