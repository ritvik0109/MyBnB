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

        String sql = "UPDATE Listings SET property_type = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.propertyType, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated property propertyTypen");
        } else {
            System.out.println("Error: Failed to update property type in database.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        String sql = "UPDATE Listings SET title = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.title, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated title!\n");
        } else {
            System.out.println("Error: Failed to update title type in database.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

        String sql = "UPDATE Listings SET description = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.description, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated description type!\n");
        } else {
            System.out.println("Error: Failed to update description type in database.");
        }
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;

        String sql = "UPDATE Listings SET price_per_night = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.pricePerNight, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated price!\n");
        } else {
            System.out.println("Error: Failed to update price type in database.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

        String sql = "UPDATE Listings SET address = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.address, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated address!\n");
        } else {
            System.out.println("Error: Failed to update address type in database.");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;

        String sql = "UPDATE Listings SET city = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.city, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated city!\n");
        } else {
            System.out.println("Error: Failed to update city type in database.");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;

        String sql = "UPDATE Listings SET country = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.country, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated country!\n");
        } else {
            System.out.println("Error: Failed to update country type in database.");
        }
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;

        String sql = "UPDATE Listings SET postal_code = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.postalCode, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated postal code!\n");
        } else {
            System.out.println("Error: Failed to update postal code type in database.");
        }
    }

    public String getUnitRoomNumber() {
        return unitRoomNumber;
    }

    public void setUnitRoomNumber(String unitRoomNumber) {
        this.unitRoomNumber = unitRoomNumber;

        String sql = "UPDATE Listings SET unit_room_number = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.unitRoomNumber, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated unit/room number!\n");
        } else {
            System.out.println("Error: Failed to update unit/room number type in database.");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;

        String sql = "UPDATE Listings SET longitude = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.longitude, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated longitude!\n");
        } else {
            System.out.println("Error: Failed to update longitude type in database.");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;

        String sql = "UPDATE Listings SET latitude = ? WHERE list_id = ?";
        String success = SQL.executeUpdate(sql, this.latitude, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println("Successfully updated latitude!\n");
        } else {
            System.out.println("Error: Failed to update latitude type in database.");
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
