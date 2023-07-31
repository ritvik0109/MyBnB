import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ReviewAsRenterHandler {

    private static final DecimalFormat decFor = new DecimalFormat("0.00");

    public static void handleRenterComments(Scanner scanner) {
        System.out.println("\n--- As a Renter: Rate/Comment on Previous Bookings ---");

        int userId = UserDetails.getUserId();
        List<BookingsInfo> bookingsList = fetchPreviousBookings(userId);

        displayBookings(bookingsList);

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

    public static void displayBooking(BookingsInfo booking) {
        System.out.println("Booking ID: " + booking.getBookingId()
                + " | Total Cost: " + decFor.format(booking.getTotalCost())
                + " | Start Date: " + booking.getStartDate()
                + " | End Date: " + booking.getEndDate()
                + " | Listing ID: " + booking.getListId()
                + " | Host ID: " + booking.getHostId());
    }

    public static void displayBookings(List<BookingsInfo> bookingsList) {
        System.out.println("Your Previous Bookings:");
        System.out.println("\nBooking ID | Total Cost | Listing ID | Start Date | End Date | Host ID");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < bookingsList.size(); i++) {
            BookingsInfo booking = bookingsList.get(i);
            displayBooking(booking);
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
            System.out.println("1. Comment on the listing");
            System.out.println("2. Comment on the host");
            System.out.println("3. Rate the listing");
            System.out.println("4. Rate the host");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    commentOnListing(bookingInfo, scanner);
                    break;
                case 2:
                    commentOnHost(bookingInfo, scanner);
                    break;
                case 3:
                    rateListing(bookingInfo, scanner);
                    break;
                case 4:
                    rateHost(bookingInfo, scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    private static void commentOnListing(BookingsInfo bookingInfo, Scanner scanner) {
        String comment;
        do {
            System.out.print("Enter your comment on the listing: ");
            comment = scanner.nextLine();
            if (comment.isEmpty()) {
                System.out.println("Comment must be non-empty. Please try again.");
            }
        } while (comment.isEmpty());

        String sql = "UPDATE Bookings SET comment_on_listing = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, comment, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Comment on the listing added successfully.");
        } else {
            System.out.println("Failed to add comment on the listing: " + result);
        }
    }

    private static void commentOnHost(BookingsInfo bookingInfo, Scanner scanner) {
        String comment;
        do {
            System.out.print("Enter your comment on the host: ");
            comment = scanner.nextLine();
            if (comment.isEmpty()) {
                System.out.println("Comment must be non-empty. Please try again.");
            }
        } while (comment.isEmpty());

        String sql = "UPDATE Bookings SET comment_on_host = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, comment, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Comment on the host added successfully.");
        } else {
            System.out.println("Failed to add comment on the host: " + result);
        }
    }

    private static void rateHost(BookingsInfo bookingInfo, Scanner scanner) {
        int rating;
        do {
            System.out.print("Rate the host (0 to 5): ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid integer. Please enter a valid rating (0 to 5): ", input);
            }
            rating = scanner.nextInt();
            if (rating < 0 || rating > 5) {
                System.out.println("Invalid rating. Please enter a rating between 0 and 5.");
            }
        } while (rating < 0 || rating > 5);

        String sql = "UPDATE Bookings SET rate_host = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, rating, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Host rated successfully.");
        } else {
            System.out.println("Failed to rate the host: " + result);
        }
    }

    private static void rateListing(BookingsInfo bookingInfo, Scanner scanner) {
        int rating;
        do {
            System.out.print("Rate the listing (0 to 5): ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid integer. Please enter a valid rating (0 to 5): ", input);
            }
            rating = scanner.nextInt();
            if (rating < 0 || rating > 5) {
                System.out.println("Invalid rating. Please enter a rating between 0 and 5.");
            }
        } while (rating < 0 || rating > 5);

        String sql = "UPDATE Bookings SET rate_listing = ? WHERE booking_id = ?";
        String result = SQL.executeUpdate(sql, rating, bookingInfo.getBookingId());
        if (result.isEmpty()) {
            System.out.println("Listing rated successfully.");
        } else {
            System.out.println("Failed to rate the listing: " + result);
        }
    }

    // BookingsInfo class to store booking information
    public static class BookingsInfo {
        private int bookingId;
        private int totalCost;
        private Date startDate;
        private Date endDate;
        private int listId;
        private int hostId;

        public BookingsInfo(int bookingId, int totalCost, Date startDate, Date endDate, int listId, int hostId) {
            this.bookingId = bookingId;
            this.totalCost = totalCost;
            this.startDate = startDate;
            this.endDate = endDate;
            this.listId = listId;
            this.hostId = hostId;
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

        public int getHostId() {
            return hostId;
        }
    }

    // Function to fetch previous bookings for the given user ID
    public static List<BookingsInfo> fetchPreviousBookings(int userId) {
        List<BookingsInfo> bookingsList = new ArrayList<>();
        String sql = "SELECT booking_id, start_date, end_date, list_id, total_cost FROM Bookings " +
                "WHERE user_id = ? AND end_date < CURDATE() AND is_cancelled = FALSE AND is_cancelled_by_host = FALSE";
        try (ResultSet resultSet = SQL.executeQuery(sql, userId)) {
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                int listId = resultSet.getInt("list_id");
                int totalCost = resultSet.getInt("total_cost");
                int hostId = retrieveHostId(listId);
                bookingsList.add(new BookingsInfo(bookingId, totalCost, startDate, endDate, listId, hostId));
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
