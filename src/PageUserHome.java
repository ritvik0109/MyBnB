import java.util.Scanner;

public class PageUserHome {
    public static void userHome(Scanner scanner) {
        System.out.println("\n--- MyBnB Home ---");
        scanner.nextLine();
        boolean exit = false;

        while (!exit) {
            displaySubMenu();
            int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    CreateListing.handleCreateListing(scanner);
                    break;
                case 2:
                    System.out.println("Sub Option 2 selected");
                    break;
                case 3:
                    System.out.println("Sub Option 3 selected");
                    break;
                case 4:
                    System.out.println("Sub Option 4 selected");
                    break;
                case 5:
                    System.out.println("Sub Option 5 selected");
                    break;
                case 6:
                    System.out.println("Sub Option 6 selected");
                    break;
                case 7:
                    exit = true; // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displaySubMenu() {
        System.out.println("=== MyBnB: User Home ===");
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
