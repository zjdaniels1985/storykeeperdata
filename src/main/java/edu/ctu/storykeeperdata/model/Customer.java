package edu.ctu.storykeeperdata.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
@Builder
@Getter
@Setter
@ToString
@Data
public class Customer {

    @Id
    private String id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    private String email;
    private String phone;
    private Address address;
}
