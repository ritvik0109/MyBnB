import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateBookingHandler {
    public static void search(Scanner scanner){
        boolean exit = false;

        while (!exit){
            // Select filters
            SearchFilters searchFilters;
            if (addFilters(scanner)){
                searchFilters = getSearchFilters(scanner);
            }
            
            // Select sorting options
            boolean sortResults = false;

            // display all bookings

            // Enter the booking id for the booking you would like to book, or
            // enter 0 to refine the search, or 
            // enter -1 to exit
            exit = true;
        }

        
    }

    private static boolean addFilters(Scanner scanner) {
        System.out.print("\nEnter 1 to add filters, or 0 to continue: ");
        int choice = getUserChoice(scanner);
        return (choice == 1);
    }

    private static boolean getAdjacentPostalCode(Scanner scanner) {
        System.out.print("\nEnter 1 to include adjacent postal codes in your search, or 0 to continue: ");
        int choice = getUserChoice(scanner);
        return (choice == 1);
    }

    private static SearchFilters getSearchFilters(Scanner scanner) {
        SearchFilters searchFilters = new SearchFilters();
        boolean exit = false;

        while (!exit){
            displayFiltersMenu();
            int filterChoice = getUserChoice(scanner);

            switch (filterChoice) {
                case 1:
                    System.out.print("\nEnter the start date (YYYY-MM-DD): ");
                    String start = scanner.nextLine();
                    while (!isValidStartDate(start)) {
                        System.out
                                .print("Invalid start date, it must be a future date. Please enter a valid date in the format YYYY-MM-DD: ");
                        start = scanner.nextLine();
                    }

                    System.out.print("\nEnter the end date (YYYY-MM-DD): ");
                    String end = scanner.nextLine();
                    while (!isValidEndDate(start, end)) {
                        System.out.print("Invalid end date, it must be after start date. Please enter a valid date in the format YYYY-MM-DD: ");
                        end = scanner.nextLine();
                    }

                    searchFilters.setStartDate(start);
                    searchFilters.setEndDate(end);

                    break;
                case 2:
                    System.out.print("\nEnter the address: ");
                    String address = getUserInput(scanner);
                    searchFilters.setAddress(address);
                    break;
                case 3:
                    System.out.print("\nEnter the postal code: ");
                    int postalCode = getUserChoice(scanner);
                    searchFilters.setPostalCode(postalCode);

                    if (getAdjacentPostalCode(scanner))
                        searchFilters.setAdjacentPC(true);
                    break;
                case 4:
                    System.out.print("\nEnter the city: ");
                    String city = getAlphabeticalInput(scanner);
                    searchFilters.setCity(city);
                    break;
                case 5:
                    System.out.print("\nEnter the country: ");
                    String country = getAlphabeticalInput(scanner);
                    searchFilters.setCountry(country);
                    break;
                case 6:
                    System.out.print("\nEnter the latitude: ");
                    double latitude = getUserDouble(scanner);
                    searchFilters.setLatitude(latitude);


                    System.out.print("\nEnter the longitude: ");
                    double longitude = getUserDouble(scanner);
                    searchFilters.setLongitude(longitude);

                    System.out.print("\nEnter the distance (radius for search, in km): ");
                    double distance = getNonNegativeDouble(scanner, "distance");
                    searchFilters.setDistance(distance);
                    break;
                case 7:
                    Amenities amenities = getSearchAmenities(scanner);
                    searchFilters.setAmenities(amenities);
                    break;
                case 8:
                    System.out.print("\nEnter the minimum price (per night): ");
                    double minPrice = getNonNegativeDouble(scanner, "minimum price");
                    searchFilters.setMinPrice(minPrice);
                    
                    System.out.print("\nEnter the maximum price (per night): ");
                    double maxPrice = getMaxPrice(scanner, minPrice);
                    searchFilters.setMaxPrice(maxPrice);
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        
        return searchFilters;
    }

    private static void displayFiltersMenu() {
        System.out.println("\n --- Add Filters ---");
        System.out.println("1. Date range"); //  start + end date
        System.out.println("2. Address"); // exact address
        System.out.println("3. Postal code"); // exact and adjacent
        System.out.println("4. City");
        System.out.println("5. Country");
        System.out.println("6. Geolocation"); //  long and lat and distance
        System.out.println("7. Amenities");
        System.out.println("8. Price range");
        System.out.println("9. Done adding filters");
        System.out.print("Enter your choice: ");
    }

    private static Amenities getSearchAmenities(Scanner scanner){
        Amenities amenities = new Amenities();
        // TODO
        return amenities;
    }

    // Data validation

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

    private static double getNonNegativeDouble(Scanner scanner, String value) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid decimal: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        double d = scanner.nextDouble();
        scanner.nextLine(); // Consume the new line character left by nextInt()

        while (d < 0) {
            String message = String.format("Invalid input, must be larger than 0. Please enter a valid " + value + ": ");
            System.out.print(message);
            d = scanner.nextDouble();
            scanner.nextLine();// Clear the invalid input from the buffer
        }
        return d;
    }

    private static double getMaxPrice(Scanner scanner, double min) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid decimal: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        double d = scanner.nextDouble();
        scanner.nextLine(); // Consume the new line character left by nextInt()

        while (d < min) {
            String message = String.format("Invalid input, must be larger than " + min + ". Please enter a valid maximum price: ");
            System.out.print(message);
            d = scanner.nextDouble();
            scanner.nextLine();// Clear the invalid input from the buffer
        }
        return d;
    }

    public static boolean isAlphabetical(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[a-zA-Z\\s]+$");
    }

    private static String getAlphabeticalInput(Scanner scanner) {
        String value = getUserInput(scanner);
        while (!isAlphabetical(value)){
            System.out.print("Invalid input. Please enter an valid alphabetical input: ");
            value = getUserInput(scanner);
        }
        return value;
    }

    private static String getUserInput(Scanner scanner) {
        while (!scanner.hasNextLine()) {
            System.out.print("Invalid input. Please enter an valid input: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextLine();
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid integer: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        int x = scanner.nextInt();
        scanner.nextLine(); // Consume the new line character left by nextInt()
        return x;
    }

    private static double getUserDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid decimal: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        double d = scanner.nextDouble();
        scanner.nextLine(); // Consume the new line character left by nextInt()
        return d;
    }
}

        