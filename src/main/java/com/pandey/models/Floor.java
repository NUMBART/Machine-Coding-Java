package com.pandey.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Floor {
    Integer floorNo;
    List<ParkingSlot> parkingSlots = new ArrayList<>();

    // Floors should be added first followed by parking slots otherwise
    // floorNo in parkingSlot will not be set properly
    public void addParkingSlot(ParkingSlot parkingSlot) {
        parkingSlot.setFloorNo(floorNo);
        parkingSlot.setSlotNo(parkingSlots.size()+1);
        parkingSlots.add(parkingSlot);
    }
}
