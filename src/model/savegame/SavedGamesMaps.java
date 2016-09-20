package model.savegame;

import java.util.ArrayList;

import model.map.GameMap;


import utility.FileProcessing;
/**
 * SavedGamesMaps class
 * store all game  that user saved
 * @author Mansour Alzahrani
 * @since 3/30/16.
 * @version 2.0 
 */
public class SavedGamesMaps {        
    private static  String JSON_FILE = "GamesMaps.json";
    private ArrayList<GameMap> maps;

    /**
     * constructor
     * create a new arraylist
     */
    public SavedGamesMaps() {
        this.maps = new ArrayList<>();
    }

    /**
     * getter for all maps
     * @return ArrayList maps
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
    public static boolean saveMapsToFile(SavedGamesMaps maps) {
        return FileProcessing.sharedInstance().writeToJsonFile(JSON_FILE, maps);
    }
    
    /**
     * Load maps from file.
     * @return read Json file
     */
    public static SavedGamesMaps loadMapsFromFile() {
        return FileProcessing.sharedInstance().readFromJsonFile(JSON_FILE, SavedGamesMaps.class);
    }

}

