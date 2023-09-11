package com.pandey.models;

import com.pandey.constants.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Vehicle {
    String regNo;
    VehicleType vehicleType;
    String color;
}
