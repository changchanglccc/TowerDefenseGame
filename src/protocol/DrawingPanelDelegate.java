package protocol;

import model.tower.Tower;

/**
 * interface for refeshing the tower view 
 * @author yongpinggao 
 * @since 3/13/16.
 * @version 1.0 
 */
public interface DrawingPanelDelegate {

    /**
     * refresh tower base on the provided tower 
     * @param tower tower 
     */
    void reloadPanelBasedOnTower(Tower tower);
    
    /**
     * refresh tower in specific coordinate 
     * @param index  tower coordinate 
     */
    void reloadLogPanelBasedOnIndexOfTower(int index);
}
