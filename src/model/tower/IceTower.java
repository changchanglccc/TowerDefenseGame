package model.tower;
import model.tower.shootingstrategy.TowerShootingStrategy;
import view.map.Position;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;


/**
 * class defins tower of type ice tower
 *@author yongpinggao 
 *@since 3/15/16.
 *@version 2.0
 */
// Freezing the critter for a time (have a higher priority to poison tower)
public class IceTower extends Tower {
    private TowerShootingStrategy baseShootingStrategy;
    public IceTower(int level) {
        if (level <= MAX_LEVEL) {
            this.level = level;
            initTower();
        }
    }

    /**
     * initialize the tower base on its level 
     */
    private void initTower() {
        this.specification = "<html>" + "Ice Tower" + "<br> Level: " + level + "<br> Good at attack fast creatures with its freezing effect</html>";
        int baseFrozenTime = 1000;
        int baseRateOfFire = 10;

        switch (level) {
            case 1:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerType = TowerType.IceTower1;
                range = 110;
                towerShootingBehavior = new IceTowerShootingBehavior(
                    baseFrozenTime,
                    baseRateOfFire
                );
                towerView = new TowerView(towerType);
                break;
            case 2:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerType = TowerType.IceTower2;
                range = 120;
                baseShootingStrategy = towerShootingBehavior.getShootingStrategy();
                towerShootingBehavior = new IceTowerShootingBehavior(
                    baseFrozenTime * Double.valueOf(level * 0.75).intValue(),
                    baseRateOfFire * level
                );
                towerShootingBehavior.setShootingStrategy(baseShootingStrategy);
                towerView = new TowerView(towerType);
                break;
            case 3:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerType = TowerType.IceTower3;
                range = 130;
                baseShootingStrategy = towerShootingBehavior.getShootingStrategy();
                towerShootingBehavior = new IceTowerShootingBehavior(
                    baseFrozenTime * Double.valueOf(level * 0.66).intValue(), 
                    baseRateOfFire * level
                );
                towerShootingBehavior.setShootingStrategy(baseShootingStrategy);
                towerView = new TowerView(towerType);
                break;
        }
    }

    /**
     * getter for tower image 
     * @return the tower image location 
     * {@inheritDoc}
     */
    @Override
    public String getHdImageName() {
        return "res/towerB_high.png";
    }

    /**
     * set the tower level 
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
     * set tower shooting range base on tower level 
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
        towerShootingRangeView = new TowerShootingRangeView(position, range);
        switch (level) {
            case 1:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 3));
                break;
            case 2:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 4));
                break;
            case 3:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 5));
                break;
        }
        towerShootingBehavior.setTowerDidShotDelegate(towerShootingView);
        towerShootingBehavior.setTowerPosition(this.getPosition());
    }
}