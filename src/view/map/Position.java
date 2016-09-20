package view.map;

/**
 * class for coordinate system 
 * @author yongpinggao 
 * @since 3/19/16.
 * @version 1.0
 */
public class Position {
    private int x;
    private int y;
    /**
     * A  for position   
     * @param x coordinate
     * @param y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * getter for x coordinate
     * @return x coordinate
     */
    public int getX() {
        return x;
    }
    /**
     * setter for x coordinate 
     * @param x x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * getter for y coordinate
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
    /**
     * setter for y coordinate
     * @param y y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * measure the distance between point 
     * @param p the x and y coordinate
     * @return distance 
     */
    public int distanceTo(Position p) {
        int dx = p.x - this.x;
        int dy = p.y - this.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * getter for position 
     * @return  position
     */
    public Position getCenterPosition() {
        return new Position(x + Drawing.CELL_SIZE / 2, y + Drawing.CELL_SIZE / 2);
    }
}