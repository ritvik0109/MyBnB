import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ReviewAsHostHandler {

    private static final DecimalFormat decFor = new DecimalFormat("0.00");

    public static void handleHostComments(Scanner scanner) {
        System.out.println("\n--- As a Host: Rate/Comment on Previous Renters ---");

        int userId = UserDetails.getUserId();
        List<BookingsInfo> bookingsList = fetchPreviousBookings(userId);

        if (displayBookings(bookingsList) > 0) {
            System.out.print("Enter the BookingID of the booking you want to comment on: ");
            int bookingId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            int index = isIdInList(bookingsList, bookingId);
            if (index >= 0) {
                BookingsInfo selectedBooking = bookingsList.get(index);
                handleSelectedBooking(selectedBooking, scanner);
            } else {
                System.out.println("Invalid booking number. Please try again.");
            }
        }
    }

    public static void displayBooking(BookingsInfo booking) {
        System.out.println("Booking ID: " + booking.getBookingId()
                + " | Total Cost: " + decFor.format(booking.getTotalCost())
                + " | Start Date: " + booking.getStartDate()
                + " | End Date: " + booking.getEndDate()
                + " | Listing ID: " + booking.getListId()
                + " | Renter ID: " + booking.getRenterId());
    }

    public static int displayBookings(List<BookingsInfo> bookingsList) {
        System.out.println("Your Previous Bookings:");
        System.out.println("\nBooking ID | Total Cost | Listing ID | Start Date | End Date | Renter ID");
        System.out.println("-------------------------------------------------------------------------------");
        if (bookingsList.size() <= 0) {
            System.out.println("No successful bookings found.");
            return -1;
        } else {
            for (int i = 0; i < bookingsList.size(); i++) {
                BookingsInfo booking = bookingsList.get(i);
                displayBooking(booking);
            }
            return 1;
        }
    }

    private static int isIdInList(List<BookingsInfo> bookingsList, int id) {
        for (int i = 0; i < bookingsList.size(); i++) {
            if (bookingsList.get(i).getBookingId() == id) {
                return i;
            }
        }
        return -1;
    }

    private static void handleSelectedBooking(BookingsInfo bookingInfo, Scanner scanner) {
        boolean exit = false;

        while (exit == false) {
            System.out.println("\nSelected Booking:");
            displayBooking(bookingInfo);
            System.out.println("1. Comment on the renter");
            System.out.println("2. Rate the renter");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    commentOnRenter(bookingInfo, scanner);
                    break;
                case 2:
                    rateRenter(bookingInfo, scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    private static void commentOnRenter(BookingsInfo bookingInfo, Scanner scanner) {
        String comment;
        do {
            System.out.print("Enter your comment on the renter: ");
            comment = scanner.nextLine();
            if (comment.isEmpty()) {
                System.out.println("Comment must be non-empty. Please try again.");
            }
        } while (comment.isEmpty());

        String sql = "UPDATE Bookings SET comment_on_renter = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, comment, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Comment on the renter added successfully.");
        } else {
            System.out.println("Failed to add comment on the renter: " + result);
        }
    }

    private static void rateRenter(BookingsInfo bookingInfo, Scanner scanner) {
        int rating;
        do {
            System.out.print("Rate the renter (0 to 5): ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid integer. Please enter a valid rating (0 to 5): ", input);
            }
            rating = scanner.nextInt();
            if (rating < 0 || rating > 5) {
                System.out.println("Invalid rating. Please enter a rating between 0 and 5.");
            }
        } while (rating < 0 || rating > 5);

        String sql = "UPDATE Bookings SET rate_renter = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, rating, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Renter rated successfully.");
        } else {
            System.out.println("Failed to rate the renter: " + result);
        }
    }

    // BookingsInfo class to store booking information
    public static class BookingsInfo {
        private int bookingId;
        private int totalCost;
        private Date startDate;
        private Date endDate;
        private int listId;
        private int renterId;

        public BookingsInfo(int bookingId, int totalCost, Date startDate, Date endDate, int listId, int renterId) {
            this.bookingId = bookingId;
            this.totalCost = totalCost;
            this.startDate = startDate;
            this.endDate = endDate;
            this.listId = listId;
            this.renterId = renterId;
        }

        public int getBookingId() {
            return bookingId;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public int getListId() {
            return listId;
        }

        public int getRenterId() {
            return renterId;
        }
    }

    // Function to fetch previous bookings for the given host ID
    public static List<BookingsInfo> fetchPreviousBookings(int hostId) {
        List<BookingsInfo> bookingsList = new ArrayList<>();
        String sql = "SELECT Bookings.booking_id, start_date, end_date, Bookings.list_id, total_cost, Bookings.user_id FROM Bookings "
                +
                "JOIN Listings ON Bookings.list_id = Listings.list_id " +
                "WHERE Listings.user_id = ? AND end_date < CURDATE() AND is_cancelled = FALSE AND is_cancelled_by_host = FALSE";
        try (ResultSet resultSet = SQL.executeQuery(sql, hostId)) {
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int listId = resultSet.getInt("list_id");
                int totalCost = resultSet.getInt("total_cost");
                int renterId = resultSet.getInt("user_id"); // Display the renter ID instead of host ID
                bookingsList.add(new BookingsInfo(bookingId, totalCost, startDate, endDate, listId, renterId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch previous bookings.");
        }
        return bookingsList;
    }

    // Function to retrieve the host_id for a given list_id
    public static int retrieveHostId(int listId) {
        String sql = "SELECT user_id FROM Listings WHERE list_id = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, listId)) {
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if host_id is not found (optional error handling)
    }

}
