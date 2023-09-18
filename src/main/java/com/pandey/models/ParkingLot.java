package com.pandey.models;

import com.pandey.constants.TicketStatus;
import com.pandey.constants.VehicleType;
import com.pandey.exceptions.AllSlotsFullException;
import com.pandey.exceptions.InvalidTicketIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    String parkingLotId;
    List<Floor> floors = new ArrayList<>();
    HashMap<Integer, Ticket> tickets = new HashMap<>();
    Integer currentTicketId = 1;

    public Floor getFloor(Integer id) {
        return floors.get(id-1);
    }
    ParkingSlot getFirstAvailableSlot(VehicleType vehicleType) throws AllSlotsFullException {
        for(Floor floor: floors)
            for(ParkingSlot parkingSlot: floor.getParkingSlots())
                if(parkingSlot.getVehicleType() == vehicleType && !parkingSlot.isOccupied())
                    return parkingSlot;
        throw new AllSlotsFullException("Sorry! No slots are available for " + vehicleType);
    }
    public Ticket park(Vehicle vehicle) throws AllSlotsFullException {
        try {
            ParkingSlot availableSlot = getFirstAvailableSlot(vehicle.getVehicleType());
            availableSlot.setVehicle(vehicle);
            availableSlot.setIsOccupied(true);
            Ticket ticket = new Ticket(currentTicketId++, parkingLotId, availableSlot);
            tickets.put(ticket.getTicketId(), ticket);
            return ticket;
        }
        catch(AllSlotsFullException ex) {
            throw ex;
        }
    }
    public Vehicle unpark(Integer ticketId) throws InvalidTicketIdException {
        Ticket ticket = tickets.get(ticketId);
        if(ticket == null)
            throw new InvalidTicketIdException("No ticket with given ID found!");
        if(ticket.getTicketStatus() == TicketStatus.UNPARKED)
            throw new InvalidTicketIdException("Vehicle already unparked for this ticket!");
        ParkingSlot parkingSlot = ticket.getParkingSlot();
        parkingSlot.setIsOccupied(false);
        parkingSlot.setVehicle(null);
        ticket.setTicketStatus(TicketStatus.UNPARKED);
        return ticket.getParkingSlot().getVehicle();
    }
    public void displayFreeSlotCount() {
        for(Floor floor: floors) {
            int freeSlotCnt = 0;
            for(ParkingSlot parkingSlot: floor.getParkingSlots()) {
                if(!parkingSlot.isOccupied()) freeSlotCnt++;
            }
            System.out.println("Number of free slots in floor " + floor.getFloorNo() + ": " + freeSlotCnt);
        }
    }
    public void displayFreeSlots() {
        for(Floor floor: floors) {
            System.out.println("Free slots in floor " + floor.getFloorNo() + ": ");
            for(ParkingSlot parkingSlot: floor.getParkingSlots()) {
                if(!parkingSlot.isOccupied())
                    System.out.println(parkingSlot);
            }
        }
    }
    public void displayOccupiedSlots() {
        for(Floor floor: floors) {
            System.out.println("Occupied slots in floor " + floor.getFloorNo() + ": ");
            for(ParkingSlot parkingSlot: floor.getParkingSlots()) {
                if(parkingSlot.isOccupied())
                    System.out.println(parkingSlot);
            }
        }
    }

    public static class Builder {
        String parkingLotId;
        List<Floor> floors = new ArrayList<>();
        public Builder setParkingLotId(String parkingLotId) {
            this.parkingLotId = parkingLotId;
            return this;
        }
        public Builder addFloor(Floor floor) {
            floor.setFloorNo(floors.size()+1);
            this.floors.add(floor);
            return this;
        }
        public ParkingLot build() {
            ParkingLot parkingLot = new ParkingLot(parkingLotId, floors);
            return parkingLot;
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    private ParkingLot(String parkingLotId, List<Floor> floors) {
        this.parkingLotId = parkingLotId;
        this.floors = floors;
    }
}
