package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public void addProduct(Product product) {
        // Bisa tambah validasi bisnis di sini, misal: harga tidak boleh negatif
        if (product.getPrice() < 0) return;
        productDAO.save(product);
    }

    public void deleteProduct(String code) {
        productDAO.delete(code);
    }
}