package view.mapeditorview;

import java.awt.*;
import view.BaseWindowView;

/**
 * Class for the map edition.
 * @author yongpinggao 
 * @version 1.0 
 * @since 1/24/16
 */
public class MapEditorView extends BaseWindowView {

    public TopView topView;
    public MapView mapView;

    /**
     * Default constructor, starts view panels.
     */
    public MapEditorView() {
        super("Map Editor");
        topView = new TopView();
        mapView = new MapView();

        Container c = this.getContentPane();
        c.add(topView, BorderLayout.PAGE_START);
        c.add(mapView, BorderLayout.LINE_START);
    }
}