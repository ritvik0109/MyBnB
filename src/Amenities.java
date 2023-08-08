import java.util.ArrayList;
import java.util.List;

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
        this.pool = pool;
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

    public Amenities() {}

    public Amenities(boolean bool, int list_id) {
        this.listId = list_id;
        this.wifi = bool;
        this.kitchen = bool;
        this.washer = bool;
        this.dryer = bool;
        this.ac = bool;
        this.heating = bool;
        this.workspace = bool;
        this.tv = bool;
        this.hairDryer = bool;
        this.iron = bool;
        this.smokeAlarm = bool;
        this.carbonMonoxideAlarm = bool;
        this.freeParking = bool;
        this.crib = bool;
        this.bbqGrill = bool;
        this.indoorFireplace = bool;
        this.smokingAllowed = bool;
        this.breakfast = bool;
        this.gym = bool;
        this.evCharger = bool;
        this.hotTub = bool;
    }

    public List<String> getCodes(boolean included) {
        List<String> codes = new ArrayList<String>();

        if (this.wifi == included)
            codes.add("wifi");
        if (this.kitchen == included)
            codes.add("kitchen");
        if (this.washer == included)
            codes.add("washer");
        if (this.dryer == included)
            codes.add("dryer");
        if (this.ac == included)
            codes.add("ac");
        if (this.heating == included)
            codes.add("heating");
        if (this.workspace == included)
            codes.add("workspace");
        if (this.tv == included)
            codes.add("tv");
        if (this.hairDryer == included)
            codes.add("hair_dryer");
        if (this.iron == included)
            codes.add("iron");
        if (this.smokeAlarm == included)
            codes.add("smoke_alarm");
        if (this.carbonMonoxideAlarm == included)
            codes.add("carbon_monoxide_alarm");
        if (this.pool == included)
            codes.add("pool");
        if (this.freeParking == included)
            codes.add("free_parking");
        if (this.crib == included)
            codes.add("crib");
        if (this.bbqGrill == included)
            codes.add("bbq_grill");
        if (this.indoorFireplace == included)
            codes.add("indoor_fireplace");
        if (this.smokingAllowed == included)
            codes.add("smoking_allowed");
        if (this.breakfast == included)
            codes.add("breakfast");
        if (this.gym == included)
            codes.add("gym");
        if (this.evCharger == included)
            codes.add("ev_charger");
        if (this.hotTub == included)
            codes.add("hot_tub");
        
        return codes;
    }

    public String getAmenityNamebyCode(String code) {
        if (code == "wifi")
            return "Wifi";
        if (code == "kitchen")
            return "Kitchen";
        if (code == "washer")
            return "Washer";
        if (code == "dryer")
            return "Dryer";
        if (code == "ac")
            return "AC";
        if (code == "heating")
            return "Heating";
        if (code == "workspace")
            return "Workspace";
        if (code == "tv")
            return "TV";
        if (code == "hair_dryer")
            return "Hair Dryer";
        if (code == "iron")
            return "Iron";
        if (code == "smoke_alarm")
            return "Smoke Alarm";
        if (code == "carbon_monoxide_alarm")
            return "Carbon Monoxide Alarm";
        if (code == "pool")
            return "Pool";
        if (code == "free_parking")
            return "Free Parking";
        if (code == "crib")
            return "Crib";
        if (code == "bbq_grill")
            return "BBQ Grill";
        if (code == "indoor_fireplace")
            return "Indoor Fireplace";
        if (code == "smoking_allowed")
            return "Smoking Allowed";
        if (code == "breakfast")
            return "Breakfast";
        if (code == "gym")
            return "Gym";
        if (code == "ev_charger")
            return "EV Charger";
        if (code == "hot_tub")
            return "Hot Tub";

        return "";
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
    }

    public void updateWifi(boolean wifi) {
        this.wifi = wifi;
        setAmenity("wifi", wifi);
    }

    public boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public void updateKitchen(boolean kitchen) {
        this.kitchen = kitchen;
        setAmenity("kitchen", kitchen);
    }

    public boolean getWasher() {
        return washer;
    }

    public void setWasher(boolean washer) {
        this.washer = washer;
    }

    public void updateWasher(boolean washer) {
        this.washer = washer;
        setAmenity("washer", washer);
    }

    public boolean getDryer() {
        return dryer;
    }

    public void setDryer(boolean dryer) {
        this.dryer = dryer;
    }

    public void updateDryer(boolean dryer) {
        this.dryer = dryer;
        setAmenity("dryer", dryer);
    }

    public boolean getAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public void updateAc(boolean ac) {
        this.ac = ac;
        setAmenity("ac", ac);
    }

    public boolean getHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public void updateHeating(boolean heating) {
        this.heating = heating;
        setAmenity("heating", heating);
    }

    public boolean getWorkspace() {
        return workspace;
    }

    public void setWorkspace(boolean workspace) {
        this.workspace = workspace;
    }

    public void updateWorkspace(boolean workspace) {
        this.workspace = workspace;
        setAmenity("workspace", workspace);
    }

    public boolean getTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void updateTv(boolean tv) {
        this.tv = tv;
        setAmenity("tv", tv);
    }

    public boolean getHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(boolean hairDryer) {
        this.hairDryer = hairDryer;
    }

    public void updateHairDryer(boolean hairDryer) {
        this.hairDryer = hairDryer;
        setAmenity("hair_dryer", hairDryer);
    }

    public boolean getIron() {
        return iron;
    }

    public void setIron(boolean iron) {
        this.iron = iron;
    }

    public void updateIron(boolean iron) {
        this.iron = iron;
        setAmenity("iron", iron);
    }

    public boolean getSmokeAlarm() {
        return smokeAlarm;
    }

    public void setSmokeAlarm(boolean smokeAlarm) {
        this.smokeAlarm = smokeAlarm;
    }

    public void updateSmokeAlarm(boolean smokeAlarm) {
        this.smokeAlarm = smokeAlarm;
        setAmenity("smoke_alarm", smokeAlarm);
    }

    public boolean getCarbonMonoxideAlarm() {
        return carbonMonoxideAlarm;
    }

    public void setCarbonMonoxideAlarm(boolean carbonMonoxideAlarm) {
        this.carbonMonoxideAlarm = carbonMonoxideAlarm;
    }

    public void updateCarbonMonoxideAlarm(boolean carbonMonoxideAlarm) {
        this.carbonMonoxideAlarm = carbonMonoxideAlarm;
        setAmenity("carbon_monoxide_alarm", carbonMonoxideAlarm);
    }

    public boolean getPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public void updatePool(boolean pool) {
        this.pool = pool;
        setAmenity("pool", pool);
    }

    public boolean getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    public void updateFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
        setAmenity("free_parking", freeParking);
    }

    public boolean getCrib() {
        return crib;
    }

    public void setCrib(boolean crib) {
        this.crib = crib;
    }

    public void updateCrib(boolean crib) {
        this.crib = crib;
        setAmenity("crib", crib);
    }

    public boolean getBbqGrill() {
        return bbqGrill;
    }

    public void setBbqGrill(boolean bbqGrill) {
        this.bbqGrill = bbqGrill;
    }

    public void updateBbqGrill(boolean bbqGrill) {
        this.bbqGrill = bbqGrill;
        setAmenity("bbq_grill", bbqGrill);
    }

    public boolean getIndoorFireplace() {
        return indoorFireplace;
    }

    public void setIndoorFireplace(boolean indoorFireplace) {
        this.indoorFireplace = indoorFireplace;
    }

    public void updateIndoorFireplace(boolean indoorFireplace) {
        this.indoorFireplace = indoorFireplace;
        setAmenity("indoor_fireplace", indoorFireplace);
    }

    public boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public void updateSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
        setAmenity("smoking_allowed", smokingAllowed);
    }

    public boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public void updateBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
        setAmenity("breakfast", breakfast);
    }

    public boolean getGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public void updateGym(boolean gym) {
        this.gym = gym;
        setAmenity("gym", gym);
    }

    public boolean getEvCharger() {
        return evCharger;
    }

    public void setEvCharger(boolean evCharger) {
        this.evCharger = evCharger;
    }

    public void updateEvCharger(boolean evCharger) {
        this.evCharger = evCharger;
        setAmenity("ev_charger", evCharger);
    }

    public boolean getHotTub() {
        return hotTub;
    }

    public void setHotTub(boolean hotTub) {
        this.hotTub = hotTub;
    }

    public void updateHotTub(boolean hotTub) {
        this.hotTub = hotTub;
        setAmenity("hot_tub", hotTub);
    }

    public void addAmenity() {
        // Adds the current amenity to the amenity
        String sql = "INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, " +
                "iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, " +
                "indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String success = SQL.executeUpdate(sql, this.wifi, this.kitchen, this.washer, this.dryer, this.ac,
                this.heating, this.workspace, this.tv, this.hairDryer, this.iron, this.smokeAlarm,
                this.carbonMonoxideAlarm, this.pool, this.freeParking, this.crib, this.bbqGrill,
                this.indoorFireplace, this.smokingAllowed, this.breakfast, this.gym, this.evCharger,
                this.hotTub, this.listId);
        if (success.isEmpty()) {
            System.out.println("Successfully added amenities!");
        } else {
            System.out.println("Failed to add amenities! Please try again.");
            System.out.println("Error: " + success);
        }
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
