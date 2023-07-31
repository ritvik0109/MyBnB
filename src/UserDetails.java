import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetails {
    // Static variables to store user details
    private static int userId;
    private static String userEmail;
    // Add more user details as needed...

    // Setter methods to update user details
    public static void setUserId(int id) {
        userId = id;
    }

    public static void setUserEmail(String email) {
        userEmail = email;
    }

    // Getter methods to retrieve user details
    public static int getUserId() {
        return userId;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    // Clear user details (e.g., on logout)
    public static void clearUserDetails() {
        userId = 0;
        userEmail = "";
        // Clear other user details as needed...
    }

    // Function to get the name of a user by user_id
    public static String getName(int userId) {
        String sql = "SELECT name FROM Users WHERE user_id = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, userId)) {
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return "User not found"; // Return an appropriate message if the user_id doesn't exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to fetch user name";
        }
    }
}