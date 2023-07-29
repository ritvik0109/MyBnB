public class Listing {
    private int listId;
    private String propertyType;
    private String title;
    private String description;
    private double pricePerNight;
    private String address;
    private String city;
    private String country;
    private int postalCode;
    private String unitRoomNumber;
    private double longitude;
    private double latitude;
    private int userId;

    // Constructor
    public Listing(int listId, String propertyType, String title, String description, double pricePerNight,
            String address, String city, String country, int postalCode, String unitRoomNumber,
            double longitude, double latitude, int userId) {
        this.listId = listId;
        this.propertyType = propertyType;
        this.title = title;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.unitRoomNumber = unitRoomNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userId = userId;
    }

    public Listing() {
        this.listId = -1;
    }

    // Getters and Setters
    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getUnitRoomNumber() {
        return unitRoomNumber;
    }

    public void setUnitRoomNumber(String unitRoomNumber) {
        this.unitRoomNumber = unitRoomNumber;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
