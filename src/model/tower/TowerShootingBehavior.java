package model.tower;

import java.util.Set;

import javax.swing.Timer;

import model.critter.Critter;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import model.tower.shootingstrategy.TowerShootingStrategy;
import protocol.TowerDidShotDelegate;
import view.map.Position;

/**
 * a model for all towers shooting behavior 
 *@author yongpinggao 
 *@since 3/19/16.
 *@version 2.0
 */
public class TowerShootingBehavior {
    // tower shooting behavior
    protected int power;
    protected int rateOfFire;
    protected TowerShootingStrategy shootingStrategy = new TargetBasedOnWeakest();
    protected boolean isShooting;
    protected boolean timeToShoot;

    protected Timer towerTimer;

    protected Set<Critter> crittersInRange;

    protected TowerDidShotDelegate towerDidShotDelegate;
    private Position towerPosition;

    /**
     * get tower power 
     * @return tower power 
     */
    public int getPower() {
        return power;
    }

    /**
     * get tower rate of fire 
     * @return tower rate of fire
     */
    public int getRateOfFire() {
        return rateOfFire;
    }

    /**
     * set critter in tower range 
     * @param crittersInRange critter in tower range 
     */
    public void setCrittersInRange(Set<Critter> crittersInRange) {
        this.crittersInRange = crittersInRange;
    }

    /**
     * get the critters in tower range 
     * @return critters in tower range 
     */
    public Set<Critter> getCrittersInRange() {
        return crittersInRange;
    }

    /**
     * get whether tower shooting or not 
     * @return whether tower shooting or not 
     */
    public boolean isShooting() {
        return isShooting;
    }

    /**
     * set whether tower shooting or not 
     * @param shooting  whether tower shooting or not
     */
    public void setShooting(boolean shooting) {
        if (shooting) {
            towerTimer.start();
        } else {
            towerTimer.stop();
        }
        isShooting = shooting;
    }

    /**
     * get whether is time for the tower to shoot or not
     * @return  whether time to shoot or not
     */
    public boolean isTimeToShoot() {
        return timeToShoot;
    }

    /**
     * set whether is time for the tower to shoot or not
     * @param timeToShoot whether is time for the tower to shoot or not
     */
    public void setTimeToShoot(boolean timeToShoot) {
        this.timeToShoot = timeToShoot;
    }

    /**
     * set tower TowerDidShotDelegate observer 
     * @param towerDidShotDelegate  observer
     */
    public void setTowerDidShotDelegate(TowerDidShotDelegate towerDidShotDelegate) {
        this.towerDidShotDelegate = towerDidShotDelegate;
    }

    /**
     * get tower shooting strategy
     * @return tower strategy 
     */
    public TowerShootingStrategy getShootingStrategy() {
        return shootingStrategy;
    }

    /**
     * set tower shooting strategy
     * @param shootingStrategy
     */
    public void setShootingStrategy(TowerShootingStrategy shootingStrategy) {
        this.shootingStrategy = shootingStrategy;
    }

    /**
     * defines shoot methood for all towers 
     */
    public void shoot() {}

    /**
     * set tower position 
     * @param towerPosition tower position 
     */
    public void setTowerPosition(Position towerPosition) {
        this.towerPosition = towerPosition;
    }
    /**
     * get tower position 
     * @return tower position 
     */
    public Position getTowerPosition() {
        return this.towerPosition;
    }
}