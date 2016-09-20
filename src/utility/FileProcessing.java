package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

/**
 * File processing class
 * save and load map from json file
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public class FileProcessing {

    // Singleton
    private static FileProcessing instance = new FileProcessing();
    public static FileProcessing sharedInstance() {
        return instance;
    }

    private static Gson gson;
    /**
     * Constructor for FileProcessing
     */
    private FileProcessing() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * write to json file
     * @param fileName file name 
     * @param t general type
     * @return true if it is successful
     */
    public <T> boolean writeToJsonFile(String fileName, T t) {
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(gson.toJson(t));
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }

    /**
     * read from json file
     * @param fileName 
     * @param t general type
     * @return true if it is successful
     */
    public <T> T readFromJsonFile(String fileName, Class<T> _class) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                T t = gson.fromJson(br, _class);
                return t;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }
}
