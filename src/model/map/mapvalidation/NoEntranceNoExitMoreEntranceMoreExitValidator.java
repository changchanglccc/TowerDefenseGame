package model.map.mapvalidation;

import java.util.ArrayList;

import model.map.CellState;
import model.map.mapvalidation.MapValidator;

/**
 * This class is to validate whether the number of Entrance or Exit is legal.
 * NO Entrance or Exit is illegal.
 * More than one Entrance or one Exit is illegal.
 * 
 * This class implements MapValidator
 * 
 * @author LiChong
 * @version 1.0
 */
public class NoEntranceNoExitMoreEntranceMoreExitValidator implements MapValidator {

    private ArrayList<CellState> cellList;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub 
        int numberOfEntrance = 0;
        int numberOfExit = 0;
        
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i) == CellState.Entrance) {
                numberOfEntrance++;
            }
            if (cellList.get(i) == CellState.Exit) {
                numberOfExit++;
            }
        }   
        if (numberOfEntrance == 0 || numberOfEntrance > 1 || numberOfExit == 0 || numberOfExit > 1) {
            return false;
        } else {
            return true;
        }
        
    }
    
    /**
     * Contructor receives a list of tiles to validate
     * @param cellList list of tiles
     */
    public NoEntranceNoExitMoreEntranceMoreExitValidator(ArrayList<CellState> cellList) {
        // TODO Auto-generated constructor stub
        this.cellList = cellList;
    }

}
