package ru.shornikov.restapi.enity;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;
    private String department;
    private Date createdate;
    private Date changedate;
    private int securelevel;
}
