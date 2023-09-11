package com.pandey.models;

import com.pandey.constants.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    String parkingLotId;
    Integer ticketId;
    ParkingSlot parkingSlot;
    TicketStatus ticketStatus;

    public Ticket(Integer ticketId, String parkingLotId, ParkingSlot parkingSlot) {
        this.ticketId = ticketId;
        this.parkingLotId = parkingLotId;
        this.parkingSlot = parkingSlot;
    }
}
