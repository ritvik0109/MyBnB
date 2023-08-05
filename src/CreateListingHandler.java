import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateListingHandler {

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
        String city = handleInputWord(scanner, "city");
        String country = handleInputWord(scanner, "country");

        int postalCode = handleInputInt(scanner, "postal code");
        String unitRoomNumber = handleInputString(scanner, "unit/room number");

        BigDecimal longitude = handleInputBigDecimal(scanner, "longitude");
        BigDecimal latitude = handleInputBigDecimal(scanner, "latitude");

        // Debugging
        // String propertyType = "house";
        // String title = "random title";
        // String description = "some desctip";
        // BigDecimal pricePerNight = new BigDecimal(90);
        // String address = "100 military";
        // String city = "Scar";
        // String country = "bourough";
        // int postalCode = 123;
        // String unitRoomNumber = "12";
        // BigDecimal longitude = new BigDecimal(100);
        // BigDecimal latitude = new BigDecimal(-100);

        boolean success = createListing(propertyType, title, description, pricePerNight, address, city, country, postalCode,
                unitRoomNumber, longitude, latitude);

        int listId = getListId(propertyType, title, description, pricePerNight, address, city, country, postalCode,
                unitRoomNumber, longitude, latitude);

        if (success && listId != -1){
            editAmenities(scanner, listId);

            // Add availability
            boolean addAvailability = true;
            while (addAvailability){
                System.out.println("\nAdd Availability");
                System.out.print("Enter the start date (YYYY-MM-DD): ");
                String start = scanner.nextLine();
                // scanner.next();
                while (!isValidStartDate(start)) {
                    System.out
                            .print("Invalid start date. Please enter a valid date in the format YYYY-MM-DD: ");
                    
                    start = scanner.nextLine();
                }

                System.out.print("Enter the end date (YYYY-MM-DD): ");
                String end = scanner.nextLine();
                while (!isValidEndDate(start, end)) {
                    System.out.print("Invalid end date. Please enter a valid date in the format YYYY-MM-DD: ");
                    end = scanner.nextLine();
                }

                Availability.addAvailability(listId, start, end);

                int exit = handleInputInt(scanner, "0 to exit, or 1 to add more availability");
                if (exit == 0)
                    addAvailability = false;
            }
        }
        System.out.println("\nSuccessfully added listing! Return to Main Menu");
    }

    private static boolean createListing(String propertyType, String title, String description,
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
            return true;
        } else {
            System.out.println("Failed to create listing! Please try again.");
            System.out.println("Error: " + success);
            return false;
        }
    }

    private static int getListId(String propertyType, String title, String description,
            BigDecimal pricePerNight, String address, String city, String country,
            int postalCode, String unitRoomNumber, BigDecimal longitude, BigDecimal latitude) {
        // SQL QUERY: Perform sign-up process here
        String sql = "SELECT list_id FROM Listings WHERE property_type = ? AND title = ?" + 
                " AND description = ? AND price_per_night = ? AND address = ? AND city = ?" + 
                " AND country = ? AND postal_code = ? AND unit_room_number = ?" +
                " AND longitude = ? AND latitude = ? AND user_id = ?";
        int userId = UserDetails.getUserId();

        // Truncated values to fit the schema specifications:
        BigDecimal trPrice = pricePerNight.setScale(2, RoundingMode.DOWN);
        BigDecimal trLongitude = longitude.setScale(9, RoundingMode.DOWN);
        BigDecimal trLatitude = latitude.setScale(9, RoundingMode.DOWN);

        try (ResultSet resultSet = SQL.executeQuery(sql, propertyType, title, description,
                                trPrice, address, city, country, postalCode, unitRoomNumber,
                                trLongitude, trLatitude, userId);) {
            while (resultSet.next()) {
                return resultSet.getInt("list_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void editAmenities(Scanner scanner, int list_id) {

        Amenities amenities = new Amenities(false, list_id);

        boolean noMoreEdits = false;
        while (!noMoreEdits) {
            displayAmenities(amenities);

            int toUpdate = handleInputInt(scanner, "amenity to toggle");

            switch (toUpdate) {
                case 1:
                    amenities.setWifi(!amenities.getWifi());
                    break;
                case 2:
                    amenities.setKitchen(!amenities.getKitchen());
                    break;
                case 3:
                    amenities.setWasher(!amenities.getWasher());
                    break;
                case 4:
                    amenities.setDryer(!amenities.getDryer());
                    break;
                case 5:
                    amenities.setAc(!amenities.getAc());
                    break;
                case 6:
                    amenities.setHeating(!amenities.getHeating());
                    break;
                case 7:
                    amenities.setWorkspace(!amenities.getWorkspace());
                    break;
                case 8:
                    amenities.setTv(!amenities.getTv());
                    break;
                case 9:
                    amenities.setHairDryer(!amenities.getHairDryer());
                    break;
                case 10:
                    amenities.setIron(!amenities.getIron());
                    break;
                case 11:
                    amenities.setSmokeAlarm(!amenities.getSmokeAlarm());
                    break;
                case 12:
                    amenities.setCarbonMonoxideAlarm(!amenities.getCarbonMonoxideAlarm());
                    break;
                case 13:
                    amenities.setPool(!amenities.getPool());
                    break;
                case 14:
                    amenities.setFreeParking(!amenities.getFreeParking());
                    break;
                case 15:
                    amenities.setCrib(!amenities.getCrib());
                    break;
                case 16:
                    amenities.setBbqGrill(!amenities.getBbqGrill());
                    break;
                case 17:
                    amenities.setIndoorFireplace(!amenities.getIndoorFireplace());
                    break;
                case 18:
                    amenities.setSmokingAllowed(!amenities.getSmokingAllowed());
                    break;
                case 19:
                    amenities.setBreakfast(!amenities.getBreakfast());
                    break;
                case 20:
                    amenities.setGym(!amenities.getGym());
                    break;
                case 21:
                    amenities.setEvCharger(!amenities.getEvCharger());
                    break;
                case 22:
                    amenities.setHotTub(!amenities.getHotTub());
                    break;
                case 23:
                    noMoreEdits = true;
                    break;
                default:
                    System.out.println("Invalid value");
                    break;
            }
        }

        amenities.addAmenity();
    }

    private static void displayAmenities(Amenities amenities) {
        System.out.println("\nAdd amenities: \n");
        System.out.println("1.  Wifi: " + (amenities.getWifi() ? "" : "Not ") + "Included");
        System.out.println("2.  Kitchen: " + (amenities.getKitchen() ? "" : "Not ") + "Included");
        System.out.println("3.  Washer: " + (amenities.getWasher() ? "" : "Not ") + "Included");
        System.out.println("4.  Dryer: " + (amenities.getDryer() ? "" : "Not ") + "Included");
        System.out.println("5.  AC: " + (amenities.getAc() ? "" : "Not ") + "Included");
        System.out.println("6.  Heating: " + (amenities.getHeating() ? "" : "Not ") + "Included");
        System.out.println("7.  Workspace: " + (amenities.getWorkspace() ? "" : "Not ") + "Included");
        System.out.println("8.  TV: " + (amenities.getTv() ? "" : "Not ") + "Included");
        System.out.println("9.  Hair Dryer: " + (amenities.getHairDryer() ? "" : "Not ") + "Included");
        System.out.println("10. Iron: " + (amenities.getIron() ? "" : "Not ") + "Included");
        System.out.println("11. Smoke Alarm: " + (amenities.getSmokeAlarm() ? "" : "Not ") + "Included");
        System.out.println(
                "12. Carbon Monoxide Alarm: " + (amenities.getCarbonMonoxideAlarm() ? "" : "Not ") + "Included");
        System.out.println("13. Pool: " + (amenities.getPool() ? "" : "Not ") + "Included");
        System.out.println("14. Free Parking: " + (amenities.getFreeParking() ? "" : "Not ") + "Included");
        System.out.println("15. Crib: " + (amenities.getCrib() ? "" : "Not ") + "Included");
        System.out.println("16. BBQ Grill: " + (amenities.getBbqGrill() ? "" : "Not ") + "Included");
        System.out.println("17. Indoor Fireplace: " + (amenities.getIndoorFireplace() ? "" : "Not ") + "Included");
        System.out.println("18. Smoking Allowed: " + (amenities.getSmokingAllowed() ? "" : "Not ") + "Included");
        System.out.println("19. Breakfast: " + (amenities.getBreakfast() ? "" : "Not ") + "Included");
        System.out.println("20. Gym: " + (amenities.getGym() ? "" : "Not ") + "Included");
        System.out.println("21. EV Charger: " + (amenities.getEvCharger() ? "" : "Not ") + "Included");
        System.out.println("22. Hot Tub: " + (amenities.getHotTub() ? "" : "Not ") + "Included");
        System.out.println("23. Exit (no more edits)");
    }

    // -- Data Validation Methods:

    private static boolean isValidStartDate(String start) {
        try {
            // Parse the input date string to a LocalDate object
            LocalDate startDate = LocalDate.parse(start);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the date of birth is before the current date
            return startDate.isAfter(currentDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidEndDate(String start, String end) {
        try {
            // Parse the input date string to a LocalDate object
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            // Check if the date of birth is before the current date
            return startDate.isBefore(endDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidPropertyType(String propertyType) {
        return propertyType.equals("house") || propertyType.equals("apartment")
                || propertyType.equals("guesthouse") || propertyType.equals("hotel");
    }

    private static boolean isValidBigDecimal(BigDecimal inputValue) {
        // Check if the input value is not null and greater than zero
        return inputValue != null && inputValue.compareTo(BigDecimal.ZERO) > 0;
    }

    private static boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
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

    private static String handleInputWord(Scanner scanner, String value) {
        System.out.print("Enter " + value + ": ");
        String stringValue = scanner.nextLine();
        while (!isValidWord(stringValue)) {
            String message = String.format("Invalid %s, Please use only alphabetical characters %s: ", value, value);
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