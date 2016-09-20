package model.savegame;

import java.util.ArrayList;
import java.util.HashMap;

import model.gamelog.Log;
import model.tower.Tower;

/**
 * GameInfo class for the game info to save a game.
 * @author Mansour Alzahrani
 * @since 3/30/16.
 * @version 2.0 
 */
public class GameInfo {
    private double balance;
    private int waveNum;
    private int coins;
    private String gameName;
    private String mapName;
    private HashMap<Integer, Tower> towerCollection;
    private ArrayList<Log> logList;
    /**
     * Constructor for game info
     * @param towerCollection  towers on the map 
     * @param logList  log record
     * @param balance player balance 
     * @param coins    coins left
     * @param waveNum  wave number
     * @param gameName game name pick by user 
     * @param mapName  map name 
     */
    public GameInfo(
            HashMap<Integer, Tower> towerCollection,
            ArrayList<Log> logList, 
            double balance,
            int coins,
            int waveNum, 
            String gameName,
            String mapName 
            ) {
        this.towerCollection = towerCollection;
        this.logList = logList;
        this.balance = balance;
        this.gameName = gameName;
        this.mapName = mapName;
        this.coins = coins;
        this.waveNum = waveNum;
    }
    /**
     * getter for the tower collection 
     * @return towerCollection
     */
    public HashMap<Integer, Tower> getTowerCollection() {
        return towerCollection;
    }
    /**
     * setter for tower collection
     * @param towerCollection set the tower collection
     */
    public void setTowerCollection(HashMap<Integer, Tower> towerCollection) {
        this.towerCollection = towerCollection;
    }
    /**
     * getter for log record
     * @return log record 
     */
    public ArrayList<Log> getLogList() {
        return logList;
    }
    /**
     * setter for log record 
     * @param logList log record
     */
    public void setLogList(ArrayList<Log> logList) {
        this.logList = logList;
    }
    /**
     * getter for player balance 
     * @return player balance 
     */
    public double getBalance() {
        return balance;
    }
    /**
     * setter for player blance 
     * @param balance player balance 
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * getter for wave number
     * @return   wave number
     */
    public int getWaveNum() {
        return waveNum;
    }
    /**
     * setter for wave number
     * @param waveNum wave number
     */
    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }
    /**
     * getter for player coins 
     * @return  coins left
     */
    public int getCoins() {
        return coins;
    }
    /**
     * setter coins 
     * @param coins
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }
    /**
     * getter for game name 
     * @return game name 
     */
    public String getGameName() {
        return gameName;
    }
    /**
     * setter for game name 
     * @param gameName game name 
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    /**
     * getter for map name
     * @return map name
     */
    public String getMapName() {
        return mapName;
    }
    /**
     * setter for map name 
     * @param mapName map name 
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}