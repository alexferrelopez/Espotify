package Business;


import Persistence.DAO.UserDAO;
import Persistence.SQL.SQLUserDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that controls the user, UserManager.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class UserManager {
    private final UserDAO userDAO;
    private User user;

    /**
     * Constructor empty.
     * Used to start the UserDAO
     */
    public UserManager() {
        this.userDAO = new SQLUserDAO();
    }

    /**
     * We ensure that the password complies with the specifications
     * and that the two passwords are the same.
     *
     * @param password        Entered password.
     * @param passwordConfirm Entered password confirm.
     * @return Returns true if everything is correct.
     */
    public boolean isValidRepeatPassword(String password, String passwordConfirm) {
        if (password.equals(passwordConfirm)) {
            String value = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
            Pattern pattern = Pattern.compile(value);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }

    /**
     * We make sure that the email address is correct.
     *
     * @param email String with the user's email address.
     */
    public boolean isAValidEmail(String email) {
        String value = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(value);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * We make sure that the password is correct.
     *
     * @param password Entered password.
     * @return Returns true if everything is correct.
     */
    public boolean isValidPassword(String password) {
        String value = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        Pattern pattern = Pattern.compile(value);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * We use this method to create a user.
     *
     * @param userData User data to be created.
     */
    public String createUser(List<String> userData) {
        String email = userData.get(0);
        String login = userData.get(1);
        String password = userData.get(2);
        String validRegister = isValidRegister(email, login);
        // comprobamos que el email y password concuerden
        if (validRegister.equals("true")) {
            User user = new User(login, email, passwordEncription(password));
            userDAO.addUser(user);
            return "true";
        }
        return validRegister;
    }

    /**
     * Method that deletes a persisted user.
     *
     * @param userData A string representation of the email or persistent user.
     * @param password User password for verification.
     * @return Returns true if everything is correct and false if not.
     */
    public boolean deleteUser(String userData, String password) {
        if (this.user.userAccess(userData, passwordEncription(password))) {
            userDAO.deleteUser(this.user.getEmail());
            return true;
        }
        return false;
    }

    /**
     * Method that checks if a user exists in the database.
     *
     * @param email User's email to check.
     * @param login User's login to check.
     * @return Returns a message, depending on each check.
     */
    public String isValidRegister(String email, String login) {
        return userDAO.userExists(email, login);
    }

    /**
     * Method to do the login verification.
     * With this what we do is to check that the user and password to enter exist and that they match.
     *
     * @param userData List of user data.
     * @return Returns a message, depending on each check.
     */
    public String isValidLogin(List<String> userData) {
        String dataUser = userData.get(0);
        String password = userData.get(1);
        // comprobamos que no existe en el database
        String userExists = userDAO.userVerifyLogin(dataUser, dataUser, passwordEncription(password));
        if (userExists.equals("true")) {
            return "true";
        }
        return userExists;
    }

    /**
     * Method to encrypt the password.
     *
     * @param password Entered password.
     * @return Returns the encrypted password.
     */
    private String passwordEncription(String password) {
        String passwordHash = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            passwordHash = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }

    public void logout() {
        user = null;
    }

    /**
     * Method to obtain the current user.
     *
     * @return Return User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Method to store the acting user in memory.
     */
    public void setUser(String userData) {
        user = userDAO.getUser(userData);
    }

    /**
     * Method to get the id of the currently connected user.
     *
     * @return Return ID user
     */
    public int getIdUser() {
        return user.getIdUser();
    }
}
