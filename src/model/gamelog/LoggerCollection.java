package model.gamelog;

import model.map.GameMap;

import java.util.ArrayList;

/**
 * Gather all logger.
 * @author yongpinggao
 * @since 3/25/16.
 * @version 2.0 
 */
public class LoggerCollection {
    private static LoggerCollection sharedInstance = new LoggerCollection();
    /**
     * Get instance of loggerCollection.
     * @return the shared instance
     */
    public static LoggerCollection getInstance() {
        return sharedInstance;
    }
    /**
     * Constructor of LoggerCollection.
     * @return
     */
    private LoggerCollection() {}
    private ArrayList<Log> logList = new ArrayList<>();
    /**
     * Get log list.
     * @return
     */
    public ArrayList<Log> getLogList() {
        return logList;
    }
    /**
     * Set log list.
     * @param logList an arraylist contains log
     */
    public void setLogList(ArrayList<Log> logList) {
        this.logList = logList;
    }
    /**
     * Add log into log list.
     * @param log the log needs to be added
     */
    public void addLog(Log log) {
        logList.add(log);
    }
    /**
     * Clear all logs.
     */
    public void clearAllLogs() {
        logList.clear();
    }
    /**
     * Show all logs
     * @return log contents
     */
    public String showAllLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log: logList) {
            stringBuilder.append(log.toString());
        }
        return stringBuilder.toString();
    }
    /**
     * Show all logs of tower.
     * @return tower log contents
     */
    public String showAllTowerLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log: logList) {
            if (log.getWho() == LogType.Tower) {
                stringBuilder.append(log.toString());
            }
        }
        return stringBuilder.toString();
    }
    /**
     * Show tower log of specific log id.
     * @param index the index of log
     * @return log contents
     */
    public String showTowerLogAtIndex(int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log: logList) {
            if (log.getWho() == LogType.Tower) {
                if (index == log.getId()) {
                    stringBuilder.append(log.toString());
                }
            }
        }
        return stringBuilder.toString();
    }
    /**
     * Show log of wave
     * @return wave log
     */
    public String showWaveLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log: logList) {
            if (log.getWho() == LogType.Wave) {
                stringBuilder.append(log.toString());
            }
        }
        return stringBuilder.toString();
    }
    /**
     * Show log of map.
     * @return map log
     */
    public String showMapLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log: logList) {
            if (log.getWho() == LogType.Map) {
                stringBuilder.append(log.toString());
            }
        }
        return stringBuilder.toString();
    }
    /**
     * Add all map log of gameMap.
     * @param gameMap a specific gameMap
     */
    public void addAllMapLog(GameMap gameMap) {
        addLog(new Log(LogType.Map, "Player created this Map at: " + gameMap.getCreateTime()));

        String editedTime = gameMap.getAllEditTime();
        if (!editedTime.equals("")) {
            addLog(new Log(LogType.Map, "Player edited this Map at: \n" + editedTime));
        }
        String results = gameMap.getAllResults();
        if (!results.equals("")) {
            addLog(new Log(LogType.Map, "The result of each play: \n" + results));
        }

    }
}