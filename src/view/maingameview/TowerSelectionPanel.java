package view.maingameview;

import model.tower.TowerFactory;
import view.tower.TowerType;

import javax.swing.*;
import java.awt.*;

/**
 * class for towers selection view  
 * @author yongpinggao 
 * @since 3/13/16.
 * @version 2.0
 */
public class TowerSelectionPanel extends JPanel {

    public JButton towerAButton;
    public JButton towerBButton;
    public JButton towerCButton;
    /**
     * A constructor  for towers selection view 
     */
    public TowerSelectionPanel() {
        towerAButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.BurningTower1).getHdImageName()));
        towerBButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.IceTower1).getHdImageName()));
        towerCButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.SplashTower1).getHdImageName()));
        setLayout(new GridLayout(1,3));
        setBackground(Color.blue);
        add(towerAButton);
        add(towerBButton);
        add(towerCButton);
    }
}
