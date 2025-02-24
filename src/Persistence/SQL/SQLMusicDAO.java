package Persistence.SQL;

import Business.Music;
import Persistence.DAO.MusicDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class in charge of controlling the connection to the Music table in the database.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SQLMusicDAO implements MusicDAO {
    /**
     * Method that saves a music.
     *
     * @param music Music to add.
     */
    @Override
    public void addMusic(Music music) {
        String query = "INSERT INTO Music (ID_Owner, ID_Singer, Name, Gender, Album, Duration, URL) VALUES ('" +
                music.getIdOwner() + "', '" +
                music.getIdSinger() + "', '" +
                music.getName() + "', '" +
                music.getGender() + "', '" +
                music.getAlbum() + "', '" +
                "00:00" + "', '" +
                music.getUrl() + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Method that reads persistent information, returning all stored music.
     *
     * @return A list containing all persistent music.
     */
    @Override
    public List<Music> getAllMusic() {
        List<Music> musicList = new ArrayList<>();
        String query = "SELECT User.Login, Music.*, Singer.Name AS NameSinger FROM User " +
                "JOIN Music ON User.ID_User = Music.ID_Owner JOIN Singer ON Music.ID_Singer = Singer.ID_Singer;";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                int id_Music = resultSet.getInt("ID_Music");
                int id_Singer = resultSet.getInt("ID_Singer");
                int id_Owner = resultSet.getInt("ID_Owner");
                String name = resultSet.getString("Name");
                String nameSinger = resultSet.getString("NameSinger");
                String nameOwner = resultSet.getString("Login");
                String gender = resultSet.getString("Gender");
                String album = resultSet.getString("Album");
                String duration = resultSet.getString("Duration");
                String url = resultSet.getString("URL");
                musicList.add(new Music(id_Music, id_Singer, id_Owner, name, nameSinger, nameOwner, gender, album, duration, url));
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return musicList;
    }

    /**
     * A method that reads persistent information, returning all stored music from a particular playlist.
     *
     * @param nameMyPlaylist Name of the playlist.
     * @return Returns a list of all the songs in this playlist.
     */
    @Override
    public List<Music> getMyMusic(String nameMyPlaylist) {
        List<Music> musicList = new ArrayList<>();
        String query = "SELECT User.Login, Music.*, Singer.Name AS NameSinger, PlayList.Name AS NamePlaylist FROM PlayList " +
                "JOIN MusicPlayList ON PlayList.ID_PlayList = MusicPlayList.ID_PlayList " +
                "JOIN Music ON MusicPlayList.ID_Music = Music.ID_Music JOIN User ON User.ID_User = Music.ID_Owner " +
                "JOIN Singer ON Singer.ID_Singer = Music.ID_Singer WHERE PlayList.Name = '" + nameMyPlaylist + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                int id_Music = resultSet.getInt("ID_Music");
                int id_Singer = resultSet.getInt("ID_Singer");
                int id_Owner = resultSet.getInt("ID_Owner");
                String name = resultSet.getString("Name");
                String nameSinger = resultSet.getString("NameSinger");
                String nameOwner = resultSet.getString("Login");
                String gender = resultSet.getString("Gender");
                String album = resultSet.getString("Album");
                String duration = resultSet.getString("Duration");
                String url = resultSet.getString("URL");
                musicList.add(new Music(id_Music, id_Singer, id_Owner, name, nameSinger, nameOwner, gender, album, duration, url));
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return musicList;
    }

    /**
     * Method used to verify whether music is already created using the music title.
     *
     * @param title Title of the music to be checked.
     * @return Returns true if found and false otherwise.
     */
    @Override
    public boolean musicExists(String title) {
        //USAR EL LIKE
        String query = "SELECT * FROM Music WHERE Name = '" + title + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return false;
    }

    /**
     * Method for updating the music.
     * Used to insert the duration of the music.
     *
     * @param nameMusic Name of the music.
     * @param duration  The duration of the music.
     */
    @Override
    public void updateQuery(String nameMusic, String duration) {
        String query = "UPDATE Music SET Duration = '" + duration + "' WHERE Name = '" + nameMusic + "';";
        SQLConnector.getInstance().updateQuery(query);
    }

    /**
     * Method that deletes a persisted music, by its id.
     *
     * @param idSong The id of music.
     */
    @Override
    public void deleteMusic(int idSong) {
        String query = "DELETE FROM Music WHERE ID_Music = '" + idSong + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
