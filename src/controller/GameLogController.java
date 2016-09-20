package controller;

import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;
import view.gamelogview.GameLogView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class to control all log records in the game
 * @author yongpinggao 
 * @since 3/26/16.
 * @version 1.0
 */
public class GameLogController {

    public GameLogView gameLogView;
    private Timer refreshTimer;
    private String log;
    private LogType currentLogType = LogType.Game;


    public GameLogController() {
        gameLogView = new GameLogView();
        refreshTimer = new Timer(100, new ActionListener() {
            /**
             * listener to refresh the log 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentLogType) {
                    case Game:
                        log = LoggerCollection.getInstance().showAllLog();
                        break;
                    case Tower:
                        log = LoggerCollection.getInstance().showAllTowerLog();
                        break;
                    case Wave:
                        log = LoggerCollection.getInstance().showWaveLog();
                        break;
                    case Map:
                        log = LoggerCollection.getInstance().showMapLog();
                }
                gameLogView.logArea.setText("");
                gameLogView.logArea.append(log);
            }
        });

        refreshTimer.start();

        /**
         * listener for game log button to show all log records
         */
        gameLogView.gameLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log = LoggerCollection.getInstance().showAllLog();
                currentLogType = LogType.Game;
            }
        });

        /**
         * listener to show all towers log
         */
        gameLogView.towerLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log = LoggerCollection.getInstance().showAllTowerLog();
                currentLogType = LogType.Tower;
            }
        });

        
        /**
         * listener for wave log 
         */
        gameLogView.waveLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log = LoggerCollection.getInstance().showWaveLog();
                currentLogType = LogType.Wave;
            }
        });

        /**
         * listener for map log
         */
        gameLogView.mapLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log = LoggerCollection.getInstance().showWaveLog();
                currentLogType = LogType.Map;
            }
        });

    }

    /**
     * getter for log.
     * @return all log records.
     */
    public String getLog() {
        return log;
    }
    /**
     * setter for log
     * @param log the log content
     */
    public void setLog(String log) {
        this.log = log;
    }
}
