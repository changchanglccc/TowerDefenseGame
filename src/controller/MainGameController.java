package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.bankaccount.BankAccount;
import model.critter.Critter;
import model.critter.CritterCollection;
import model.critter.CritterMovingBehavior;
import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;
import model.map.CellState;
import model.map.GameMap;
import model.map.GameMapCollection;
import model.savegame.GameCollection;
import model.savegame.GameInfo;
import model.savegame.SavedGamesMaps;
import model.tower.Tower;
import model.tower.TowerCollection;
import model.tower.TowerFactory;
import model.tower.shootingstrategy.TargetBasedOnNearest;
import model.tower.shootingstrategy.TargetBasedOnStrongest;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import model.tower.shootingstrategy.TargetBasedOnClosestToTower;
import model.wave.WaveFactory;
import protocol.DrawingDataPanelDelegate;
import protocol.DrawingMapDelegate;
import protocol.DrawingMapInGameDelegate;
import protocol.DrawingPanelDelegate;
import view.maingameview.MainGameView;
import view.map.Drawing;
import view.tower.TowerType;

/**
 * This controller will start all the logic for the view elements and start the models.
 * 
 * @author yongpinggao
 * @see model.map.GameMap
 * @see model.critter.Critter
 * @see model.bankaccount.BankAccount
 * @see model.critter.Critter
 * @see model.critter.CritterCollection
 * @see model.map.CellState
 * @see model.map.GameMap
 * @see model.tower.shootingstrategy.TargetBasedOnWeakest
 * @see model.tower.shootingstrategy.TargetBasedOnStrongest
 * @see model.tower.shootingstrategy.TargetBasedOnNearest
 * @see model.tower.shootingstrategy.TowerShootingStrategy
 * @see model.tower.Tower
 * @see model.tower.TowerCollection
 * @see model.tower.TowerFactory
 * @see model.wave.WaveFactory
 * @see protocol.DrawingDataPanelDelegate
 * @see protocol.DrawingMapInGameDelegate
 * @see protocol.DrawingPanelDelegate
 * @see view.maingameview.MainGameView
 */
public class MainGameController {

    MainGameView mainGameView;
    GameLogController gameLogController;

    GameMap gameMap = new GameMap();
    TowerCollection towerCollection = new TowerCollection();
    CritterCollection critterCollection = new CritterCollection();
    Tower currentTower;
    int currentCritterIndex = 0;
    int currentIndex = 0;
    int currentWaveNum = 0;
    double balance = 0;
    String gameName = "";
    BankAccount account ;

    Timer critterGeneratorTimer;
    Timer paintingTimer;

    DrawingMapInGameDelegate drawingMapInGameDelegate;
    DrawingMapDelegate drawingMapDelegate;
    DrawingPanelDelegate drawingSpecificationPanelDelegate;
    DrawingPanelDelegate drawingSellUpgradePanelDelegate;
    DrawingDataPanelDelegate drawingDataPanelDelegate;

    private final int REFRESH_RATE = 100;
    private final int CRITTER_GENERATE_TIME = 1000;

    private int coins = 10;
    private boolean preWavePhase = true;
    private boolean loadGame = false;
    
    /**
     * A constuctor to start a new game
     * @param gameMap game map for the game
     */
    public MainGameController(GameMap gameMap) {
        this.gameMap = gameMap;
        LoggerCollection.getInstance().addAllMapLog(gameMap);
        initBankAccount();
        initializeProtocol();
        initPaintingTimers();
        initMapArea();
        initWaveTimers();
        initTowerButtons();
        initSellUpgradeButtons();
        initFunctionalButtonsInTopPanel();
        showHighScoreList();
    }

