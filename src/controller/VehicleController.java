package controller;

import vehicle_model.Vehicle;
import vehicle_model.VehicleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class VehicleController {
    private static Map<String, Vehicle> registeredVehicles = new HashMap<>();

    public static void createTestVehicles() throws IOException {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                createVehicle("K LB " + i, VehicleType.MOTORCYCLE);
            } else createVehicle("K NA " + i, VehicleType.CAR);
        }
    }

    public static void createVehicle(String vehicleId, VehicleType type) throws IOException {
        try {
            vehicleId = vehicleId.toUpperCase().trim();
            System.out.println("Trying to create vehicle with Id: " + vehicleId);
            if (!checkIfExists(vehicleId)) {
                registeredVehicles.put(vehicleId, new Vehicle(vehicleId, type));
                System.out.println("Vehicle registered !");
                return;
            }
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Vehicle with such plate number (" + vehicleId + ") already exists.\nPlease choose another ID:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String newId = reader.readLine();
            createVehicle(newId, type);
        }
    }

    public static void getRegisteredVehicles() {
        for (Map.Entry<String, Vehicle> vehicleId : registeredVehicles.entrySet()) {
            System.out.println(vehicleId.getKey());
        }
    }

    public static Vehicle getVehicleById(String id) {
        id = id.toUpperCase().trim();
        if (!VehicleController.checkIfExists(id)) {
            System.out.println("Non-existent vehicle.");
            return null;
        }
        return registeredVehicles.get(id);
    }

    private static boolean checkIfExists(String vehicleId) {
        return registeredVehicles.containsKey(vehicleId);
    }
}
