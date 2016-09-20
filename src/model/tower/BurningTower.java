package model.tower;

import model.tower.shootingstrategy.TowerShootingStrategy;
import view.map.Position;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;

/**
 * a class defines tower type burning 
 * @author yongpinggao
 * @since 3/13/16.
 * @version 2.0
 */
public class BurningTower extends Tower {
    private TowerShootingStrategy baseShootingStrategy;
    public BurningTower(int level) {
        if (level <= MAX_LEVEL) {
            this.level = level;
            initTower();
        }
    }
    /**
     * initialize buring tower type base on its level 
     */
    private void initTower() {
        this.specification = "<html>" + "Burning Tower" + "<br> Level: " + level + "<br> Good at attack normal creature</html>";
        int basePower = 5;
        int baseRateOfFire = 100;
        int baseBurningDamage = 5;
        
        switch (level) {
            case 1:
                buyPrice = 20.0;
                sellPrice = 10.0;
                towerType = TowerType.BurningTower1;
                range = 120;
                towerShootingBehavior = new BurningTowerShootingBehavior(
                    basePower, 
                    baseRateOfFire, 
                    baseBurningDamage
                );
                towerView = new TowerView(towerType);
                break;
            case 2:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerType = TowerType.BurningTower2;
                range = 150;
                baseShootingStrategy = towerShootingBehavior.getShootingStrategy();
                towerShootingBehavior = new BurningTowerShootingBehavior(
                    basePower * level, 
                    baseRateOfFire * level, 
                    baseBurningDamage * level
                );
                towerShootingBehavior.setShootingStrategy(baseShootingStrategy);
                towerView = new TowerView(towerType);
                break;
            case 3:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerType = TowerType.BurningTower3;
                range = 180;
                baseShootingStrategy = towerShootingBehavior.getShootingStrategy();
                towerShootingBehavior = new BurningTowerShootingBehavior(
                    basePower * level, 
                    baseRateOfFire * level, 
                    baseBurningDamage * (level + 1)
                );
                towerShootingBehavior.setShootingStrategy(baseShootingStrategy);
                towerView = new TowerView(towerType);
                break;
        }
    }

    /**
     * setter for tower level 
     * @param level tower level 
     * {@inheritDoc}
     */
    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        this.level = level;
        initTower();
        setPosition(position);
    }
    /**
     * set  tower shooting range base on tower level
     * @param position tower coordinate
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
        towerShootingRangeView = new TowerShootingRangeView(position, range);
        switch (level) {
            case 1:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 3));
                break;
            case 2:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 4));
                break;
            case 3:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 5));
                break;
        }
        
        towerShootingBehavior.setTowerDidShotDelegate(towerShootingView);
        towerShootingBehavior.setTowerPosition(this.getPosition());
    }
    /**
     * getter for tower image 
     * @return the tower image location 
     * {@inheritDoc}
     */
    @Override
    public String getHdImageName() {
        return "res/towerA_high.png";
    }
}
