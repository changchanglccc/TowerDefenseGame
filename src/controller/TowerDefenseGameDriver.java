package controller;

import javax.swing.*;

/**
 * Game driver, game will run from this point
 * @author yongpinggao 
 * @version 1.0 1/24/16.
 */
public class TowerDefenseGameDriver {
    
    /**
     * executable
     * @param args get arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuController().mainMenuView.setVisible(true);
            }
        });
    }
}
