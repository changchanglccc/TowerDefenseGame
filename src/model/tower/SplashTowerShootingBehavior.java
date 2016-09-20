package model.tower;

import model.critter.Critter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;


/**
 * a class defines splash tower shooting behavior 
 * @author yongpinggao
 * @since 3/24/16.
 * @version 2.0
 */
public class SplashTowerShootingBehavior extends TowerShootingBehavior {

    /**
     * A constructor for  SplashTowerShootingBehavior
     * @param power tower power 
     * @param rateOfFire tower rate of fire 
     */
    public SplashTowerShootingBehavior(int power, int rateOfFire) {
        isShooting = true;
        this.power = power;
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
        // to avoid ConcurrentModificationException for removing item during iteration
        HashSet<Critter> killedCritters = new HashSet<>();

        if (!crittersInRange.isEmpty()) {
            for (Critter c: crittersInRange) {
                int health = c.getCurrentHealth();
                health -= power;
                c.setCurrentHealth(health);

                if (c.getCurrentHealth() <= 0) {
                    killedCritters.add(c);
                }
            }
            crittersInRange.removeAll(killedCritters);
        }
    }
}
