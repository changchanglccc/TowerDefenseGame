package view.maingameview;

import protocol.DrawingDataPanelDelegate;

import javax.swing.*;
import java.awt.*;


/**
 * Defines the game data view.
 *@author yongpinggao 
 *@since  3/13/16.
 *@version 2.0
 */
public class GameDataPanel extends JPanel implements DrawingDataPanelDelegate {



    public JLabel balanceLabel;
    public JLabel coinsLabel;
    public JLabel waveNumLabel;

    public JLabel infoLabel;
    public JButton waveStartButton;
    public JButton showLogButton;
    public JButton saveGame;

    /**
     * A constructor for GameDataPanel
     */
    public GameDataPanel() {

        setLayout(new GridLayout(3,3));

        balanceLabel = new JLabel("balanceLabel");
        coinsLabel = new JLabel("coinsLabel");
        waveNumLabel = new JLabel("Wave: ");

        infoLabel = new JLabel("");
        waveStartButton = new JButton("Next Wave");
        showLogButton = new JButton("Log");
        saveGame = new JButton("Save Game");
        
        add(balanceLabel);
        add(coinsLabel);
        add(waveNumLabel);
        add(waveStartButton);
        add(saveGame);
        add(showLogButton);
        add(infoLabel);

    }
    /**
     * refresh the wave  label 
     */
    @Override
    public void reloadWaveDataView(int waveNum) {
        waveNumLabel.setText("Wave: " + waveNum + " now");
    }
    /**
     * refresh the balance label 
     */
    @Override
    public void reloadBalanceDataView(double balance) {
        balanceLabel.setText("Gold: " + balance);
    }

    /**
     * refresh the coins label 
     */
    @Override
    public void reloadCoinDataView(int coin) {
        coinsLabel.setText("Coin left: " + coin);
    }
    /**
     * refresh the information label 
     */
    @Override
    public void reloadInfoDataView(String info) {
        infoLabel.setText(info);
    }

}