    /**
     * A constructor to start a loaded game 
     * @param gameInfo loaded game info
     */
    public MainGameController(GameInfo gameInfo) {
 
        loadGame = true;
        
        SavedGamesMaps mapCollection = SavedGamesMaps.loadMapsFromFile();      
        for (int i = 0 ; i < mapCollection.getMaps().size(); i++ ) {
            String gameMapName = mapCollection.getMaps().get(i).getMapName();
            
            if (gameMapName.equalsIgnoreCase(gameInfo.getMapName())) {
                this.gameMap = mapCollection.getMaps().get(i);
            }
        }
             
        towerCollection.setTowers(gameInfo.getTowerCollection()); 
        for (Map.Entry<Integer, Tower> entry : towerCollection.getTowers().entrySet()) {         
            gameMap.getCells().set(entry.getKey(), CellState.Tower);
        }
        
        balance = gameInfo.getBalance();
        coins = gameInfo.getCoins();
        currentWaveNum = gameInfo.getWaveNum();
        gameName = gameInfo.getGameName();
        LoggerCollection.getInstance().addAllMapLog(gameMap);
        LoggerCollection.getInstance().setLogList(gameInfo.getLogList());
        
        initBankAccount();
        initializeProtocol();
        initPaintingTimers();
        refreshGamePanelsView();
        initTowerButtons();
        initSellUpgradeButtons();
        initFunctionalButtonsInTopPanel();
        initWaveTimers();
        initMapArea();
        showHighScoreList();
        
    }

    /**
     * Initialize  all view observer
     */
    private void initializeProtocol() {
        
        mainGameView = new MainGameView();
        gameLogController = new GameLogController();
        drawingMapInGameDelegate = mainGameView.mapView.mapPanel;
        drawingMapDelegate = mainGameView.mapView.mapPanel;
        drawingSpecificationPanelDelegate = mainGameView.endView.towerSpecificationPanel;
        drawingSellUpgradePanelDelegate = mainGameView.endView.towerUpgradeSellPanel;
        drawingDataPanelDelegate = mainGameView.topView.gameDataPanel;

        drawingMapDelegate.refreshMap(gameMap);
        drawingDataPanelDelegate.reloadCoinDataView(coins);
        drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
        drawingDataPanelDelegate.reloadWaveDataView(currentWaveNum);
    }
    /**
     * Initialize player balance
     */
    private void initBankAccount() {
        account =  new BankAccount();
        if (!loadGame) {
            account.setBalance(BankAccount.INITIAL_BALANCE);
        } else {
            account.setBalance(balance);
        }
    }


    /**
     * Launch waves 
     */
    private void startNextWave() {
        if (!preWavePhase) {
            if (currentWaveNum >= WaveFactory.MAX_WAVE_NUM) {
                gameShouldFinishedWithUserWin(true);
            } else {
                initCrittersForWave(++currentWaveNum);
                LoggerCollection.getInstance().addLog(new Log(LogType.Wave, "Wave " + currentWaveNum + " starts"));
                drawingDataPanelDelegate.reloadWaveDataView(currentWaveNum);
            }
        }
    }

