package com.pandey.models;

import com.pandey.constants.VehicleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingSlot {
    Integer slotNo;
    Integer floorNo;
    VehicleType vehicleType;
    Boolean isOccupied = false;
    Vehicle vehicle;

    public Boolean isOccupied() {
        return this.isOccupied;
    }

    public ParkingSlot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
