package com.hillel.artemjev.contactbook.services.factory;

import com.hillel.artemjev.contactbook.config.AppProperties;
import com.hillel.artemjev.contactbook.services.AccessToken;
import com.hillel.artemjev.contactbook.services.contacts.ContactsService;
import com.hillel.artemjev.contactbook.services.contacts.DbContactsService;
import com.hillel.artemjev.contactbook.services.user.DbUserService;
import com.hillel.artemjev.contactbook.services.user.UserService;
import com.hillel.artemjev.contactbook.util.ContactDtoBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import javax.sql.DataSource;

public class DbServiceFactory implements ServiceFactory {
    private final AppProperties appProperties;
    private final DataSource dataSource;

    public DbServiceFactory(AppProperties appProperties) {
        this.appProperties = appProperties;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(appProperties.getDatabaseUrl());
        config.setUsername(appProperties.getDatabaseUsername());
        config.setPassword(appProperties.getDatabasePassword());
        config.setMaximumPoolSize(3);
        config.setMinimumIdle(1);
        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public UserService createUserService() {
        return new DbUserService(
                dataSource,
                new AccessToken()
        );
    }

    @Override
    public ContactsService createContactsService(UserService userService) {
        return new DbContactsService(
                userService,
                dataSource,
                new ContactDtoBuilder()
        );
    }
}
