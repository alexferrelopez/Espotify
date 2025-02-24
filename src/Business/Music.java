package Business;

/**
 * Music entity with relevant data.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class Music {
    private int idMusic;
    private int idSinger;
    private int idOwner;
    private String name;
    private String singer;
    private String owner;
    private String gender;
    private String album;
    private String duration;
    private String url;

    /**
     * Constructor empty.
     */
    public Music() {
    }

    /**
     * Constructor used to read data from the database.
     *
     * @param idMusic  ID Music.
     * @param idSinger ID Singer.
     * @param idOwner  ID Owner.
     * @param name     Name music.
     * @param singer   Singer.
     * @param owner    Owner.
     * @param gender   Gender.
     * @param album    Album.
     * @param duration Duration.
     * @param url      URL File.
     */
    public Music(int idMusic, int idSinger, int idOwner, String name, String singer, String owner, String gender, String album, String duration, String url) {
        this.idMusic = idMusic;
        this.idSinger = idSinger;
        this.idOwner = idOwner;
        this.name = name;
        this.singer = singer;
        this.owner = owner;
        this.gender = gender;
        this.album = album;
        this.duration = duration;
        this.url = url;
    }

    /**
     * Constructor used to write data to the database.
     *
     * @param idOwner  ID Owner.
     * @param idSinger ID Singer.
     * @param name     Name music.
     * @param gender   Gender.
     * @param album    Album.
     * @param url      URL File.
     */
    public Music(int idOwner, int idSinger, String name, String gender, String album, String url) {
        this.idOwner = idOwner;
        this.idSinger = idSinger;
        this.name = name;
        this.gender = gender;
        this.album = album;
        this.url = url;
    }

    /**
     * Getter ID music.
     *
     * @return Returns ID music.
     */
    public int getIdMusic() {
        return idMusic;
    }

    /**
     * Getter ID singer.
     *
     * @return Returns
     */
    public int getIdSinger() {
        return idSinger;
    }

    /**
     * Getter ID owner.
     *
     * @return Returns
     */
    public int getIdOwner() {
        return idOwner;
    }

    /**
     * Getter name.
     *
     * @return Returns
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name music.
     *
     * @param name Name music.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter gender.
     *
     * @return Returns gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Getter duration.
     *
     * @return Returns duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter duration.
     *
     * @param duration Duration.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Getter URL path.
     *
     * @return Returns URL path.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter URL path.
     *
     * @param url URL path.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter album.
     *
     * @return Returns album.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Getter singer.
     *
     * @return Returns singer.
     */
    public String getSinger() {
        return singer;
    }

    /**
     * Getter owner.
     *
     * @return Returns owner.
     */
    public String getOwner() {
        return owner;
    }
}
