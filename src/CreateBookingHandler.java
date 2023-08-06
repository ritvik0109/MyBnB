import java.util.Scanner;

public class CreateBookingHandler {
    public static void search(Scanner scanner){
        boolean exit = false;

        while (!exit){
            // Select filters
            if (addFilters(scanner)){
                SearchFilters searchFilters = getSearchFilters(scanner);
            }
            
            // Select sorting options
            boolean sortResults = false;

            // display all bookings

            // Enter the booking id for the booking you would like to book, or
            // enter 0 to refine the search, or 
            // enter -1 to exit
        }

        
    }

    private static boolean addFilters(Scanner scanner) {
        System.out.print("Enter 1 if you would like to add filters, or 0 to continue: ");
        int choice = getUserChoice(scanner);
        return (choice == 1);
    }

    private static SearchFilters getSearchFilters(Scanner scanner) {
        SearchFilters searchFilters = new SearchFilters();
        boolean addFilters = true;

        while (addFilters){
            displayFiltersMenu();
            int filterChoice = getUserChoice(scanner);

            switch (filterChoice) {
                case 1:
                    // Implement logic for Date range filter
                    // applyDateRangeFilter();
                    break;
                case 2:
                    // Implement logic for Address filter
                    // applyAddressFilter();
                    break;
                case 3:
                    // Implement logic for Postal code filter
                    // applyPostalCodeFilter();
                    break;
                case 4:
                    // Implement logic for City filter
                    // applyCityFilter();
                    break;
                case 5:
                    // Implement logic for Country filter
                    // applyCountryFilter();
                    break;
                case 6:
                    // Implement logic for Geolocation filter
                    // applyGeolocationFilter();
                    break;
                case 7:
                    // Implement logic for Amenities filter
                    // applyAmenitiesFilter();
                    break;
                case 8:
                    // Implement logic for Price range filter
                    // applyPriceRangeFilter();
                    break;
                case 9:
                    addFilters = false;
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
        System.out.print("Enter your choice: ");
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
        scanner.nextLine();// Clear the invalid input from the buffer
        return d;
    }
}

        