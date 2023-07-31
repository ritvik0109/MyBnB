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
}