import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateUserHandler {

    public static void handleCreateUser(Scanner scanner) {
        System.out.println("\n--- Main Menu (Registration/Login) ---");
        scanner.nextLine();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        while (!isValidString(name)) {
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

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        while (!isValidString(address)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            name = scanner.nextLine();
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
        while (!isValidOccupation(occupation)) {
            System.out.print("Invalid occupation. Please enter a valid occupation (only using letters): ");
            occupation = scanner.nextLine();
        }

        // Debugging:
        // String name = "John Does";
        // String email = "john@does.com";
        // String password = "johnny";
        // String address = "here";
        // String dateOfBirth = "2002-02-02";
        // String sin = "234567890";
        // String occupation = "strong";

        createUser(name, email, password, address, sin, dateOfBirth, occupation);
        // scanner.close();
    }

    private static void createUser(String name, String email, String password, String address, String sin,
            String dateofBirth, String occupation) {
        // SQL QUERY: Perform sign-up process here
        String sql = "INSERT INTO USERS (name, email, password, address, sin, date_of_birth, occupation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String success = SQL.executeUpdate(sql, name, email, password, address, sin, dateofBirth, occupation);
        if (success.isEmpty()) {
            System.out.println("Successfully created user! Please log in.");
        } else {
            System.out.println("Failed to create user! Please try again.");
            System.out.println("Error: " + success);
        }
    }

    // Data validation methods:

    private static boolean isValidString(String inputString) {
        return inputString.length() > 0;
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

    private static boolean isValidOccupation(String occupation) {
        // Validate if the occupation is not null or empty
        if (occupation == null || occupation.trim().isEmpty()) {
            return false;
        }

        // Check if the occupation contains only letters
        for (char c : occupation.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        // If all characters are letters, then the occupation is valid
        return true;
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
