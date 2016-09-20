package model.tower;


import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;

import java.awt.*;
import java.util.HashMap;

/**
 * This class define collection of tower.
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0  
 */
public class TowerCollection {


    private HashMap<Integer, Tower> towers;

    /**
     * Constructor of TowerCollection
     */
    public TowerCollection() {
        this.towers = new HashMap<>();
    }

    /**
     * Get all towers
     * @return all tower on the map 
     */
    public HashMap<Integer, Tower> getTowers() {
        return towers;
    }

    /**
     * Using index to add tower into map
     * @param index represents map position
     * @param tower represents tower
     */
    public void setTowers(HashMap<Integer, Tower> towers) {
        this.towers = towers;
    }

    /**
     * Using index to add tower into map
     * @param index represents map position
     * @param tower represents tower
     */
    public void addTowerAtIndex(int index, Tower tower) {
        towers.put(index, tower);
    }
    
    /**
     * Remove tower by index
     * @param index represents tower position
     */
    public void removeTowerAtIndex(int index) {
        towers.remove(index);
    }
}