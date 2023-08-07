public class Amenities {

    private int amenityId;
    private boolean wifi;
    private boolean kitchen;
    private boolean washer;
    private boolean dryer;
    private boolean ac;
    private boolean heating;
    private boolean workspace;
    private boolean tv;
    private boolean hairDryer;
    private boolean iron;
    private boolean smokeAlarm;
    private boolean carbonMonoxideAlarm;
    private boolean pool;
    private boolean freeParking;
    private boolean crib;
    private boolean bbqGrill;
    private boolean indoorFireplace;
    private boolean smokingAllowed;
    private boolean breakfast;
    private boolean gym;
    private boolean evCharger;
    private boolean hotTub;
    private int listId;

    // Constructor
    public Amenities(int amenityId, boolean wifi, boolean kitchen, boolean washer,
            boolean dryer, boolean ac, boolean heating, boolean workspace,
            boolean tv, boolean hairDryer, boolean iron, boolean smokeAlarm,
            boolean carbonMonoxideAlarm, boolean pool, boolean freeParking,
            boolean crib, boolean bbqGrill, boolean indoorFireplace,
            boolean smokingAllowed, boolean breakfast, boolean gym,
            boolean evCharger, boolean hotTub, int listId) {
        this.amenityId = amenityId;
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.washer = washer;
        this.dryer = dryer;
        this.ac = ac;
        this.heating = heating;
        this.workspace = workspace;
        this.tv = tv;
        this.hairDryer = hairDryer;
        this.iron = iron;
        this.smokeAlarm = smokeAlarm;
        this.carbonMonoxideAlarm = carbonMonoxideAlarm;
        this.freeParking = freeParking;
        this.crib = crib;
        this.bbqGrill = bbqGrill;
        this.indoorFireplace = indoorFireplace;
        this.smokingAllowed = smokingAllowed;
        this.breakfast = breakfast;
        this.gym = gym;
        this.evCharger = evCharger;
        this.hotTub = hotTub;
        this.listId = listId;
    }

    public Amenities() {

    }

    // Getters and setters
    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
        setAmenity("wifi", wifi);
    }

    public boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
        setAmenity("kitchen", kitchen);
    }

    public boolean getWasher() {
        return washer;
    }

    public void setWasher(boolean washer) {
        this.washer = washer;
        setAmenity("washer", washer);
    }

    public boolean getDryer() {
        return dryer;
    }

    public void setDryer(boolean dryer) {
        this.dryer = dryer;
        setAmenity("dryer", dryer);
    }

    public boolean getAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
        setAmenity("ac", ac);
    }

    public boolean getHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
        setAmenity("heating", heating);
    }

    public boolean getWorkspace() {
        return workspace;
    }

    public void setWorkspace(boolean workspace) {
        this.workspace = workspace;
        setAmenity("workspace", workspace);
    }

    public boolean getTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
        setAmenity("tv", tv);
    }

    public boolean getHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(boolean hairDryer) {
        this.hairDryer = hairDryer;
        setAmenity("hair_dryer", hairDryer);
    }

    public boolean getIron() {
        return iron;
    }

    public void setIron(boolean iron) {
        this.iron = iron;
        setAmenity("iron", iron);
    }

    public boolean getSmokeAlarm() {
        return smokeAlarm;
    }

    public void setSmokeAlarm(boolean smokeAlarm) {
        this.smokeAlarm = smokeAlarm;
        setAmenity("smoke_alarm", smokeAlarm);
    }

    public boolean getCarbonMonoxideAlarm() {
        return carbonMonoxideAlarm;
    }

    public void setCarbonMonoxideAlarm(boolean carbonMonoxideAlarm) {
        this.carbonMonoxideAlarm = carbonMonoxideAlarm;
        setAmenity("carbon_monoxide_alarm", carbonMonoxideAlarm);
    }

    public boolean getPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
        setAmenity("pool", pool);
    }

    public boolean getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
        setAmenity("free_parking", freeParking);
    }

    public boolean getCrib() {
        return crib;
    }

    public void setCrib(boolean crib) {
        this.crib = crib;
        setAmenity("crib", crib);
    }

    public boolean getBbqGrill() {
        return bbqGrill;
    }

    public void setBbqGrill(boolean bbqGrill) {
        this.bbqGrill = bbqGrill;
        setAmenity("bbq_grill", bbqGrill);
    }

    public boolean getIndoorFireplace() {
        return indoorFireplace;
    }

    public void setIndoorFireplace(boolean indoorFireplace) {
        this.indoorFireplace = indoorFireplace;
        setAmenity("indoor_fireplace", indoorFireplace);
    }

    public boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
        setAmenity("smoking_allowed", smokingAllowed);
    }

    public boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
        setAmenity("breakfast", breakfast);
    }

    public boolean getGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
        setAmenity("gym", gym);
    }

    public boolean getEvCharger() {
        return evCharger;
    }

    public void setEvCharger(boolean evCharger) {
        this.evCharger = evCharger;
        setAmenity("ev_charger", evCharger);
    }

    public boolean getHotTub() {
        return hotTub;
    }

    public void setHotTub(boolean hotTub) {
        this.hotTub = hotTub;
        setAmenity("hot_tub", hotTub);
    }

    private void setAmenity(String code, boolean value) {
        String sql = String.format("UPDATE Amenities SET %s = ? WHERE list_id = ?", code);
        String success = SQL.executeUpdate(sql, value, this.listId);
        System.out.println();
        if (success.isEmpty()) {
            System.out.println(String.format("Successfully updated %s!\n", code));
        } else {
            System.out.println(String.format("Error: Failed to update %s in database.", code));
        }
    }
}
