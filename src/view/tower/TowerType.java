package view.tower;

/**
 * set constants for towers   
 * @author yongpinggao 
 * @since 3/19/16.
 * @version 1.0
 */
public enum TowerType {
    TowerNull(""),

    // Burning Tower
    BurningTower1("towerA_1"),
    BurningTower2("towerA_2"),
    BurningTower3("towerA_3"),

    // Ice Tower
    IceTower1("towerB_1"),
    IceTower2("towerB_2"),
    IceTower3("towerB_3"),

    // Splash Tower
    SplashTower1("towerC_1"),
    SplashTower2("towerC_2"),
    SplashTower3("towerC_3");


    private String towerImageName;
    /**
     * A constructor for TowerType 
     * @param towerImageName tower name 
     */
    TowerType(String towerImageName) {
        this.towerImageName = towerImageName;
    }
    /**
     * getter for tower name 
     * @return tower name 
     */
    public String getTowerImageName() {
        return towerImageName;
    }
}
