package Persistence.DAO;

import Business.Music;

import java.util.List;

/**
 * Interface that abstracts persistence of music from the rest of the code.
 * <p>
 * In particular, it follows the data access object design pattern,
 * which is commonly used to abstract persistence implementations with a set of operations.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public interface MusicDAO {
    /**
     * Method that saves a music.
     *
     * @param music Music to add.
     */
    void addMusic(Music music);

    /**
     * Method that reads persistent information, returning all stored music.
     *
     * @return A list containing all persistent music.
     */
    List<Music> getAllMusic();

    /**
     * A method that reads persistent information, returning all stored music from a particular playlist.
     *
     * @param nameMyPlaylist Name of the playlist.
     * @return Returns a list of all the songs in this playlist.
     */
    List<Music> getMyMusic(String nameMyPlaylist);

    /**
     * Method used to verify whether music is already created using the music title.
     *
     * @param title Title of the music to be checked.
     * @return Returns true if found and false otherwise.
     */
    boolean musicExists(String title);

    /**
     * Method for updating the music.
     * Used to insert the duration of the music.
     *
     * @param nameMusic Name of the music.
     * @param duration  The duration of the music.
     */
    void updateQuery(String nameMusic, String duration);

    /**
     * Method that deletes a persisted music, by its id.
     *
     * @param idSong The id of music.
     */
    void deleteMusic(int idSong);
}
