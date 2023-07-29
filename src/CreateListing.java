import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CreateListing {

    public static void handleCreateListing(Scanner scanner) {
        System.out.println("\n--- Create a Listing ---");
        scanner.nextLine();

        // Get user input for each attribute of the Listings table
        System.out.print("Enter property type (house/apartment/guesthouse/hotel): ");
        String propertyType = scanner.nextLine();
        while (!isValidPropertyType(propertyType)) {
            System.out.println(
                    "Invalid property type. Allowed values: house, apartment, guesthouse, hotel. \n Please enter a valid property type: ");
            propertyType = scanner.nextLine();
        }

        String title = handleInputString(scanner, "title");
        String description = handleInputString(scanner, "description");

        BigDecimal pricePerNight = handleInputBigDecimal(scanner, "price per night");

        String address = handleInputString(scanner, "address");
        String city = handleInputString(scanner, "city");
        String country = handleInputString(scanner, "country");

        int postalCode = handleInputInt(scanner, "postal code");
        String unitRoomNumber = handleInputString(scanner, "unit/room number");

        BigDecimal longitude = handleInputBigDecimal(scanner, "longitude");
        BigDecimal latitude = handleInputBigDecimal(scanner, "latitude");

        createListing(propertyType, title, description, pricePerNight, address, city, country, postalCode,
                unitRoomNumber, longitude, latitude);
    }

    private static void createListing(String propertyType, String title, String description,
            BigDecimal pricePerNight, String address, String city, String country,
            int postalCode, String unitRoomNumber, BigDecimal longitude, BigDecimal latitude) {
        // SQL QUERY: Perform sign-up process here
        String sql = "INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int userId = UserDetails.getUserId();
        System.out.println("UserID: " + Integer.toString(UserDetails.getUserId()));

        // Truncated values to fit the schema specifications:
        BigDecimal trPrice = pricePerNight.setScale(2, RoundingMode.DOWN);
        BigDecimal trLongitude = longitude.setScale(9, RoundingMode.DOWN);
        BigDecimal trLatitude = latitude.setScale(9, RoundingMode.DOWN);

        String success = SQL.executeUpdate(sql, propertyType, title, description, trPrice, address, city, country,
                postalCode, unitRoomNumber, trLongitude, trLatitude, userId);
        if (success.isEmpty()) {
            System.out.println("Successfully added listing! Return to Main Menu");
        } else {
            System.out.println("Failed to create listing! Please try again.");
            System.out.println("Error: " + success);
        }
    }

    // -- Data Validation Methods:

    private static boolean isValidPropertyType(String propertyType) {
        return propertyType.equals("house") || propertyType.equals("apartment")
                || propertyType.equals("guesthouse") || propertyType.equals("hotel");
    }

    private static boolean isValidBigDecimal(BigDecimal inputValue) {
        // Check if the input value is not null and greater than zero
        return inputValue != null && inputValue.compareTo(BigDecimal.ZERO) > 0;
    }

    private static BigDecimal handleInputBigDecimal(Scanner scanner, String value) {
        System.out.print("Enter " + value + ": ");

        while (!scanner.hasNextBigDecimal()) {
            // Consume the invalid input
            scanner.nextLine();
            String message = String.format("Invalid %s, Please enter a valid %s: ", value, value);
            System.out.println(message);
        }

        BigDecimal bigD = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume the newline character left by nextBigDecimal()

        while (!isValidBigDecimal(bigD)) {
            String message = String.format("Invalid %s, must be larger than 0. Please enter a valid %s: ", value,
                    value);
            System.out.println(message);
            bigD = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume the newline character left by nextBigDecimal()
        }

        return bigD;
    }

    private static int handleInputInt(Scanner scanner, String value) {
        System.out.print("Enter " + value + ": ");

        while (!scanner.hasNextInt()) {
            // Consume the invalid input
            scanner.nextLine();
            String message = String.format("Invalid %s, Please enter a valid integer %s: ", value, value);
            System.out.println(message);
        }

        int intValue = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        return intValue;
    }

    private static String handleInputString(Scanner scanner, String value) {
        System.out.print("Enter " + value + ": ");
        String stringValue = scanner.nextLine();
        while (stringValue.isEmpty()) {
            String message = String.format("Invalid %s, Please enter a non-empty %s: ", value, value);
            System.out.println(message);
            System.out.print("Enter " + value + ": ");
            stringValue = scanner.nextLine();
        }
        return stringValue;
    }

}

// Debugging:
// String name = "John Does";
// String email = "john@does.com";
// String password = "johnny";
// String occupation = "strong";
// String address = "here";
// String dateOfBirth = "2002-02-02";
// String sin = "234567890";