package com.hillel.artemjev.contactbook.dto.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String dateBorn;
}
