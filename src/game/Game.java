package game;

import javax.swing.JFrame;

/**
 * Main Class
 *
 * 25/01/2014
 *
 * @version 0.1.0
 * @author Mo
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window = new JFrame("First Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel game = new GamePanel();
        window.add(game);

        window.pack();
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

}
