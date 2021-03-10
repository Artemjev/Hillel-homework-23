package com.hillel.artemjev.contactbook.services.contacts;

import com.hillel.artemjev.contactbook.entities.Contact;
import com.hillel.artemjev.contactbook.services.user.UserService;
import com.hillel.artemjev.contactbook.util.ContactDtoBuilder;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
public class DbContactsService implements ContactsService {
    private final UserService userService;
    private final DataSource dataSource;
    private final ContactDtoBuilder contactDtoBuilder;

    @Override
    public void add(Contact contact) {
        try {
            System.out.println(userService.getUserId());
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO  contacts(user_id, type, name, value) VALUES(?,?,?,?)"
            );
            statement.setInt(1, userService.getUserId());
            statement.setString(2, contact.getType().toString());
            statement.setString(3, contact.getName());
            statement.setString(4, contact.getContact());
            statement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new LinkedList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, type, name, value FROM contacts WHERE user_id=?"
            );
            statement.setInt(1, userService.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contacts.add(contactDtoBuilder.getContact(resultSet));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> findByName(String name) {
        List<Contact> contacts = new LinkedList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, type, name, value FROM contacts WHERE user_id = ? AND name LIKE ?"
            );
            statement.setInt(1, userService.getUserId());
            statement.setString(2, name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contacts.add(contactDtoBuilder.getContact(resultSet));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> findByValue(String value) {
        List<Contact> contacts = new LinkedList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, type, name, value FROM contacts WHERE user_id = ? AND name LIKE ?"
            );
            statement.setInt(1, userService.getUserId());
            statement.setString(2, "%" + value + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contacts.add(contactDtoBuilder.getContact(resultSet));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean isAuth() {
        return this.userService.isAuth();
    }

    @Override
    public void remove(Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM contacts WHERE user_id = ? AND id = ?"
            );
            statement.setInt(1, userService.getUserId());
            statement.setInt(2, id);
            statement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
