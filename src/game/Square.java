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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Enemy class, represents the object the player must avoid
 *
 * @version 0.1.0
 * @author Mohammed Ibrahim
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
