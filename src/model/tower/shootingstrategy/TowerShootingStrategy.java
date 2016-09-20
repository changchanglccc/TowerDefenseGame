package model.tower.shootingstrategy;

import model.critter.Critter;
import view.map.Position;

import java.util.Set;

/**
 * An interface that define shooting strategy of tower
 * @author yongpinggao 
 * @since 3/16/16.
 * @version 2.0  
 */
public interface TowerShootingStrategy {
     Critter targetOnCritters(Set<Critter> crittersInRange, Position towerPosition);
}
