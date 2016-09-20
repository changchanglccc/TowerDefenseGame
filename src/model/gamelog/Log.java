package model.gamelog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Game Log
 * @author yongpinggao
 * @version 1.0
 * @since 3/25/16
 */
public class Log {
    private String currentTime;
    private String content;
    private LogType who;
    private int id;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * Constructor of Log.
     * @param who it represents who needs log
     * @param content the contents of log
     */
    public Log(LogType who, String content) {
        this.currentTime = dateFormat.format(new Date());
        this.content = content;
        this.who = who;
    }
    /**
     * Consructor of Log.
     * @param who it represents who needs log
     * @param id Log id
     * @param content the contents of log
     */
    public Log(LogType who, int id, String content) {
        this.currentTime = dateFormat.format(new Date());
        this.content = content;
        this.who = who;
        this.id = id;
    }
    /**
     * Get a log with specific type.
     * @return the log
     */
    public LogType getWho() {
        return who;
    }
    /**
     * Get log id.
     * @return
     */
    public int getId() {
        return id;
    }
    /**
     * Get current time of log.
     * @return
     */
    public String getCurrentTime() {
        return currentTime;
    }
    /**
     * Set current time of log
     * @param currentTime
     */
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    /**
     * Get log content.
     * @return
     */
    public String getContent() {
        return content;
    }
    /**
     * Set log content.
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Translate Log with specific format.
     */
    @Override
    public String toString() {
        return "[" + currentTime + "]" + " : " + content + "\n";
    }

}
