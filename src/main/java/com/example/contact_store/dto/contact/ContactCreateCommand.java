package com.example.contact_store.dto.contact;

import com.example.contact_store.domain.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactCreateCommand {

    private String name;
    private Date bornDate;
    private String mothersName;
    private Integer tajNumber;
    private Integer taxIdentificationNumber;
    private List<Address> addresses;
    private Long userId;
    private String phone;
    private String email;
}
