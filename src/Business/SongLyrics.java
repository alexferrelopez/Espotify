package Business;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
class SongLyrics implements Runnable {
    private static final String LYRICS_NOT_AVAILABLE = "No esta disponible en este momento la letra";
    private static final String apiKey = "&apikey=c407eea86ce11df0cbb667b9c6c97812";
    private final String BASE_URL = "https://api.musixmatch.com/ws/1.1/";
    private final Consumer<String> lyricsConsumer;
    private final String singer;
    private final String song;
    private final String album;
    private String URL = "https://api.lyrics.ovh/v1/";

    /**
     * Constructor to create the runnable.
     *
     * @param singer        singer of the song we are searching the lyrics of.
     * @param song          name of the song we are searching the lyrics of.
     * @param lyricConsumer consumer used to give back the lyrics to the controller and ultimately the view.
     * @param album         album of the song we are searching the lyrics of.
     */
    public SongLyrics(String singer, String song, Consumer<String> lyricConsumer, String album) {
        this.singer = singer;
        this.song = song;
        this.lyricsConsumer = lyricConsumer;
        this.album = album;
    }

    /**
     * Method to search for the lyrics
     */
    @Override
    public void run() {
        //getLyricsFromAPIPocha();
        getLyricsFromMusixmatch();
    }

    /**
     * Method that establishes a connection with the api: <a href="https://api.lyrics.ovh/v1/">...</a>
     * and gets the lyrics very, very slowly.
     */
    private void getLyricsFromAPIPocha() {
        String singer_name = singer.replace(" ", "%20");
        String song_name = song.replace(" ", "%20");
        URL = URL + singer_name + "/" + song_name;
        HttpURLConnection conn = null;
        try {
            java.net.URL url = new URL(URL);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());

                if (scan.hasNext()) {
                    String lyrics = scan.nextLine();

                    JsonElement element = JsonParser.parseString(lyrics);
                    JsonObject object = element.getAsJsonObject();
                    lyrics = object.get("lyrics").getAsString();

                    lyricsConsumer.accept(lyrics);
                } else lyricsConsumer.accept(LYRICS_NOT_AVAILABLE);
            }
        } catch (IOException e) {
            lyricsConsumer.accept(LYRICS_NOT_AVAILABLE);
        } finally {
            lyricsConsumer.accept(LYRICS_NOT_AVAILABLE);
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Method that establishes a connection with the API: <a href="https://api.musixmatch.com/ws/1.1/">...</a>
     * This API is about 10 times faster than the API above.
     */
    private void getLyricsFromMusixmatch() {
        String urlSearch = BASE_URL + "track.search?" + "q_track_artist=" + URLEncoder.encode(song, StandardCharsets.UTF_8) + "%20" + URLEncoder.encode(singer, StandardCharsets.UTF_8) + "&q_album=" + URLEncoder.encode(album, StandardCharsets.UTF_8) + "&s_track_rating=desc&f_has_lyrics=true" + apiKey;
        //System.out.println(urlSearch);
        JsonElement jsonElement = getJsonElement(urlSearch);

        String s = getTrackID(jsonElement)
                .map(trackID -> BASE_URL + "track.lyrics.get?track_id=" + trackID + apiKey)
                .map(this::getJsonElement)
                .flatMap(this::getLyrics)
                .orElse(LYRICS_NOT_AVAILABLE);

        lyricsConsumer.accept(s);
    }

    /**
     * Method to get the trackID from a list of possible tracks coinciding with our fields.
     *
     * @param jsonElement json element to be traversed.
     * @return id of the track we will search the lyrics of.
     */
    private Optional<String> getTrackID(JsonElement jsonElement) {
        JsonObject object = jsonElement.getAsJsonObject();
        return Optional.ofNullable(object.get("message"))
                .map(o -> o.getAsJsonObject().get("body"))
                .filter(JsonElement::isJsonObject)
                .map(o -> o.getAsJsonObject().get("track_list"))
                .filter(JsonElement::isJsonArray)
                .map(JsonElement::getAsJsonArray)
                .filter(a -> !a.isEmpty())
                .map(o -> o.get(0))
                .map(o -> o.getAsJsonObject().get("track"))
                .map(o -> o.getAsJsonObject().get("track_id"))
                .map(JsonElement::getAsString);
    }

    /**
     * Method to obtain the lyrics as an optional from a json element obtained from an API request.
     *
     * @param jsonElement2 json element to be traversed.
     * @return optional of the lyrics to be returned. We know that the lyrics exist since specify it in the API call.
     */
    private Optional<String> getLyrics(JsonElement jsonElement2) {
        JsonObject object1 = jsonElement2.getAsJsonObject();
        return Optional.ofNullable(object1.get("message"))
                .map(o -> o.getAsJsonObject().get("body"))
                .filter(JsonElement::isJsonObject)
                .map(o -> o.getAsJsonObject().get("lyrics"))
                .map(o -> o.getAsJsonObject().get("lyrics_body"))
                .map(JsonElement::getAsString);
    }

    /**
     * Method to establish a connection with the API with a given URL.
     *
     * @param url url of a specific request.
     * @return jsonElement containing all the data given from the server.
     */
    private JsonElement getJsonElement(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            // getting JSON string from URL
            return JsonParser.parseReader(rd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
