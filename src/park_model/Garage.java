
package park_model;

import vehicle_model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Garage {
    private static Map<String, String> parkedVehicles = new HashMap<>();
    private ParkingFloor[] floors;

    public Garage(int floors, int spots) {
        this.floors = new ParkingFloor[floors];

        for (int i = 0; i < floors; i++) {
            this.floors[i] = new ParkingFloor(spots);
        }
    }

    public int getAvailableSpots() {
        int flag = 0;
        for (ParkingFloor floor : floors) {
            flag += floor.getAvailableSpots();
        }
        System.out.println("The amount of available spots in the garage is: " + flag);
        return flag;
    }

    public void park(Vehicle vehicle) {
        System.out.println("Trying to park the vehicle: " + vehicle.getId());

        for (int i = 0; i < floors.length; i++) {
            if (floors[i].getAvailableSpots() > 0) {
                System.out.println("On floor " + i + " are available spots. Parking your vehicle here.");
                int spot = floors[i].park(vehicle);
                String floorAndSpot = i + "-" + spot;
                parkedVehicles.put(vehicle.getId(), floorAndSpot);
                //System.out.println("Vehicle parked.");
                return;
            } else if (i == floors.length - 1) {
                System.out.println("There are no available spots in the garage.");
            }
        }
    }

    public void unparkVehicle(String vehicleId) {
        vehicleId = vehicleId.toUpperCase().trim();
        if (!this.checkIfParked(vehicleId)) return;
        String floorAndSpot = parkedVehicles.get(vehicleId);
        int floor = this.getFloor(floorAndSpot);
        int spot = this.getSpot(floorAndSpot);
        System.out.println("Your vehicle is parked on the floor: " + floor + " and spot: " + spot + "\nUnparking.");
        parkedVehicles.remove(vehicleId);
        this.floors[floor].unpark(spot);
        System.out.println("Vehicle unparked.");
    }

    public void findVehicle(String vehicleId) {
        vehicleId = vehicleId.toUpperCase().trim();
        if (!this.checkIfParked(vehicleId)) return;
        String floorAndSpot = parkedVehicles.get(vehicleId);
        int floor = this.getFloor(floorAndSpot);
        int spot = this.getSpot(floorAndSpot);
        System.out.println("Your vehicle is parked on the floor: " + floor + " and spot: " + spot);
    }

    public void unparkFromSpot(int floor, int spot) {
        String vehicleId = this.findFromSpot(floor, spot);
        unparkVehicle(vehicleId);
    }

    public String findFromSpot(int floor, int spot) {
        String vehicleId = null;
        String floorAndSpot = floor + "-" + spot;
        for (Map.Entry<String, String> key : parkedVehicles.entrySet()) {
            if (floorAndSpot.equals(key.getValue())) {
                vehicleId = key.getKey();
                System.out.println("The vehicle parked on this spot is: " + vehicleId);
            }
        }

        if (vehicleId == null) {
            System.out.println("There is no parked vehicle on this spot.");
        }
        return vehicleId;
    }

    private boolean checkIfParked(String vehicleId) {
        vehicleId = vehicleId.toUpperCase().trim();
        if (!parkedVehicles.containsKey(vehicleId)) {
            System.out.println("This vehicle is not parked here.");
            return false;
        }
        return true;
    }

    private int getFloor(String floorAndSpot) {
        return Integer.parseInt(floorAndSpot.substring(0, floorAndSpot.indexOf('-')));
    }

    private int getSpot(String floorAndSpot) {
        return Integer.parseInt(floorAndSpot.substring(floorAndSpot.indexOf('-') + 1, floorAndSpot.length()));
    }

}

