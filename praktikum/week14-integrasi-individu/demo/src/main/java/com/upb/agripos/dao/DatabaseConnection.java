package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection instance;

    private DatabaseConnection() {} // Constructor private agar tidak bisa di-new sembarangan

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            // Sesuaikan username dan password DB kamu di sini
            instance = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos_db", 
                "postgres", 
                "bagusafm" 
            );
        }
        return instance;
    }
}