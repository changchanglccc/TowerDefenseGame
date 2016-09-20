package view.tower;

import javax.swing.*;
import java.awt.*;

/**
 * a class for tower view 
 *@author yongpinggao 
 *@since 3/19/16.
 *@version 2.0
 */
public class TowerView {

    private String BASE_URL = "res/";
    private String PNG = ".png";
    private Image towerImage;

    /**
     * A constructor for TowerView
     * @param towerType tower type 
     */
    public TowerView(TowerType towerType) {
        this.towerImage = new ImageIcon(BASE_URL + towerType.getTowerImageName() + PNG).getImage();
    }
    /**
     * getter for tower image 
     * @return tower image 
     */
    public Image getTowerImage() {
        return towerImage;
    }
}
