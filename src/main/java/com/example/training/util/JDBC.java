package com.example.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBC {

    private Connection conn;
    private Logger logger = LogManager.getLogger();


    public Connection getConnection() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");

            conn = DriverManager.getConnection(url, username, password);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return conn;
    }
}
