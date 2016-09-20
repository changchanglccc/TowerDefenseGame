package model.tower;

import model.critter.Critter;
import model.tower.shootingstrategy.TowerShootingStrategy;
import view.map.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * a class defines ice tower shooting behavior 
 * @author yongpinggao
 * @since 3/24/16.
 * @version 2.0
 */
public class IceTowerShootingBehavior extends TowerShootingBehavior {

    private int frozenTime;

    /**
     * A constructor for IceTowerShootingBehavior 
     * @param frozenTime the frozen time 
     * @param rateOfFire the rate of fire 
     */
    public IceTowerShootingBehavior(int frozenTime, int rateOfFire) {
        this.setFrozenTime(frozenTime);
        this.rateOfFire = rateOfFire;

        crittersInRange = new HashSet<>();
        towerTimer = new Timer(1000 - rateOfFire, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeToShoot = true;
            }
        });
    }
    
    /**
     * defines the shooting effect on critter when tower shoot it.
     */
    public void shoot() {
        super.shoot();
        Critter critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange, this.getTowerPosition());
        if (critterUnderAttack != null && !critterUnderAttack.getSpecicalEffectTimer().isRunning() && !critterUnderAttack.isKilled()) {
            towerDidShotDelegate.towerDidShotAt(critterUnderAttack.getMovingBehavior().getCurrentPosition());
            critterUnderAttack.getMovingBehavior().getMovingTimer().stop();
            Timer frozenTimer = new Timer(0, critterUnderAttack);
            frozenTimer.setInitialDelay(getFrozenTime());
            frozenTimer.setRepeats(false);
            frozenTimer.setActionCommand("ICE_TOWER");
            critterUnderAttack.setSpecicalEffectTimer(frozenTimer);
            critterUnderAttack.getSpecicalEffectTimer().start();
        } else towerDidShotDelegate.towerDidShotAt(null);

        if (critterUnderAttack.getCurrentHealth() <= 0) {
            crittersInRange.remove(critterUnderAttack);
        }
    }

    /**
     * get tower freezing time   
     * @return freezing time 
     */
    public int getFrozenTime() {
        return frozenTime;
    }

    /**
     * set tower freezing time   
     * @param frozenTime freezing time 
     */
    public void setFrozenTime(int frozenTime) {
        this.frozenTime = frozenTime;
    }
}
