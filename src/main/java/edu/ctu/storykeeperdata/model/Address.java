package edu.ctu.storykeeperdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addressLine1;
    private String city;
    private String state;
    private String postCode;



}
