package com.hillel.artemjev.contactbook.util;

import com.hillel.artemjev.contactbook.dto.user.GetUserResponse;
import com.hillel.artemjev.contactbook.dto.user.LoginRequest;
import com.hillel.artemjev.contactbook.dto.user.RegisterRequest;
import com.hillel.artemjev.contactbook.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDtoBuilder {

    public LoginRequest getLoinRequest(com.hillel.artemjev.contactbook.entities.User user) {
        LoginRequest request = new LoginRequest();
        request.setLogin(user.getLogin());
        request.setPassword(user.getPassword());
        return request;
    }

    public RegisterRequest getRegisterRequest(com.hillel.artemjev.contactbook.entities.User user) {
        RegisterRequest request = new RegisterRequest();
        request.setLogin(user.getLogin());
        request.setPassword(user.getPassword());
        request.setDateBorn(user.getDateBorn().toString());
        return request;
    }

    public User getUser(GetUserResponse.User userResponse) {
        User user = new User();
        user.setLogin(userResponse.getLogin());
        user.setPassword(userResponse.getPassword());
        user.setDateBorn(LocalDate.parse(userResponse.getDateBorn(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return user;
    }
}
