/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Mo
 */
public class Square extends GameObject {
    
    private Color color;

    public Square(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
        
        /*
        set color of grahpic object
            -> SexyOrange (255, 255, 140)
        */
        color = new Color(255, 149, 237);
    }

    @Override
    void gameUpdate() {
        if (x > GamePanel.GAME_WIDTH - width || y > GamePanel.GAME_HEIGHT - height){
            x = -50;
            y = -50;
        }
        x++;
        y++;
    }

    @Override
    void gameRender(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
