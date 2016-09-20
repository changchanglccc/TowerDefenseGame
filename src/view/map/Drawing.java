package view.map;

/**
 * A class static functions to converter coordinate system from x and y coordinate to index and vice versa.  
 * @author yongpinggao  
 * @since 3/15/16.
 * @version 2.0
 */
// Base class of Drawing
public class Drawing {
    // input: coordinate(x,y)(pixels), cell size of a cell. And cols number
    // output: nth cell in whole map
    
    /**
     * 
     * @param x x coordinate 
     * @param y y coordinate 
     * @param cols number of columns 
     * @return index 
     */
    public static int coordinateToIndexConverter(int x, int y, int cols) {
        return x / CELL_SIZE + (y / CELL_SIZE) * cols;
    }
    
    /**
     * get the position base on the index 
     * @param index the cell index 
     * @param cols number of columns 
     * @return position represent the x and y coordinate 
     */
    public static Position indexToCoordinateConverter(int index, int cols) {
        int x = index % cols;
        int y = index / cols;
        return new Position(x * CELL_SIZE, y * CELL_SIZE);
    }

    public final static int CELL_SIZE = 30;
}
