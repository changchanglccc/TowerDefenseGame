package view.mainmenuview;

import java.awt.*;
import javax.swing.*;

/**
 * A class static functions to converter coordinate system from x and y coordinate to index and vice versa.  
 * @author yongpinggao  
 * @since 3/15/16.
 * @version 2.0
 */
public class MainMenuView extends JFrame {

    private final static int WINDOW_WIDTH = 400;
    private final static int WINDOW_HEIGHT = 222;
    private final String MENU_TITLE = "Defense of the Tower";
    public JButton editorButton;
    public JButton startGameButton;
    public JButton loadGame;


    /**
     * Default constructor, calls initializing  method .
     */
    public MainMenuView() {
        initMenuWindow();
    }

    /**
     * Creates the menu with default info.
     */
    private void initMenuWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(MENU_TITLE);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        editorButton = new JButton("Create New Map");
        startGameButton = new JButton("Start Game");
        loadGame = new JButton("Load Game");
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        c.add(editorButton, new GridBagConstraints());
        c.add(loadGame, new GridBagConstraints());
        c.add(startGameButton, new GridBagConstraints());
    }
}