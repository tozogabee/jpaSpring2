package com.jpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getRequiredProperty("db.url"));
        config.setUsername(environment.getRequiredProperty("db.username"));
        config.setPassword(environment.getRequiredProperty("db.password"));
        config.setMaximumPoolSize(environment.getRequiredProperty("db.connection.maxpoolsize", Integer.class));
        return new HikariDataSource(config);
    }

    @Bean(destroyMethod = "stop")
    public Server server() throws SQLException {
        return Server.createWebServer("-webPort", environment.getRequiredProperty("db.h2.server.port")).start();
    }
}
