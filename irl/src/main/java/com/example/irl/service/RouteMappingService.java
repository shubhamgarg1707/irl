package com.example.irl.service;

import com.example.irl.entities.Driver;
import com.example.irl.entities.RouteMapping;
import com.example.irl.entities.Vehicle;
import com.example.irl.exception.DriverNotFoundException;
import com.example.irl.exception.RouteNotFoundException;
import com.example.irl.exception.VehicleNotFoundException;
import com.example.irl.repository.RouteMappingRepository;
import com.example.irl.requests.CreateRouteVehicleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteMappingService {

    @Autowired
    DriverService driverService;


    @Autowired
    VehicleService vehicleService;


    @Autowired
    RouteMappingRepository repository;


    public RouteMapping assignRoute(CreateRouteVehicleMapping routeVehicleMapping){
        RouteMapping routeMapping = routeVehicleMapping.toRouteMapping();
        if(Objects.isNull(routeMapping.getRouteId())){
            throw new RouteNotFoundException();
        }

        Long vehicleId = routeVehicleMapping.getVehicleId();
        // check if vehicle exists
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId).orElseThrow(VehicleNotFoundException::new);
        routeMapping.setVehicle(vehicle);

        return saveOrUpdate(routeMapping);
    }

    private RouteMapping saveOrUpdate(RouteMapping routeMapping){
        return repository.save(routeMapping);
    }


    public List<Driver> fetchListOfDriversAssignedToRoute(String time, Long routeId){
        String requestedTime = StringUtils.trimAllWhitespace(time);
        LocalTime startTime = LocalTime.of(0,0);
        LocalTime endTime = LocalTime.of(23, 59);
        if(Objects.nonNull(requestedTime) && !requestedTime.isBlank()){
            startTime = LocalTime.parse(time);
            endTime = LocalTime.parse(time);
        }

        if(Objects.isNull(routeId) || !repository.existsByRouteIdAndActiveTrue(routeId)){
            throw new RouteNotFoundException();
        }

        List<RouteMapping> routeMappings =
                repository.findAllByRouteIdAndActiveTrueAndStartIsLessThanEqualAndEndIsGreaterThanEqual(routeId, startTime, endTime);

        if(CollectionUtils.isEmpty(routeMappings)){
            throw new DriverNotFoundException();
        }
        return routeMappings.stream().map(RouteMapping::getVehicle).map(Vehicle::getDriver).distinct().collect(Collectors.toList());
    }



}
