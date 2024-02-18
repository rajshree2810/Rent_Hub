package com.stackroute.producthistory.model;

import lombok.*;

@Getter
@Setter
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
