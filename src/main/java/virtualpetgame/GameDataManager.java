/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDataManager {

    //Neccesary objects
    DBManager DBManager;
    Connection conn;
    Statement statement;

    /**
     * Create a new GameDataManager object.
     * Used for managing game configurations.
     */
    public GameDataManager() {
        DBManager = new DBManager();
        conn = DBManager.getConnection();
        try {
            statement = conn.createStatement();
            this.initTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Close the connection to the database
     */
    public void closeConnection() {
        this.DBManager.closeConnection();
    }

    /**
     * Used to initialise the DB table. 
     * Checks if the table exists or not, if not, creates the table.
     */
    private void initTable() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM GameData");
            resultSet.next();
            int rowCount = resultSet.getInt(1);
            if (rowCount == 0) {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO GameData (previousGame, playedBefore) VALUES (?, ?)"
                );
                preparedStatement.setString(1, "");
                preparedStatement.setBoolean(2, false);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            //never happens
        }
    }

    /**
     * Writes the given string to the previousGame key in the DB table.
     * 
     * @param previousGame a string containing the name of the save file to write.
     */
    public void writePreviousGame(String previousGame) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE GameData SET previousGame = ?"
            );
            preparedStatement.setString(1, previousGame);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //never happens
        }
    }

    /**
     * @return a string containing the name of the previously played game file, without the extension.
     */
    public String getPreviousGame() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT previousGame FROM GameData");
            if (resultSet.next()) {
                return resultSet.getString("previousGame");
            }
        } catch (SQLException e) {
            //never happens
        }
        return null;
    }

    /**
     * Set to true to indicate the player has played before.
     * The value is used to decide whether to display the welcome text or not.
     * 
     * @param playedBefore boolean true if played before, false otherwise.
     */
    public void setPlayedBefore(boolean playedBefore) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE GameData SET playedBefore = ?"
            );
            preparedStatement.setBoolean(1, playedBefore);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //never happens
        }
    }

    /**
     * Gets the value stored in the playedBefore key in the DB.
     * 
     * @return true if the user has played before, false otherwise.
     */
    public boolean getPlayedBefore() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT playedBefore FROM GameData");
            if (resultSet.next()) {
                return resultSet.getBoolean("playedBefore");
            }
        } catch (SQLException e) {
            //never happens
        }
        return false;
    }
}
