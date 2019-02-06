package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * A simple player object
 *
 * @version 0.1.0
 * @author Mo
 */
public class Player extends GameObject {

    private int dx;
    private int dy;

    private Color color;
    private short red;
    private short green;
    private short blue;

    /**
     * Constructs a new player object at x,y with width, height
     *
     * @param x the x position
     * @param y this y position
     * @param width the width of the player
     * @param height the height of the player
     */
    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);

        /*
         set color of grahpic object
         -> SexyOrange (255, 255, 140)
         */
        red = 255;
        green = 255;
        blue = 140;
        color = new Color(red, green, blue);

        //Speed/direction player moves
        dx = 0;
        dy = 0;
    }

    /**
     * Listens for key press and reacts accordingly
     *
     * Move player up, down, left, right when key is pressed
     *
     * @param e key event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }

        //Increment color depending on button pressed
        if (key == KeyEvent.VK_R) {
            red += 4;
            if (red >= 255) {
                red = 0;
            }
            color = new Color(red, green, blue);
        }
        if (key == KeyEvent.VK_G) {
            green += 4;
            if (green >= 255) {
                green = 0;
            }
            color = new Color(red, green, blue);
        }
        if (key == KeyEvent.VK_B) {
            blue += 4;
            if (blue >= 255) {
                blue = 0;
            }
            color = new Color(red, green, blue);
        }

    }

    /**
     * Stop moving when the key is released
     *
     * @param e key event
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    @Override
    void gameUpdate() {
        //To the right of screen
        if (x > GamePanel.GAME_WIDTH - width) {
            x = 0;
        }
        //To the botom
        if (y > GamePanel.GAME_HEIGHT - height) {
            y = 0;
        }
        //To the left
        if (x < 0) {
            x = GamePanel.GAME_WIDTH - width;
        }
        //To the top
        if (y < 0) {
            y = GamePanel.GAME_HEIGHT - height;
        }
//        if (x < GamePanel.GAME_WIDTH - width || y < GamePanel.GAME_HEIGHT - height) {
//            x = GamePanel.GAME_WIDTH - width;
//            y = GamePanel.GAME_HEIGHT - height;
//        }
        x += dx;
        y += dy;

        hitbox.x = x;
        hitbox.y = y;
    }

    @Override
    void gameRender(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        //g.setColor(Color.BLACK);
        //g.fill(hitbox);
    }
}
