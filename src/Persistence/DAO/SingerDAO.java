package Persistence.DAO;

import Business.Singer;

/**
 * Interface that abstracts the persistence of playLists from the rest of the code.
 * <p>
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public interface SingerDAO {
    /**
     * Method that create a specific singer.
     *
     * @param singer Name of the singer to be saved.
     */
    void addSingerDAO(String singer);

    /**
     * Method that returns all singer's data.
     *
     * @param name Singer's name.
     * @return Returns the singer's details.
     */
    Singer getSingerDAO(String name);

    /**
     * Method to verify if a singer exists or not.
     *
     * @param name Singer's name.
     * @return Returns true if it exists and false otherwise.
     */
    boolean verificationSinger(String name);
}
