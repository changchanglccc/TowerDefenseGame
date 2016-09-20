package view.maingameview;

import java.awt.*;
import javax.swing.*;

/**
 * Class to display the top panel view.
 * @author yongpinggao
 * @version  1.0 3/13/16.
 */
public class TopView extends JPanel {

    public TowerSelectionPanel towerSelectionPanel;
    public GameDataPanel gameDataPanel;

    /**
     * Constructor for default top view.
     */
    public TopView() {
        setLayout(new GridLayout(1,2));
        towerSelectionPanel = new TowerSelectionPanel();
        gameDataPanel = new GameDataPanel();
        gameDataPanel.setBackground(Color.WHITE);

        this.add(towerSelectionPanel);
        this.add(gameDataPanel);
    }
}
