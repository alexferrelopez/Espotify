package Business;

/**
 * Playlist entity.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class Playlist {
    private final int idUser;
    private final String namePlaylist;
    private int idPlayList;

    /**
     * Constructor used to write data to the database.
     *
     * @param idUser       ID User.
     * @param namePlaylist Name Playlist.
     */
    public Playlist(int idUser, String namePlaylist) {
        this.idUser = idUser;
        this.namePlaylist = namePlaylist;
    }

    /**
     * Constructor used to read data from the database.
     *
     * @param idPlayList   ID playlist.
     * @param idUser       ID User.
     * @param namePlaylist Name playlist.
     */
    public Playlist(int idPlayList, int idUser, String namePlaylist) {
        this.idPlayList = idPlayList;
        this.idUser = idUser;
        this.namePlaylist = namePlaylist;
    }

    /**
     * Getter ID playlist.
     *
     * @return ID playlist.
     */
    public int getIdPlayList() {
        return idPlayList;
    }

    /**
     * Getter ID user.
     *
     * @return ID user.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Getter name playlist.
     *
     * @return Name playlist.
     */
    public String getNamePlaylist() {
        return namePlaylist;
    }
}
