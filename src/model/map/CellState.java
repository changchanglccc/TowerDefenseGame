package model.map;

/**
 * CellState for gamemap
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public enum CellState {
    Grass("grass"),
    Path("path"),
    Entrance("entrance"),
    Exit("exit"),
    ToPlaceTower("toplacetower"),
    Tower("tower"),
    Chosen("chosen");

    private String cellStateName;
    /**
     * Constructor of CellState
     * @param name it gives a name to CellState
     */
    CellState(String name) {
        cellStateName = name;
    }
    /**
     * Set CellState name
     * @return 
     */
    public String getCellStateName() {
        return cellStateName;
    }

}