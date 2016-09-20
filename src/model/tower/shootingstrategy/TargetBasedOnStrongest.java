package model.tower.shootingstrategy;

import model.critter.Critter;
import view.map.Position;

import java.util.Iterator;
import java.util.Set;

/**
 * A specific strategy that define to target strongest critter.
 * TargetBasedOnStrongest class implements TowerShootingStrategy
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0  
 */
public class TargetBasedOnStrongest implements TowerShootingStrategy {
    /**
     * Overrides targetOnCritters
     * {@inheritDoc}
     * Constructor of targetOnCritter.
     * And target on strongest critter in towers attack range.
     */
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange, Position towerPosition) {
        if (crittersInRange.size() > 0) {
            Iterator<Critter> iterator = crittersInRange.iterator();
            Critter strongestCritter = iterator.next();
            while (iterator.hasNext()) {
                Critter c = iterator.next();
                if (c.getCurrentHealth() > strongestCritter.getCurrentHealth()) {
                    strongestCritter = c;
                }
            }
            return strongestCritter;
        } else {
            return null;
        }
    }
    /**
     * a method to return a specific string.
     */
    @Override
    public String toString() {
        return "Target On Strongest";
    }
}
