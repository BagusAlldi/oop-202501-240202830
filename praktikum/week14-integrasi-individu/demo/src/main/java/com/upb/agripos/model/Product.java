package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private String category;
    private double price;
    private int stock;

    public Product(String code, String name, String category, double price, int stock) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getter wajib ada untuk TableView JavaFX
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}