
package park_model;

import vehicle_model.Vehicle;

public class ParkingFloor {


    private final Vehicle[] spotsOnFloor;
    private int availableSpots;


    public ParkingFloor(int spotOnFloor) {
        this.availableSpots = spotOnFloor;
        spotsOnFloor = new Vehicle[spotOnFloor];
    }

    public void manipulateAvailableSpots(boolean shouldAdd) {
        if (shouldAdd) {
            availableSpots++;
        } else {
            availableSpots--;
        }
    }

    public int park(Vehicle vehicle) {
        for (int i = 0; i < spotsOnFloor.length; i++) {
            if (this.spotsOnFloor[i] == null) {
                this.spotsOnFloor[i] = vehicle;
                System.out.println("Vehicle " + vehicle.getId() + " parked on spot: " + i);
                this.manipulateAvailableSpots(false);
                return i;
            }
        }
        return 0;
    }

    //TODO: unpark
    public void unpark(int spot) {
        this.spotsOnFloor[spot] = null;
        this.manipulateAvailableSpots(true);
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

}

