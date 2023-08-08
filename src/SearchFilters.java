import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SearchFilters {
    private String startDate;
    private String endDate;

    private String address;
    private int postalCode;
    private boolean adjacentPC;
    private String city;
    private String country;

    private boolean geolocation;
    private double latitude;
    private double longitude;
    private double distance;

    private double minPrice;
    private double maxPrice;
    private Amenities amenities;

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
        this.geolocation = false;
        this.latitude = 0;
        this.longitude = 0;
        this.distance = 10;
        this.minPrice = -1;
        this.maxPrice = -1;
        this.amenities = null;
        this.sortDistance = false;
        this.ascendingDistance = false;
        this.sortPrice = false;
        this.ascendingPrice = false;
    }

    public String getSearchQuery() {
        String sql = "SELECT l.list_id, l.property_type, l.title, l.description, l.price_per_night, l.address, l.city, l.country, l.postal_code, l.unit_room_number, l.longitude, l.latitude, l.user_id FROM Listings AS l";
        
        if (isEmpty())
            return sql;

        if (geolocation){
            sql = String.format("SELECT *, 6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS((latitude - (%f)) / 2)), 2) + " + 
                "COS(RADIANS(%f)) * COS(RADIANS(latitude)) * " + 
                " POWER(SIN(RADIANS((longitude - (%f)) / 2)), 2))) AS distance FROM listings AS l", latitude, latitude, longitude);
        }

        // Join other tables here, as necessary
        if (amenities != null)
            sql += " JOIN Amenities AS am";

        if (!startDate.isEmpty() && !endDate.isEmpty())
            sql += " JOIN Availabilities AS av";

        sql += " WHERE";

        // Add join conditions here
        if (amenities != null)
            sql += " l.list_id = am.list_id AND";

        if (!startDate.isEmpty() && !endDate.isEmpty())
            sql += " l.list_id = av.list_id AND";

        // Add queries for the respective filters
        if (!address.isEmpty())
            sql += String.format(" l.address = \'%s\' AND", address);

        if (!city.isEmpty())
            sql += String.format(" l.city = \'%s\' AND", city);

        if (!country.isEmpty())
            sql += String.format(" l.country = \'%s\' AND", country);

        if (postalCode != -1){
            if (adjacentPC) {
                sql += String.format(" l.postal_code > %d AND l.postal_code < %d AND", postalCode - 10, postalCode + 10);
                // TODO: Modify fake data so it checks this
            } else 
                sql += String.format(" l.postal_code = %d AND", postalCode);
        }

        if (!startDate.isEmpty() && !endDate.isEmpty())
            sql += String.format(" av.start_date <= \'%s\' AND av.end_date >= \'%s\' AND", startDate, endDate);

        if (amenities != null){
            sql += (amenities.getWifi() ? " am.wifi = true AND" : "");
            sql += (amenities.getKitchen() ? " am.kitchen = true AND" : "");
            sql += (amenities.getWasher() ? " am.washer = true AND" : "");
            sql += (amenities.getDryer() ? " am.dryer = true AND" : "");
            sql += (amenities.getAc() ? " am.ac = true AND" : "");
            sql += (amenities.getHeating() ? " am.heating = true AND" : "");
            sql += (amenities.getWorkspace() ? " am.workspace = true AND" : "");
            sql += (amenities.getTv() ? " am.tv = true AND" : "");
            sql += (amenities.getHairDryer() ? " am.hair_dryer = true AND" : "");
            sql += (amenities.getIron() ? " am.iron = true AND" : "");
            sql += (amenities.getSmokeAlarm() ? " am.smoke_alarm = true AND" : "");
            sql += (amenities.getCarbonMonoxideAlarm() ? " am.carbon_monoxide_alarm = true AND" : "");
            sql += (amenities.getPool() ? " am.spool = true AND" : "");
            sql += (amenities.getFreeParking() ? " am.free_parking = true AND" : "");
            sql += (amenities.getCrib() ? " am.crib = true AND" : "");
            sql += (amenities.getBbqGrill() ? " am.bbq_grill = true AND" : "");
            sql += (amenities.getIndoorFireplace() ? " am.indoor_fireplace = true AND" : "");
            sql += (amenities.getSmokingAllowed() ? " am.smoking_allowed = true AND" : "");
            sql += (amenities.getBreakfast() ? " am.breakfast = true AND" : "");
            sql += (amenities.getGym() ? " am.gym = true AND" : "");
            sql += (amenities.getEvCharger() ? " am.ev_charger = true AND" : "");
            sql += (amenities.getHotTub() ? " am.hot_tub = true AND" : "");
        }

        if (minPrice != -1 && maxPrice != -1){
            sql += String.format(" l.price_per_night > %f AND l.price_per_night < %f AND", minPrice, maxPrice);
        }

        sql = removeSuffix(sql, "WHERE");
        sql = removeSuffix(sql, "AND");

        // Debugging
        // Nearby Point (within 10 km):
        // Latitude: 40.668587
        // Longitude: -74.120145
    
        // Far Point (more than 10 km away):
        // Latitude: 40.751234
        // Longitude: -74.030987

        if (geolocation){
            sql += String.format(" HAVING distance <= %f", distance);
            if (sortDistance){
                String sort = ascendingDistance ? "ASC" : "DESC";
                sql += " ORDER BY distance " + sort;
            }
        }

        if (sortPrice){
            String sort = ascendingPrice ? "ASC" : "DESC";
            sql += " ORDER BY l.price_per_night " + sort;
        }

        return sql;
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

    private static String removeSuffix(String input, String suffix) {
        if (input.endsWith(suffix)) {
            return input.substring(0, input.length() - suffix.length());
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

    public boolean getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(boolean geolocation) {
        this.geolocation = geolocation;
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
