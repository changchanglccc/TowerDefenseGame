package model.tower.shootingstrategy;

import model.critter.Critter;
import view.map.Position;

import java.util.Iterator;
import java.util.Set;

/**
 * Target strategy based on closest critter to tower
 * @author yongpinggao
 * @since 3/25/16.
 * @version 2.0 
 */
public class TargetBasedOnClosestToTower implements TowerShootingStrategy {
    /**
     * Overrides targetOnCritters
     * {@inheritDoc}
     * Constructor of targetOnCritter.
     * And target on closest critter to tower in tower attack range.
     */
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange, Position towerPosition) {   
        if (!crittersInRange.isEmpty()) {
            Iterator<Critter> iterator = crittersInRange.iterator();
            Critter closestCritter = iterator.next();
            while (iterator.hasNext()) {
                Critter c = iterator.next();            
                if (
                    c.getMovingBehavior().getCurrentPosition().distanceTo(towerPosition.getCenterPosition()) 
                    <
                    closestCritter.getMovingBehavior().getCurrentPosition().distanceTo(towerPosition.getCenterPosition())
                ) {
                    closestCritter = c;
                }
            }
            return closestCritter;
        }
        return null;
    }
    /**
     * a method to return a specific string.
     */
    @Override
    public String toString() {
        return "Target On Closest to Tower";
    }
}
