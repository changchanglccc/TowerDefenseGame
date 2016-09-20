package controller;

import model.map.GameMap;
import model.map.GameMapCollection;
import view.mainmenuview.MainMenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the first menu where player can select to play or create a map.
 * @author yongpinggao 
 * @version 0.1 1/24/16.
 */
public class MainMenuController {

    MainMenuView mainMenuView;
    
    /**
     * Creates listeners for options in the main menu of the game.
     */
    public MainMenuController() {
        mainMenuView = new MainMenuView();
        
        /**
         * Sets listener for opening the map editor.
         */
        mainMenuView.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuView.setVisible(false);
                new MapEditorController(new GameMap()).mapEditorView.setVisible(true);
            }
        });
        
        /**
         * Sets listener for starting the game view.
         */
        mainMenuView.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuView.setVisible(false);
                if (GameMapCollection.loadMapsFromFile() == null) {

                    JOptionPane.showMessageDialog(
                        mainMenuView, 
                        "No Saved Maps, please go to create a new map",
                        "Error",
                        JOptionPane.YES_OPTION
                    );
                    
                    mainMenuView.setVisible(false);
                    new MapEditorController(new GameMap()).mapEditorView.setVisible(true);
                } else {
                    mainMenuView.setVisible(false);
                    new MapChooseController().mapChooseView.setVisible(true);
                }
            }
        });
        /**
         * Sets listener for starting the game view.
         */
        mainMenuView.loadGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    mainMenuView.setVisible(false);
                    new GameChooseController().gameChooseView.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                
            }
    });
}
}