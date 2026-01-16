package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductTableView extends VBox {
    private ProductController controller;
    private TableView<Product> table;

    // Menambahkan TextField untuk Kategori
    private TextField txtCode, txtName, txtCategory, txtPrice, txtStock;

    public ProductTableView() {
        this.controller = new ProductController();
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        setupTable();
        setupForm();
        controller.loadData();
    }

    private void setupTable() {
        table = new TableView<>();
        table.setItems(controller.getProductList());

        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        // PERBAIKAN 1: Menambahkan Kolom Kategori di Tabel
        TableColumn<Product, String> colCat = new TableColumn<>("Kategori");
        colCat.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Masukkan kolom kategori ke dalam tabel
        table.getColumns().addAll(colCode, colName, colCat, colPrice, colStock);
        this.getChildren().add(table);
    }

    private void setupForm() {
        txtCode = new TextField(); txtCode.setPromptText("Kode");
        txtName = new TextField(); txtName.setPromptText("Nama");
        // PERBAIKAN 2: Menambahkan Input Field Kategori
        txtCategory = new TextField(); txtCategory.setPromptText("Kategori");
        txtPrice = new TextField(); txtPrice.setPromptText("Harga");
        txtStock = new TextField(); txtStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah");
        Button btnDelete = new Button("Hapus");

        // PERBAIKAN 3: Mengirim data Kategori ke Controller via Lambda
        btnAdd.setOnAction(e -> {
            try {
                String code = txtCode.getText();
                String name = txtName.getText();
                String cat = txtCategory.getText(); // Ambil input kategori
                double price = Double.parseDouble(txtPrice.getText());
                int stock = Integer.parseInt(txtStock.getText());
                
                // Panggil method controller yang baru (5 parameter)
                controller.addProduct(code, name, cat, price, stock);
                clearFields();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Input harga/stok harus angka!");
            }
        });

        btnDelete.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.deleteProduct(selected.getCode());
            } else {
                showAlert("Warning", "Pilih produk yang akan dihapus!");
            }
        });

        // Masukkan txtCategory ke dalam baris input
        HBox formBox = new HBox(10, txtCode, txtName, txtCategory, txtPrice, txtStock, btnAdd, btnDelete);
        this.getChildren().add(formBox);
    }

    private void clearFields() {
        txtCode.clear(); txtName.clear(); txtCategory.clear(); txtPrice.clear(); txtStock.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}