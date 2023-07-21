import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    CreateUserHandler.handleCreateUser();
                    break;
                case 2:
                    LoginUserHandler.handleUserLogin();
                    break;
                case 3:
                    System.out.println("Exiting the program");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n ===== MyBnB Login / Sign Up =====");
        System.out.println("1. Create User");
        System.out.println("2. User Login");
        System.out.println("3. Exit");
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
