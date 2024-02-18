package com.user.register.model;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String userId;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String gender;
    String dateofbirth;
    String imageUrl;
    BankDetail bankDetail;
}
