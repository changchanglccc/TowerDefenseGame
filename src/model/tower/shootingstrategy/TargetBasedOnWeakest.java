package model.tower.shootingstrategy;

import model.critter.Critter;
import view.map.Position;

import java.util.Iterator;
import java.util.Set;

/**
 * A specific strategy that define to target weakest critter.
 * TargetBasedOnWeakest class implements TowerShootingStrategy
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0  
 */
public class TargetBasedOnWeakest implements TowerShootingStrategy {
    /**
     * Overrides targetOnCritters
     * {@inheritDoc}
     * Constructor of targetOnCritter.
     * And target on weakest critter in towers attack range.
     */
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange, Position towerPosition) {
        if (crittersInRange.size() > 0) {
            Iterator<Critter> iterator = crittersInRange.iterator();
            Critter weakestCritter = iterator.next();
            while (iterator.hasNext()) {
                Critter c = iterator.next();
                if (c.getCurrentHealth() < weakestCritter.getCurrentHealth()) {
                    weakestCritter = c;
                }
            }
            return weakestCritter;
        } else {
            return null;
        }
    }
    /**
     * a method to return a specific string.
     */
    @Override
    public String toString() {
        return "Target On Weakest";
    }
}
