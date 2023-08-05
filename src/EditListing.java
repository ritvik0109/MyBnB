import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                        System.out.print("Enter new property type (house, apartment, guesthouse, hotel): ");
                        String propertyType = scanner.nextLine();
                        scanner.next();
                        while (!isValidPropertyType(propertyType)) {
                            System.out.println(
                                    "Invalid choice. Please select one of ('house', 'apartment', 'guesthouse', 'hotel'): ");
                            propertyType = scanner.nextLine();
                        }

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
                        double price = getUserDouble(scanner);
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
                        System.out.println("Current Availability: ");
                        displayListingAvailability(id);

                        System.out.print("Enter the start date (YYYY-MM-DD): ");
                        String start = scanner.nextLine();
                        scanner.next();
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

                        Availability.addAvailability(id, start, end);
                        break;
                    case 13:
                        System.out.println("Current Availability: ");
                        List<Availability> availabilities = displayListingAvailability(id);
                        int availId = getAvailToDelete(availabilities, scanner);
                        Availability.removeAvailability(availId);
                        break;
                    case 14:
                        editAmenities(id, scanner);
                        break;
                    case 15:
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
        System.out.println("12. Add availability");
        System.out.println("13. Remove availability");
        System.out.println("14. Edit amenities");
        System.out.println("15. Exit (no more edits)");

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
                System.out.println("\n---------------------------------------\n");
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

    private static boolean isValidPropertyType(String input) {
        String[] choices = { "house", "apartment", "guesthouse", "hotel" };
        for (String choice : choices) {
            if (choice.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    private static List<Availability> displayListingAvailability(int list_id) {
        String sql = "SELECT * FROM Availabilities WHERE list_id=?";
        try (ResultSet resultSet = SQL.executeQuery(sql, list_id)) {
            List<Availability> availabilities = new ArrayList<Availability>();

            while (resultSet.next()) {
                int availId = resultSet.getInt("avail_id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");

                Availability avail = new Availability(availId, startDate, endDate, list_id);

                availabilities.add(avail);

                // Display the listing information
                System.out.println("\n--------------\n");
                System.out.println("Availability ID: " + availId);
                System.out.println("Start date: " + startDate);
                System.out.println("End date: " + endDate);
            }
            return availabilities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Availability>();
        }
    }

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

    private static int getAvailToDelete(List<Availability> availabilities, Scanner scanner) {
        System.out.print("Select which availability to delete (Availability ID): ");
        int id = getUserChoice(scanner);
        while (!containsAvailId(availabilities, id)) {
            System.out.print("Please select a valid availability id: ");
            id = getUserChoice(scanner);
        }
        return id;
    }

    private static boolean containsAvailId(List<Availability> availabilities, int id) {
        for (Availability availability : availabilities) {
            if (availability.getAvailId() == id) {
                return true;
            }
        }
        return false;
    }

    private static void editAmenities(int id, Scanner scanner) {

        Amenities amenities = getAmenities(id);

        boolean noMoreEdits = false;
        while (!noMoreEdits) {
            displayAmenities(amenities);

            System.out.print("Select amenity to toggle: ");
            int toUpdate = getUserChoice(scanner);

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
    }

    private static Amenities getAmenities(int id) {
        String sql = "SELECT * FROM Amenities WHERE list_id=?";
        try (ResultSet resultSet = SQL.executeQuery(sql, id)) {
            while (resultSet.next()) {
                return new Amenities(
                        resultSet.getInt("amenity_id"),
                        resultSet.getBoolean("wifi"),
                        resultSet.getBoolean("kitchen"),
                        resultSet.getBoolean("washer"),
                        resultSet.getBoolean("dryer"),
                        resultSet.getBoolean("ac"),
                        resultSet.getBoolean("heating"),
                        resultSet.getBoolean("workspace"),
                        resultSet.getBoolean("tv"),
                        resultSet.getBoolean("hair_dryer"),
                        resultSet.getBoolean("iron"),
                        resultSet.getBoolean("smoke_alarm"),
                        resultSet.getBoolean("carbon_monoxide_alarm"),
                        resultSet.getBoolean("pool"),
                        resultSet.getBoolean("free_parking"),
                        resultSet.getBoolean("crib"),
                        resultSet.getBoolean("bbq_grill"),
                        resultSet.getBoolean("indoor_fireplace"),
                        resultSet.getBoolean("smoking_allowed"),
                        resultSet.getBoolean("breakfast"),
                        resultSet.getBoolean("gym"),
                        resultSet.getBoolean("ev_charger"),
                        resultSet.getBoolean("hot_tub"),
                        id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static void displayAmenities(Amenities amenities) {
        System.out.println("\n-------------\n");
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

    private static double getUserDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid decimal: ");
            scanner.next(); // Clear the invalid input from the buffer
        }
        double d = scanner.nextDouble();

        while (d < 0) {
            String message = String.format("Invalid input, must be larger than 0. Please enter a valid price: ");
            System.out.println(message);
            d = scanner.nextDouble();
            scanner.nextLine();// Clear the invalid input from the buffer
        }
        return d;
    }
}
