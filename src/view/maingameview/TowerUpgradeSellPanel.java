package view.maingameview;

import model.gamelog.LoggerCollection;
import model.tower.SplashTower;
import protocol.DrawingPanelDelegate;
import model.tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * class for upgrading and selling towers view 
 * @author yongpinggao  
 * @version 2.0.
 * @since 3/13/16.
 */
public class TowerUpgradeSellPanel extends JPanel implements DrawingPanelDelegate {

    private final static String[] towerStrategies = {"Target On Weakest", "Target On Strongest", "Target On Nearest to End", "Target On Closest to Tower"};

    public JButton sellButton;
    public JButton upgradeButton;
    public JLabel towerImageLabel;
    public JComboBox strategyComboBox;
    public JTextArea towerLogArea;
    public JScrollPane towerLogScrollPane;
    /**
     * Constructor TowerUpgradeSellPanel.
     */
    public TowerUpgradeSellPanel() {
        setBackground(Color.black);
        sellButton = new JButton("Sell");
        sellButton.setEnabled(false);
        upgradeButton = new JButton("Upgrade");
        upgradeButton.setEnabled(false);
        towerImageLabel = new JLabel(new ImageIcon());
        strategyComboBox = new JComboBox(towerStrategies);
        strategyComboBox.setEnabled(false);
        towerLogArea = new JTextArea(3, 5);
        towerLogArea.setEditable(false);
        towerLogScrollPane = new JScrollPane(towerLogArea);

        setLayout(null);
        add(sellButton);
        add(towerImageLabel);
        add(upgradeButton);
        add(towerLogScrollPane);
        add(strategyComboBox);
        sellButton.setBounds(0, 0, 118, 58);
        upgradeButton.setBounds(120, 0, 120, 58);
        towerImageLabel.setBounds(0, 58 , 240, 132);
        strategyComboBox.setBounds(0, 188, 240, 30);
        towerLogScrollPane.setBounds(0, 218, 240, 70);
        towerLogArea.setCaretPosition(towerLogArea.getDocument().getLength());
    }
    /**
     * refresh the view base on selected tower
     */
    @Override
    public void reloadPanelBasedOnTower(Tower tower) {
        if (tower != null) {
            if (tower.getPosition() != null) {
                sellButton.setEnabled(true);
                upgradeButton.setEnabled(true);
            } else {
                sellButton.setEnabled(false);
                upgradeButton.setEnabled(false);
            }
            towerImageLabel.setIcon(new ImageIcon(tower.getHdImageName()));
            strategyComboBox.setSelectedItem(tower.getTowerShootingBehavior().getShootingStrategy().toString());
            if (tower instanceof SplashTower) {
                strategyComboBox.setEnabled(false);
            } else {
                strategyComboBox.setEnabled(true);
            }
        } else {
            towerImageLabel.setIcon(null);
            strategyComboBox.setEnabled(false);
            sellButton.setEnabled(false);
            upgradeButton.setEnabled(false);
        }

    }
    /**
     * refresh tower base on tower index
     */
    @Override
    public void reloadLogPanelBasedOnIndexOfTower(int index) {
        towerLogArea.setText("");
        towerLogArea.append(LoggerCollection.getInstance().showTowerLogAtIndex(index));
    }
}