package com.hillel.artemjev.contactbook.services.user;

import com.hillel.artemjev.contactbook.entities.User;

import java.util.List;

public class FictiveUserService implements UserService {

    @Override
    public boolean isAuth() {
        return true;
    }

    @Override
    public void register(User user) {
        throw new UnsupportedOperationException("method \"register\" not supported");
    }

    @Override
    public void login(User user) {
        throw new UnsupportedOperationException("method \"login\" not supported");
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("method \"getAll\" not supported");
    }

    @Override
    public String getToken() {
        throw new UnsupportedOperationException("method \"getToken\" not supported");
    }

    @Override
    public Integer getUserId() {
        throw new UnsupportedOperationException("method \"getUserId\" not supported");
    }
}
