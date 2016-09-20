package view.critter;

import view.critter.CritterType;

import javax.swing.*;
import java.awt.*;

/**
 * defines critter image recourses 
 * @author yongpinggao 
 * @since 3/18/16.
 * @version 2.0
 */
public class CritterView {

    private String BASE_URL = "res/";
    private String PNG = ".png";
    private Image critterImage;
    private ImageIcon critterImageIcon;
    /**
     * A constructor for CritterView
     * @param critterType critter type 
     */
    public CritterView(CritterType critterType) {
        this.critterImageIcon = new ImageIcon(BASE_URL + critterType.getCritterImageName() + PNG);
        this.critterImage = critterImageIcon.getImage();
    }
    /**
     * getter for critter image 
     * @return an image for critter 
     */
    public Image getCritterImage() {
        return critterImage;
    }

}
