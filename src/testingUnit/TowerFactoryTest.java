package testingUnit;

import org.junit.Test;

import model.tower.Tower;
import model.tower.TowerFactory;
import view.tower.TowerType;

import static org.junit.Assert.*;

/**
 * Test the TowerFactory class 
 * 
 * @author m_lzahra
 * @version 1.0
 * @since 15/2/2016
 */
public class TowerFactoryTest {
    Tower tower ;

    /**
     * Test getting instance function return the wanted tower instance.
     */
    @Test
    public void getTowerTest() {
        tower = TowerFactory.sharedInstance().getTower(TowerType.BurningTower1);
        assertTrue(tower.getClass().getName().equals("model.tower.BurningTower")); 

    }
}