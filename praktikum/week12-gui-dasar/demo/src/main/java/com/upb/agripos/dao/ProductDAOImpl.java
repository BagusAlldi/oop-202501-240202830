package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product p) throws Exception {
        // Query menyesuaikan kolom database baru
        String sql = "INSERT INTO products(kode, nama, kategori, harga, stok) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getKode());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getKategori()); // Input Kategori
            ps.setDouble(4, p.getHarga());
            ps.setInt(5, p.getStok());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE products SET nama=?, kategori=?, harga=?, stok=? WHERE kode=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getKategori());
            ps.setDouble(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.setString(5, p.getKode()); // WHERE kode = ...
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String kode) throws Exception {
        String sql = "DELETE FROM products WHERE kode=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        }
    }

    @Override
    public Product findByCode(String kode) throws Exception {
        String sql = "SELECT * FROM products WHERE kode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getString("kode"),
                        rs.getString("nama"),
                        rs.getString("kategori"), // Ambil kategori
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                ));
            }
        }
        return list;
    }
}