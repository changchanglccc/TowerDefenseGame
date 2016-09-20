package view.gamelogview;


import view.BaseWindowView;

import javax.swing.*;

/**
 * defines the game log view 
 * @author yongpinggao 
 * @since 3/26/16.
 * @version 2.0
 */
public class GameLogView extends BaseWindowView {

    public JButton gameLogButton;
    public JButton waveLogButton;
    public JButton towerLogButton;
    public JButton mapLogButton;

    public JTextArea logArea;
    public JScrollPane logScrollPane;
    /**
     * Constructor for GameLogView 
     */
    public GameLogView() {
        super(600, 722, "Game Log");

        gameLogButton = new JButton("GameLog");
        waveLogButton = new JButton("WaveLog");
        towerLogButton = new JButton("TowerLog");
        mapLogButton = new JButton("MapLog");

        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        logScrollPane = new JScrollPane(logArea);


        setLayout(null);
        add(gameLogButton);
        add(waveLogButton);
        add(towerLogButton);
        add(mapLogButton);
        add(logScrollPane);

        gameLogButton.setBounds(0, 0, 150, 50);
        towerLogButton.setBounds(150, 0, 150, 50);
        waveLogButton.setBounds(300, 0, 150, 50);
        mapLogButton.setBounds(450, 0, 150, 50);
        logScrollPane.setBounds(0, 50, 600, 672);
        logArea.setCaretPosition(logArea.getDocument().getLength());

    }
}