package testingUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;

/**
 * test game log.
 * 
 * @author m_lzahra
 * @version 1.0
 * @since 14/3/2016
 */
public class GameLogTest {

    Log waveLog,FirstTowerLog,secondTowerLog,mapLog,gameLog;
    String waveContent ;
    String firstTowerContent ;
    String secondTowerContent ;
    String mapContent ;
    int firstTowerId;
    int secondTowerId;
    
    /**
     * Test Setting values.
     */
    @Before
    public void testSetValus() {
        LoggerCollection.getInstance().clearAllLogs();
        firstTowerId = 1;
        secondTowerId = 2;
        waveLog = new Log(LogType.Wave,"critter is moving");
        waveContent = waveLog.toString();
        FirstTowerLog = new Log(LogType.Tower,firstTowerId,"fist Tower");
        firstTowerContent = FirstTowerLog.toString();
        secondTowerLog = new Log(LogType.Tower,secondTowerId,"second Tower");
        secondTowerContent =secondTowerLog.toString() ;
        mapLog = new Log(LogType.Map,"map has played");
        mapContent = mapLog.toString();
        LoggerCollection.getInstance().addLog(waveLog);
        LoggerCollection.getInstance().addLog(FirstTowerLog);
        LoggerCollection.getInstance().addLog(secondTowerLog);
        LoggerCollection.getInstance().addLog(mapLog);
    }
    
    /**
     * Test getting wave content.
     */
    @Test
    public void testGetWaveContent() {
        String waveContentFromLog = LoggerCollection.getInstance().showWaveLog();
        assertTrue(waveContentFromLog.equals(waveContent));
    }
    
    /**
     * Test getting first tower content.
     */
    @Test
    public void testGetFristTowerContent() {
        String fristTowerContentFromLog = LoggerCollection.getInstance().showTowerLogAtIndex(firstTowerId);
        assertTrue(fristTowerContentFromLog.equals(firstTowerContent));
    }
    
    /**
     * Test getting all towers' contents.
     */
    @Test
    public void testGetAllTowersContent() {
        String allTowersContentFromLog = LoggerCollection.getInstance().showAllTowerLog();
        String firstTowerAndSecondTower = firstTowerContent + secondTowerContent;
        assertTrue(allTowersContentFromLog.equals(firstTowerAndSecondTower));
    }
    
    /**
     * Test getting map contents.
     */
    @Test
    public void testGetMapContent() {
        String mapContenetFromLog = LoggerCollection.getInstance().showMapLog();
        assertTrue(mapContenetFromLog.equals(mapContent));
    }
    
    /**
     * Test getting all logs.
     */
    @Test
    public void testGetAllLogs() {
        String allLogFromLog = LoggerCollection.getInstance().showAllLog();
        String allLogs = waveContent + firstTowerContent + secondTowerContent + mapContent;
        System.out.println(allLogFromLog);
        assertTrue(allLogFromLog.equals(allLogs));
    }
}