package com.example.irl.service;

import com.example.irl.entities.Driver;
import com.example.irl.entities.Vehicle;
import com.example.irl.exception.DriverNotFoundException;
import com.example.irl.exception.DuplicateVehicleException;
import com.example.irl.repository.VehicleRepository;
import com.example.irl.requests.CreateVehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {


    @Autowired
    DriverService driverService;


    @Autowired
    VehicleRepository vehicleRepository;


    public Vehicle addVehicleToDriver(CreateVehicleRequest vehicleRequest){
        Vehicle vehicle = vehicleRequest.toVehicle();
        /**
         * check if driver exists
         */
        Driver driver = driverService.fetchDriverById(vehicleRequest.getDriverId()).orElseThrow(DriverNotFoundException::new);
        vehicle.setDriver(driver);
        /**
         * check if duplicate vehicle
         */
        Optional<Vehicle> existingVehicle = vehicleRepository.findVehicleByVehicleRegistration(vehicle.getVehicleRegistration());
        if(existingVehicle.isPresent()){
            throw new DuplicateVehicleException();
        }
        return saveOrUpdate(vehicle);
    }

    private Vehicle saveOrUpdate(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findVehicleById(Long id){
        return vehicleRepository.findById(id);
    }


}
