package com.upb.agripos.config;

public class DatabaseConnection {
    // 1. Atribut static untuk menyimpan instance
    private static DatabaseConnection instance;

    // 2. Constructor private agar tidak bisa di-instansiasi dari luar
    private DatabaseConnection() {
        System.out.println("Koneksi Database Berhasil Dibuat.");
    }

    // 3. Method static untuk mendapatkan instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}