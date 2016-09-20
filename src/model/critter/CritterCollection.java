package model.critter;

import java.util.ArrayList;
/**
 * CritterCollection
 * @author yongpinggao
 * @version 1.0
 * @since 3/13/16
 */
public class CritterCollection {
    private ArrayList<Critter> critters = new ArrayList<>();
    /**
     * Add critters.
     * @param critter it represents the added critter
     */
    public void addCritter(Critter critter) {
        critters.add(critter);
    }
    /**
     * Remove critter.
     * @param critter it represents which critter will be removed
     */
    public void removeCritter(Critter critter) {
        critters.remove(critter);
    }
    /**
     * Clear all critters.
     */
    public void clearAllCritters() {
        critters.clear();
    }
    /**
     * Get critters
     * @return critters represents whose critters in game.
     */
    public ArrayList<Critter> getCritters() {
        return critters;
    }

}
