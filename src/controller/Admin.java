package controller;

import park_model.Garage;
import vehicle_model.Vehicle;
import vehicle_model.VehicleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {
    private Garage park;
    private BufferedReader reader;

    public Admin() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        VehicleController.createTestVehicles();
        this.createPark();
        this.startApp();
    }

    private void createPark() throws IOException {
        System.out.println("Please give the number of floors: ");
        int floors = Integer.parseInt(reader.readLine());
        System.out.println("Give the number of spots per floor: ");
        int spots = Integer.parseInt(reader.readLine());
        park = new Garage(floors, spots);
    }

    private void startApp() throws IOException {
        while (true) {
            this.menu();
            switch (Integer.parseInt(reader.readLine())) {
                case 0:
                    System.out.println("Exiting.");
                    return;
                case 1:
                    park.getAvailableSpots();
                    break;
                case 2:
                    System.out.println("Introduce the vehicle id: ");
                    park.findVehicle(reader.readLine());
                    break;
                case 3:
                    System.out.println("Introduce the floor number and after the spot number: ");
                    park.findFromSpot(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
                    break;
                case 4:
                    System.out.println("Please give the plate number: ");
                    Vehicle vehicle = this.getVehicleById(reader.readLine());
                    if (vehicle != null) park.park(vehicle);
                    else System.out.println("Cannot park non-existent vehicle.");
                    break;
                case 5:
                    System.out.println("Please give the plate number: ");
                    park.unparkVehicle(reader.readLine());
                    break;
                case 6:
                    this.unparkByFloorAndSpot();
                    break;
                case 7:
                    this.createVehicle();
                    break;
            }
        }
    }

    private void menu() {
        System.out.println("------------------------------------------------------");
        System.out.println("1. Press 1 to see all available places.");
        System.out.println("2. Press 2 to find spot by vehicle ID.");
        System.out.println("3. Press 3 to find vehicle by floor and spot number.");
        System.out.println("4. Press 4 to park a new vehicle.");
        System.out.println("5. Press 5 to unpark vehicle by ID.");
        System.out.println("6. Press 6 to unpark vehicle by spot.");
        System.out.println("7. Press 7 to create a new vehicle.");
        System.out.println("0. Press 0 to end the program.");
        System.out.println("------------------------------------------------------");
    }

    private void createVehicle() throws IOException {
        System.out.println("Please introduce the plate number: ");
        String id = reader.readLine();
        System.out.println("Select 1 for car - Select 2 for Motorcycle: ");
        int type = Integer.parseInt(reader.readLine());
        switch (type) {
            case 1:
                VehicleController.createVehicle(id, VehicleType.CAR);
                break;
            case 2:
                VehicleController.createVehicle(id, VehicleType.MOTORCYCLE);
                break;
        }
    }

    private Vehicle getVehicleById(String vehicleId) {
        return VehicleController.getVehicleById(vehicleId);
    }

    private void unparkByFloorAndSpot() throws IOException {
        System.out.println("Introduce the floor: ");
        int floor = Integer.parseInt(reader.readLine());
        System.out.println("Introduce the spot: ");
        int spot = Integer.parseInt(reader.readLine());
        park.unparkFromSpot(floor, spot);
    }
}
