package testingUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.map.CellState;
import model.map.GameMap;
import model.map.mapvalidation.CirclePathValidator;
import model.map.mapvalidation.ContinousPathValidator;
import model.map.mapvalidation.EntranceExitInMiddlePathValidator;
import model.map.mapvalidation.ExtraPathValidator;
import model.map.mapvalidation.LengthValidator;
import model.map.mapvalidation.MapValidationManager;
import model.map.mapvalidation.NoEntranceNoExitMoreEntranceMoreExitValidator;
import model.map.mapvalidation.SeperateEntranceAndExitValidator;

/**
 * test map  validator 
 * @author m_lzahra
 * @version 1.0
 * @since 14/3/2016
 */
public class ValidateMapTest {
    private ArrayList<CellState> cellListTest;
    private HashMap<Integer, Integer> countMap;
    private NoEntranceNoExitMoreEntranceMoreExitValidator  noEntranceNoExitMoreEntranceMoreExitValidator;
    private boolean checkMap;
    private SeperateEntranceAndExitValidator seperateEntranceAndExitValidator;
    private LengthValidator lengthValidator;
    private EntranceExitInMiddlePathValidator entranceExitInMiddlePathValidator;
    private MapValidationManager mapValidationManager;
    private GameMap gameMap;

    /**
     * Set values 
     */
    @Before
    public void setValues() {
        cellListTest = new ArrayList<CellState>();
        countMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < 30 * 15; i++) {
            cellListTest.add(CellState.Grass );
        }
    }

    /**
     * Test no Enterer or Exist point 
     */
    @Test
    public void testNoEntranceOrExit() {  
        noEntranceNoExitMoreEntranceMoreExitValidator = new NoEntranceNoExitMoreEntranceMoreExitValidator(cellListTest);
        checkMap = noEntranceNoExitMoreEntranceMoreExitValidator.validate();
        assertFalse(checkMap);
    }

    /**
     * Test when only one  Enterer and Exist point.
     */
    @Test
    public void testOneEntranceOrExit() {
        cellListTest.set(0, CellState.Entrance );
        cellListTest.set(100, CellState.Exit );
        noEntranceNoExitMoreEntranceMoreExitValidator= new NoEntranceNoExitMoreEntranceMoreExitValidator(cellListTest);
        checkMap = noEntranceNoExitMoreEntranceMoreExitValidator.validate();
        assertTrue(checkMap);
    }

    /**
     * Test when more then one  Enterer and Exist point.
     */
    @Test
    public void testMoreThenOneEntranceOrExit() {
        cellListTest.set(0, CellState.Entrance );
        cellListTest.set(100, CellState.Exit );
        cellListTest.set(5, CellState.Entrance );
        cellListTest.set(6, CellState.Exit );
        noEntranceNoExitMoreEntranceMoreExitValidator= new NoEntranceNoExitMoreEntranceMoreExitValidator(cellListTest);
        checkMap = noEntranceNoExitMoreEntranceMoreExitValidator.validate();
        assertFalse(checkMap);
    }

    /**
     * Test separate entrance and exit point.
     */
    @Test
    public void testSeperateEntranceAndExit() {
        for (int i = 0; i <20 ; i++) {
            cellListTest.set(i,CellState.Path );
        }
        cellListTest.set(5,CellState.Grass );
        cellListTest.set(0,CellState.Entrance);
        cellListTest.set(19,CellState.Exit);
        seperateEntranceAndExitValidator = new SeperateEntranceAndExitValidator(10,cellListTest);
        checkMap = seperateEntranceAndExitValidator.validate();
        assertTrue(checkMap);
    }

    /**
     * Test not Separate entrance and exit point 
     */
    @Test
    public void testNotEntranceAndExit() {
        for (int i = 0; i < 20 ; i++) {
            cellListTest.set(i,CellState.Path );
        }
        cellListTest.set(0,CellState.Entrance);
        cellListTest.set(19,CellState.Exit);
        seperateEntranceAndExitValidator = new SeperateEntranceAndExitValidator(10,cellListTest);
        checkMap = seperateEntranceAndExitValidator.validate();
        assertTrue(checkMap);
    }

    /**
     * Test whether the path is short.
     */
    @Test
    public void testToShortMap() {
        for (int i = 0; i < 9; i++) {
            cellListTest.set(i, CellState.Path );
        }
        lengthValidator = new LengthValidator(cellListTest);
        checkMap = lengthValidator.validate();
        assertFalse(checkMap);
    }

    /**
     * Test whether the path is not short.
     */
    @Test
    public void testNotToShortMap() {
        for (int i = 0; i < 25; i++) {
            cellListTest.set(i, CellState.Path );
        }

        lengthValidator = new LengthValidator(cellListTest);
        checkMap = lengthValidator.validate();
        assertTrue(checkMap);
    }

    /**
     * Test circle path.
     */
    @Test
    public void testCirclePath() {
        for (int i= 0 ; i < 7 ; i++) {
            countMap.put(i, i);
        }

        CirclePathValidator circlePathValidator = new CirclePathValidator(countMap);    
        checkMap = circlePathValidator.validate();
        assertTrue(checkMap);

    }
    /**
     * Test continous path
     */
    @Test
    public void testContinousPathValidator() {            
        for (int i= 0 ; i < 7 ; i++) {
            countMap.put(i, i);
        }    
        ContinousPathValidator testPath = new ContinousPathValidator(countMap, cellListTest, 30);        
        boolean testContinousPath = testPath.validate();
        assertTrue(testContinousPath);    
    }

    /**
     * Test Entrance in middle path.
     */
    @Test
    public void testExitInMiddleOfPath() {
        gameMap =new GameMap();
        for (int i = 0; i < 20; i++) {
            cellListTest.set(i,CellState.Path );
        }
        cellListTest.set(0,CellState.Entrance );
        cellListTest.set(15,CellState.Exit );
        gameMap.setCells(cellListTest);
        mapValidationManager = new MapValidationManager(gameMap);
        countMap = mapValidationManager.getCountMap();
        entranceExitInMiddlePathValidator = new EntranceExitInMiddlePathValidator(cellListTest, countMap);
        assertFalse(entranceExitInMiddlePathValidator.validate());
    }

    /**
     * Test Entrance in middle path.
     */
    @Test
    public void testEnteranceInMiddlePathValidator() {
        gameMap = new GameMap();
        for (int i = 0; i < 20; i++) {
            cellListTest.set(i,CellState.Path );
        }
        cellListTest.set(4,CellState.Entrance );
        cellListTest.set(19,CellState.Exit );
        gameMap.setCells(cellListTest);
        mapValidationManager = new MapValidationManager(gameMap);
        countMap = mapValidationManager.getCountMap();
        entranceExitInMiddlePathValidator = new EntranceExitInMiddlePathValidator(cellListTest, countMap);
        assertFalse(entranceExitInMiddlePathValidator.validate());
    }

    /**
     * Test no entrance or Exit in middle path.
     */
    @Test
    public void testEnteranceNotInMiddlePath() {
        gameMap = new GameMap();
        for (int i = 0; i < 20; i++) {
            cellListTest.set(i,CellState.Path );
        }
        cellListTest.set(0,CellState.Entrance);
        cellListTest.set(19,CellState.Exit );
        gameMap.setCells(cellListTest);
        mapValidationManager = new MapValidationManager(gameMap);
        countMap = mapValidationManager.getCountMap();
        entranceExitInMiddlePathValidator = new EntranceExitInMiddlePathValidator(cellListTest, countMap);
        assertTrue(entranceExitInMiddlePathValidator.validate());
    }

    /**
     * Test ExtraPath.
     */
    @Test
    public void ExtraPath() {
        gameMap = new GameMap();
        for (int i = 0; i < 21 ; i++) {
            cellListTest.set(i , CellState.Path );
        }
        cellListTest.set(0,CellState.Entrance );
        cellListTest.set(19,CellState.Exit );
        cellListTest.set(200, CellState.Path);
        gameMap.setCells(cellListTest);

        mapValidationManager = new MapValidationManager(gameMap);
        countMap = mapValidationManager.getCountMap();
        checkMap = new ExtraPathValidator(countMap,cellListTest).validate();
        assertTrue(checkMap);
    }

    /**
     * Test no ExtraPath.
     */
    @Test
    public void noExtraPath() {
        gameMap = new GameMap();
        for (int i = 0; i < 20 ; i++) {
            cellListTest.set(i , CellState.Path );
        }
        cellListTest.set(0,CellState.Entrance );
        cellListTest.set(19,CellState.Exit );
        gameMap.setCells(cellListTest);
        mapValidationManager = new MapValidationManager(gameMap);
        countMap = mapValidationManager.getCountMap();
        checkMap = new ExtraPathValidator(countMap,cellListTest).validate();
        assertTrue(checkMap);
    }    
}