import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CancelBookingHandler {
    public static void handleCancelBooking(Scanner scanner) {
        boolean exit = false;

        while (!exit){
            displaySubMenu();
            int mainChoice = getUserChoice(scanner);

            switch (mainChoice) {
                case 1:
                    handleCancellation(true, scanner);
                    break;
                case 2:
                    handleCancellation(false, scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displaySubMenu() {
        System.out.println("\n1. Cancel bookings for my listing");
        System.out.println("2. Cancel my rental bookings");
        System.out.println("3. Exit, no more cancelations");
        System.out.print("Enter your choice: ");
    }

    private static void handleCancellation(boolean is_host, Scanner scanner){
        boolean noMoreCancellations = false;

        while (!noMoreCancellations){
            List<Booking> bookings = displayBookings(is_host);
            
            int id = getBookingToDelete(bookings, scanner);
            if (id == -1)
                break;
            
            cancelBooking(id, is_host);
            addBackAvailability(bookings, id);

            System.out.print("Enter -1 to return, or enter anything to delete more bookings: ");
            int value = getUserChoice(scanner);
            if (value == -1)
                noMoreCancellations = true;
        }
    }

    private static void cancelBooking (int booking_id, boolean is_cancelled_by_host){
        String sql = "UPDATE Bookings SET is_cancelled = true, is_cancelled_by_host = ? WHERE booking_id = ?";
        String success = SQL.executeUpdate(sql, is_cancelled_by_host, booking_id);
        if (success.isEmpty()) {
            System.out.println("Successfully cancelled booking!\n");
        } else {
            System.out.println("Failed to cancel booking. Please try again later.");
        }
    }

    private static List<Booking> displayBookings (boolean host_bookings) {
        String sql;
        if (host_bookings){
            sql = "SELECT b.* FROM Bookings b INNER JOIN Listings l ON b.list_id = l.list_id WHERE l.user_id = ? AND is_cancelled=false AND start_date > CURDATE()";
        } else
            sql = "SELECT * FROM Bookings WHERE user_id=? AND is_cancelled=false AND start_date > CURDATE()";
        List<Booking> bookings = new ArrayList<Booking>();
        try (ResultSet resultSet = SQL.executeQuery(sql, UserDetails.getUserId())) {
            while (resultSet.next()) {
                int booking_id = resultSet.getInt("booking_id");
                double total_cost = resultSet.getDouble("total_cost");
                String start_date = resultSet.getString("start_date");
                String end_date = resultSet.getString("end_date");
                int user_id = UserDetails.getUserId();
                int list_id = resultSet.getInt("list_id");

                // Display some information from the Listing table?

                Booking booking = new Booking(booking_id, total_cost, start_date, end_date, user_id, list_id);

                bookings.add(booking);

                // Display the listing information
                System.out.println("\n----------------------\n");
                System.out.println("Booking ID: " + booking_id);
                System.out.println("Total cost: " + total_cost);
                System.out.println("Start Date: " + start_date);
                System.out.println("End Date: " + end_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private static int getBookingToDelete(List<Booking> bookings, Scanner scanner) {
        System.out.print("Select which booking to delete (Booking ID), or enter -1 to return: ");
        int id = getUserChoice(scanner);
        while (!(containsBookingId(bookings, id) || id == -1)) {
            System.out.print("Please select a valid booking id, or enter -1 to return: ");
            id = getUserChoice(scanner);
        }
        return id;
    }

    private static boolean containsBookingId(List<Booking> bookings, int id) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == id) {
                return true;
            }
        }
        return false;
    }

    private static void addBackAvailability(List<Booking> bookings, int id) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == id) {
                Availability.addAvailability(booking.getListId(), booking.getStartDate(), booking.getEndDate(), false);
            }
        }
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
}
