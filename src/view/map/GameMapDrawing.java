package view.map;

import model.map.CellState;
import model.map.GameMap;
import java.awt.*;
import java.util.ArrayList;


/**
 * a class for drawing the game map 
 * @author yongpinggao 
 * @since 1/29/16.
 * @version 2.0 
 */
public class GameMapDrawing extends Drawing {

    /**
     * drawing the game map 
     * @param g graphics 
     * @param map the game map 
     */
    public static void drawGameMap(Graphics g, GameMap map) {
        Graphics2D g2d = (Graphics2D) g.create();
        ArrayList<CellState> cellList = map.getCells();
        int mapCols = map.getmCols();
        for (int i = 0; i < CELL_SIZE * map.getmCols(); i = i + CELL_SIZE) {
            for (int j = 0; j < CELL_SIZE * map.getmRows(); j = j + CELL_SIZE) {
                switch (cellList.get(coordinateToIndexConverter(i, j, mapCols))) {
                    case Grass:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, null);
                        break;
                    case Path:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Path), i, j, null);
                        break;
                    case Entrance:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Entrance), i, j, null);
                        break;
                    case Exit:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Exit), i, j, null);
                        break;
                    case ToPlaceTower:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.ToPlaceTower), i, j, null);
                        break;

                    case Chosen:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, null);
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Chosen), i, j, null);
                        break;
                    default:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, null);
                        break;
                }
            }
        }
        g2d.dispose();
    }
}