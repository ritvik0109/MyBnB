import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static void addAvailability(int listId, String startDate, String endDate){
        // If there is an availability with an end_date = startDate - 1, then merge them
        String sql = "SELECT * FROM Availabilities WHERE list_id = ? AND end_date = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, listId, getPreviousDay(startDate))) {
            if (resultSet.next()) {
                int availId = resultSet.getInt("avail_id");
                String availStart = resultSet.getString("start_date");

                // Delete this availability
                removeAvailability(availId);

                // Merged availability has new start date
                startDate = availStart;
                
                // Insert merged availability happens below
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // If there is an availability with start_date = endDate + 1, then merge them
        sql = "SELECT * FROM Availabilities WHERE list_id = ? AND start_date = ?";
        try (ResultSet resultSet = SQL.executeQuery(sql, listId, getNextDay(endDate))) {
            if (resultSet.next()) {
                int availId = resultSet.getInt("avail_id");
                String availEnd = resultSet.getString("end_date");

                // Delete this availability
                removeAvailability(availId);

                // Merged availability has new end date
                endDate = availEnd;
                
                // Insert merged availability happens below
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        insertAvailability(listId, startDate, endDate);
    }

    private static void insertAvailability(int listId, String startDate, String endDate){
        String sql = "INSERT INTO Availabilities (start_date, end_date, list_id) VALUES (?, ?, ?)";
        String success = SQL.executeUpdate(sql, startDate, endDate, listId);
        if (success.isEmpty()) {
            System.out.println("Successfully added availability!");
        } else {
            System.out.println("Failed to add availability! Please try again.");
            System.out.println("Error: " + success);
        }
    }

    public static void removeAvailability(int availId){
        String sql = "DELETE FROM Availabilities WHERE avail_id = ?";
        String success = SQL.executeUpdate(sql, availId);
        if (success.isEmpty()) {
            System.out.println("Successfully deleted availability!");
        } else {
            System.out.println("Failed to delete availability! Please try again.");
            System.out.println("Error: " + success);
        }
    }

    private static String getPreviousDay(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        LocalDate previousDay = date.minusDays(1);
        return previousDay.format(formatter);
    }

    private static String getNextDay(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        LocalDate nextDay = date.plusDays(1);
        return nextDay.format(formatter);
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
