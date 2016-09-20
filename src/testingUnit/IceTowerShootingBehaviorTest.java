
package testingUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.critter.Critter;
import model.critter.CritterMovingBehavior;
import model.map.CellState;
import model.map.GameMap;
import model.tower.IceTower;
import model.tower.IceTowerShootingBehavior;
import view.critter.CritterType;
import view.map.Position;

/**
 * Test ice tower shooting behavior
 * @author LiChong
 * @since 3/4/2016
 * @version 1.1
 *
 */
public class IceTowerShootingBehaviorTest {
    private IceTower iceTower;
    private IceTowerShootingBehavior iceTowerShootingBehavior;
    private Critter critter;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        critter = new Critter(CritterType.CritterB);
        GameMap gameMap = new GameMap();
        ArrayList<CellState>cellListTest = new ArrayList<CellState>();
        for (int i = 0; i < 20; i++) {
            cellListTest.add(CellState.Grass );
        }
        cellListTest.set(0, CellState.Entrance);
        cellListTest.set(19, CellState.Exit);
        gameMap.setCells(cellListTest);
        critter.setMovingBehavior(new CritterMovingBehavior(gameMap,3));
        critter.getMovingBehavior().setCurrentPosition(new Position(0,0));
        critter.getMovingBehavior().move();
        iceTower = new IceTower(1);
        iceTower.setPosition(new Position(0,0));
        iceTower.getTowerShootingBehavior().getCrittersInRange().add(critter);
    }

    /**
     * Test method for {@link model.tower.IceTowerShootingBehavior#shoot()}.
     */
    @Test
    public void testShoot() {
        iceTower.getTowerShootingBehavior().shoot();
        assertTrue( critter.getSpecicalEffectTimer().isRunning());
    }

    /**
     * Test method for {@link model.tower.IceTowerShootingBehavior#IceTowerShootingBehavior(int, int)}.
     */
    @Test
    public void testIceTowerShootingBehavior() {
        IceTowerShootingBehavior iceTowerShootingBehavior = new IceTowerShootingBehavior(1000,10);
        assertEquals("forzen equal",1000,iceTowerShootingBehavior.getFrozenTime());
        assertEquals("rateOfFire equal",10,iceTowerShootingBehavior.getRateOfFire());
    }

}
