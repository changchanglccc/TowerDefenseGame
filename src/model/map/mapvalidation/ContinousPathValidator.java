package model.map.mapvalidation;

import java.util.ArrayList;
import java.util.HashMap;

import model.map.CellState;

/**
 * This class is to validate whether the path in map is consecutive.
 * Having gap in path is illegal.
 * 
 * @version 1.3
 * 
 * @author LiChong
 *
 */
public class ContinousPathValidator implements MapValidator {

    private ArrayList<CellState> cellList;
    private HashMap<Integer,Integer> cMap;
    //private ArrayList<CellState> pathList;
    private int Cols;
 
    /**
     * This method validate() overrides the method validate() of parent class MapValidator. 
     * And it checks whether the path is continous.
     * 
     * @return boolean A boolean values that represent whether the path is continous.
     * 
     */    
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        
        for (int i = 0;i < cellList.size(); i++) {
            if (cellList.get(i) == CellState.Path 
                    && cMap.get(i) == 0) {//some path grid alone
                return false;
            } else if (cellList.get(i) == CellState.Path 
                    && cMap.get(i) == 1) {//path break in somewhere
                if (
                	cellList.get(i-1) == CellState.Entrance
                    || cellList.get(i-1) == CellState.Exit
                    || cellList.get(i+1) == CellState.Entrance
                    || cellList.get(i+1) == CellState.Exit
                    || cellList.get(i-Cols) == CellState.Entrance
                    || cellList.get(i-Cols) == CellState.Exit
                    || cellList.get(i+Cols) == CellState.Entrance
                    || cellList.get(i+Cols) == CellState.Exit
                ) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Constructor
     * 
     * @param countMap this is a HashMap to store the number of neighbours of every gird map path
     * @param cellList cellList this parameter represents the map.
     * @param mapCols mapCols this parameter represents the column of map.
     */
    public ContinousPathValidator(HashMap<Integer,Integer> countMap,ArrayList<CellState> cellList, int mapCols) {
        this.cMap = countMap;
        this.cellList = cellList;
        this.Cols = mapCols;
    }
}