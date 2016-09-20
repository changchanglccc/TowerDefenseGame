package view;

import java.awt.*;
import javax.swing.*;

/**
 * Class to create windows 
 * @author yongpinggao 
 * @version 1.0 1/28/16.
 */
public class BaseWindowView extends JFrame {

    public final static int WINDOW_WIDTH = 1200;
    public final static int WINDOW_HEIGHT = 722;

    private int width;
    private int height;

    private String titleName;

    /**
     * Constructor for default window.
     * @param titleName windows title
     */
    public BaseWindowView(String titleName) {
        width = WINDOW_WIDTH;
        height = WINDOW_HEIGHT;
        this.titleName = titleName;
        initWindow();
    }
    
    /**
     * Constructor for custom sized window.
     * @param width window width
     * @param height window height
     * @param titleName title for window
     */
    public BaseWindowView(int width, int height, String titleName) {
        this.width = width;
        this.height = height;
        this.titleName = titleName;
        initWindow();
    }
    
    /**
     * Generates a window using default properties.
     */
    private void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(titleName);
        this.setPreferredSize(new Dimension(width,height));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
