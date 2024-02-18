package com.stackroute.ProductAdditionSeller.model;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String userId;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String gender;
    String dateofbirth;
    String imageUrl;

}
