package view.mapeditorview;

import javax.swing.*;

/**
 * Class for the pop up use in the map editor
 * @author yongpinggao
 * @version  1.0 
 * @since 3/12/16
 */
public class PopupMenuView extends JPopupMenu {

    private final String OPTION_ENTRANCE = "Set as an Entrance";
    private final String OPTION_EXIT = "Set as an Exit";

    public JMenuItem menuItem1;
    public JMenuItem menuItem2;

    /**
     * Default constructor, sets two options.
     */
    public PopupMenuView() {
        menuItem1 = new JMenuItem(OPTION_ENTRANCE);
        menuItem2 = new JMenuItem(OPTION_EXIT);
        add(menuItem1);
        add(menuItem2);
    }
}