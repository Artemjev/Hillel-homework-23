package com.hillel.artemjev.contactbook.config;

import com.hillel.artemjev.contactbook.annotations.PropertyName;
import com.hillel.artemjev.contactbook.exception.FailLoadPropertiesFromFileException;
import lombok.Data;

@Data
public class AppProperties {
    @PropertyName("app.service.workmode")
    private String mode;

    @PropertyName("api.base-uri")
    private String baseUri;

    @PropertyName("file.path")
    private String filePath;

    @PropertyName("database.url")
    private String databaseUrl;

    @PropertyName("database.username")
    private String databaseUsername;

    @PropertyName("database.password")
    private String databasePassword;

    public void checkPropertiesExists() {
        if (this.mode == null) {
            throw new FailLoadPropertiesFromFileException("\"mode\" property is empty");
        }
        if (this.mode.equals("api") && baseUri == null) {
            throw new FailLoadPropertiesFromFileException("\"baseUri\" property is empty for \"api\" mode");
        }
        if (this.mode.equals("file") && filePath == null) {
            throw new FailLoadPropertiesFromFileException("\"filePath\" property is empty for \"file\" mode");
        }
        if (this.mode.equals("database") && databaseUrl == null) {
            throw new FailLoadPropertiesFromFileException("\"databaseUrl\" property is empty for \"database\" mode");
        }
        if (this.mode.equals("database") && databaseUsername == null) {
            throw new FailLoadPropertiesFromFileException("\"databaseUsername\" property is empty for \"database\" mode");
        }
        if (this.mode.equals("database") && databasePassword == null) {
            throw new FailLoadPropertiesFromFileException("\"databasePassword\" property is empty for \"database\" mode");
        }
    }
}
