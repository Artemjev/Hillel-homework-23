package com.hillel.artemjev.contactbook.services.user;

import com.hillel.artemjev.contactbook.entities.User;
import com.hillel.artemjev.contactbook.services.AccessToken;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class DbUserService implements UserService {
    private final DataSource dataSource;
    private final AccessToken token;

    @Override
    public boolean isAuth() {
        return this.token.isValid();
    }

    @Override
    public void register(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(login, password, date_born) VALUES(?,?,?)"
            );
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setDate(3, Date.valueOf(user.getDateBorn()));
            statement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void login(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM users WHERE login=? and password=?"
            );
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                token.setUserId(resultSet.getInt("id"));//костыль, как быстрое решение. В токене храню id юзера.
                token.refreshToken("");
            } else {
                throw new RuntimeException("authorization failed");
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new LinkedList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, password, date_born FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setDateBorn(LocalDate.parse(resultSet.getString("date_born")));
                users.add(user);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public String getToken() {
        throw new UnsupportedOperationException("method \"getToken\" not supported");
    }

    @Override
    public Integer getUserId() {
        return token.getUserId();
    }
}
