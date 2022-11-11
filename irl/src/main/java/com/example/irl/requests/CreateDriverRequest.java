package com.example.irl.requests;

import com.example.irl.entities.Driver;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class CreateDriverRequest {

    @NotBlank
    String firstName;
    @NotBlank
    String lastName;

    public Driver toDriver(){
        return Driver.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
