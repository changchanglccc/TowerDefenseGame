
package testingUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.critter.Critter;
import model.critter.CritterMovingBehavior;
import model.map.CellState;
import model.map.GameMap;
import model.tower.BurningTower;
import model.tower.BurningTowerShootingBehavior;
import model.tower.shootingstrategy.TargetBasedOnNearest;
import view.critter.CritterType;
import view.map.Position;

/**
 * Test target strategy, target on the nearest
 * @author LiChong
 * @since 5/4/2016
 * @version 1.3
 *
 */
public class TargetBasedOnNearestTest {
    private Set<Critter> critterInRange;
    private Critter critter1;
    private Critter critter2;
    private Critter critter3;
    private GameMap gameMap;
    private ArrayList<CellState> cells;
    private ArrayList<Integer> pathList1;
    private ArrayList<Integer> pathList2;
    private ArrayList<Integer> pathList3;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        critterInRange = new HashSet<>();
        critter1 = new Critter(CritterType.CritterA);
        critter2 = new Critter(CritterType.CritterA);
        critter3 = new Critter(CritterType.CritterA);
        critterInRange.add(critter1);
        critterInRange.add(critter2);
        critterInRange.add(critter3);
        cells = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            cells.add(CellState.Path);
        }
        cells.add(0, CellState.Entrance);
        cells.add(19, CellState.Exit);

        gameMap = new GameMap(10, 15, cells, "myGame");
        CritterMovingBehavior critterMovingBehavior1 = new CritterMovingBehavior(gameMap, 10);
        CritterMovingBehavior critterMovingBehavior2 = new CritterMovingBehavior(gameMap, 10);
        CritterMovingBehavior critterMovingBehavior3 = new CritterMovingBehavior(gameMap, 10);

        critter1.setMovingBehavior(critterMovingBehavior1);
        critter2.setMovingBehavior(critterMovingBehavior2);
        critter3.setMovingBehavior(critterMovingBehavior3);

        for (int i = 1; i < 5; i++) {
            critter1.getMovingBehavior().getPathList().remove(i);
        }
        for (int i = 1; i < 4; i++) {
            critter2.getMovingBehavior().getPathList().remove(i);
        }
        for (int i = 1; i < 3; i++) {
            critter3.getMovingBehavior().getPathList().remove(i);
        }        
    }

    /**
     * Test method for {@link model.tower.shootingstrategy.TargetBasedOnNearest#targetOnCritters(java.util.Set, view.map.Position)}.
     */
    @Test
    public void testTargetOnCritters() {
        TargetBasedOnNearest targetBasedOnNearest = new TargetBasedOnNearest();
        Critter nearestCritter = targetBasedOnNearest.targetOnCritters(critterInRange, null);
        System.out.println(nearestCritter);
        System.out.println("1" + critter1);
        System.out.println("2" + critter2);
        System.out.println("3" + critter3);
        assertSame("who is the nearest to Exit",nearestCritter,critter1);
        assertNotSame("who is the nearest to Exit",nearestCritter,critter2);
        assertNotSame("who is the nearest to Exit",nearestCritter,critter3);
    }
}
