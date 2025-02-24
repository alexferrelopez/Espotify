package Persistence.DAO;

/**
 * Interface that abstracts the persistence of playLists from the rest of the code.
 * <p>
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public interface MusicPlayListDAO {
    /**
     * Method to add a song to a playlist.
     *
     * @param idPlaylist Playlist ID.
     * @param idMusic    Music ID.
     */
    void addMusicPlayListDAO(int idPlaylist, int idMusic);

    /**
     * Method to check if your music is already in the playlist.
     *
     * @param idPlaylist Playlist ID.
     * @param idMusic    Music ID.
     * @return Returns false if the music exists, otherwise it returns true.
     */
    boolean existMusicPlayListDAO(int idPlaylist, int idMusic);

    /**
     * Method that deletes a persisted music.
     *
     * @param namePlayList Name of the playlist to delete.
     * @param idMusic      ID of the music to delete.
     */
    void deleteMusicPlayListDAO(String namePlayList, int idMusic);
}
