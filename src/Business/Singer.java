package Business;

/**
 * Singer entity.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class Singer {
    private final int idSinger;

    /**
     * Constructor.
     *
     * @param idSinger ID Singer.
     */
    public Singer(int idSinger) {
        this.idSinger = idSinger;
    }

    /**
     * Getter ID singer.
     *
     * @return Returns the ID of the singer.
     */
    public int getIdSinger() {
        return idSinger;
    }
}

