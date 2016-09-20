package view.mapchooseview;

import java.awt.*;
import javax.swing.*;
import view.BaseWindowView;

/**
 * Class for the map selection menu
 * @author yongpinggao
 * @version 1.0
 * @since  3/12/16
 */
public class MapChooseView extends BaseWindowView {

    public JButton startGameButton;
    public JButton editMapButton;
    public JScrollPane pane;
    public JList list;

    /**
     * Constructs the list of options for maps.
     * @param  listModel list of map options.
     */
    public MapChooseView(DefaultListModel listModel) {
        
        super(260, 200, "Choose a Map");
        startGameButton = new JButton("Start Game");
        editMapButton = new JButton("Edit the Map");
        list = new JList<>(listModel);
        list.setSelectedIndex(0);
        pane = new JScrollPane(list);

        setLayout(new BorderLayout());
        add(pane, BorderLayout.PAGE_START);
        add(editMapButton, BorderLayout.LINE_START);
        add(startGameButton, BorderLayout.LINE_END);
       
    }
}