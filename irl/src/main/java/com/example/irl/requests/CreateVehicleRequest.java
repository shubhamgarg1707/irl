package com.example.irl.requests;

import com.example.irl.entities.Vehicle;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateVehicleRequest {

    @NotNull
    Long driverId;
    @NotBlank
    String registrationNumber;

    public Vehicle toVehicle(){
        return Vehicle.builder()
                .vehicleRegistration(registrationNumber)
                .build();
    }



}
