package Persistence.SQL;

import Persistence.DAO.MusicPlayListDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class in charge of controlling the connection to the database table MusicPlaylist table in the database.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SQLMusicPlaylistDAO implements MusicPlayListDAO {
    /**
     * Method to add a song to a playlist.
     *
     * @param idPlaylist Playlist ID.
     * @param idMusic    Music ID.
     */
    @Override
    public void addMusicPlayListDAO(int idPlaylist, int idMusic) {
        String query = "INSERT INTO MusicPlayList (ID_PlayList, ID_Music) VALUES ('" +
                idPlaylist + "', '" +
                idMusic + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Method to check if your music is already in the playlist.
     *
     * @param idPlaylist Playlist ID.
     * @param idMusic    Music ID.
     * @return Returns false if the music exists, otherwise it returns true.
     */
    @Override
    public boolean existMusicPlayListDAO(int idPlaylist, int idMusic) {
        String query = "SELECT * FROM MusicPlayList WHERE ID_PlayList = '" + idPlaylist + "' and ID_Music = '" + idMusic + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return true;
    }

    /**
     * Method that deletes a persisted music.
     *
     * @param namePlayList Name of the playlist to delete.
     * @param idMusic      ID of the music to delete.
     */
    @Override
    public void deleteMusicPlayListDAO(String namePlayList, int idMusic) {
        String query = "DELETE MusicPlayList.* FROM MusicPlayList JOIN PlayList ON PlayList.ID_PlayList = MusicPlayList.ID_PlayList " +
                "WHERE PlayList.Name = '" + namePlayList + "' and ID_Music = '" + idMusic + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }
}