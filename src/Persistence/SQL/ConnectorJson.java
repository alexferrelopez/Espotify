package Persistence.SQL;

import Business.ConfigJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class takes care of making the connection with json.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
class ConnectorJson {
    private static final String path = "src/config.json";
    private final Gson gson;
    private ConfigJson configJson;

    /**
     * Class used for reading the config.json file
     * Which is used to get the data needed for the database connection.
     */
    public ConnectorJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            configJson = gson.fromJson(gson.newJsonReader(new FileReader(path)), ConfigJson.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading the file.
     *
     * @return Returns a string array with all data.
     */
    public String[] readPlayer() {
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(path));
            configJson = gson.fromJson(jsonReader, ConfigJson.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] jsonData = new String[5];
        jsonData[0] = configJson.getUsername();
        jsonData[1] = configJson.getPassword();
        jsonData[2] = configJson.getIp();
        jsonData[3] = String.valueOf(configJson.getPort());
        jsonData[4] = configJson.getDatabase();
        return jsonData;
    }
}
