package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Enemy class, represents the object the player must avoid
 *
 * @version 0.1.0
 * @author Mo
 */
public class Square extends GameObject {

    private Color color;

    /**
     * Constructs a new enemy object at x,y with width, height
     *
     * @param x the x position
     * @param y this y position
     * @param width the width of the enemy
     * @param height the height of the enemy
     */
    public Square(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);

        color = new Color(255, 149, 237);
    }

    @Override
    void gameUpdate() {
        if (x > GamePanel.GAME_WIDTH - width
                || y > GamePanel.GAME_HEIGHT - height) {
            x = -50;
            y = -50;
        }
        x++;
        y++;
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
