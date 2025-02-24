package Business;

/**
 * ConfigJson entity.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class ConfigJson {
    private final String username;
    private final String password;
    private final String ip;
    private final int port;
    private final String database;

    /**
     * Constructor ConfigJson.
     *
     * @param username User name.
     * @param password Password.
     * @param ip       IP.
     * @param port     Port.
     * @param database Database.
     */
    public ConfigJson(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.database = database;
    }

    /**
     * Getter Username.
     *
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter Password.
     *
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter IP.
     *
     * @return Returns the IP.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Getter Port.
     *
     * @return Returns the port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter Database.
     *
     * @return Returns the database.
     */
    public String getDatabase() {
        return database;
    }
}
