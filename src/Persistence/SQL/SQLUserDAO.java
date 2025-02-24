package Persistence.SQL;

import Business.User;
import Persistence.DAO.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class in charge of controlling the connection to the database table User table in the database.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SQLUserDAO implements UserDAO {
    /**
     * Method that saves a specific user.
     *
     * @param user The new user to save.
     */
    @Override
    public void addUser(User user) {
        String query = "INSERT INTO User (Login, Email, Password) VALUES ('" +
                user.getLogin() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Method that gets a user in the database
     *
     * @param userData A String that represents the email or the name of the user.
     */
    @Override
    public User getUser(String userData) {
        String query = "SELECT * FROM User WHERE Email = '" + userData + "' OR Login = '" + userData + "'";
        User user = new User();
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            // teorícamente solo pasaría una vez
            while (resultSet.next()) {
                user.setIdUser(resultSet.getInt("ID_User"));
                user.setLogin(resultSet.getString("Login"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return user;
    }

    /**
     * Method that deletes a persisted user.
     *
     * @param userData A string representation of the email or persistent user.
     */
    @Override
    public void deleteUser(String userData) {
        String deleteUser = "DELETE FROM User WHERE Email = '" + userData + "' OR Login = '" + userData + "'";
        SQLConnector.getInstance().deleteQuery(deleteUser);
    }

    /**
     * Method that checks if a user exists in the database.
     *
     * @param email User's email to check.
     * @param login User's login to check.
     * @return Returns a message, depending on each check.
     */
    @Override
    public String userExists(String email, String login) {
        String query = "SELECT * FROM User WHERE Email = '" + email + "' OR Login = '" + login + "'";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            while (resultSet.next()) {
                String get_email = resultSet.getString("Email");
                String get_login = resultSet.getString("Login");
                // teorícamente solo pasaría una vez
                if (email.equals(get_email)) {
                    return "emailExist";
                } else if (login.equals(get_login)) {
                    return "loginExist";
                }
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return "true";
    }

    /**
     * Method to do the login verification.
     * With this what we do is to check that the user and password to enter exist and that they match.
     *
     * @param email    Email of the user to verify.
     * @param login    Login of the user to verify.
     * @param password Password of the user to verify.
     * @return Returns a message, depending on each check.
     */
    @Override
    public String userVerifyLogin(String email, String login, String password) {
        String query = "SELECT * FROM User WHERE Email = '" + email + "' OR Login = '" + login + "'";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            if (resultSet.next()) {
                String get_Password = resultSet.getString("Password");
                // teorícamente solo pasaría una vez
                if (password.equals(get_Password)) {
                    return "true";
                } else {
                    return "passwordNotExisit";
                }
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return "loginNotExist";
    }
}
