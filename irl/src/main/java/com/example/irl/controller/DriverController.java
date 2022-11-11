package com.example.irl.controller;

import com.example.irl.entities.Driver;
import com.example.irl.requests.CreateDriverRequest;
import com.example.irl.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping(value = "/driver", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> addDriver(@Valid @RequestBody CreateDriverRequest request){
        log.info(" creating driver {} ", request);
        return new ResponseEntity<>(driverService.createDriver(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/driver/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Driver>> getDriver(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(driverService.fetchDriverById(id), HttpStatus.OK);

    }

}
