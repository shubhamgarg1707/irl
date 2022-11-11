package com.example.irl.service;

import com.example.irl.entities.Driver;
import com.example.irl.repository.DriverRepository;
import com.example.irl.requests.CreateDriverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;


    /**
     * <p>
     *     The below method is used to create a driver
     * </p>
     * @param request
     * @return {@link  Driver}
     */
    public Driver createDriver(CreateDriverRequest request) {
        var driver = request.toDriver();
        return saveOrUpdate(driver);
    }



    private Driver saveOrUpdate(Driver driver){
        return driverRepository.save(driver);
    }

    public Optional<Driver> fetchDriverById(Long id) {
        return driverRepository.findById(id);
    }
}
