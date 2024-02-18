package com.user.register.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetail {
    String bankName;
    Long accountNumber;
    String ifsc;
    String holderName;
}
