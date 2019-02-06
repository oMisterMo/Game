package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Represents any object which is drawn to the screen.
 *
 * Possible subclasses: -> Tile -> Particle -> Player -> Square
 *
 * ALT + SHIFT + F = auto re-factor
 *
 * @version 0.1.0
 * @author Mo
 */
public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitbox;

    abstract void gameUpdate();

    abstract void gameRender(Graphics2D g);

    //Getters and Setter
    /**
     * Sets the position and size of the game object
     *
     * @param x the x position to set
     * @param y the y position to set
     * @param width the width to set
     * @param height the height to set
     */
    public void setGameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x position of the game object
     *
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the game object
     *
     * @return y position
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the game object
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the game object
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the bounding rectangle of the current game object
     *
     * @return
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

}
