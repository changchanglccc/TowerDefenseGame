package model.map;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * GameMap class for the Game
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public class GameMap {
    // cell image size in pixels
    private ArrayList<CellState> cells;
    private int mCols;
    private int mRows;
    private String mapName;

    private String createTime;
    private ArrayList<String> editedTimeList = new ArrayList<>();
    private ArrayList<Double> scoreList = new ArrayList<>();
    private HashMap<String, String> resultMap = new HashMap<>();

    /**
     * Constructor for game map.
     * @param mapRows map rows
     * @param mapCols map cols 
     * @param cells map cells
     * @param mapName image name for image
     */
    public GameMap(int mapRows, int mapCols, ArrayList<CellState> cells, String mapName) {
        this.cells = cells;
        this.mCols = mapCols;
        this.mRows = mapRows;
        this.mapName = mapName;
    }

    /**
     * Default Constructor.
     */
    public GameMap() {
        int mCols = 30;
        int mRows = 15;
        ArrayList<CellState> cells = new ArrayList<>();
        for (int i = 0; i < mCols * mRows; i++) {
            cells.add(CellState.Grass);
        }
        String imageName = "";
        this.cells = cells;
        this.mCols = mCols;
        this.mRows = mRows;
        this.mapName = imageName;
    }

    /**
     * all cell state of map.
     * getter method
     * @return
     */
    public ArrayList<CellState> getCells() {
        return cells;
    }

    /**
     * all cell state of map.
     * setter method
     * @return
     */
    public void setCells(ArrayList<CellState> cells) {
        this.cells = cells;
    }

    /**
     * cols of the map.
     * getter method
     * @return
     */
    public int getmCols() {
        return mCols;
    }

    /**
     * cols of the map.
     * setter method
     * @return
     */
    public void setmCols(int mCols) {
        this.mCols = mCols;
    }

    /**
     * rows of map.
     * getter method
     * @return
     */
    public int getmRows() {
        return mRows;
    }

    /**
     * rows of map.
     * setter method
     * @return
     */
    public void setmRows(int mRows) {
        this.mRows = mRows;
    }

    /**
     * image name of game map.
     * getter method
     * @return
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * image name of game map.
     * setter method
     * @return
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * change cell state from grass to toPlaceTower state.
     */
    public void setToPlaceTowerState() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == CellState.Grass) {
                cells.set(i, CellState.ToPlaceTower);
            } else if (cells.get(i) == CellState.Chosen) {
                cells.set(i, CellState.Tower);
            }
        }
    }

    /**
     * change toPlaceTower state back to grass state.
     */ 
    public void setToGrassState() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == CellState.ToPlaceTower) {
                cells.set(i, CellState.Grass);
            }
        }
    }

    /**
     * toggle chosen state of cell.
     * @param index the index of cells
     */
    public void toggleChosenState(int index) {

        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == CellState.Chosen) {
                cells.set(i, CellState.Tower);
            }
        }
        cells.set(index, CellState.Chosen);
    }

    /**
     * set state back to grass.
     */
    public void clearState() {
        // if the user press the wrong cells, aka path, etc.
        // set state back to grass
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == CellState.Chosen) {
                cells.set(i, CellState.Tower);
            } else if (cells.get(i) == CellState.ToPlaceTower) {
                cells.set(i, CellState.Grass);
            }

        }
    }

    /**
     * set state to pure map state.
     * it is usually invoked when the game finished
     */
    public void clearStateToPureMapState() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == CellState.Chosen || cells.get(i) == CellState.ToPlaceTower || cells.get(i) == CellState.Tower) {
                cells.set(i, CellState.Grass);
            }
        }
    }

    /**
     * find exit index of game map.
     * @return
     */
    public int findExitIndex() {
        for (int i = 0; i < cells.size() ; i++) {
            if (cells.get(i) == CellState.Exit) { // Entrance -> indexEntrance
                return i;
            }
        }
        return -1;
    }

    /**
     * find entrance index of game map.
     * @return
     */
    public int findEntranceIndex() {
        for (int i = 0; i < cells.size() ; i++) {
            if (cells.get(i) == CellState.Entrance) { // Entrance -> indexEntrance
                return i;
            }
        }
        return -1;
    }

    /**.
     * find path list of the game map.
     * @return
     */
    public ArrayList<Integer> findPathList() {
        ArrayList<Integer> pathList = new ArrayList<>();
        for (int i = 0; i < cells.size() ; i++) {
            if (cells.get(i) == CellState.Path) { // PATH -> pathList
                pathList.add(i);
            }
        }
        return pathList;
    }
    /**
     * Get time of creating.
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * Set time of creating.
     * @param createTime it represents the time of creating
     */
    public void setCreateTime(Date createTime) {
        this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }
    /**
     * Get edited time list
     * @return
     */
    public ArrayList<String> getEditedTimeList() {
        return editedTimeList;
    }
    /**
     * Add edited time.
     * @param editedTime a time that needs to be added
     */
    public void addEditedTime(Date editedTime) {
        this.editedTimeList.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(editedTime));
    }
    /**
     * Get score list
     * @return
     */
    public ArrayList<Double> getScoreList() {
        return scoreList;
    }
    /**
     * Add score into score list.
     * @param score the score will be added
     */
    public void addScore(Double score) {
        this.scoreList.add(score);
        // each time a new score is added, sort it
        Collections.sort(scoreList, Collections.reverseOrder());
    }
    /**
     * Get five highest score.
     * @return
     */
    public ArrayList<Double> getFiveHighestScore() {
        int size = scoreList.size();
        ArrayList<Double> fiveHighestList = new ArrayList<>();
        if (size <= 5) {
            for (Double score: scoreList) {
                fiveHighestList.add(score);
            }
        } else {
            for (int i = 0; i < 5; i++)
                fiveHighestList.add(scoreList.get(i));
        }
        return fiveHighestList;
    }
    /**
     * Get a map with result.
     * @return
     */
    public HashMap<String, String> getResultMap() {
        return resultMap;
    }
    /**
     * Add result to map.
     * @param result the result of playing game
     */
    public void addResultToMap(String result) {
        resultMap.put(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), result);
    }
    /**
     * Get the time of last editing.
     * @return
     */
    public String getLastEditTime() {
        return editedTimeList.get(editedTimeList.size() - 1);
    }
    /**
     * Get all edit time.
     * @return
     */
    public String getAllEditTime() {
        StringBuilder sb = new StringBuilder();
        for (String date: editedTimeList) {
            sb.append(date + ", \n");
        }
        return sb.toString();
    }
    /**
     * Get all results of playing time.
     * @return
     */
    public String getAllResults() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> results : resultMap.entrySet()) {
            sb.append(results.getKey().toString() + " : " + results.getValue().toString() + "\n");
        }
        return sb.toString();
    }
}