public class Availability {
    private int availId;
    private String startDate;
    private String endDate;
    private int listId;

    public Availability(int availId, String startDate, String endDate, int listId) {
        this.availId = availId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.listId = listId;
    }

    // Getters and setters
    public int getAvailId() {
        return availId;
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

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public static void addAvailability(int listId, String startDate, String endDate, boolean show_messages){
        String sql = "INSERT INTO Availabilities (start_date, end_date, list_id) VALUES (?, ?, ?)";
        String success = SQL.executeUpdate(sql, startDate, endDate, listId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully added availability!");
            return;
        } else {
            if (show_messages){
                System.out.println("Failed to add availability! Please try again.");
                System.out.println("Error: " + success);
            }
        }
    }

    public static void removeAvailability(int availId, boolean show_messages){
        String sql = "DELETE FROM Availabilities WHERE avail_id = ?";
        String success = SQL.executeUpdate(sql, availId);
        if (success.isEmpty()) {
            if (show_messages)
                System.out.println("Successfully deleted availability!");
        } else {
            if (show_messages){
                System.out.println("Failed to delete availability! Please try again.");
                System.out.println("Error: " + success);
            }
        }
    }

    // toString method for printing the Availability object
    @Override
    public String toString() {
        return "Availability{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", listId=" + listId +
                '}';
    }
}
