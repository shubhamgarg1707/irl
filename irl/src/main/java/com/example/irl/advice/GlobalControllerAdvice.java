package com.example.irl.advice;

import com.example.irl.exception.DriverNotFoundException;
import com.example.irl.exception.DuplicateVehicleException;
import com.example.irl.exception.RouteNotFoundException;
import com.example.irl.exception.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {


    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDriverNotFoundException(){
        log.info(" exception received {} ", DriverNotFoundException.class.getName());
        return new ResponseEntity<>("Driver Not Found ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDuplicateVehicleException(){
        log.info(" exception received {} ", DuplicateVehicleException.class.getName());
        return new ResponseEntity<>("Vehicle Already Exists ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<String> handleRouteNotFoundException(){
        log.info(" exception received {} ", RouteNotFoundException.class.getName());
        return new ResponseEntity<>("Route Not Found ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(){
        log.info(" exception received {} ", VehicleNotFoundException.class.getName());
        return new ResponseEntity<>("Vehicle Not Found ", HttpStatus.BAD_REQUEST);
    }
    
}
