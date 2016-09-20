package model.tower.shootingstrategy;

import model.critter.Critter;
import view.map.Position;

import java.util.Iterator;
import java.util.Set;

/**
 * Target strategy based on closest critter to tower
 * @author yongpinggao
 * @since 3/14/16.
 * @version 1.0 
 */
public class TargetBasedOnNearest implements TowerShootingStrategy {
    /**
     * Overrides targetOnCritters
     * {@inheritDoc}
     * Constructor of targetOnCritter.
     * And target on nearest critter to tower in tower attack range.
     */
    @Override
    public Critter targetOnCritters(Set<Critter> crittersInRange, Position towerPosition) {

        if (crittersInRange.size() > 0) {
            Iterator<Critter> iterator = crittersInRange.iterator();
            Critter nearestCritter = iterator.next();

            while (iterator.hasNext()) {
                Critter c = iterator.next();
                if (c.getMovingBehavior().getPathList().size() < nearestCritter.getMovingBehavior().getPathList().size()) {
                    nearestCritter = c;
                }
            }
            return nearestCritter;
        }
        return null;
    }
    /**
     * a method to return a specific string.
     */
    @Override
    public String toString() {
        return "Target On Nearest to End";
    }
}


