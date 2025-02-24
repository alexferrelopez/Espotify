package Persistence.SQL;

import Business.Playlist;
import Persistence.DAO.PlaylistDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class in charge of controlling the connection to the database table Playlist table in the database.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SQLPlaylistDAO implements PlaylistDAO {
    /**
     * Method that saves a specific playList, persisting it.
     *
     * @param playList The new playList to save.
     */
    @Override
    public void addPlaylist(Playlist playList) {
        String query = "INSERT INTO PlayList (ID_User, Name) VALUES ('" +
                playList.getIdUser() + "', '" +
                playList.getNamePlaylist() + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Method that reads the persisted information, returning all stored playlist.
     *
     * @return Returns a list of all playlists.
     */
    @Override
    public List<Playlist> getAllPlaylist(int idUser, int option) {
        List<Playlist> playLists = new LinkedList<>();
        String query = "SELECT * FROM PlayList ";

        if (option == 1) {
            query = query + "WHERE ID_User = '" + idUser + "';";
        } else {
            query = query + "WHERE ID_User != '" + idUser + "';";
        }
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                int id_PlayList = resultSet.getInt("ID_PlayList");
                int id_User = resultSet.getInt("ID_User");
                String name = resultSet.getString("Name");
                playLists.add(new Playlist(id_PlayList, id_User, name));
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return playLists;
    }

    /**
     * Method to see if a playlist exists or not, returning a message with the result.
     *
     * @param idUser ID User.
     * @param name   Name Playlist.
     * @return Returns a message with the result.
     */
    @Override
    public String playlistExists(int idUser, String name) {
        String query = "SELECT * FROM PlayList WHERE ID_User = '" + idUser + "'";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                String getName = resultSet.getString("Name");
                // teorícamente solo pasaría una vez
                if (name.equals(getName)) {
                    return "playlistExist";
                }
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return "true";
    }
}