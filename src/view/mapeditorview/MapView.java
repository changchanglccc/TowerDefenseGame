package view.mapeditorview;

import model.map.GameMap;
import protocol.DrawingMapDelegate;
import view.BaseWindowView;
import view.map.GameMapDrawing;

import javax.swing.*;
import java.awt.*;

/**
 * a class for the map view 
 * @author yongpinggao 
 * @since 3/12/16.
 * @version 1.0
 */
public class MapView extends JPanel {

    public MapPanel mapPanel;

    /**
     * A constructor MapView
     */
    public MapView() {
        setPreferredSize(new Dimension(BaseWindowView.WINDOW_WIDTH, BaseWindowView.WINDOW_HEIGHT / 10 * 9));
        mapPanel = new MapPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        add(mapPanel, c);
    }
    /**
     * class for drawing on map view 
     * @author yongpinggao 
     * @since 3/12/16.
     * @version 1.0
     */
    public class MapPanel extends JPanel implements DrawingMapDelegate {

        private GameMap map = new GameMap();
        /**
         * getter for the map view dimension 
         * @return view dimension
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GameMapDrawing.CELL_SIZE * map.getmCols(), GameMapDrawing.CELL_SIZE * map.getmRows());
        }
        /**
         * paint component 
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            GameMapDrawing.drawGameMap(g, map);
        }

        /**
         * Refresh map view and tower collection
         * @param map  game map.
         */
        @Override
        public void refreshMap(GameMap map) {
            this.map = map;
            mapPanel.revalidate();
            repaint();
        }
    }
}