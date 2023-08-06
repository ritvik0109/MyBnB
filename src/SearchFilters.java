public class SearchFilters {
    private String startDate;
    private String endDate;

    private String address;
    private int postalCode;
    private boolean adjacentPC; // TODO: Figure this out
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private double distance; // or int?

    private double minPrice; // per night
    private double maxPrice; // per night
    private Amenities amenities; // they are looking for the ones that are included!

    // Sorting
    private boolean sortDistance;
    private boolean ascendingDistance;
    private boolean sortPrice;
    private boolean ascendingPrice;

    public SearchFilters() {
        this.startDate = null;
        this.endDate = null;
        this.address = null;
        this.postalCode = -1;
        this.adjacentPC = false;
        this.city = null;
        this.country = null;
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

    public String getSearchQuery() {
        // TODO
        return "";
    }
}
