package com.example.irl.repository;

import com.example.irl.entities.RouteMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface RouteMappingRepository extends JpaRepository<RouteMapping, Long> {

    boolean existsByRouteIdAndActiveTrue(Long routeId);


    List<RouteMapping> findAllByRouteIdAndActiveTrueAndStartIsLessThanEqualAndEndIsGreaterThanEqual(Long routeId,
                                                                                                    LocalTime startLessThanEqualTo,
                                                                                                    LocalTime endGreaterThanEqualTo);
}
