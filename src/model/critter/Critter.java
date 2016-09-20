package model.critter;

import view.critter.CritterType;
import view.critter.CritterView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Critter implements ActionListener interface
 * @author yongpinggao
 * @version 1.0
 * @since 3/13/16
 */
public class Critter implements ActionListener {
    private CritterType critterType;
    private CritterView critterView;
    private CritterMovingBehavior movingBehavior;
    private int currentHealth;
    private int maxHealth;
    private double worth;
    private int movingSpeed;

    private int damage;
    private Timer innerTimer;
    private Timer specicalEffectTimer = new Timer(0, this);
    
    private boolean isVisible;
    private boolean isKilled;
    private boolean isDonated;
    
    /**
     * Donation for killing a critter.
     * @return a boolean value that represents whether players get the donation.
     */
    public boolean isDonated() {
        return isDonated;
    }

    /**
     * set donation value for killing a critter.
     * @param donated donation value
     */
    public void setDonated(boolean donated) {
        isDonated = donated;
    }

    /**
     * set damage value.
     * @param damage damage value
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Constructor of Critter. 
     * @param critterType 
     */
    public Critter(CritterType critterType) {
        this.setCritterType(critterType);
        this.critterView = new CritterView(critterType);
        switch (critterType) {
            case CritterA:
                initCritterA();
                break;
            case CritterB:
                initCritterB();
                break;
            case CritterC:
                initCritterC();
                break;
            case CritterD:
                initCritterD();
        }
    }

    /**
     * Initialize CritterA
     */
    private void initCritterA() {
        maxHealth = 100;
        currentHealth = maxHealth;
        worth = 20;
        movingSpeed = 20;
    }

    /**
     * Initialize CritterB
     */
    private void initCritterB() {
        maxHealth = 60;
        currentHealth = maxHealth;
        worth = 30;
        movingSpeed = 40;
    }

    /**
     * Initialize CritterC
     */
    private void initCritterC() {
        maxHealth = 200;
        currentHealth = maxHealth;
        worth = 40;
        movingSpeed = 10;
    }

    /**
     * Initialize CritterD
     */
    private void initCritterD() {
        maxHealth = 400;
        currentHealth = maxHealth;
        worth = 50;
        movingSpeed = 10;
    }

    /**
     * Judge whether the critter has been killed.
     * @return A boolean value represents whether the critter has been killed.
     */
    public boolean isKilled() {
        return isKilled;
    }

    /**
     * Set kill value.
     * @param killed
     */
    public void setKilled(boolean killed) {
        isKilled = killed;
        if (killed) {
            movingBehavior.getMovingTimer().stop();
        }
    }

    /**
     * Worth for a critter
     * @return critter's worth
     */
    public double getWorth() {
        return worth;
    }

    /**
     * Get critter's health bar length.
     * @return length
     */
    public float getHealthBarLength() {
        return (float)(currentHealth) / maxHealth;
    }

    /**
     * Get critter's moving behavior.
     * @return movingBehavior
     */
    public CritterMovingBehavior getMovingBehavior() {
        return movingBehavior;
    }

    /**
     * Set critter's moving behavior.
     * @param movingBehavior The parameter represents moving behavior
     */
    public void setMovingBehavior(CritterMovingBehavior movingBehavior) {
        this.movingBehavior = movingBehavior;
    }

    /**
     * Get moving speed.
     * @return moving speed
     */
    public int getMovingSpeed() {
        return movingSpeed;
    }

    /**
     * Set moving speed.
     * @param movingSpeed it represents moving speed
     */
    public void setMovingSpeed(int movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    /**
     * Get critter's current health value.
     * @return current health value
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Set current health value of critter.
     * @param currentHealth it represents critter's health
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Decide whether the critter is visible after creating.
     * @return a boolean value means whether is visible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Set visible critter.
     * @param visible a boolean value means whether is visible
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Get critter view.
     * @return critterView
     */
    public CritterView getCritterView() {
        return critterView;
    }

    /**
     * Get critter inner timer.
     * @return
     */
    public Timer getInnerTimer() {
        return innerTimer;
    }

    /**
     * Set critter's inner timer.
     * @param innerTimer it represents a timer of critter itself
     */
    public void setInnerTimer(Timer innerTimer) {
        this.innerTimer = innerTimer;
    }

    /**
     * Get special effect timer.
     * @return specicalEffectTimer
     */
    public Timer getSpecicalEffectTimer() {
        return specicalEffectTimer;
    }

    /**
     * Set special effect timer.
     * @param specicalEffectTimer it represents a timer of special effects
     */
    public void setSpecicalEffectTimer(Timer specicalEffectTimer) {
        this.specicalEffectTimer = specicalEffectTimer;
    }

    /**
     * For event e, perform actions, different tower have to do various action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (e.getActionCommand().equals("ICE_TOWER")) {
                movingBehavior.getMovingTimer().start();
                specicalEffectTimer.stop();
            }
            if (e.getActionCommand().equals("BURNING_TOWER")) {
                specicalEffectTimer.stop();
                innerTimer.stop();
            }
        } else {
            currentHealth -= damage;
        }
    }

    /**
     * Get critter type.
     * @return critterType
     */
    public CritterType getCritterType() {
        return critterType;
    }

    /**
     * Set critter type.
     * @param critterType it represents critter's type
     */
    public void setCritterType(CritterType critterType) {
        this.critterType = critterType;
    }
}

