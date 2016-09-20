
package testingUnit;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.critter.Critter;
import model.tower.shootingstrategy.TargetBasedOnStrongest;
import view.critter.CritterType;
import view.map.Position;

/**
 * Test target strategy, target on the strongest
 * @author LiChong
 * @since 4/4/2016
 * @version 1.3
 *
 */
public class TargetBasedOnStrongestTest {
    private Set<Critter> crittersInRange;
    private Critter critter1;
    private Critter critter2;
    private Critter critter3;
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        crittersInRange = new HashSet();
        critter1 = new Critter(CritterType.CritterA);
        critter2 = new Critter(CritterType.CritterB);
        critter3 = new Critter(CritterType.CritterC);
        crittersInRange.add(critter1);
        crittersInRange.add(critter2);
        crittersInRange.add(critter3);
    }

    /**
     * Test method for {@link model.tower.shootingstrategy.TargetBasedOnStrongest#targetOnCritters(java.util.Set, view.map.Position)}.
     */
    @Test
    public void testTargetOnCritters() {
        TargetBasedOnStrongest targetBasedOnStrongest = new TargetBasedOnStrongest();
        Critter strongestCritter = targetBasedOnStrongest.targetOnCritters(crittersInRange, new Position(10,10));
        assertTrue("find the strongest",strongestCritter.getCritterType() == critter3.getCritterType());

    }

}
