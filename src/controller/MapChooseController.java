package controller;

import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;
import model.map.GameMap;
import model.map.GameMapCollection;
import view.mapchooseview.MapChooseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;


/**
 * Controller for the map select view
 * @author yongpinggao
 * @version 1.0
 * @since 1.0 3/12/16.
 */
public class MapChooseController {

    MapChooseView mapChooseView;
    GameMapCollection mapCollection;
    DefaultListModel listModel;

    /**
     * Gets the list of saved maps for the user to select the one he wants to play or edit
     */
    public MapChooseController() {
        listModel = new DefaultListModel();
        mapCollection = GameMapCollection.loadMapsFromFile();
        
        for (int i = 0; i < mapCollection.getMaps().size(); i++) {
            listModel.addElement(mapCollection.getMaps().get(i).getMapName());
        }

        mapChooseView = new MapChooseView(listModel);
        
        /**
         * Sets listener to start game
         */
        mapChooseView.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapChooseView.setVisible(false);
                GameMapCollection mapCollection = GameMapCollection.loadMapsFromFile();
                GameMap gameMap = mapCollection.getMaps().get(mapChooseView.list.getSelectedIndex());
                new MainGameController(gameMap).mainGameView.setVisible(true);
            }
        });

        /**
         * Sets listener to edit map
         */
        mapChooseView.editMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapChooseView.setVisible(false);
                new MapEditorController(mapCollection.getMaps().get(mapChooseView.list.getSelectedIndex())).mapEditorView.setVisible(true);
            }
        });
    }
}