import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUserHandler {

    public static void handleUserLogin() {
        System.out.println("\n--- User Login ---");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        // String email = "r@mail.com"; // Debugging
        while (!isValidEmail(email) || !isUserEmail(email)) {
            if (!isValidEmail(email))
                System.out.print("Please enter a valid email address: ");
            else if (!isUserEmail(email))
                System.out.print("No user account with this email exists. Please enter a valid email address: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        int attempts = 3;
        while (!isLoginValid(email, password)) {
            attempts -= 1;
            if (attempts == 0) {
                exit = true;
                break;
            }

            System.out.print("Incorrect password. Please try again: ");
            password = scanner.nextLine();
        }

        // scanner.close();

        if (!exit) {
            UserMainMenu.userHome();
        }

    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }

    private static boolean isUserEmail(String email) {
        String sql = "SELECT COUNT(*) AS count FROM Users WHERE email = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, email)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    private static boolean isLoginValid(String email, String password) {
        String sql = "SELECT COUNT(*) AS count FROM Users WHERE email = ? AND password = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, email, password)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    private static String getUserInput(Scanner scanner) {
        while (!scanner.hasNextLine()) {
            System.out.println("Invalid input. Please enter an valid input.");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextLine();
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer choice.");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextInt();
    }
}
