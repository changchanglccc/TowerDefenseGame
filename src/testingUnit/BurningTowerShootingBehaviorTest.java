
package testingUnit;

import static org.junit.Assert.*;

import javax.swing.Timer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.critter.Critter;
import model.tower.BurningTower;
import model.tower.BurningTowerShootingBehavior;
import model.tower.TowerShootingBehavior;
import view.critter.CritterType;

/**
 * Test burning tower shooting behavior
 * @author LiChong
 * @since 3/4/2016
 * @version 1.1
 *
 */
public class BurningTowerShootingBehaviorTest {
    private BurningTower burningTower;
    private BurningTowerShootingBehavior burningTowerShootingBehavior;
    private Critter critter;
    /**
     * Set up a new burning tower whose level is 1.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        critter = new Critter(CritterType.CritterA);
        burningTower = new BurningTower(1);
        burningTowerShootingBehavior = new BurningTowerShootingBehavior(5, 100, 5);
        burningTowerShootingBehavior.getCrittersInRange().add(critter);
    }

    /**
     * Test method for {@link model.tower.BurningTowerShootingBehavior#shoot()}.
     */
    @Test
    public void testShoot() {
        burningTowerShootingBehavior.shoot();
        assertTrue( "burningTimer works", critter.getInnerTimer().isRunning());
        assertTrue("losingHealthTimer works", critter.getSpecicalEffectTimer().isRunning());
    }

    /**
     * Test method for {@link model.tower.BurningTowerShootingBehavior#BurningTowerShootingBehavior(int, int, int)}.
     */
    @Test
    public void testBurningTowerShootingBehavior() {
        BurningTowerShootingBehavior btsb = new BurningTowerShootingBehavior(5, 100, 5);
        assertEquals("power equal",5,btsb.getPower());
        assertEquals("rateOfFire equal",100,btsb.getRateOfFire());
        assertEquals("burningDamage equal",5,btsb.getBurningDamage());
    }

}
