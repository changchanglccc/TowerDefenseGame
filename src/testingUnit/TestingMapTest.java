
package testingUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.map.CellState;
import model.map.GameMap;
import model.map.GameMapCollection;


/**
 * Testing adding and delete map from GameMap class.
 * 
 * @author m_lzahra
 * @since 15/2/2016
 * @version 1.0
 */
public class TestingMapTest {
    GameMapCollection gameMapCollection;
    GameMap gameMap;
    int beforAddingMap;
    int afterAddingMap;
    int index;
    ArrayList<CellState> cellListTest;
    
    /**
     * Test adding map.
     */
    @Test
    public void testAddingMap() {
        gameMapCollection = new GameMapCollection();
        gameMap = new GameMap(2,2,null,"MANS");
        beforAddingMap = gameMapCollection.getMaps().size();
        gameMapCollection.addMap(gameMap);
        afterAddingMap = gameMapCollection.getMaps().size();
        assertTrue(beforAddingMap < afterAddingMap);
    }

    /**
     * Test deleting map.
     */
    @Test
    public void testDeleteMap() {
        gameMapCollection = new GameMapCollection();
        gameMap = new GameMap(2,2,null,"MANS");
        beforAddingMap = gameMapCollection.getMaps().size();
        gameMapCollection.addMap(gameMap);
        index = gameMapCollection.getMaps().indexOf(gameMap);
        gameMapCollection.deleteMap(index);
        afterAddingMap = gameMapCollection.getMaps().size();
        assertTrue(beforAddingMap == afterAddingMap);
    }

    /**
     * Test map path.
     */
    @Test
    public void testFindingPathInMap() {
        cellListTest = new ArrayList<CellState>();
        for (int i = 0 ; i < 15 * 30 ; i++) { 
            cellListTest.add(CellState.Grass);
        }
        for (int i = 0 ; i < 20 ; i++) { 
            cellListTest.add(CellState.Path);
        }
        cellListTest.set(0, CellState.Entrance);
        cellListTest.set(19,CellState.Exit );
        gameMap = new GameMap();
        gameMap.setCells(cellListTest);
        assertTrue(gameMap.findPathList().size() == 20);
    }

    /**
     * Test loading maps form files.
     */
    @Test
    public void testLoadingMap() {
        GameMapCollection gameCollection = new GameMapCollection();
        int sizeBeforLoading = gameCollection.getMaps().size();
        GameMapCollection loadedGameCollection = GameMapCollection.loadMapsFromFile();
        int sizeAfterLoading = loadedGameCollection.getMaps().size();
        assertTrue(sizeBeforLoading < sizeAfterLoading );
    }

    /**
     * Test Saving maps to files.
     */
    @Test
    public void testSavingMap() {
        GameMapCollection gameCollection = GameMapCollection.loadMapsFromFile();
        gameMap = new GameMap(2,2,null,"MANS");
        gameCollection.addMap(gameMap);
        assertTrue(gameCollection.saveMapsToFile(gameCollection));
        // deleting  saved map and rewrite the file without 
        int index = gameCollection.findGameMapInCollection(gameMap);
        gameCollection.deleteMap(index);
        gameCollection.saveMapsToFile(gameCollection);
    }
}