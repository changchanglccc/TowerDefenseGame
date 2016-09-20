package model.tower;

import java.awt.*;

/**
 * class defines the shooting effect information 
 * for example color and width
 *@author yongpinggao 
 *@since 3/14/16.
 *@version 2.0
 */
public class ShootingEffect {

    private Color color;
    private int width;

    /**
     * A constructor for ShootingEffect
     * @param color  color to draw the tower missile  
     * @param width  missile width
     */
    public ShootingEffect(Color color, int width) {
        this.color = color;
        this.width = width;
    }

    /**
     * get tower missile color 
     * @return  tower missile color 
     */
    public Color getColor() {
        return color;
    }

    /**
     * get tower missile width
     * @return tower missile width 
     */
    public int getWidth() {
        return width;
    }

}
