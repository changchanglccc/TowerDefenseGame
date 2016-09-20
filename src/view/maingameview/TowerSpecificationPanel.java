package view.maingameview;

import protocol.DrawingPanelDelegate;
import model.tower.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * class for tower specification view 
 *@author yongpinggao 
 *@since 3/13/16.
 *@version 2.0
 */
public class TowerSpecificationPanel extends JPanel implements DrawingPanelDelegate {
    public JLabel specificationLabel;
    public JLabel buyPriceLabel;
    public JLabel sellPriceLabel;
    public JLabel rangeLabel;
    public JLabel powerLabel;
    public JLabel rateOfFireLabel;
    /**
     * constructor for TowerSpecificationPanel
     */
    public TowerSpecificationPanel() {
        setBackground(Color.white);
        specificationLabel = new JLabel("");
        buyPriceLabel = new JLabel("");
        sellPriceLabel = new JLabel("");
        rangeLabel = new JLabel("");
        powerLabel = new JLabel("");
        rateOfFireLabel = new JLabel("");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(specificationLabel);
        add(buyPriceLabel);
        add(sellPriceLabel);
        add(rangeLabel);
        add(powerLabel);
        add(rateOfFireLabel);


    }
    /**
     * refresh tower specificationPanel base on the tower.
     */
    @Override
    public void reloadPanelBasedOnTower(Tower tower) {
        if (tower != null) {
            specificationLabel.setText(tower.getSpecification());
            buyPriceLabel.setText("Buy Price: " + tower.getBuyPrice()+"");
            sellPriceLabel.setText("Sell Price: " + tower.getSellPrice() + "");
            rangeLabel.setText("Shooting Range: " + tower.getRange());
            powerLabel.setText("Shooting Power: " + tower.getTowerShootingBehavior().getPower());
            rateOfFireLabel.setText("Shooting Rate: " + tower.getTowerShootingBehavior().getRateOfFire());
        } else {
            specificationLabel.setText("");
            buyPriceLabel.setText("");
            sellPriceLabel.setText("");
            rangeLabel.setText("");
            powerLabel.setText("");
            rateOfFireLabel.setText("");
        }

    }
    /**
     *refresh tower specificationPanel base on the tower index. 
     */
    @Override
    public void reloadLogPanelBasedOnIndexOfTower(int index) {}
}
