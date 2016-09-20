package view.mapchooseview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.BaseWindowView;
/**
 * class for game choose view 
 * @author m_lzahra
 *@version 1.0 
 *@since  3/30/2016
 */
public class GameChooseView extends BaseWindowView {

    public JButton startGameButton;
    public JScrollPane pane;
    public JList list;

    /**
     * Constructs the list of options for games.
     * @param listModel listModel list of game options.
     */
    public GameChooseView(DefaultListModel listModel) {
        
        super(260, 200, "Choose a Game");
        startGameButton = new JButton("Start Game");
        list = new JList<>(listModel);
        list.setSelectedIndex(0);
        pane = new JScrollPane(list);
        setLayout(new BorderLayout());
        add(pane, BorderLayout.PAGE_START);
        add(startGameButton, BorderLayout.LINE_END);
    }
}