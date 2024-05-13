package com.example.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    private static Connection conn;

    public static Connection getConn() {
        if (conn == null) {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.properties")) {
                Properties properties = new Properties();
                properties.load(inputStream);
                String url = properties.getProperty("spring.datasource.url");
                String username = properties.getProperty("spring.datasource.username");
                String password = properties.getProperty("spring.datasource.password");

                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                Logger logger = LogManager.getLogger();
                logger.error(e.toString());
            }
        }
        return conn;

    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
