package Business;

import Persistence.DAO.MusicDAO;
import Persistence.SQL.SQLMusicDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Class that controls the music, MusicManager.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class MusicManager {
    private final MusicDAO musicDAO;

    /**
     * Constructor empty.
     * Used to start the MusicDAO
     */
    public MusicManager() {
        this.musicDAO = new SQLMusicDAO();
    }

    /**
     * Method used to sort a list of Map.
     *
     * @param listGender List to order.
     * @return Returns the sorted list.
     */
    private Map<String, Integer> sortByValue(Map<String, Integer> listGender) {
        return listGender.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * We use this method to add a song to the database.
     */
    public void addMusic(String name, String gender, String album, String filePath, int idOwner, int idSinger) {
        musicDAO.addMusic(new Music(idOwner, idSinger, name, gender, album, filePath));
    }

    /**
     * Method to get all the songs.
     *
     * @return Returns a list of music.
     */
    public List<Music> getAllMusic() {
        return musicDAO.getAllMusic();
    }

    /**
     * Method to get the list of songs in a playlist.
     *
     * @param nameMyPlaylist Name playlist.
     * @return Returns the list of songs in the playlist.
     */
    public List<Music> getMyMusic(String nameMyPlaylist) {
        return musicDAO.getMyMusic(nameMyPlaylist);
    }

    /**
     * We use this method to delete a song.
     *
     * @param idSong ID music of the song to delete.
     */
    public boolean deleteSong(int idSong, String filePath) {
        boolean b = deleteSongFile(filePath);
        musicDAO.deleteMusic(idSong);
        return b;
    }

    /**
     * Method used to update the data of a music.
     * We use it to insert the duration of the music.
     *
     * @param nameMusic Name music.
     * @param duration  Duration
     */
    public void updateMusic(String nameMusic, String duration) {
        musicDAO.updateQuery(nameMusic, duration);
    }

    /**
     * Method used to create the folder where we store all the songs.
     *
     * @return Returns ture if created and false if already created.
     */
    private boolean createFile() {
        File directorio = new File("FileSongs");
        boolean mkdirs = false;
        if (!directorio.exists()) {
            mkdirs = directorio.mkdirs();
        }
        return mkdirs;
    }

    /**
     * Method to add the song to the folder.
     *
     * @param filePath File Path of the song
     * @return If the folder is not created it returns false and if it is created it adds the music and returns true.
     */
    public boolean addSongToFile(String filePath) {
        // Si la capeta no está creada que lo cree.
        boolean fileCreatedSuccessfully = createFile();

        String destinationPath = "FileSongs/";                  // Destination file path
        File sourceFile = new File(filePath);                   // Creating a Source File

        //Creating A Destination File. Name stays the same this way, referring to getName()
        File destinationFile = new File(destinationPath + sourceFile.getName());
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
            fileCreatedSuccessfully = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return fileCreatedSuccessfully;
    }

    /**
     * Method used to search for a song.
     *
     * @param musicDetails List of the song data to be searched for.
     * @return Returns the found song.
     */
    public Music searchSong(List<String> musicDetails) {
        for (Music music : getAllMusic()) {
            if (music.getName().equals(musicDetails.get(0)) &&
                    music.getAlbum().equals(musicDetails.get(1)) &&
                    music.getGender().equals(musicDetails.get(2)) &&
                    music.getSinger().equals(musicDetails.get(3)) &&
                    music.getOwner().equals(musicDetails.get(4))
            ) {
                return music;
            }
        }
        return null;
    }

    /**
     * Method for deleting a song file.
     *
     * @param filePath Path of the song to delete.
     * @return Returns true if already removed.
     */
    private boolean deleteSongFile(String filePath) {
        File file = new File(filePath);
        boolean delete = false;
        if (file.exists()) {
            delete = file.delete();
        }
        return delete;
    }

    /**
     * Method to delete the files of all songs uploaded by a user.
     *
     * @param musicPathList List of all song paths to be deleted.
     */
    public void deleteAllSongFile(List<String> musicPathList) {
        for (String musicPath : musicPathList) {
            File file = new File(musicPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Method used to verify whether music is already created using the music title.
     *
     * @param title Title of the music to be checked.
     * @return Returns true if found and false otherwise
     */
    public boolean isValidSong(String title) {
        return musicDAO.musicExists(title);
    }

    /**
     * Method for creating a gender list for use in statistics.
     *
     * @return Returns the gender list.
     */
    public Map<String, Integer> listGender() {
        List<String> listGender = new ArrayList<>(getAllMusic().stream().map(Music::getGender).map(String::trim).toList());
        Set<String> set = new HashSet<>(listGender);
        Map<String, Integer> listMap = new HashMap<>();
        for (String r : set) {
            listMap.put(r, Collections.frequency(listGender, r));
        }
        return sortByValue(listMap);
    }

    /**
     * Method for making Lyrics.
     *
     * @param singer        Singer.
     * @param song          Song.
     * @param lyricConsumer Lyric Consumer.
     * @param album         Album
     */
    public void getSongLyrics(String singer, String song, Consumer<String> lyricConsumer, String album) {
        SongLyrics songLyrics = new SongLyrics(singer, song, lyricConsumer, album);
        Thread thread = new Thread(songLyrics);
        thread.start();
    }
}
