package edu.ctu.storykeeperdata.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Address {

    private String addressLine1;
    private String city;
    private String state;
    private String postCode;



}
