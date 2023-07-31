import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveListingHandler {

  public static void handleRemoveListing(Scanner scanner) {
    System.out.println("\n--- Remove a Listing ---");
    int userId = UserDetails.getUserId();

    // Fetch and display the user's listings before proceeding with removal
    List<ListingInfo> listings = getUserListings(userId);
    System.out.println("\nNumber | Type | Title | Address");
    System.out.println("------------------------------------------");
    for (ListingInfo listing : listings) {
      System.out.println(listing.getNumber() + " | " + listing.getPropertyType() + " | " + listing.getTitle() + " | "
          + listing.getAddress());
    }

    System.out.print("Enter the number of the listing you want to remove: ");
    int selectedNumber = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character left by nextInt()

    if (selectedNumber > 0 && selectedNumber <= listings.size()) {
      ListingInfo selectedListing = listings.get(selectedNumber - 1);
      int listId = selectedListing.getListId();

      boolean success = removeListing(listId);

      if (success) {
        System.out.println("Listing with title: \"" + selectedListing.getTitle() + "\", number: "
            + selectedListing.getNumber() + ", has been removed successfully.");
      } else {
        System.out.println("Failed to remove the listing with list_id " + listId + ". (number: "
            + selectedListing.getNumber() + "). Please try again.");
      }
    } else {
      System.out.println("Invalid input. Please enter a valid number.");
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

  private static List<ListingInfo> getUserListings(int userId) {
    List<ListingInfo> listings = new ArrayList<>();
    String sql = "SELECT list_id, property_type, title, price_per_night, address FROM Listings WHERE user_id = ?";
    try (ResultSet resultSet = SQL.executeQuery(sql, userId)) {
      int number = 1;
      while (resultSet.next()) {
        int listId = resultSet.getInt("list_id");
        String propertyType = resultSet.getString("property_type");
        String title = resultSet.getString("title");
        BigDecimal pricePerNight = resultSet.getBigDecimal("price_per_night");
        String address = resultSet.getString("address");

        ListingInfo listing = new ListingInfo(number, listId, propertyType, title, pricePerNight, address);
        listings.add(listing);

        number++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch your listings.");
    }
    return listings;
  }

  public static class ListingInfo {
    private int number;
    private int listId;
    private String propertyType;
    private String title;
    private BigDecimal pricePerNight;
    private String address;

    public ListingInfo(int number, int listId, String propertyType, String title, BigDecimal pricePerNight,
        String address) {
      this.number = number;
      this.listId = listId;
      this.propertyType = propertyType;
      this.title = title;
      this.pricePerNight = pricePerNight;
      this.address = address;
    }

    // Getter methods for the fields (if needed)
    public int getNumber() {
      return number;
    }

    public int getListId() {
      return listId;
    }

    public String getPropertyType() {
      return propertyType;
    }

    public String getTitle() {
      return title;
    }

    public BigDecimal getPricePerNight() {
      return pricePerNight;
    }

    public String getAddress() {
      return address;
    }

    // Add getters as needed
  }
}
