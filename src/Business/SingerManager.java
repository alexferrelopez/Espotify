package Business;

import Persistence.DAO.SingerDAO;
import Persistence.SQL.SQLSingerDAO;

/**
 * Class that controls the singer, SingerManager.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SingerManager {
    private final SingerDAO singerDAO;

    /**
     * Constructor empty.
     * Used to start the SingerDAO.
     */
    public SingerManager() {
        this.singerDAO = new SQLSingerDAO();
    }

    /**
     * Method that add a specific singer.
     *
     * @param name Name of the singer to be saved.
     */
    public void addSinger(String name) {
        singerDAO.addSingerDAO(name);
    }

    /**
     * Method that returns the ID of the singer.
     *
     * @param name Name singer.
     * @return Returns the ID of the singer.
     */
    public int getIdSinger(String name) {
        return singerDAO.getSingerDAO(name).getIdSinger();
    }

    /**
     * Method to verify if a singer exists or not.
     *
     * @param name Singer's name.
     * @return Returns true if it exists and false otherwise.
     */
    public boolean verificationSinger(String name) {
        return singerDAO.verificationSinger(name);
    }
}
