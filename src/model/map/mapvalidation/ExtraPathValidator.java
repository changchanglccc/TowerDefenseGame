package model.map.mapvalidation;

import model.map.CellState;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class is to validate whether there is circle or extra path in map.
 * Having circle path in map is illegal.
 * Having extra path in map is illegal.
 * 
 * @author LiChong
 *
 */
public class ExtraPathValidator implements MapValidator {

    private ArrayList<CellState> cellList;
    private HashMap<Integer,Integer> ceMap;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        
        for (int i = 0; i < cellList.size(); i++) {
            if (ceMap.containsValue(3) || ceMap.containsValue(4)) {
                return false;  // extra path
            }
        }
        return true;
    }
    
    /**
     * Sets the Map and cell list for validation.
     * @param cellList ArrayList of map
     * @param countMap HashMap 
     */
    public ExtraPathValidator(HashMap<Integer,Integer> countMap, ArrayList<CellState> cellList) {
        this.cellList = cellList;
        this.ceMap = countMap;
    }
}
