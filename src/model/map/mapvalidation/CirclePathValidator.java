package model.map.mapvalidation;
import model.map.mapvalidation.MapValidator;

import java.util.HashMap;

/**
 * This class is to validate whether the path is in a circle.
 * @author LiChong
 *
 */
public class CirclePathValidator implements MapValidator {

    private HashMap<Integer, Integer> countMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        for (Integer i : countMap.values()) {
            if (i == 2) { 
                continue;
            } else {
                return true;
            }
        }
    
        return false;
    }
    
    /**
     * Setter to the validator property.
     * @param countMap HashMap validator
     */
    public CirclePathValidator(HashMap<Integer, Integer> countMap) {
        this.countMap = countMap;
    }
}
