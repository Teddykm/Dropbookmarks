package com.udemy.dropbookmarks;

import com.sun.istack.internal.NotNull;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;


public class DropBookmarksConfiguration extends Configuration {

    @NotEmpty
    private String password;

    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }


}
