package com.upb.agripos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. Static instance (Singleton)
    private static DatabaseConnection instance;
    private Connection connection;

    // 2. Private Constructor
    private DatabaseConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/agripos_db"; // DB seragam
            String user = "postgres";
            String password = "bagusafm"; // Password kamu
            
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("LOG: Koneksi Database Berhasil (Singleton)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Public Static Method untuk akses instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}