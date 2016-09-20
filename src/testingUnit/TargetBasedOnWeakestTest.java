
package testingUnit;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import model.critter.Critter;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import view.critter.CritterType;
import view.map.Position;

/**
 * Test target strategy, target on the weakest
 * @author LiChong
 * @since 4/4/2016
 * @version 1.3
 *
 */
public class TargetBasedOnWeakestTest {
    private Set<Critter> crittersInRange;
    private Critter critter1;
    private Critter critter2;
    private Critter critter3;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        critter1 = new Critter(CritterType.CritterA);
        critter2 = new Critter(CritterType.CritterB);
        critter3 = new Critter(CritterType.CritterC);
        crittersInRange = new HashSet();
        crittersInRange.add(critter1);
        crittersInRange.add(critter2);
        crittersInRange.add(critter3);
    }

    /**
     * Test method for {@link model.tower.shootingstrategy.TargetBasedOnWeakest#targetOnCritters(java.util.Set, view.map.Position)}.
     */
    @Test
    public void testTargetOnCritters() {
        TargetBasedOnWeakest targetBasedOnWeakest = new TargetBasedOnWeakest();
        Critter weakestCritter = targetBasedOnWeakest.targetOnCritters(crittersInRange, new Position(10,10));
        assertTrue("find the weakest",weakestCritter.getCritterType() == critter2.getCritterType());
    }

}
