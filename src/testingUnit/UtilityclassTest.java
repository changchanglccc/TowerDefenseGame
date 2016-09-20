package testingUnit;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;

import utility.Helper;

/**
 * test Utility class 
 * 
 * @author m_lzahra
 * @version 1.0
 * @since 14/2/2016
 */
public class UtilityclassTest {
    private String[] array;
    Dimension imageSize;
    Dimension imageBoundary;
    Dimension newDimension;

    /**
     * setting values. 
     */
    @Before
    public void setValues() {
        array = new String[]{"11","21","41","31","1"};
    }

    /**
     * Test getting the maximum values function.
     */
    @Test
    public void maxValueTest() {
        assertEquals(41,Helper.getMaxValueFrom(array));
    }

    /**
     * Test getting scaled dimension.
     */
    @Test
    public void getScaledDimension() {
        imageSize = new Dimension(500, 100);
        imageBoundary = new Dimension(200, 200);
        newDimension = Helper.getScaledDimension(imageSize, imageBoundary);
        assertTrue((int)newDimension.getWidth() == 200);
        assertTrue((int)newDimension.getHeight() == 40);
    }
}
