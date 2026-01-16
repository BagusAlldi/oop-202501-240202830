package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private String category; // <-- Kolom Baru (Penyebab Error)
    private double price;
    private int stock;

    // Constructor Wajib Diperbarui agar menerima 5 parameter
    public Product(String code, String name, String category, double price, int stock) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getter dan Setter
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Getter Setter Kategori (Wajib Ada)
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}