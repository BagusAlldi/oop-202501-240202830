package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductController {
    private ProductService service;
    private ObservableList<Product> productList;

    public ProductController() {
        this.service = new ProductService();
        this.productList = FXCollections.observableArrayList();
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void loadData() {
        productList.clear();
        productList.addAll(service.findAll());
    }

    // PERBAIKAN DI SINI: Menerima parameter 'category' dan memasukkannya ke Constructor
    public void addProduct(String code, String name, String category, double price, int stock) {
        Product newProduct = new Product(code, name, category, price, stock);
        service.addProduct(newProduct);
        loadData(); // Refresh tabel setelah insert
    }

    public void deleteProduct(String code) {
        service.deleteProduct(code);
        loadData(); // Refresh tabel setelah delete
    }
}