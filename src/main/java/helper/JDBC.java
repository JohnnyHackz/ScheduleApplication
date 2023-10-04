package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A utility class for managing JDBC database connections.
 *
 * The class provides helper methods for opening, accessing, and closing JDBC database connections.
 * It also aids in the preparation of SQL statements and manages connection details such as the JDBC URL,
 * username, and password.
 *
 */
public class JDBC {
    private static PreparedStatement preparedStatement;
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone=UTC"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface


    /**
     * Opens and returns a connection to the database.
     *
     * @return The active Connection object, or null if the connection failed.
     */
    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful! Yay Baby!!");
            return connection;
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        return conn;
    }

    /**
     * Provides access to the currently active database connection.
     *
     * @return The current active {@link Connection} object.
     */
    public static Connection getConnection(){
        return connection;
    }


    /**
     * Prepares an SQL statement with the given connection and SQL string.
     *
     * @param conn          The database Connection to use.
     * @param sqlStatement  The SQL statement to prepare.
     * @throws SQLException If there is an error in setting the prepared statement.
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }


    /**
     * Returns the current prepared SQL statement.
     *
     * @return The active PreparedStatement object.
     */
    public static PreparedStatement getPreparedStatement() {

        return preparedStatement;
    }


    /**
     * Closes the current active database connection.
     * If any error occurs during the closure, the error message is printed to the console.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
