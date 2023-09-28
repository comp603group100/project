package virtualpetgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDataManager {

    DBManager DBManager;
    Connection conn;
    Statement statement;

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

    public void closeConnection() {
        this.DBManager.closeConnection();
    }

    private void initTable() {
        try {
            // Check if the table already exists
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM GameData");
            resultSet.next();
            int rowCount = resultSet.getInt(1);
            if (rowCount == 0) {
                // Insert a new row if the table is empty
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
