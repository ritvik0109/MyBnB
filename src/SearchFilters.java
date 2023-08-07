import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SearchFilters {
    private String startDate; // TODO
    private String endDate; // TODO

    private String address; // To test
    private int postalCode; // To test
    private boolean adjacentPC; // TODO
    private String city; // To test
    private String country; // To test
    private double latitude; // TODO
    private double longitude; // TODO
    private double distance; // TODO

    private double minPrice; // TODO (per night)
    private double maxPrice; // TODO per night
    private Amenities amenities; // TODO 

    // Sorting
    private boolean sortDistance;
    private boolean ascendingDistance;
    private boolean sortPrice;
    private boolean ascendingPrice;

    public SearchFilters() {
        this.startDate = "";
        this.endDate = "";
        this.address = "";
        this.postalCode = -1;
        this.adjacentPC = false;
        this.city = "";
        this.country = "";
        this.latitude = 0;
        this.longitude = 0;
        this.distance = -1;
        this.minPrice = -1;
        this.maxPrice = -1;
        this.amenities = null;
        this.sortDistance = false;
        this.ascendingDistance = false;
        this.sortPrice = false;
        this.ascendingPrice = false;
    }

    public String getSearchQuery() {
        String sql = "SELECT * FROM Listings l";

        if (isEmpty())
            return sql;

        // Add the inner joins here

        sql += " WHERE";

        // Add join conditions here

        if (!city.isEmpty())
            sql += String.format(" l.city = \'%s\' AND", city);

        if (!country.isEmpty())
            sql += String.format(" l.country = \'%s\' AND", country);

        if (postalCode != -1){
            if (adjacentPC) {
                // TODO: Figure out adjacent postal codes
                // Probably need to + or - like 10, to get the adjacent postal codes
            } else 
                sql += String.format(" l.postal_code = %d AND", postalCode);
        }

        /**
         * If amentities, is not empty, then we need to inner join with amenities to get all the bookings with those amentities
         * 
         * If start date is not null, we need to inner join with availability
         *  can we assume that there will always be a start and end date?
         * 
         * 
        */

        return removeEndingAND(sql);
    }

    private boolean isEmpty(){
        return startDate.isEmpty() && 
        endDate.isEmpty() && 
        address.isEmpty() && 
        postalCode == -1 && 
        adjacentPC == false && 
        city.isEmpty() && 
        country.isEmpty() && 
        latitude == 0 && 
        longitude == 0 && 
        distance == -1 && 
        minPrice == -1 && 
        maxPrice == -1 && 
        amenities == null && 
        sortDistance == false && 
        ascendingDistance == false && 
        sortPrice == false && 
        ascendingPrice == false;
    }

    public boolean hasDates() {
        return !(startDate.isEmpty() && endDate.isEmpty());
    }

    public int getDays() {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            return (int) ChronoUnit.DAYS.between(start, end);
        } catch (DateTimeException e) {
            return -1;
        }
    }

    private static String removeEndingAND(String input) {
        if (input.endsWith("AND")) {
            return input.substring(0, input.length() - 3);
        } else {
            return input;
        }
    }

    // Getters and Setters

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public boolean getAdjacentPC() {
        return adjacentPC;
    }

    public void setAdjacentPC(boolean adjacentPC) {
        this.adjacentPC = adjacentPC;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public boolean isSortDistance() {
        return sortDistance;
    }

    public void setSortDistance(boolean sortDistance) {
        this.sortDistance = sortDistance;
    }

    public boolean isAscendingDistance() {
        return ascendingDistance;
    }

    public void setAscendingDistance(boolean ascendingDistance) {
        this.ascendingDistance = ascendingDistance;
    }

    public boolean isSortPrice() {
        return sortPrice;
    }

    public void setSortPrice(boolean sortPrice) {
        this.sortPrice = sortPrice;
    }

    public boolean isAscendingPrice() {
        return ascendingPrice;
    }

    public void setAscendingPrice(boolean ascendingPrice) {
        this.ascendingPrice = ascendingPrice;
    }
}
