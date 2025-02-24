package Persistence.DAO;

import Business.Playlist;

import java.util.List;

/**
 * Interface that abstracts the persistence of playLists from the rest of the code.
 * <p>
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public interface PlaylistDAO {
    /**
     * Method that saves a specific playList, persisting it.
     *
     * @param playList The new playList to save.
     */
    void addPlaylist(Playlist playList);

    /**
     * Method that reads the persisted information, returning all stored playlist.
     *
     * @return Returns a list of all playlists.
     */
    List<Playlist> getAllPlaylist(int idUser, int option);

    /**
     * Method to see if a playlist exists or not, returning a message with the result.
     *
     * @param idUser ID User.
     * @param name   Name Playlist.
     * @return Returns a message with the result.
     */
    String playlistExists(int idUser, String name);
}
