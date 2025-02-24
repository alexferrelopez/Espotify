package Business;

import Persistence.DAO.MusicPlayListDAO;
import Persistence.DAO.PlaylistDAO;
import Persistence.SQL.SQLMusicPlaylistDAO;
import Persistence.SQL.SQLPlaylistDAO;

import java.util.List;

/**
 * Class that controls the playlist, PlaylistManager.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class PlaylistManager {
    private final PlaylistDAO playlistDAO;
    private final MusicPlayListDAO musicPlayListDAO;

    /**
     * Constructor empty.
     * Used to start the PlaylistDAO and MusicPlayListDAO.
     */
    public PlaylistManager() {
        this.playlistDAO = new SQLPlaylistDAO();
        this.musicPlayListDAO = new SQLMusicPlaylistDAO();
    }

    /**
     * Method to get all my playlists.
     *
     * @param idUser ID user.
     * @return Returns a list of the playlist.
     */
    public List<Playlist> getMyPlaylists(int idUser) {
        return playlistDAO.getAllPlaylist(idUser, 1);
    }

    /**
     * Method to list all playlists that are not mine.
     *
     * @param idUser ID user.
     * @return Returns a list of the playlist.
     */
    public List<Playlist> getAllPlaylists(int idUser) {
        return playlistDAO.getAllPlaylist(idUser, 2);
    }

    /**
     * We use this method to create a playlist.
     */
    public String createPlaylist(int idUser, String name) {
        String playlistExists = playlistDAO.playlistExists(idUser, name);
        if (playlistExists.equals("true") && !name.isEmpty()) {
            Playlist playList = new Playlist(idUser, name);
            playlistDAO.addPlaylist(playList);
            return "true";
        }
        return playlistExists;
    }

    /**
     * Utilizamos este método para añadir una canción a la lista de reproducción.
     *
     * @param idUser       ID user.
     * @param idMusic      ID music.
     * @param namePlaylist Name playlist.
     */
    public void addSongToPlaylist(int idUser, int idMusic, String namePlaylist) {
        for (Playlist playlist : getMyPlaylists(idUser)) {
            if (playlist.getNamePlaylist().equals(namePlaylist)) {
                if (isExistMusicPlaylist(playlist.getIdPlayList(), idMusic)) {
                    musicPlayListDAO.addMusicPlayListDAO(playlist.getIdPlayList(), idMusic);
                    return;
                }
            }
        }
    }

    /**
     * Method to remove a song from a playlist.
     *
     * @param namePlayList Name playlist.
     * @param idMusic      ID music.
     */
    public void deleteMusicPlayList(String namePlayList, int idMusic) {
        musicPlayListDAO.deleteMusicPlayListDAO(namePlayList, idMusic);
    }

    /**
     * Method to check if your music is already in the playlist.
     *
     * @param idPlayList Playlist ID.
     * @param idMusic    Music ID.
     * @return Returns false if the music exists, otherwise it returns true.
     */
    private boolean isExistMusicPlaylist(int idPlayList, int idMusic) {
        return musicPlayListDAO.existMusicPlayListDAO(idPlayList, idMusic);
    }
}
