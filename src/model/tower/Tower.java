package model.tower;

import view.map.Position;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;



/**
 * A model that define the all tower parameters.
 * Tower class implements TowerShootingBehavior and  ActionListener
 * @author yongpinggao
 * @see TowerShootingBehavior 
 * @since 3/16/16.
 * @version 2.0  
 */
public class Tower {

    public final static int MAX_LEVEL = 3;

    // tower normal attributes
    protected int level;
    protected TowerType towerType;
    protected String specification;
    protected double buyPrice;
    protected double sellPrice;
    protected int range;

    protected String hdImageName;

    protected TowerShootingBehavior towerShootingBehavior;

    // tower shooting range attributes
    protected Position position;

    // View
    protected TowerView towerView;
    protected TowerShootingView towerShootingView;
    protected TowerShootingRangeView towerShootingRangeView;


    /**
     * set tower level 
     * @param level tower level 
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * set tower range 
     * @param range tower range 
     */
    public void setRange(int range) {
        this.range = range;
    }
    /**
     * set tower position 
     * @param position tower coordinate 
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * get tower position 
     * @return tower position 
     */
    public Position getPosition() {
        return position;
    }

    /**
     * get tower view 
     * @return tower view
     */
    public TowerView getTowerView() {
        return towerView;
    }

    /**
     * get tower range view 
     * @return tower range view
     */
    public TowerShootingRangeView getTowerShootingRangeView() {
        return towerShootingRangeView;
    }

    /**
     * get tower specification
     * @return tower specification
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * get tower buy price 
     * @return tower buy price
     */
    public double getBuyPrice() {
        return buyPrice;
    }

    /**
     * get tower selling price 
     * @return tower selling price 
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * get tower shooting behavior 
     * @return tower shooting behavior
     */
    public TowerShootingBehavior getTowerShootingBehavior() {
        return towerShootingBehavior;
    }

    /**
     * get tower image name 
     * @return
     */
    public String getHdImageName() {
        return hdImageName;
    }
    
    /**
     * get tower level 
     * @return tower level 
     */
    public int getLevel() {
        return level;
    }

    /**
     * get tower range 
     * @return
     */
    public int getRange() {
        return range;
    }

    /**
     * get the tower shooting view 
     * @return tower shooting view 
     */
    public TowerShootingView getTowerShootingView() {
        return towerShootingView;
    }
    /**
     * get tower type 
     * @return tower type 
     */
    public TowerType getTowerType() {
        return towerType;
    }
}
