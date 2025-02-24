package Persistence.DAO;

import Business.User;

/**
 * Interface that abstracts the persistence of users from the rest of the code.
 * <p>
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public interface UserDAO {
    /**
     * Method that saves a specific user.
     *
     * @param user The new user to save.
     */
    void addUser(User user);

    /**
     * Method that gets a user in the database
     *
     * @param userData A String that represents the email or the name of the user.
     */
    User getUser(String userData);

    /**
     * Method that deletes a persisted user.
     *
     * @param userData A string representation of the email or persistent user.
     */
    void deleteUser(String userData);

    /**
     * Method that checks if a user exists in the database.
     *
     * @param email User's email to check.
     * @param login User's login to check.
     * @return Returns a message, depending on each check.
     */
    String userExists(String email, String login);

    /**
     * Method to do the login verification.
     * With this what we do is to check that the user and password to enter exist and that they match.
     *
     * @param email    Email of the user to verify.
     * @param login    Login of the user to verify.
     * @param password Password of the user to verify.
     * @return Returns a message, depending on each check.
     */
    String userVerifyLogin(String email, String login, String password);
}
