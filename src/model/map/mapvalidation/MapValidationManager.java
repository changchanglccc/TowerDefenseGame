package model.map.mapvalidation;

import model.map.CellState;
import model.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class manage all map validation 
 *  
 * @author LiChong
 * @version 1.0 
 * @since 5/4/2016
 */
public class MapValidationManager {

    private ArrayList<CellState> cellList;
    private int cols;
    private int rows;
    private String errorMessage;
    private ArrayList<Integer> pathList;
    
    private HashMap<Integer,Integer> flagMap;
    private HashMap<Integer,Integer> countMap;
    /**
     * Constructor of MapValidationManager
     * @param gameMap it represents who will be managed.
     */
    public MapValidationManager(GameMap gameMap) {
        this.cellList = gameMap.getCells();
        this.cols = gameMap.getmCols();
        this.rows = gameMap.getmRows();
        this.pathList = new ArrayList<Integer>();
        flagMap = new HashMap<Integer,Integer>();
        countMap = new HashMap<Integer,Integer>();
        
        setFlag();
        setPathList();
        setCountMap();
    }

    /**
     * Add flags to the flag map 
     */
    private void setFlag() {
        for (int i = 0; i < cellList.size(); i++) {
            if (0 < i && i < cols) {
                flagMap.put(i, 11);////upper bounds edge
            } else if ((cols * (rows - 1) < i) 
                    && (i < (cols * rows - 1)) ) { //lower bounds edge
                flagMap.put(i, 12);
            } else if (i == cols * leftOrRightEdge()) { //left edge
                flagMap.put(i, 13);
            } else if (i == cols * leftOrRightEdge() - 1) { //Right edge
                flagMap.put(i, 14);
            } else if (i == 0) { //upperleft point
                flagMap.put(i, 21);
            } else if (i == cols) { //upperright point
                flagMap.put(i, 22);
            } else if (i == cols * (rows - 1)) { //lowerleft point
                flagMap.put(i, 23);
            } else if (i == (cols * rows - 1)) { //lowerright point
                flagMap.put(i, 24);
            } else {
                flagMap.put(i, 0);//default value, presenting who has four neighbours
            }
        }
    }

    /**
     * Counts the number of rows minus 1.
     * @return i int for the number of rows
     */
    private int leftOrRightEdge() {
        int i = 1;
        while (i < rows) {
            i++;
        }
        return i;
    }
    
    /**
     * Runs through the array to get path, entrance or exit tiles
     */
    private void setPathList() {
        for (int i = 0; i < cellList.size(); i++) { //know the index of CellState.PATH
            if (
                    cellList.get(i) == CellState.Path
                    || cellList.get(i) == CellState.Entrance
                    || cellList.get(i) == CellState.Exit
                ) {
                pathList.add(i);
            }
        }
    }
    
    /**
     * Sets a map with all the restrictions to be validated by a map validator
     */
    public void setCountMap() { //

        int iL = -1;//neighbour
        int iR = -1;
        int iU = -1;
        int iD = -1;
        int count = 0;

        for (int i = 0; i < pathList.size(); i++) {
            int posIndex = pathList.get(i);
            switch (flagMap.get(posIndex)) {
            case 11:
                iL = posIndex - 1;
                iR = posIndex + 1;
                iD = posIndex + cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iR)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 12:
                iL = posIndex - 1;
                iR = posIndex + 1;
                iU = posIndex - cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iR)) { count++; }
                if (pathList.contains(iU)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 13:
                iU = posIndex - cols;
                iR = posIndex + 1;
                iD = posIndex + cols;
                
                if (pathList.contains(iU)) { count++; }
                if (pathList.contains(iR)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 14:
                iL = posIndex - 1;
                iU = posIndex - cols;
                iD = posIndex + cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iU)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 21:
                iR = posIndex + 1;
                iD = posIndex + cols;
                
                if (pathList.contains(iR)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 22:
                iL = posIndex - 1;
                iD = posIndex + cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 23:
                iU = posIndex - cols;
                iR = posIndex + 1;
                
                if (pathList.contains(iU)) { count++; }
                if (pathList.contains(iR)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            case 24:
                iL = posIndex - 1;
                iU = posIndex - cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iU)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            default:
                iL = posIndex - 1;
                iR = posIndex + 1;
                iU = posIndex - cols;
                iD = posIndex + cols;
                
                if (pathList.contains(iL)) { count++; }
                if (pathList.contains(iR)) { count++; }
                if (pathList.contains(iU)) { count++; }
                if (pathList.contains(iD)) { count++; }
                countMap.put(posIndex, count);
                count = 0;
                break;
            }
        }
    }
    
    /**
     * Getter for count Map.
     * @return count Map
     */
    public HashMap<Integer, Integer> getCountMap() {
        return countMap;
    }

    /**
     * Get error message
     * @return message property
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    
    /**
     * Setter for error message
     * @param errorMessage message to be stored
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    /**
     * Verification of valid maps will return invalid and error message 
     * 
     * In the MapEditorWindow.java call 
     * <code>
     * MapValidationManager manager = new MapValidationManager(cellList, mcols, mrows);
     * if (new MapValidationManager().checkValidate()) saveMap();
     * else println(manager.getErrorMessage());
     * </code>
     * 
     * @return true if it's a valid map, or false and update errorMessage property
     */
    public boolean checkValidate() {

        if (!new NoEntranceNoExitMoreEntranceMoreExitValidator(cellList).validate()) {
            errorMessage = "Entrance or Exit of Path is illegal!";
            return false;
        } else if (! new EntranceExitInMiddlePathValidator(cellList, countMap).validate()) {
            errorMessage = "The Entrance or Exit is not in the end cell";
            return false;
        } else if (! new LengthValidator(cellList).validate()) {
            errorMessage = "The length of path is illegal!";
            return false;
        } else if (! new CirclePathValidator(countMap).validate()) {
            errorMessage = "Path cannot be a circle!";
            return false;    
        } else if (! new ExtraPathValidator(countMap, cellList).validate()) {
            errorMessage = "There is extra path in your map!";
            return false;
        } else if (! new ContinousPathValidator( countMap, cellList,  cols).validate()) {
            errorMessage = "Your path is not continous!";
            return false;    
        } else if (! new SeperateEntranceAndExitValidator(cols, cellList).validate()) {
            errorMessage = "Entrance can not be the neighbour of Exit!";
            return false;
        } else {
            errorMessage = "";
            return true;
        }
    }
}