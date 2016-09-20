package view.critter;

/**
 * set constants for critters  
 * @author yongpinggao 
 * @since 3/19/16.
 * @version 1.0
 */
public enum CritterType {
    CritterA("critterA"),
    CritterB("critterB"),
    CritterC("critterC"),
    CritterD("critterD");

    private String critterImageName;
    /**
     * Constructor for CritterType
     * @param critterName critter name 
     */
    CritterType(String critterName) {
        this.critterImageName = critterName;
    }
    /**
     * getter for critter constant
     * @return critter constant
     */
    public String getCritterImageName() {
        return critterImageName;
    }
}
