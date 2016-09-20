package view.tower;

import view.map.Position;
import view.map.Drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * a class for the tower range view 
 * @author yongpinggao 
 * @since 3/19/16.
 * @version 2.0
 */
public class TowerShootingRangeView {

    private Ellipse2D towerRangeCircle;
    private int range;
    /**
     * A constructor for TowerShootingRangeView 
     * @param position the x and y coordinate
     * @param range tower range 
     */
    public TowerShootingRangeView(Position position, int range) {
        this.range = range;
        towerRangeCircle = new Ellipse2D.Float(position.getX() + Drawing.CELL_SIZE / 2 - range / 2, position.getY() + Drawing.CELL_SIZE  / 2 - range / 2, range, range);
    }

    /**
     * get tower range  Circle
     * @return tower range circle
     */
    public Ellipse2D getTowerRangeCircle() {
        return towerRangeCircle;
    }
    /**
     * get tower range circle boundary
     * @return tower  tower range circle boundary
     */
    public Rectangle getBounds() {
        return towerRangeCircle.getBounds();
    }
    /**
     * getter for tower range 
     * @return tower range 
     */
    public int getRange() {
        return range;
    }


}
