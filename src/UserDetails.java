import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetails {
    // Static variables to store user details
    private static int userId;
    private static String userEmail;
    private static String creditCard;
    // Add more user details as needed...

    // Setter methods to update user details
    public static void setUserId(int id) {
        userId = id;
    }

    public static void setUserEmail(String email) {
        userEmail = email;
    }

    public static void setCreditCard(String cc, boolean show_messages) {
        creditCard = cc;

        String sql = "UPDATE Users SET credit_card = ? WHERE user_id = ?";
        String success = SQL.executeUpdate(sql, cc, userId);
        System.out.println();
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully updated credit card!\n");
        } else {
            if (show_messages)
                System.out.println("Error: Failed to update credit card in database.");
        }
    }

    // Getter methods to retrieve user details
    public static int getUserId() {
        return userId;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getCreditCard() {
        return creditCard;
    }

    // Clear user details (e.g., on logout)
    public static void clearUserDetails() {
        userId = 0;
        userEmail = "";
        creditCard = "";
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