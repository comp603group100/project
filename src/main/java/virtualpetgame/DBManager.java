/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class DBManager {

    private static final String USER_NAME = "group100"; //your DB username
    private static final String PASSWORD = "virtualpetgame"; //your DB password
    private static final String URL = "jdbc:derby:GameDatabase; create=true";  //url of the DB host

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

                SQLWarning warning = conn.getWarnings();
                boolean databaseExists = false;
                
                while (warning != null) {
                    if (warning.getMessage().contains("Database 'GameDatabase' not found.")) {
                        databaseExists = true;
                        break;
                    }
                    warning = warning.getNextWarning();
                }
                
                if (!databaseExists) {
                     
                     conn.createStatement()
                             .executeUpdate(
                                     "CREATE TABLE GameData ("
                                             + "previousGame VARCHAR(255),"
                                             + "playedBefore BOOLEAN"
                                   + ")"
                             );
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
