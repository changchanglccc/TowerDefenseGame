package model.wave;

import model.critter.*;
import view.critter.CritterType;
/**
 * WaveBuild class
 * to build wave of critters
 * 
 * @author yongpinggao
 * @since 3/13/16.
 * @version 2.0 
 */
public class WaveBuilder {
    private int waveNum;
    private final int  icreacingSpeedRation = 2;
    private final int  increasingHealthRation = 4;
    private CritterCollection critterCollection = new CritterCollection();

    /**
     * Constructor of WaveBuilder.
     * @param waveNum it represents the wave order
     */
    public WaveBuilder(int waveNum) {
        this.waveNum = waveNum;
        if (critterCollection != null) critterCollection.clearAllCritters();
    }

    /**
     * Build critterA in wave.
     * @param num the number of critterA
     * @return
     */
    public WaveBuilder critterA(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterA);
            if (waveNum > 1 && waveNum <= 6 ) {
                critter.setCurrentHealth(critter.getCurrentHealth() + (waveNum *increasingHealthRation));
                critter.setMovingSpeed(critter.getMovingSpeed()+ waveNum + icreacingSpeedRation);
            }
            critterCollection.addCritter(critter);
        }
        return this;
    }

    /**
     * Build critterB in wave.
     * @param num the number of critterB
     * @return
     */
    public WaveBuilder critterB(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterB);
            if (waveNum > 1 && waveNum <= 6 ) {
                critter.setCurrentHealth(critter.getCurrentHealth() + (waveNum *increasingHealthRation));
                critter.setMovingSpeed(critter.getMovingSpeed()+ waveNum + icreacingSpeedRation);
            }
            critterCollection.addCritter(critter);
        }
        return this;
    }

    /**
     * Build critterC in wave.
     * @param num the number of critterC
     * @return
     */
    public WaveBuilder critterC(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterC);
            if (waveNum > 1 && waveNum <= 6 ) {
                critter.setCurrentHealth(critter.getCurrentHealth() + (waveNum *increasingHealthRation));
                critter.setMovingSpeed(critter.getMovingSpeed()+ waveNum + icreacingSpeedRation);
            }
            critterCollection.addCritter(critter);
        }
        return this;
    }

    /**
     * Build critterD in wave.
     * @param num the number of critterD
     * @return
     */
    public WaveBuilder critterD(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterD);
            if (waveNum > 1 && waveNum <= 6 ) {
                critter.setCurrentHealth(critter.getCurrentHealth() + (waveNum *increasingHealthRation));
                critter.setMovingSpeed(critter.getMovingSpeed()+ waveNum + icreacingSpeedRation);
            }
            critterCollection.addCritter(critter);
        }
        return this;
    }
    
    /**
     * Get critter collection
     * @return a collection which gather critters.
     */
    public CritterCollection getCritterCollection() {
        return critterCollection;
    }
}