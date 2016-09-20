
package testingUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.critter.Critter;
import model.critter.CritterMovingBehavior;
import model.map.CellState;
import model.map.GameMap;
import model.tower.shootingstrategy.TargetBasedOnClosestToTower;
import view.critter.CritterType;
import view.map.Drawing;
import view.map.Position;

/**
 * Test target strategy, target on the closest to Exit
 * @author LiChong
 * @since 5/4/2016
 * @version 1.3
 *
 */
public class TargetBasedOnClosestToTowerTest {
    private Set<Critter> crittersInRange;
    private Critter critter1;
    private Critter critter2;
    private Critter critter3;
    private ArrayList<CellState> cells;
    private GameMap gameMap;
    private Position towerPosition;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        crittersInRange = new HashSet<>();
        critter1 = new Critter(CritterType.CritterA);
        critter2 = new Critter(CritterType.CritterB);
        critter3 = new Critter(CritterType.CritterC);
        crittersInRange.add(critter1);
        crittersInRange.add(critter2);
        crittersInRange.add(critter3);
        cells = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            cells.add(CellState.Path);
        }
        cells.add(0, CellState.Entrance);
        cells.add(19,CellState.Exit);
        gameMap = new GameMap(10,15, cells, "myMap");
        CritterMovingBehavior critterMovingBehavior1 = new CritterMovingBehavior(gameMap, 10);
        CritterMovingBehavior critterMovingBehavior2 = new CritterMovingBehavior(gameMap, 10);
        CritterMovingBehavior critterMovingBehavior3 = new CritterMovingBehavior(gameMap, 10);
        towerPosition = new Position(Drawing.CELL_SIZE * 3, Drawing.CELL_SIZE);

        critter1.setMovingBehavior(critterMovingBehavior1);
        critter2.setMovingBehavior(critterMovingBehavior2);
        critter3.setMovingBehavior(critterMovingBehavior3);
        critter1.getMovingBehavior().setCurrentPosition(new Position(0,0));
        critter2.getMovingBehavior().setCurrentPosition(new Position(Drawing.CELL_SIZE * 3,0));
        critter3.getMovingBehavior().setCurrentPosition(new Position(Drawing.CELL_SIZE * 6,0));
    }

    /**
     * Test method for {@link model.tower.shootingstrategy.TargetBasedOnClosestToTower#targetOnCritters(java.util.Set, view.map.Position)}.
     */
    @Test
    public void testTargetOnCritters() {
        TargetBasedOnClosestToTower targetBasedOnClosestToTower = new TargetBasedOnClosestToTower();
        Critter closestCritter = targetBasedOnClosestToTower.targetOnCritters(crittersInRange, towerPosition);
        assertSame("find the critter who is closest to tower",closestCritter,critter2);
        assertNotSame("find the critter who is closest to tower",closestCritter,critter1);
        assertNotSame("find the critter who is closest to tower",closestCritter,critter3);
    }

}
