package com.example.irl.requests;

import com.example.irl.entities.RouteMapping;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class CreateRouteVehicleMapping {

    @NotNull
    Long routeId;
    @NotNull
    Long vehicleId;

    @NotNull
    Integer startHour;
    @NotNull
    Integer startMins;

    @NotNull
    Integer endHour;
    @NotNull
    Integer endMins;

    public RouteMapping toRouteMapping(){
        return RouteMapping.builder()
                .routeId(routeId)
                .start(LocalTime.of(startHour, startMins))
                .end(LocalTime.of(endHour, endMins))
                .isActive(true)
                .build();
    }

}
