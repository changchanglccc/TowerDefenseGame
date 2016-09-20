package model.savegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.gamelog.Log;
import model.gamelog.LogType;
import model.tower.Tower;
import model.tower.TowerFactory;
import model.tower.shootingstrategy.TargetBasedOnNearest;
import model.tower.shootingstrategy.TargetBasedOnStrongest;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import model.tower.shootingstrategy.TargetBasedOnClosestToTower;
import view.map.Position;
import view.tower.TowerType;

/**
 * GameCollection class
 * store all game  that user saved
 * @author Mansour Alzahrani
 * @since 3/30/16.
 * @version 2.0 
 */
public class GameCollection implements Serializable {
    private  ArrayList<GameInfo> games;
    private Log Log;    
    /**
     * Constructor for GameCollection
     */
    public GameCollection() {        
        this.games = new ArrayList<>();
    }

    /**
     * add a new game
     * @param game game for file or saved game
     */
    public void addGame(GameInfo game) {        
        games.add(game);
    }

    /**
     * remove a game
     * @param index index of the game to be removed
     */
    public void removeGame(int index) {    
        games.remove(index);
    }

    /**
     * getter for game 
     * @return arrayList of all games
     */
    public ArrayList<GameInfo> getGames() {
        return games;
    }

    /**
     * find a game index base on the game name
     * @param gameName game name
     * @return game index
     */
    public int findGameInCollection(String gameName) { // based on map name
        int index = 0;
        for (int i = 0; i < games.size(); i++) {
            if (gameName.equalsIgnoreCase(games.get(i).getGameName())) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Store all saved games info in xml file 
     * @throws FileNotFoundException file not found exception
     */
    public boolean saveGame() {       
        try {
            PrintWriter out;
            File selectedFile = new File("Game_Info.xml");
            FileOutputStream stream = new FileOutputStream(selectedFile); 
            out = new PrintWriter( stream );
            out.println("<?xml version=\"1.0\"?>");
            out.println("<SaveGame version=\"1.0\">");

            for (int i = 0; i < games.size(); i++) {
                out.println("<Game>");
                GameInfo game =games.get(i);
                for (Map.Entry<Integer, Tower> entry : game.getTowerCollection().entrySet()) {
                    Tower tower =entry.getValue();
                    out.println(
                        "<Tower type='" + tower.getClass().getSimpleName() + "' index='" +
                        entry.getKey() + "' level='" + tower.getLevel() + "' strategy='" +
                        tower.getTowerShootingBehavior().getShootingStrategy().getClass().getSimpleName() + "' range='" + 
                        tower.getRange() + "' x='" + tower.getPosition().getX() + "' y='" + tower.getPosition().getY()  + "' />"
                    );
                } 

                for (Log gameLogInfo : game.getLogList()) {
                    out.println(
                        "<Log currentTime='" + gameLogInfo.getCurrentTime() + "' content='" +
                        gameLogInfo.getContent() + "' id='" + gameLogInfo.getId()+ "' logType='" +
                        gameLogInfo.getWho().toString() + "' />"
                    );
                }

                out.println("<WaveNumber>" + game.getWaveNum() + "</WaveNumber>");
                out.println("<MapName>" + game.getMapName()+ "</MapName>");
                out.println("<Coins>" + game.getCoins() + "</Coins>");
                out.println("<Balance>"  + game.getBalance() +"</Balance>");
                out.println("<GameName>" + game.getGameName() + "</GameName>");
                out.println("</Game>");
            }

            out.println("</SaveGame>");
            out.close();

        } catch (Exception e) {
            System.out.println("Cannot Write To File ");
            return false;
        }
        return true;
    }

    /**
     * Read game info from xml file 
     * @throws Exception file exception
     */
    public boolean loadGame() {        
        HashMap<Integer,Tower> towersCollection = new HashMap<Integer,Tower> ();
        ArrayList<Log> gameLog= new ArrayList<>();
        double balance = 0;
        String gameName = "" ;
        String mapName = "" ;
        int waveNum = 0;
        int coins = 0;

        try { 
            Document xmldoc;
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse("Game_Info.xml");
            Element rootElement = xmldoc.getDocumentElement();

            if ( ! rootElement.getNodeName().equals("SaveGame") ) {
                throw new Exception("File is not a savegame file.");
            }

            NodeList nodes = rootElement.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i) instanceof Element) {
                    Element element = (Element)nodes.item(i);

                    if (element.getTagName().equals("Game")) {
                        NodeList curveNodesGame = element.getChildNodes();
                        for (int j = 0; j < curveNodesGame.getLength(); j++) {
                            if (curveNodesGame.item(j) instanceof Element) {
                                Element curveOfGameElement = (Element)curveNodesGame.item(j);
                                if (curveOfGameElement.getTagName().equals("GameName")) {
                                    gameName = curveOfGameElement.getTextContent();
                                } else if (curveOfGameElement.getTagName().equals("MapName")) { 
                                    mapName =curveOfGameElement.getTextContent();
                                } else if (curveOfGameElement.getTagName().equals("WaveNumber")) {
                                    waveNum = Integer.parseInt(curveOfGameElement.getTextContent());
                                } else if (curveOfGameElement.getTagName().equals("Coins")) {
                                    coins = Integer.parseInt(curveOfGameElement.getTextContent());
                                } else if (curveOfGameElement.getTagName().equals("Balance")) {
                                    balance = Double.parseDouble(curveOfGameElement.getTextContent());
                                } else if (curveOfGameElement.getTagName().equals("Log")) {
                                    String currentTime = curveOfGameElement.getAttribute("currentTime");
                                    String content = curveOfGameElement.getAttribute("content");
                                    String who = curveOfGameElement.getAttribute("logType");
                                    int id = Integer.parseInt(curveOfGameElement.getAttribute("id"));
                                    LogType whoIs = null;

                                    switch (who) {
                                    case "Game" :
                                        whoIs = LogType.Game;
                                        break;
                                    case "Map" :
                                        whoIs = LogType.Map;
                                        break;
                                    case "Tower" :
                                        whoIs = LogType.Tower;
                                        break;
                                    case "Wave" :
                                        whoIs = LogType.Wave;
                                        break;
                                    }

                                    if (! who.equals("Tower")) {
                                        Log logNotTower = new Log(whoIs , content); 
                                        gameLog.add(logNotTower);
                                    } else {
                                        Log logTower = new Log(whoIs ,id, content); 
                                        gameLog.add(logTower);
                                    }

                                } else if (curveOfGameElement.getTagName().equals("Tower")) {
                                    String towerType = curveOfGameElement.getAttribute("type");
                                    String towerStrategy = curveOfGameElement.getAttribute("strategy");

                                    int range =  Integer.parseInt(curveOfGameElement.getAttribute("range"));
                                    int posX = Integer.parseInt(curveOfGameElement.getAttribute("x"));
                                    int posY = Integer.parseInt(curveOfGameElement.getAttribute("y"));
                                    int index = Integer.parseInt(curveOfGameElement.getAttribute("index"));
                                    int level = Integer.parseInt(curveOfGameElement.getAttribute("level"));
                                    Tower tower = null ;

                                    switch (towerType) {
                                    case "BurningTower":
                                        tower = TowerFactory.sharedInstance().getTower(TowerType.BurningTower1);
                                        break;
                                    case "IceTower":
                                        tower = TowerFactory.sharedInstance().getTower(TowerType.IceTower1);
                                        break;
                                    case "SplashTower":
                                        tower = TowerFactory.sharedInstance().getTower(TowerType.SplashTower1);
                                        break;     
                                    }

                                    tower.setPosition(new Position(posX,posY));
                                    tower.setLevel(level);

                                    switch (towerStrategy) {
                                    case "TargetBasedOnNearest":
                                        tower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnNearest());
                                        break;
                                    case "TargetBasedOnStrongest":
                                        tower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnStrongest());
                                        break;
                                    case "TargetBasedOnWeakest":
                                        tower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnWeakest());
                                        break;
                                    case "TargetBasedOnClosestToTower":
                                        tower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnClosestToTower());
                                        break;
                                    }

                                    towersCollection.put(index, tower);     

                                }
                            }
                        }
                        GameInfo gameInfo = new GameInfo(towersCollection, gameLog , balance,coins,waveNum,gameName,mapName );
                        towersCollection = new HashMap<Integer,Tower> ();
                        gameLog = new ArrayList<>();
                        games.add(gameInfo);
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Cannot Read From File");
            return false;
        }
        return true;
    }
}

