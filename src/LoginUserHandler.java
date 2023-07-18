import java.util.Scanner;

public class LoginUserHandler {

    public static void handleCreateUser(Scanner scanner) {
        System.out.println("\n--- User Login ---");
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            displaySubMenu();
            int subChoice = getUserChoice(scanner);

            switch (subChoice) {
                case 1:
                    System.out.println("Sub Option 1 selected");
                    break;
                case 2:
                    System.out.println("Sub Option 2 selected");
                    break;
                case 3:
                    backToMainMenu = true; // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displaySubMenu() {
        System.out.println("===== Sub Menu =====");
        System.out.println("1. Sub Option 1");
        System.out.println("2. Sub Option 2");
        System.out.println("3. Back to Main Menu");
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
