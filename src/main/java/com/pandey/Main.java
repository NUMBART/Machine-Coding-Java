package com.pandey;

import com.pandey.constants.VehicleType;
import com.pandey.exceptions.AllSlotsFullException;
import com.pandey.exceptions.InvalidTicketIdException;
import com.pandey.models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getBuilder()
                .setParkingLotId("PR1234")
                .addFloor(new Floor())
                .addFloor(new Floor())
                .addFloor(new Floor())
                .build();
        populateFloor(parkingLot.getFloor(1));
        populateFloor(parkingLot.getFloor(2));
        populateFloor(parkingLot.getFloor(3));
        parkingLot.displayFreeSlots();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Display Free Slot Count");
            System.out.println("4. Display Free Slots");
            System.out.println("5. Display Occupied Slots");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            try {
                switch (choice) {
                    case 1:
                        parkVehicle(parkingLot, scanner);
                        break;
                    case 2:
                        unparkVehicle(parkingLot, scanner);
                        break;
                    case 3:
                        parkingLot.displayFreeSlotCount();
                        break;
                    case 4:
                        parkingLot.displayFreeSlots();
                        break;
                    case 5:
                        parkingLot.displayOccupiedSlots();
                        break;
                    case 6:
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (AllSlotsFullException | InvalidTicketIdException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void parkVehicle(ParkingLot parkingLot, Scanner scanner) throws AllSlotsFullException {
        System.out.print("Enter vehicle type (CAR, BIKE, TRUCK): ");
        String vehicleTypeStr = scanner.nextLine();
        VehicleType vehicleType = VehicleType.valueOf(vehicleTypeStr);

        System.out.print("Enter vehicle registration number: ");
        String regNo = scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        Vehicle vehicle = new Vehicle(regNo, vehicleType, color); // Create the Vehicle object with regNo, vehicleType, and color
        Ticket ticket = parkingLot.park(vehicle);
        System.out.println("Vehicle parked successfully. Ticket ID: " + ticket.getTicketId());
    }

    private static void unparkVehicle(ParkingLot parkingLot, Scanner scanner) throws InvalidTicketIdException {
        System.out.print("Enter ticket ID to unpark vehicle: ");
        Integer ticketId = scanner.nextInt();
 // Consume the newline character
        Vehicle vehicle = parkingLot.unpark(ticketId);
        System.out.println("Vehicle with ticket ID " + ticketId + " unparked successfully.");
    }

    private static void populateFloor(Floor floor) {
        floor.addParkingSlot(new ParkingSlot(VehicleType.TRUCK));
        floor.addParkingSlot(new ParkingSlot(VehicleType.BIKE));
        floor.addParkingSlot(new ParkingSlot(VehicleType.BIKE));
        floor.addParkingSlot(new ParkingSlot(VehicleType.CAR));
        floor.addParkingSlot(new ParkingSlot(VehicleType.CAR));
        floor.addParkingSlot(new ParkingSlot(VehicleType.CAR));

    }
}