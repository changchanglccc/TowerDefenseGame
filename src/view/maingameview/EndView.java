package view.maingameview;

import java.awt.*;
import javax.swing.*;

/**
 * Class for the tower management on the right side of the game view
 * @author yongpinggao
 * @version  1.0 3/13/16.
 * @version 2.0
 */
public class EndView extends JPanel {

    public TowerSpecificationPanel towerSpecificationPanel;
    public TowerUpgradeSellPanel towerUpgradeSellPanel;

    /**
     * Constructor method starts and attaches buttons to the panel.
     */
    public EndView() {
        setLayout(new GridLayout(2,1));
        towerUpgradeSellPanel = new TowerUpgradeSellPanel();
        towerSpecificationPanel = new TowerSpecificationPanel();
        add(towerUpgradeSellPanel);
        add(towerSpecificationPanel);
    }
}
