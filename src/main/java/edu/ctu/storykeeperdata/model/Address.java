package edu.ctu.storykeeperdata.model;

import lombok.Data;

@Data
public class Address {

    private String addressLine1;
    private String city;
    private String state;
    private String postCode;

    public Address(String addressLine1, String city, String state, String postCode) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
    }


}
