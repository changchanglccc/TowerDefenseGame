package utility;

import java.awt.*;
import java.util.ArrayList;

/**
 * Helper class to make code more readable
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public class Helper {

    /**
     * get max value from a string array
     * @param strArr string array
     * @return max value of this array
     */
    public static int getMaxValueFrom(String[] strArr) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (String s : strArr) {
            arr.add(Integer.parseInt(s));
        }
        int max = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }

        return max;
    }
    
    /**
     * get index from a string array of specific number
     * @param strArr a string array
     * @param n number
     * @return index of this number in string array
     */
    public static int getIndexFrom(String[] strArr, int n) {
        int index = 0;
        for (int i = 0; i < strArr.length; i++) {
            if (n == Integer.parseInt(strArr[i])) {
                index = i;
                return index;
            } else {
                index = -1;
            }
        }
        return index;
    }
    
    /**
     * get dimension of image file
     * @param imgSize
     * @param boundary
     * @return dimension
     * @reference http://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio 
     */
    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }
}