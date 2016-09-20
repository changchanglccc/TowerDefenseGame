package model.map;

import utility.FileProcessing;

import java.util.ArrayList;

/**
 * GameMapCollection class
 * store all game map that user created
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public class GameMapCollection {
    private static String JSON_FILE = "maps.json";
    private ArrayList<GameMap> maps;
    /**
     * constructor
     * create a new arraylist
     */
    public GameMapCollection() {
        this.maps = new ArrayList<>();
    }

    /**
     * Get maps list.
     * @return
     */
    public ArrayList<GameMap> getMaps() {
        return maps;
    }
    /**
     * Add map to maps list.
     * @param map
     */
    public void addMap(GameMap map) {
        this.maps.add(map);
    }
    /**
     * Delete a map from maps list.
     * @param index it represents the order of specific map
     */
    public void deleteMap(int index) {
        this.maps.remove(index);
    }
    /**
     * Save maps to file.
     * @param maps it represents who will be saved
     * @return save map into a json file
     */
    public static boolean saveMapsToFile(GameMapCollection maps) {
        return FileProcessing.sharedInstance().writeToJsonFile(JSON_FILE, maps);
    }
    /**
     * Load maps from file.
     * @return read Json file
     */
    public static GameMapCollection loadMapsFromFile() {
        return FileProcessing.sharedInstance().readFromJsonFile(JSON_FILE, GameMapCollection.class);
    }
    /**
     * Find game map in collection
     * @param gameMap the specific game map based on its name
     * @return mapcollection's index
     */
    public int findGameMapInCollection(GameMap gameMap) { // based on map name
        int index = 0;
        for (int i = 0; i < maps.size(); i++) {
            if (gameMap.getMapName().equals(maps.get(i).getMapName())) {
                index = i;
                break;
            }
        }
        return index;
    }
}