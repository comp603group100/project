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

    /**
     * Creates the DBManager object and establishes a connection the DB server.
     */
    public DBManager() {
        createConnection();
    }

    /**
     * @return the Connection object which connects to the DB server.
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Creates a connection to the DB server. 
     * 
     * Will also create the database or table if it doesn't yet exist.
     */
    public void createConnection() {
        if (this.conn == null) { //ensure not already connected.
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
                
                //if the db doesnt exist, create a table in the newly created db.
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
                //if table exists i dont wanna hear about it
            }
        }
    }

    /**
     * Close the connection to the DB serer
     */
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
