package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.scene.control.Alert;

public class ProductController {
    private ProductService service;
    private ProductFormView view;

    public ProductController(ProductService service, ProductFormView view) {
        this.service = service;
        this.view = view;
        initController();
        loadData(); // Load data saat aplikasi dibuka
    }

    private void initController() {
        // Event Handler untuk Tombol Simpan
        view.getBtnSimpan().setOnAction(e -> simpanProduk());
    }

    private void simpanProduk() {
        try {
            // 1. Ambil data dari View
            String kode = view.getTxtKode().getText();
            String nama = view.getTxtNama().getText();
            String kategori = view.getTxtKategori().getText();
            double harga = Double.parseDouble(view.getTxtHarga().getText());
            int stok = Integer.parseInt(view.getTxtStok().getText());

            // 2. Buat Model
            Product p = new Product(kode, nama, kategori, harga, stok);

            // 3. Panggil Service (Sesuai Sequence Diagram)
            service.addProduct(p);

            // 4. Update UI
            showAlert("Sukses", "Produk berhasil disimpan!");
            view.clearFields();
            loadData();

        } catch (NumberFormatException ex) {
            showAlert("Error", "Harga dan Stok harus berupa angka!");
        } catch (Exception ex) {
            showAlert("Error", "Gagal simpan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void loadData() {
        try {
            view.getListProduk().getItems().clear();
            List<Product> products = service.getAllProducts();
            for (Product p : products) {
                view.getListProduk().getItems().add(
                    p.getKode() + " - " + p.getNama() + " (" + p.getKategori() + ")"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}