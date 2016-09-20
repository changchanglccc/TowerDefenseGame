package model.tower;

import model.critter.Critter;
import model.tower.shootingstrategy.TowerShootingStrategy;
import view.map.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * class defines burning tower shooting behavior 
 * @author yongpinggao 
 * @since 3/24/16.
 * @version 2.0
 */
public class BurningTowerShootingBehavior extends TowerShootingBehavior {
    private int burningDamage;

    /**
     * A constructor for BurningTowerShootingBehavior
     * @param power tower power 
     * @param rateOfFire  tower rate of fire 
     * @param burningDamage  amount of damage cause by tower 
     */
    public BurningTowerShootingBehavior(int power, int rateOfFire, int burningDamage) {
        this.power = power;
        this.rateOfFire = rateOfFire;
        this.setBurningDamage(burningDamage);
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
        Critter critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange, this.getTowerPosition());

        if (critterUnderAttack != null && !critterUnderAttack.isKilled()) {
            if (towerDidShotDelegate != null) {
                towerDidShotDelegate.towerDidShotAt(critterUnderAttack.getMovingBehavior().getCurrentPosition());
            }
            int health = critterUnderAttack.getCurrentHealth();
            health -= power;
            critterUnderAttack.setCurrentHealth(health);

            Timer burningTimer = new Timer(0, critterUnderAttack);
            burningTimer.setInitialDelay(1000);
            burningTimer.setRepeats(false);
            burningTimer.setActionCommand("BURNING_TOWER");
            critterUnderAttack.setSpecicalEffectTimer(burningTimer);
            critterUnderAttack.getSpecicalEffectTimer().start();

            Timer losingHealthTimer = new Timer(200, critterUnderAttack);
            critterUnderAttack.setDamage(getBurningDamage());
            critterUnderAttack.setInnerTimer(losingHealthTimer);
            critterUnderAttack.getInnerTimer().start();

        } else {
            towerDidShotDelegate.towerDidShotAt(null);
        }
        if (critterUnderAttack.getCurrentHealth() <= 0) {
            crittersInRange.remove(critterUnderAttack);
        }
    }

    /**
     * getter for tower effect on critter
     * @return the amount of damage cause by the tower 
     */
    public int getBurningDamage() {
        return burningDamage;
    }

    /**
     * set amount of damge for the tower 
     * @param burningDamage amount of damge for the tower  
     */
    public void setBurningDamage(int burningDamage) {
        this.burningDamage = burningDamage;
    }
}
