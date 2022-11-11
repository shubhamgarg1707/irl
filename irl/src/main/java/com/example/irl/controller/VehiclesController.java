package com.example.irl.controller;

import com.example.irl.entities.Driver;
import com.example.irl.entities.Vehicle;
import com.example.irl.requests.CreateDriverRequest;
import com.example.irl.requests.CreateVehicleRequest;
import com.example.irl.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class VehiclesController {

    @Autowired
    VehicleService vehicleService;


    @PostMapping(value = "/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> addDriver(@Valid @RequestBody CreateVehicleRequest request){
        log.info(" creating vehicle {} ", request);
        return new ResponseEntity<>(vehicleService.addVehicleToDriver(request), HttpStatus.CREATED);
    }


}
