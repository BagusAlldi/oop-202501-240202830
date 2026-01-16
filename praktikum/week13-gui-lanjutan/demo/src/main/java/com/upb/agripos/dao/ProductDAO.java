package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Konfigurasi koneksi dengan password 'bagusafm' dan db 'agripos_db'
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos_db", 
            "postgres",   // Default username postgres
            "bagusafm"    // Password kamu
        );
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        // Menggunakan nama kolom bahasa Indonesia (Week 12)
        String sql = "SELECT * FROM products"; 

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                    rs.getString("kode"),      // DB: kode
                    rs.getString("nama"),      // DB: nama
                    rs.getString("kategori"),  // DB: kategori
                    rs.getDouble("harga"),     // DB: harga
                    rs.getInt("stok")          // DB: stok
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void save(Product product) {
        String sql = "INSERT INTO products (kode, nama, kategori, harga, stok) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStock());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String code) {
        String sql = "DELETE FROM products WHERE kode = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}