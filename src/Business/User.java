package Business;

/**
 * User entity.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class User {
    private int idUser;
    private String login;
    private String email;
    private String password;

    /**
     * Constructor empty.
     */
    public User() {
    }

    /**
     * Constructor for reading and writing data.
     *
     * @param login    Login.
     * @param email    Email.
     * @param password Password.
     */
    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    /**
     * Getter ID user.
     *
     * @return Returns ID user.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Setter ID user.
     *
     * @param idUser ID user.
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Getter login.
     *
     * @return Returns login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter login.
     *
     * @param login Login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter email.
     *
     * @return Returns email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter email.
     *
     * @param email Email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter password.
     *
     * @return Returns password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter password.
     *
     * @param password Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verify that the password entered matches the user.
     *
     * @param userData Login or Email.
     * @param password Password
     * @return Returns true if it matches and false if it does not match.
     */
    public boolean userAccess(String userData, String password) {
        return (this.email.equals(userData) || this.login.equals(userData)) && this.password.equals(password);
    }
}
