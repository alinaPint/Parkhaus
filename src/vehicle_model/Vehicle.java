package vehicle_model;

import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    protected String vehicleId;
    protected VehicleType vehicleType;

    public Vehicle(String id, VehicleType type) {
        this.vehicleId = id;
        this.vehicleType = type;
    }

    //getters/setters methods
    public String getId() {
        return vehicleId;
    }

    public VehicleType getVehicleTyp() {
        return vehicleType;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
