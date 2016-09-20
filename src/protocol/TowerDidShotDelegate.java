package protocol;

import view.map.Position;

/**
 * interface for refreshing tower shooting 
 *@author yongpinggao 
 *@since 3/20/16.
 *@version 1.0 
 */
public interface TowerDidShotDelegate {
	
    /**
     * refreshing tower shooting 
     * @param critterPosition critter coordinate 
     */
    void towerDidShotAt(Position critterPosition);
}
