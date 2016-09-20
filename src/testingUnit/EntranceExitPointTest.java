package testingUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.map.CellState;
import model.map.GameMap;

/**
 * test getting the exist and entrance form map file.
 * 
 * @author m_lzahra
 * @version 1.0
 * @since 14/3/2016
 */
public class EntranceExitPointTest {

    private GameMap  gameMap;
    private ArrayList<CellState> cellListTest;

    /**
     * Setting the pointer for gameMap.
     */
    @Before
    public void setValus() {
        gameMap = new GameMap();
        cellListTest = gameMap.getCells();
        cellListTest.set(0, CellState.Entrance);
        cellListTest.set(10, CellState.Exit);
        gameMap.setCells(cellListTest);
    }

    /**
     * test get the entrance.
     */
    @Test
    public void getExtrancePosition() {
        assertTrue(gameMap.findEntranceIndex() == 0);
    }

    /**
     * test get the exist.
     */
    @Test
    public void getExitPosition() {
        assertTrue(gameMap.findExitIndex() == 10);
    }
}
