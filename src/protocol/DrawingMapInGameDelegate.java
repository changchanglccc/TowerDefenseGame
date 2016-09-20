package protocol;

import model.critter.CritterCollection;
import model.map.GameMap;
import model.tower.TowerCollection;


/**
 * interface for refeshing map and critter on map
 *@author yongpinggao 
 *@since 3/14/16.
 *@version 1.0
 */
// for map in the game(towers, critters, etc.)
public interface DrawingMapInGameDelegate extends DrawingMapDelegate {

    /**
     * refreshing map  and towers
     * @param map game map 
     * @param towerCollection  all towers on the map
     */
    void refreshMap(GameMap map, TowerCollection towerCollection);
    
    /**
     * refreshing critters
     * @param critterCollection  all critter on map 
     */
    void refreshCrittersInMap(CritterCollection critterCollection);
}
