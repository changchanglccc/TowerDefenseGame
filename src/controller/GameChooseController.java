package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.savegame.GameCollection;
import view.mapchooseview.GameChooseView;

/**
 * Controller for the game select view.
 * @author M_lzahra
 * @version 1.0
 * @since 3/29/16
 */
public class GameChooseController {

    GameChooseView gameChooseView;
    DefaultListModel listModel;
    GameCollection gameCollection;

    /**
     * A constructor for GameChooseController
     */
    public GameChooseController() {
        listModel = new DefaultListModel();
        gameCollection = new GameCollection();
        try {
            gameCollection.loadGame();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(gameChooseView, "Cannot Read for File");
            new MainMenuController().mainMenuView.setVisible(true);
        }
        
        int size = gameCollection.getGames().size();
        if (size > 0) {
            
            for (int i = 0; i < size; i++) {
                listModel.addElement(gameCollection.getGames().get(i).getGameName());
            }

            gameChooseView = new GameChooseView(listModel);
            gameChooseView.setVisible(true);
                
            /**
             * Sets listener to start game
             */
            gameChooseView.startGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameChooseView.setVisible(false);
                    new MainGameController(gameCollection.getGames().get(gameChooseView.list.getSelectedIndex())).mainGameView.setVisible(true);
                 }
             });

        } else {
            JOptionPane.showMessageDialog(gameChooseView, "There is no Games please play and save");
            new MainMenuController().mainMenuView.setVisible(true);
        }
    }
}