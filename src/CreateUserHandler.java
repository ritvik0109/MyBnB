import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateUserHandler {

    public static void handleCreateUser() {
        System.out.println("\n--- Create User (Sign Up) ---");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name  = scanner.nextLine();
        while (!isValidName(name)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("Invalid email. Please enter a valid email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.print("Invalid password. Password must be at least 8 characters long: ");
            password = scanner.nextLine();
        }

        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();
        while (!isValidDateOfBirth(dateOfBirth)) {
            System.out.print("Invalid date of birth. Please enter a valid date in the format YYYY-MM-DD: ");
            dateOfBirth = scanner.nextLine();
        }

        System.out.print("Enter your SIN: ");
        String sin = scanner.nextLine();
        while (!isValidSin(sin)) {
            System.out.print("Invalid SIN. Please enter a valid 9-digit SIN: ");
            sin = scanner.nextLine();
        }

        System.out.print("Enter your occupation: ");
        String occupation = scanner.nextLine();

        createUser(name, email, password, dateOfBirth, sin, occupation);

        System.out.println("Sign-up successful! Please login to access your account.");
        scanner.close();
    }

    // SQL Query: 

    private static void createUser(String name, String email, String password, String dateofBirth, String sin, String occupation) {
        // SQL QUERY: Perform sign-up process here
        try {
            String sql = "INSERT INTO users (name, date_of_birth, occupation) VALUES (?, ?, ?)";
            SQL.executeUpdate(sql, name, dateofBirth, occupation);
            System.out.println("Sign-up successful! Please login to access your account.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create user!");
        }
    }

    // Data validation methods:

    private static boolean isValidName(String password) {
        return password.length() > 0;
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private static boolean isValidSin(String sin) {
        return sin.matches("\\d{9}");
    }

    private static boolean isValidDateOfBirth(String dateOfBirthStr) {
        try {
            // Parse the input date string to a LocalDate object
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the date of birth is before the current date
            return dateOfBirth.isBefore(currentDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
