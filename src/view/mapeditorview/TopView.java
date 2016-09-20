package view.mapeditorview;

import view.BaseWindowView;
import java.awt.*;
import javax.swing.*;

/**
 * Class for the top view, use in the game play view.
 * @author  yongpinggao
 * @version  1.0 
 * @since 3/12/16
 */
public class TopView extends JPanel {

    public JComboBox widthList;
    public JComboBox heightList;
    public JLabel widthLabel;
    public JLabel heightLabel;
    public JButton saveButton;
    public JButton discardButton;

    public final static String[] widthStrings = {"5","10","15","20","25","30"};
    public final static String[] heightStrings = {"10","15"};

    /**
     * Default constructor, sets default color, size and all labels.
     */
    public TopView() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(BaseWindowView.WINDOW_WIDTH, BaseWindowView.WINDOW_HEIGHT / 10));
        initComponents();
    }

    /**
     * Adds all the labels to the top view.
     */
    void initComponents() {
        widthList = new JComboBox(widthStrings);
        heightList = new JComboBox(heightStrings);
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");
        saveButton = new JButton("Save");
        discardButton = new JButton("Discard");

        // Flow Layout: Horizontal
        this.add(widthLabel);
        this.add(widthList);
        this.add(heightLabel);
        this.add(heightList);
        this.add(saveButton);
        this.add(discardButton);
    }
}