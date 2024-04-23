package com.example.training.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {

    private String driverName = "com.mysql.cj.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private String USERNAME = "root";
    private String PASSWORD = "abc123";
    private Connection conn;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
