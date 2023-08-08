public class Booking {
    private int bookingId;
    private double totalCost;
    private String startDate;
    private String endDate;
    private int userId;
    private int listId;

    public Booking(int bookingId, double totalCost, String startDate, String endDate,
                   int userId, int listId) {
        this.bookingId = bookingId;
        this.totalCost = totalCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.listId = listId;
    }

    // Getters and Setters for each attribute

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
