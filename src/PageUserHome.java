import java.util.Scanner;

public class PageUserHome {
    public static void userHome(Scanner scanner, String email) {
        boolean exit = false;

        while (!exit) {
            displaySubMenu();
            // int mainChoice = 6;
            int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    CreateListingHandler.handleCreateListing(scanner);
                    break;
                case 2:
                    EditListingHandler.editListing(scanner);
                    break;
                case 3:
                    RemoveListingHandler.handleRemoveListing(scanner);
                    break;
                case 4:
                    System.out.println("Create Booking / Search");
                    break;
                case 5:
                    System.out.println("Cancel Booking");
                    break;
                case 6:
                    PageReviewExp.handleReviewExp(scanner);
                    break;
                case 7:
                    if (confirmDelete(scanner))
                        deleteAccount(false);
                    exit = true;
                    UserDetails.clearUserDetails();
                    break;
                case 8:
                    System.out.println("...logging out");
                    exit = true; // Go back to the main menu
                    UserDetails.clearUserDetails();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displaySubMenu() {
        System.out.println("\n === MyBnB: User Home ===");
        System.out.println("1. Create Listing");
        System.out.println("2. Edit Existing Listing");
        System.out.println("3. Remove Listing");
        System.out.println("4. Create Booking (Search)");
        System.out.println("5. Cancel Booking");
        System.out.println("6. Review Your Experience (Rate & Comment)");
        System.out.println("7. Delete Account");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");
    }

    private static boolean confirmDelete(Scanner scanner) {
        System.out.print("\nDeleting your account will cancel all future bookings, and delete all your listings.\n" + //
                "Enter 1 to delete your account, or 0 to cancel. : ");
        int choice = getUserChoice(scanner);
        return (choice == 1);
    }

    private static void deleteAccount(boolean show_messages) {
        int userId = UserDetails.getUserId();

        // Remove availability from all their listings
        String sql = "DELETE FROM Availabilities WHERE list_id IN (SELECT list_id FROM Listings WHERE user_id = ?)";
        String success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
            return;
        }

        // Cancel all their future rental bookings
        sql = "UPDATE Bookings SET is_cancelled = true WHERE user_id=? AND start_date > CURDATE()";
        success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
            return;
        }

        // For all past rental bookings set user_id to NULL
        sql = "UPDATE Bookings SET user_id = NULL WHERE user_id=?";
        success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
            return;
        }

        // Cancel all their future host bookings
        sql = "UPDATE Bookings SET is_cancelled = true, is_cancelled_by_host = true WHERE start_date > CURDATE() AND list_id IN (SELECT list_id FROM Listings WHERE user_id = ?)";
        success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
            return;
        }

        // For all past host bookings, we do nothing
        
        // For all the users' listings, we set the user_id to NULL
        // We keep them so in the future we can extend functionality
        sql = "UPDATE Listings SET user_id = NULL WHERE user_id=?";
        success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            if (show_messages)
            System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
            return;
        }
        
        // Similarly, we keep the amenities for the listing

        // Delete user from users table
        sql = "DELETE FROM Users WHERE user_id=?";
        success = SQL.executeUpdate(sql, userId);
        if (success.isEmpty()) {
            System.out.println("Successfully deleted account!");
        } else {
            System.out.println("Failed to delete account! Please try again.");
            System.out.println("Error: " + success);
        }
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer choice.");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextInt();
    }
}
