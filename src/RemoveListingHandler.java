import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveListingHandler {
  public static void handleRemoveListing(Scanner scanner) {
    System.out.println("\n--- Remove a Listing ---");
    int userId = UserDetails.getUserId();

    // Display the user's listings before proceeding with removal
    displayUserListings(userId, scanner);

    System.out.print("Enter the list_id of the listing you want to remove: ");
    int listId = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character left by nextInt()

    boolean success = removeListing(listId);

    if (success) {
      System.out.println("Listing with list_id " + listId + " has been removed successfully.");
    } else {
      System.out.println("Failed to remove the listing with list_id " + listId + ". Please try again.");
    }
  }

  private static boolean removeListing(int listId) {
    String sql = "DELETE FROM Listings WHERE list_id = ?";
    try {
      String result = SQL.executeUpdate(sql, listId);
      return result.isEmpty(); // If the result is empty, it means the deletion was successful
    } catch (Exception e) {
      e.printStackTrace();
      return false; // Return false if any exception occurs during query execution
    }
  }

  private static void displayUserListings(int userId, Scanner scanner) {
    String sql = "SELECT list_id, title FROM Listings WHERE user_id = ?";
    try (ResultSet resultSet = SQL.executeQuery(sql, userId)) {
      System.out.println("\nYour Listings:");
      while (resultSet.next()) {
        int listId = resultSet.getInt("list_id");
        String title = resultSet.getString("title");
        System.out.println(listId + ": " + title);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch your listings.");
    }
  }
}
