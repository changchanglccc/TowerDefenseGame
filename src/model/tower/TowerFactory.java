package model.tower;

import view.tower.TowerType;

/**
 * produce new tower instance base on TowerName.
 * This class apply singleton design.
 * @author yongpinggao 
 * @since 3/16/16.
 * @version 2.0
 */
public class TowerFactory {

    private static TowerFactory instance = new TowerFactory();
    /**
     * Returns the tower factory.
     * @return 
     */
    public static TowerFactory sharedInstance() {
        return instance;
    }
    /**
     * Constructor make TowerFactory Private.  
     */
    private TowerFactory() {}

    /**
     * Getter that returns a new Tower of the the specified type.
     * @param name tower name 
     */
    public Tower getTower(TowerType name) {
        switch (name) {
            case BurningTower1:
                return new BurningTower(1);
            case BurningTower2:
                return new BurningTower(2);
            case BurningTower3:
                return new BurningTower(3);
            case IceTower1:
                return new IceTower(1);
            case IceTower2:
                return new IceTower(2);
            case IceTower3:
                return new IceTower(3);
            case SplashTower1:
                return new SplashTower(1);
            case SplashTower2:
                return new SplashTower(2);
            case SplashTower3:
                return new SplashTower(3);
            default:
                return null;
        }
    }
}