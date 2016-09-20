package testingUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import view.map.Drawing;
import view.map.Position;

/**
 * test coordinate converter from x and y coordinate to index and vice versa.
 * 
 * @author m_lzahra
 * @version 1.0
 * @since 14/2/2016
 */
public class CoordinateConverterTest {
    private int xCoordinate;
    private int yCoordinate;
    private int numberOfColumns;
    private int index;
    
    /**
     * setting values 
     */
    @Before
    public void setValues() {
        xCoordinate = 0;
        yCoordinate = 0;
        index = 0;
        numberOfColumns = 10;
    }
    
    /**
     * test coordinate converter from x and y coordinate to index
     */
    @Test
    public void testgettingIndex() {
        int expected = 0 ;    
        int result= Drawing.coordinateToIndexConverter(xCoordinate, yCoordinate, numberOfColumns);
        assertTrue(expected == result);
    }
    
    /**
     * test coordinate converter from index to x and y coordinate 
     */
    @Test
    public void testgettingXAndYCoordinate() {
        Position arr = Drawing.indexToCoordinateConverter(index, numberOfColumns);
        assertEquals(arr.getX(), 0);
        assertEquals(arr.getY(), 0);
    }
}