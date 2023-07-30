import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditListing {
    public static void editListing(Scanner scanner) {
        System.out.println("\n--- MyBnB Home ---");
        boolean exit = false;

        while (!exit) {
            int user_id = 1; // TODO: Fix me
            // int user_id = UserDetails.getUserId();

            List<Listing> listings = displayUserListings(user_id);

            int id = getListingToEdit(listings, scanner);

            if (id == -1) {
                exit = true;
                break;
            }

            Listing listing = getListingByListId(listings, id);

            boolean noMoreEdits = false;

            while (!noMoreEdits) {
                displayEditOptions();
                int mainChoice = getUserChoice(scanner);

                switch (mainChoice) {
                    case 1:
                        // TODO: Figure out how to get valid property type input
                        // System.out.print("Enter new property type (house, apartment, guesthouse, hotel): ");
                        // String property_type = getUserInput(scanner);
                        // while (!isValidPropertyType(property_type)){
                        //     System.out.println("Invalid choice. Please select one of ('house', 'apartment', 'guesthouse', 'hotel'): ");
                        //     property_type = getUserInput(scanner);
                        // }
                        String property_type = getUserInput(scanner);
                        listing.setPropertyType(property_type);
                        break;
                    case 2:
                        System.out.print("Enter new title: ");
                        String title = getUserInput(scanner);
                        listing.setTitle(title);
                        break;
                    case 3:
                        System.out.print("Enter new description: ");
                        String description = getUserInput(scanner);
                        listing.setDescription(description);
                        break;
                    case 4:
                        System.out.print("Enter new price (per night): ");
                        int price = getUserChoice(scanner);
                        listing.setPricePerNight(price);
                        break;
                    case 5:
                        System.out.print("Enter new address: ");
                        String address = getUserInput(scanner);
                        listing.setAddress(address);
                        break;
                    case 6:
                        System.out.print("Enter new city: ");
                        String city = getUserInput(scanner);
                        listing.setCity(city);
                        break;
                    case 7:
                        System.out.print("Enter new country: ");
                        String country = getUserInput(scanner);
                        listing.setCountry(country);
                        break;
                    case 8:
                        System.out.print("Enter new postal code: ");
                        int postal_code = getUserChoice(scanner);
                        listing.setPostalCode(postal_code);
                        break;
                    case 9:
                        System.out.print("Enter new longitude: ");
                        double longitude = getUserDouble(scanner);
                        listing.setLongitude(longitude);
                        break;
                    case 10:
                        System.out.print("Enter new latitude: ");
                        double latitude = getUserDouble(scanner);
                        listing.setLatitude(latitude);
                        break;
                    case 11:
                        System.out.print("Enter new unit/room number: ");
                        String unit = getUserInput(scanner);
                        listing.setUnitRoomNumber(unit);
                        break;
                    case 12:
                        noMoreEdits = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private static void displayEditOptions() {
        System.out.println("\n1. Property type");
        System.out.println("2. Title");
        System.out.println("3. Description");
        System.out.println("4. Price (per night)");
        System.out.println("5. Address");
        System.out.println("6. City");
        System.out.println("7. Country");
        System.out.println("8. Postal Code");
        System.out.println("9. Longitude");
        System.out.println("10. Latitude");
        System.out.println("11. Unit Room Number");
        System.out.println("12. Exit (no more edits)");
        System.out.print("Select what you would like to update: ");
    }

    private static List<Listing> displayUserListings(int user_id) {
        String sql = "SELECT * FROM Listings WHERE user_id=?";
        try (ResultSet resultSet = SQL.executeQuery(sql, user_id)) {
            List<Listing> listingIds = new ArrayList<Listing>();

            while (resultSet.next()) {
                int listId = resultSet.getInt("list_id");
                String propertyType = resultSet.getString("property_type");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double pricePerNight = resultSet.getDouble("price_per_night");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String country = resultSet.getString("country");
                int postalCode = resultSet.getInt("postal_code");
                String unitRoomNumber = resultSet.getString("unit_room_number");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                Listing newListing = new Listing(listId, propertyType, title, description, pricePerNight,
                        address, city, country, postalCode, unitRoomNumber,
                        longitude, latitude, user_id);

                listingIds.add(newListing);

                // Display the listing information
                System.out.println("---------------------------------------\n");
                System.out.println("Listing ID: " + listId);
                System.out.println("Property Type: " + propertyType);
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Price per Night: " + pricePerNight);
                System.out.println("Address: " + address);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
                System.out.println("Postal Code: " + postalCode);
                System.out.println("Unit/Room Number: " + unitRoomNumber);
                System.out.println("Longitude: " + longitude);
                System.out.println("Latitude: " + latitude);
            }
            return listingIds;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Listing>();
        }
    }

    private static int getListingToEdit(List<Listing> listings, Scanner scanner) {
        System.out.print("Select which listing to edit (Listing ID), or enter -1 to return: ");
        int id = getUserChoice(scanner);
        while (!(containsListId(listings, id) || id == -1)) {
            System.out.print("Please select a valid list id: ");
            id = getUserChoice(scanner);
        }
        return id;
    }

    private static boolean containsListId(List<Listing> listings, int id) {
        for (Listing listing : listings) {
            if (listing.getListId() == id) {
                return true;
            }
        }
        return false;
    }

    private static Listing getListingByListId(List<Listing> listings, int id) {
        for (Listing listing : listings) {
            if (listing.getListId() == id) {
                return listing;
            }
        }
        return new Listing();
    }

    // public static String getValidPropertyType(Scanner scanner) {
    //     // System.out.println("Enter new property type (house, apartment, guesthouse, hotel): ");
    //     // String input = getUserInput(scanner);
    //     // System.out.println(input);
    //     // while (!isValidPropertyType(input)) {
    //     //     System.out.println("Invalid choice. Please select one of ('house', 'apartment', 'guesthouse', 'hotel'): ");
    //     //     input = getUserInput(scanner);
    //     // }
    //     // String input;
    //     // do {
    //     //     System.out.println("Enter new property type (house, apartment, guesthouse, hotel): ");
    //     //     System.out.println("-1");
    //     //     input = scanner.nextLine().trim().toLowerCase();
    //     // } while (!isValidPropertyType(input));
    //     while (!scanner.hasNextLine()) {
    //         System.out.print("Invalid input. Please enter an valid input: ");
    //         scanner.next(); // Clear the invalid input from the buffer
    //     }
    //     String input = scanner.nextLine();
    //     while (!isValidPropertyType(input)){
    //         while (!scanner.hasNextLine()) {
    //             System.out.print("Invalid input. Please enter an valid input: ");
    //             scanner.next(); // Clear the invalid input from the buffer
    //         }
    //         input = scanner.nextLine();
    //     }
    //     return input;
    // }

    // private static boolean isValidPropertyType(String input) {
    //     String[] choices = { "house", "apartment", "guesthouse", "hotel" };
    //     for (String choice : choices) {
    //         if (choice.equalsIgnoreCase(input)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public static String getValidPropertyType(String message, String[] choices) {
    //     String input;
    //     do {
    //         System.out.print(message);
    //         input = scanner.nextLine().trim().toLowerCase();
    //     } while (!isValidChoice(input, choices));

    //     return input;
    // }

    // private static boolean isValidChoice(String input, String[] choices) {
    //     for (String choice : choices) {
    //         if (choice.equalsIgnoreCase(input)) {
    //             return true;
    //         }
    //     }
    //     System.out.println("Invalid choice. Please select one of: " + String.join(", ", choices));
    //     return false;
    // }

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
        return scanner.nextInt();
    }

    private static int getUserDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid decimal: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        return scanner.nextInt();
    }
}
