package com.example.irl.controller;

import com.example.irl.entities.Driver;
import com.example.irl.entities.RouteMapping;
import com.example.irl.requests.CreateRouteVehicleMapping;
import com.example.irl.service.RouteMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class RouteController {

    @Autowired
    RouteMappingService routeMappingService;

    @PostMapping(value = "/routes/assign", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteMapping> assignRoute(@Valid @RequestBody CreateRouteVehicleMapping request){
        log.info(" creating mapping {} ", request);
        return new ResponseEntity<>(routeMappingService.assignRoute(request), HttpStatus.CREATED);
    }

    // GET ~/{RouteId}/DriverDetails?Time=time
    @GetMapping(value = "{RouteId}/DriverDetails" ,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Driver>> fetchDriverDetailsOnRoute(@RequestParam(name = "Time") String time,
                                                                  @PathVariable(value = "RouteId") Long routeId){
        return new ResponseEntity<>( routeMappingService.fetchListOfDriversAssignedToRoute(time, routeId), HttpStatus.OK);
    }
}
