package model.map.mapvalidation;

import model.map.CellState;

import java.util.ArrayList;

/**
 * This class is to validate whether Entrance is the neighbour of Exit.
 * If they are neighbours of each other, it is illegal.
 * 
 * SeperateEntranceAndExitValidator implements MapValidator
 * 
 * @author LiChong
 * @version 1.0
 * 
 */
public class SeperateEntranceAndExitValidator implements MapValidator {

    private ArrayList<CellState> cellList;
    private int indexOfEntrance;
    private int indexOfExit;
    private int seaeCols;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i) == CellState.Entrance) {
                indexOfEntrance = i;
            } else if (cellList.get(i) == CellState.Exit) {
                indexOfExit = i;
            }
        }
        
        if (
                indexOfEntrance - seaeCols == indexOfExit 
                || indexOfEntrance + seaeCols == indexOfExit
                || indexOfEntrance - 1 == indexOfExit 
                || indexOfEntrance + 1 == indexOfExit
            ) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Constructor receives the number of columns and the tiles list.
     * @param cols number of cols in a map
     * @param cellList list of tiles in the map
     */
    public SeperateEntranceAndExitValidator(int cols, ArrayList<CellState> cellList) {
        this.cellList = cellList;
        this.seaeCols = cols;
    }

}
