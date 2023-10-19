package com.mcancankaya.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/VehicleFabric";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "SelamBneCna.12";
    private static Connection connection;

    public static Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Bağlantı başarılı");
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage() + "\nCode : " + e.getErrorCode());
        }
        return connection;
    }
}
