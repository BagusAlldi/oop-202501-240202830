package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private Product model;
    private ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.displayProductDetails(model);
    }

    // Contoh manipulasi data lewat controller
    public void setProductPrice(double newPrice) {
        model.setHarga(newPrice);
    }
}