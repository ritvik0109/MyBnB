import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SQL {
    private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1/mybnb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = getPassword();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASS);
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String executeUpdate(String sql, Object... params) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.executeUpdate();
            return "";
        } catch (SQLException e) {
            // e.printStackTrace();
            return e.getMessage();
        }
    }

    public static ResultSet executeQuery(String sql, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getPassword() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String pass = properties.getProperty("db.password");
        return pass;
    }

    // CustomResultSet class to encapsulate the ResultSet and exception message
    // public static class CustomResultSet {
    // private ResultSet resultSet;
    // private String errorMessage;

    // public CustomResultSet(ResultSet resultSet, String errorMessage) {
    // this.resultSet = resultSet;
    // this.errorMessage = errorMessage;
    // }

    // public ResultSet getResultSet() {
    // return resultSet;
    // }

    // public String getErrorMessage() {
    // return errorMessage;
    // }
    // }

    // private static final String DB_URL = "jdbc:mysql://your_database_url"; //
    // Replace with your database URL
    // private static final String DB_USER = "your_username"; // Replace with your
    // database username
    // private static final String DB_PASSWORD = "your_password"; // Replace with
    // your database password
}
