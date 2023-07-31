import java.util.Scanner;

public class PageUserHome {
    public static void userHome(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            displaySubMenu();
            int mainChoice = 6;
            // TODO: uncomment, int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    CreateListingHandler.handleCreateListing(scanner);
                    break;
                case 2:
                    System.out.println("Edit Listing");
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
                    System.out.println("Delete Account");
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

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer choice.");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextInt();
    }
}