    /**
     * Initialize listener for buttons in the top panel
     */
    private void initFunctionalButtonsInTopPanel() {
        LoggerCollection.getInstance().addLog(new Log(LogType.Wave, "Wave Preparation Phase..."));
        // waveStartButton
        mainGameView.topView.gameDataPanel.waveStartButton.addActionListener(new ActionListener() {
            /**
             * a listener for stating wave 
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                preWavePhase = false;
                startNextWave();
                mainGameView.topView.gameDataPanel.waveStartButton.setEnabled(false);
                mainGameView.topView.gameDataPanel.saveGame.setEnabled(false);
            }
        });

        /**
         * listener for game log button to show game log
         */
        mainGameView.topView.gameDataPanel.showLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameLogController.gameLogView.isVisible()) {
                    gameLogController.gameLogView.setVisible(false);
                } else {
                    gameLogController.gameLogView.setVisible(true);
                }
            }
        });
        /**
         * a listener for saving game button 
         */
        mainGameView.topView.gameDataPanel.saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveGame();
                LoggerCollection.getInstance().addLog(new Log(LogType.Map, "Player saved the game"));
                gameShouldFinishedWithUserWin();
            }
        }); 
    }
    
    
        
    /**
     * Initialize  waves and add the new wave to the log
     * @param waveNum wave number
     */
    private void initCrittersForWave(int waveNum) {
        for (Tower t: towerCollection.getTowers().values()) {
            t.getTowerShootingBehavior().getCrittersInRange().clear();
        }
        currentCritterIndex = 0;
        critterCollection = WaveFactory.sharedInstance().getWave(waveNum).getCritterCollection();
        LoggerCollection.getInstance().addLog(
            new Log(
                LogType.Wave,
                "Now in wave " + currentWaveNum + " : " + critterCollection.getCritters().size() + " critters have been created"
            )
        );
    }

    /**
     * define the listeners for selling,upgrading,and changing strategy for towers
     */
    private void initSellUpgradeButtons() {

        /**
         * listener for upgrading tower
         */
        mainGameView.endView.towerUpgradeSellPanel.upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTower != null) {
                    int level = currentTower.getLevel();
                    if (level < Tower.MAX_LEVEL) {
                        double oldPrice = currentTower.getBuyPrice();
                        currentTower.setLevel(++level);
                        double newPrice = currentTower.getBuyPrice();
                        if (newPrice - oldPrice > account.getBalance()) {
                            drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                            currentTower.setLevel(--level);
                        } else {
                            account.withDraw(newPrice - oldPrice);
                            drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());

                            LoggerCollection.getInstance().addLog(
                                new Log(
                                    LogType.Tower,
                                    currentIndex,
                                    "Player update a tower to: " + currentTower.getTowerType() + " at position " + currentIndex
                                )
                            );
                        }
                        refreshGamePanelsView();
                    } else { // warning!
                        drawingDataPanelDelegate.reloadInfoDataView("Max Level of tower is " + Tower.MAX_LEVEL);
                    }
                    towerCollection.addTowerAtIndex(currentIndex, currentTower);
                }
            }
        });

        /**
         * a listener for selling tower 
         */
        mainGameView.endView.towerUpgradeSellPanel.sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTower != null) {

                    LoggerCollection.getInstance().addLog(
                        new Log(
                            LogType.Tower, 
                            currentIndex, 
                            "Player sell a tower: " + currentTower.getTowerType() + " at position " + currentIndex
                        )
                    );

                    account.deposit(currentTower.getSellPrice());
                    drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                    currentTower = null;
                    towerCollection.removeTowerAtIndex(currentIndex);
                    gameMap.getCells().set(currentIndex, CellState.Grass);
                    currentIndex = -1;
                    refreshGamePanelsView();
                }
            }
        });

        /**
         * a listener for changing tower strategy
         */
        mainGameView.endView.towerUpgradeSellPanel.strategyComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTower != null && currentTower.getPosition() != null) {
                    if (e.getSource() instanceof JComboBox) {
                        JComboBox cb = (JComboBox)(e.getSource());
                        String strategy = (String)cb.getSelectedItem();
                        String oldStrategy = currentTower.getTowerShootingBehavior().getShootingStrategy().toString();
                        switch (strategy) {
                            case "Target On Weakest":
                                currentTower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnWeakest());
                                break;
                            case "Target On Strongest":
                                currentTower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnStrongest());
                                break;
                            case "Target On Nearest to End":
                                currentTower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnNearest());
                                break;
                            case "Target On Closest to Tower":
                                currentTower.getTowerShootingBehavior().setShootingStrategy(new TargetBasedOnClosestToTower());
                                break;
                        }
                        if (!oldStrategy.equals(strategy)) {
                            towerCollection.addTowerAtIndex(currentIndex, currentTower);
                            LoggerCollection.getInstance().addLog(
                                new Log(
                                    LogType.Tower, 
                                    currentIndex, 
                                    "Player set a \"" 
                                        + strategy 
                                        + "\" strategy to " 
                                        + currentTower.getTowerType() 
                                        + " at position " 
                                        + currentIndex
                                )
                            );
                        }
                    }
                }
             }
        });
    }

    /**
     * refresh game view 
     */
    private void refreshGamePanelsView() {
        drawingMapInGameDelegate.refreshMap(gameMap, towerCollection);
        drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
        drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
        drawingSellUpgradePanelDelegate.reloadLogPanelBasedOnIndexOfTower(currentIndex);
    }


    /**
     * define the listener for the game map area
     */
    private void initMapArea() {
        /**
         * listener for mouse click
         */
        mainGameView.mapView.mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                {
                    super.mousePressed(e);

                    int x = e.getX();
                    int y = e.getY();

                    int index = view.map.GameMapDrawing.coordinateToIndexConverter(x, y, gameMap.getmCols());
                    ArrayList<CellState> cellList = gameMap.getCells();

                    if (e.getButton() == MouseEvent.BUTTON1) { // User left click map cells

                        // 1. if it is "toPlaceTower" state:  toPlaceTower -> Tower state
                        if (cellList.get(index) == CellState.ToPlaceTower) {
                            account.withDraw(currentTower.getBuyPrice());
                            drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                            cellList.set(index, CellState.Tower);
                            currentTower.setPosition(Drawing.indexToCoordinateConverter(index, gameMap.getmCols()));
                            towerCollection.addTowerAtIndex(index, currentTower);

                            LoggerCollection.getInstance().addLog(
                                new Log(
                                    LogType.Tower, 
                                    index, 
                                    "Player plant a new tower: " 
                                        + currentTower.getTowerType() 
                                        + " at position " 
                                        + index
                                )
                            );

                            gameMap.setToGrassState();
                            refreshGamePanelsView();
                            currentTower = null;
                        }
                        // 2. if it is "Tower" state:  Tower state -> Chosen state
                        else if (cellList.get(index) == CellState.Tower) {
                            gameMap.setToGrassState();
                            gameMap.toggleChosenState(index);
                            currentTower = towerCollection.getTowers().get(index);
                            currentIndex = index;

                            refreshGamePanelsView();
                        }
                        // 3. if it is "Chosen" state: Chosen state -> Tower State
                        else if (cellList.get(index) == CellState.Chosen) {
                            cellList.set(index, CellState.Tower);
                            currentTower = null;
                            currentIndex = -1;
                            refreshGamePanelsView();
                        }
                        // 4. Other Cells: Chosen state -> Tower state.
                        // ToPlaceTower state -> Grass state
                        else {
                            // if the user press the wrong cells, aka path, etc.
                            // set state back to grass
                            gameMap.clearState();
                            currentTower = null;
                            currentIndex = -1;
                            refreshGamePanelsView();
                        }
                    }
                }
            }
        });

    }

    /**
     * define the listener for towers selection buttons 
     */
    private void initTowerButtons() {

        /**
         * listener for burning tower button
         */
        mainGameView.topView.towerSelectionPanel.towerAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.BurningTower1);
                if (currentTower.getBuyPrice() <= account.getBalance()) {
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });

        /**
         * Listen for ice tower button
         */
        mainGameView.topView.towerSelectionPanel.towerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.IceTower1);
                if (currentTower.getBuyPrice() <= account.getBalance()) {
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });

        /**
         * Listener for splash tower button
         */
        mainGameView.topView.towerSelectionPanel.towerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.SplashTower1);
                if (currentTower.getBuyPrice() <= account.getBalance()) {
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });
    }

    /**
     * Initialize  the timer to refresh game view 
     */
    private void initPaintingTimers() {
        paintingTimer = new Timer(REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingMapInGameDelegate.refreshCrittersInMap(critterCollection);
                detectingCrittersInRange();
                detectingCrittersStoleCoins();
                detectingNextWaveShouldStart();
            }
        });
        paintingTimer.start();
    }

    /**
     * detecting when next wave should start
     */
    private void detectingNextWaveShouldStart() {
        int count = 0;
        if (critterCollection != null) {
            for (Critter c : critterCollection.getCritters()) {
                if (c.isKilled() || c.isDonated()) {
                    count++;
                    continue;
                } else break;
            }
            if (count == critterCollection.getCritters().size() && count != 0) {
                LoggerCollection.getInstance().addLog(new Log(LogType.Wave, "Wave Preparation Phase..."));
                mainGameView.topView.gameDataPanel.waveStartButton.setEnabled(true);
                mainGameView.topView.gameDataPanel.saveGame.setEnabled(true);
                critterCollection.clearAllCritters();
            }
        }
    }

    /**
     * detecting critters stole coins by reaching the exist point  
     */
    private void detectingCrittersStoleCoins() {
        for (Critter c : critterCollection.getCritters()) {
            if (c.getMovingBehavior() != null) {
                if (c.getMovingBehavior().isArrivedAtExit() && !c.isDonated()) {
                    coins --;

                    LoggerCollection.getInstance().addLog(
                        new Log(
                            LogType.Wave, 
                            "Now in wave " + currentWaveNum + ": A critter just stole a coin"
                        )
                    );

                    drawingDataPanelDelegate.reloadCoinDataView(coins);
                    c.setDonated(true);
                    if (coins == 0) gameShouldFinishedWithUserWin(false);
                }
            }
        }
    }

    /**
     * deals with the game ending actions
     */
    private void gameShouldFinishedWithUserWin() {
        clearGame();
        mainGameView.setVisible(false);
        new MainMenuController().mainMenuView.setVisible(true);
    }

    /**
     * deals with the game ending when the player win
     * @param win player won 
     */
    private void gameShouldFinishedWithUserWin(boolean win) {

        GameMapCollection mapCollection = GameMapCollection.loadMapsFromFile();
        gameMap.addScore(account.getBalance());

        if (win) {
            gameMap.addResultToMap("Win");
            JOptionPane.showMessageDialog(mainGameView, "Good Job! You win!");

            LoggerCollection.getInstance().addLog(
                new Log(
                    LogType.Map, 
                    "Player wins the game" + "Score: " + account.getBalance()
                )
            );

        } else {
            gameMap.addResultToMap("Lose");
            JOptionPane.showMessageDialog(mainGameView, "Sorry, You lose the game!");

            LoggerCollection.getInstance().addLog(
                new Log(
                    LogType.Map, 
                    "Player loses the game" + "Score: " + account.getBalance()
                )
            );
        }

        gameMap.clearStateToPureMapState(); // Important! -> In case chosen state or to place tower state are saved to json File
        mapCollection.getMaps().set(mapCollection.findGameMapInCollection(gameMap), gameMap);
        GameMapCollection.saveMapsToFile(mapCollection);

        showHighScoreList();

        mainGameView.setVisible(false);
        new MainMenuController().mainMenuView.setVisible(true);
        clearGame();

    }

    /**
     * to clear the game when it ends 
     */
    private void clearGame() {
        critterCollection = null;
        towerCollection = null;
        critterGeneratorTimer.stop();
        critterGeneratorTimer = null;
        paintingTimer.stop();
        paintingTimer = null;
        gameLogController.gameLogView.setVisible(false);
        gameLogController = null;
        currentTower = null;
        currentCritterIndex = 0;
        currentIndex = 0;
        currentWaveNum = 0;
        LoggerCollection.getInstance().clearAllLogs();
    }

    /**
     * initialize wave timer to build waves 
     */
    private void initWaveTimers() {

        critterGeneratorTimer = new Timer(CRITTER_GENERATE_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCritterIndex < critterCollection.getCritters().size()) {
                    Critter c = critterCollection.getCritters().get(currentCritterIndex);
                    c.setMovingBehavior(new CritterMovingBehavior(gameMap, c.getMovingSpeed()));
                    c.setVisible(true);
                    c.getMovingBehavior().move();
                    currentCritterIndex++;
                }
            }
        });
        critterGeneratorTimer.start();
    }

    /**
     * detecting critters in towers range
     */
    private void detectingCrittersInRange() {
        for (Tower t: towerCollection.getTowers().values()) {
            for (Critter c : critterCollection.getCritters()) {
                if (c.isVisible() && !c.isKilled()) {
                    if (c.getMovingBehavior().getCurrentPosition().distanceTo(t.getPosition()) <= t.getRange() / 2 ) {
                        t.getTowerShootingBehavior().getCrittersInRange().add(c);
                    } else {
                        t.getTowerShootingBehavior().getCrittersInRange().remove(c);
                    }
                    if (c.getCurrentHealth() <= 0) {
                        c.setKilled(true);

                        LoggerCollection.getInstance().addLog(
                            new Log(
                                LogType.Wave, 
                                "Now in wave " + currentWaveNum + ": A critter just got killed"
                            )
                        );

                        account.deposit(c.getWorth());
                        drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                        c.setVisible(false);
                    }
                }
            }

            if (!t.getTowerShootingBehavior().getCrittersInRange().isEmpty()) {
                t.getTowerShootingBehavior().setShooting(true);

                if (t.getTowerShootingBehavior().isTimeToShoot()) {
                    t.getTowerShootingBehavior().shoot();
                }
            } else {
                t.getTowerShootingBehavior().setShooting(false);
            }
        }
    }

    /**
     * shows the higher scores the player got by current map 
     */
    private void showHighScoreList() {
        ArrayList<Double> scoreList = gameMap.getFiveHighestScore();
        if (!scoreList.isEmpty()) {
            StringBuilder sb =new StringBuilder("<html>");

            for (int i = 1; i <= scoreList.size(); i++) {
                sb.append("<br>" + i + " : " + scoreList.get(i - 1));
            }
            sb.append("</html>");
            JOptionPane.showMessageDialog(mainGameView,
                    sb.toString(),
                    "Top ScoreList",
                    JOptionPane.PLAIN_MESSAGE,
                    null);
        } else {
            JOptionPane.showMessageDialog(
                mainGameView,
                "No score history of this map",
                "Top ScoreList",
                JOptionPane.PLAIN_MESSAGE,
                null
            );
        }
    }
    
    /**
     * save game info to file
     */
    private void saveGame() {
        
        GameCollection gameCollection = new GameCollection();
        try {
            gameCollection.loadGame();;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        boolean isReadyToCreate = true;
        if (!gameName.equals("")) {// old game
           
            GameInfo game = new GameInfo(
                towerCollection.getTowers(),
                LoggerCollection.getInstance().getLogList(),
                account.getBalance(),
                coins,
                currentWaveNum,
                gameName, 
                gameMap.getMapName()
            );

            gameCollection.getGames().set(gameCollection.findGameInCollection(gameName),game);
            try {
                gameCollection.saveGame();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainGameView, "Game Not saved!!!");
            }
            JOptionPane.showMessageDialog(mainGameView, "Saved Successful!");
        } else {//brand new map
            String userGameName = (String) JOptionPane.showInputDialog(mainGameView,
                    "Type in the game name:",
                    "Save map to file",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Game1");
            if (userGameName != null) { // if user choose cancel, mapName -> null
                if (!userGameName.equals("")) { // if the name is empty then it's invalidate

                    if (gameCollection != null) { // if the file already exits, check the filename and volume
                        int size = gameCollection.getGames().size();
                        for (int i = 0; i < size; i++) {
                            if (gameCollection.getGames().get(i).getGameName().equalsIgnoreCase(userGameName)) {
                                
                                String gameRename;// if they have the same name, please rename
                                do {
                                    gameRename = (String) JOptionPane.showInputDialog(mainGameView,
                                            "Already taken, please rename:",
                                            "Save map to file",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            null,
                                            "Game1");
                                } while (userGameName.equals(gameRename));
                                if (gameRename != null) userGameName = gameRename;
                                else isReadyToCreate = false;
                            }
                        }
                    } 
                }
            }

            if (isReadyToCreate) {
                GameInfo game = new GameInfo(
                    towerCollection.getTowers(),
                    LoggerCollection.getInstance().getLogList(),
                    account.getBalance(),
                    coins,
                    currentWaveNum,
                    userGameName,
                    gameMap.getMapName()
                );

                gameCollection.addGame(game);
                SavedGamesMaps mapCollection = SavedGamesMaps.loadMapsFromFile();
                if (mapCollection == null) {
                   mapCollection = new  SavedGamesMaps();
                }
                mapCollection.addMap(gameMap);
                mapCollection.saveMapsToFile(mapCollection);
                   
                   
                try {
                    gameCollection.saveGame();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(mainGameView, "Game Not saved!!!");
                }
                gameName = userGameName;   /// more then one save while playing the game 
            }
        }
    }
}