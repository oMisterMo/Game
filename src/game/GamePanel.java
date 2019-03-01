/* 
 * Copyright (C) 2018 Mohammed Ibrahim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package game;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Game screen which ties together all classes
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * Width of the game world
     */
    public static final int GAME_WIDTH = 600;

    /**
     * Height of the game world
     */
    public static final int GAME_HEIGHT = 600;

    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;

    private final int FPS = 60;     //max fps
    private long averageFPS;
    private int counter = 0;        //dont need 

    //GAME VARIABLES HERE
    private Player player;
    private Square square;
    private Square square2;
    private Color backgroundColor;    //Represents colour of background, duh

    //CONSTRUCTOR
    /**
     * Initialises game variables
     *
     * Sets focus and key listener
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        setFocusable(true);
        requestFocus();

        //initialise varialbes
        init();
    }

    private void init() {
        player = new Player(50, GAME_HEIGHT - 100, 50, 50);
        square = new Square(100, 100, 150, 150);
        square2 = new Square(400, 100, 150, 150);
        backgroundColor = new Color(100, 149, 237);    //Represents colour of background

        addKeyListener(new TAdapter());
    }

    //METHODS
    /*
     Is called after our JPanel has been added to the JFrame component.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        long startTime;
        long timeTaken;
        long frameCount = 0;
        long totalTime = 0;
        long waitTime;
        long targetTime = 1000 / FPS;

        //To test independent speed
        long start2 = 0;
        long timeMillis2 = 0;
        long timeMillis3 = 0;
        long timeMillis4 = 0;

        running = true;

        image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        //GAME LOOP
        while (running) {
            startTime = System.nanoTime();

            start2 = System.nanoTime();
            gameUpdate();
            timeMillis2 = (System.nanoTime() - start2) / 1000000;

            start2 = System.nanoTime();
            gameRender(g);
            timeMillis3 = (System.nanoTime() - start2) / 1000000;

            start2 = System.nanoTime();
            gameDraw();
            timeMillis4 = (System.nanoTime() - start2) / 1000000;

//            gameUpdate();
//            gameRender(g);
//            gameDraw();
            //How long it took to run
            timeTaken = (System.nanoTime() - startTime) / 1000000;
            //16ms - targetTime
            waitTime = targetTime - timeTaken;

            //System.out.println(timeTaken);
            if (waitTime < 0) {
                //I get a negative value at the beg
                System.out.println(counter++ + " - NEGATIVE: " + waitTime);
                System.out.println("targetTime = " + targetTime);
                System.out.println("timeTaken = " + timeTaken + "\n");
            }

//            //Speed TEST methods
//            if(frameCount >= 58) {
//                //Test the time taken to run
//                System.out.println("Process input time: " + timeMillis2);
//                System.out.println("Update time: " + timeMillis3);
//                System.out.println("Draw time: " + timeMillis4);
//                System.out.println("------------------------------------------");
//            }
            try {
                //System.out.println("Sleeping for: " + waitTime);
                //thread.sleep(waitTime);
                Thread.sleep(waitTime);
            } catch (Exception ex) {

            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;

            //If the current frame == 60  we calculate the average frame count
            if (frameCount >= FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                //System.out.println("Average fps: " + averageFPS);
            }
        }

    }

    private boolean isCollided(GameObject one, GameObject two) {
        return one.getHitbox().intersects(two.getHitbox());
    }

    private void gameUpdate() {
        //********** Do updates HERE **********
        player.gameUpdate();
        square.gameUpdate();
        square2.gameUpdate();

        if (player.hitbox.intersects(square.hitbox)) {
            System.out.println("yes 1");
//            System.exit(0);
        }
        if (player.hitbox.intersects(square2.hitbox)) {
            System.out.println("yes 2");
//            System.exit(0);
        }
        if (isCollided(player, square)) {
            System.out.println("Enemies collide");
//            System.exit(0);
        }
    }

    private void gameRender(Graphics2D g) {
        g.setColor(backgroundColor);
        //g.setColor(Color.WHITE);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

//        //Any test code here
//        g.drawString("getRGB() ->   " + backgroundColor.getRGB(), 450, 50);
//        g.drawString("Red: " + backgroundColor.getRed() + " ", 450, 100);
//        g.drawString("Green: " + backgroundColor.getGreen() + " ", 450, 150);
//        g.drawString("Blue: " + backgroundColor.getBlue() + " ", 450, 200);
        //********** Do drawings HERE **********
        player.gameRender(g);
        square.gameRender(g);
        square2.gameRender(g);

        g.setColor(Color.BLACK);
        g.drawString("FPS:" + averageFPS, 10, 20);
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    //Handle Input ** Inner Class
    private class TAdapter extends KeyAdapter {

        //When a key is pressed, let the CRAFT class deal with it.
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

}
