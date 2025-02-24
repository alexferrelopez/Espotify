package Persistence.SQL;

import Business.Singer;
import Persistence.DAO.SingerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class in charge of controlling the connection to the database table Singer table in the database.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SQLSingerDAO implements SingerDAO {
    /**
     * Method that create a specific singer.
     *
     * @param singer Name of the singer to be saved.
     */
    @Override
    public void addSingerDAO(String singer) {
        String query = "INSERT INTO Singer (Name) VALUES ('" + singer + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Method that returns all singer's data.
     *
     * @param name Singer's name.
     * @return Returns the singer's details.
     */
    @Override
    public Singer getSingerDAO(String name) {
        String query = "SELECT * FROM Singer WHERE Name = '" + name + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            if (resultSet.next()) {
                int idSinger = resultSet.getInt("ID_Singer");
                return new Singer(idSinger);
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return null;
    }

    /**
     * Method to verify if a singer exists or not.
     *
     * @param name Singer's name.
     * @return Returns true if it exists and false otherwise.
     */
    @Override
    public boolean verificationSinger(String name) {
        String query = "SELECT * FROM Singer WHERE Name = '" + name + "';";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return false;
    }
}