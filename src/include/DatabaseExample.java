package include;

import java.sql.*;

public class DatabaseExample {
    private static final String DEFAULT_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String hostname = "";
    private static String dbName = "";
    private static int port = 6603;
    private static final String DEFAULT_URL = "jdbc:mysql://"+ hostname +"/"+dbName;
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "";
    private Connection connection;

    public DatabaseExample() throws SQLException, ClassNotFoundException {
            Class.forName(DEFAULT_DRIVER_CLASS);
            connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}