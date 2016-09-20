package testingUnit;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.savegame.GameCollection;
import model.savegame.GameInfo;

/**
 * testing saving,loading, and extract information from games saved to the file.
 * 
 * This test build on the assumption there is at last one saved game in the file 
 * @author m_lzahra
 * @since 4/4/2016
 * @version 1.0
 */
public class SavingLoadingGameTest {

    GameCollection gameCollection ;
    int gmaeCollectionSize;
    GameInfo game;

    /**
     * setting values 
     */
    @Before
    public void setValues() {
        gameCollection = new GameCollection(); 
        gameCollection.loadGame();  
    }
    /**
     * deleting test info from file
     */
    @After
    public void deletTestGameFromFile() {
        if (game != null) {
            int index = gameCollection.findGameInCollection("GameTest");
            gameCollection.removeGame(index);
            gameCollection.saveGame();  
        }
    }

    /**
     * Test saving game.
     */
    @Test
    public void testSaveGame() {
        game = new GameInfo(null , null ,1000,100,4,"GameTest", "GameTest");
        gameCollection.addGame(game);
        gameCollection.saveGame();
        gameCollection.loadGame(); // load the file again with the new game added on 
        gmaeCollectionSize = gameCollection.getGames().size();
        assertTrue(gmaeCollectionSize == gameCollection.getGames().size() );
    }

    /**
     * Test loading game.
     */
    @Test
    public void testLoadGame() {
        gmaeCollectionSize = gameCollection.getGames().size();
        if (gameCollection.loadGame())// otherwise means file not exist yet because no game has been saved 
            assertTrue(gmaeCollectionSize < gameCollection.getGames().size() );
    }

    /**
     * Test loading game and getting information.
     */
    @Test
    public void testLoadGameAndGetInfo() {
        gameCollection.loadGame();
        if (gameCollection.getGames().size() > 0) {  // otherwise no game had been saved to file  
            GameInfo gameExtractInfo = gameCollection.getGames().get(0);
            assertTrue(gameExtractInfo.getGameName() != null);
            assertTrue(gameExtractInfo.getMapName() != null);
        }
    }
}
