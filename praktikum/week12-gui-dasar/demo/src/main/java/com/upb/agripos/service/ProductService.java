package com.upb.agripos.service;

import java.sql.Connection;
import java.util.List;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class ProductService {
    private ProductDAO dao;

    public ProductService() {
        // Mengambil koneksi Singleton dari Week 10
        Connection conn = DatabaseConnection.getInstance().getConnection();
        this.dao = new ProductDAOImpl(conn);
    }

    public void addProduct(Product p) throws Exception {
        // Validasi sederhana
        if (p.getHarga() < 0) throw new Exception("Harga tidak boleh negatif!");
        if (p.getStok() < 0) throw new Exception("Stok tidak boleh negatif!");
        
        // Panggil DAO untuk insert ke DB
        dao.insert(p);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}