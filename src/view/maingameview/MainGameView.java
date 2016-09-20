package view.maingameview;

import view.BaseWindowView;
import java.awt.*;

/**
 * Class to define the view for game play.
 * @author yongpinggao 
 * @version 2.0  
 * @since 3/13/16.
 * 
 */
public class MainGameView extends BaseWindowView {

    private int topAreaWidth;
    private int topAreaHeight;
    private int endAreaWidth;
    private int endAreaHeight;
    private int mapAreaWidth;
    private int mapAreaHeight;

    public TopView topView;
    public EndView endView;
    public MapView mapView;

    /**
     * Constructor sets all views used in the game view.
     */
    public MainGameView() {
        super("Tower Defense Game");
        topView = new TopView();
        endView = new EndView();
        mapView = new MapView();
        initPanel();
    }

    /**
     * Creates game view and define positioning of the other panels.
     */
    private void initPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(null);
        topAreaWidth = WINDOW_WIDTH;
        topAreaHeight = WINDOW_HEIGHT / 5;
        endAreaWidth = WINDOW_WIDTH/ 5;
        endAreaHeight =  WINDOW_HEIGHT - topAreaHeight;
        mapAreaWidth = WINDOW_WIDTH - endAreaWidth;
        mapAreaHeight = WINDOW_HEIGHT - topAreaHeight;
        add(topView);
        add(mapView);
        add(endView);
        Insets insets = this.getInsets();
        topView.setBounds(insets.left, insets.top, topAreaWidth, topAreaHeight);
        mapView.setBounds(insets.left, insets.top + topAreaHeight, mapAreaWidth, mapAreaHeight);
        endView.setBounds(insets.left + mapAreaWidth, insets.top + topAreaHeight, endAreaWidth, endAreaHeight);
    }
}
