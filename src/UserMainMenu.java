import java.util.Scanner;

public class UserMainMenu {
    public static void userHome(String email) {
        System.out.println("\n--- MyBnB Home ---");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displaySubMenu();
            int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    System.out.println("Sub Option 1 selected");
                    break;
                case 2:
                    EditListing.editListing(scanner);
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
                    exit = true;    // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displaySubMenu() {
        System.out.println("\n1. Create Listing");
        System.out.println("2. Edit existing listing");
        System.out.println("3. Remove listing");
        System.out.println("4. Book listing (search)");
        System.out.println("5. Cancel booking");
        System.out.println("6. Delete account");
        System.out.println("7. Logout");
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
