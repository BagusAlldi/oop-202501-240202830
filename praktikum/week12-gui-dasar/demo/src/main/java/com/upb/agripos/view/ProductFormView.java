package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductFormView {
    // Komponen UI
    private TextField txtKode = new TextField();
    private TextField txtNama = new TextField();
    private TextField txtKategori = new TextField(); // Sesuai DB
    private TextField txtHarga = new TextField();
    private TextField txtStok = new TextField();
    private Button btnSimpan = new Button("Tambah Produk");
    private ListView<String> listProduk = new ListView<>();
    private VBox layout;

    public ProductFormView() {
        initUI();
    }

    private void initUI() {
        // 1. Form Input (GridPane)
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        formGrid.addRow(0, new Label("Kode Produk:"), txtKode);
        formGrid.addRow(1, new Label("Nama Produk:"), txtNama);
        formGrid.addRow(2, new Label("Kategori:"), txtKategori);
        formGrid.addRow(3, new Label("Harga (Rp):"), txtHarga);
        formGrid.addRow(4, new Label("Stok:"), txtStok);
        formGrid.add(btnSimpan, 1, 5);

        // 2. Daftar Produk (ListView)
        listProduk.setPrefHeight(200);

        // 3. Layout Utama (VBox)
        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(
            new Label("=== AGRI-POS: KELOLA PRODUK ==="),
            formGrid,
            new Label("Daftar Produk di Database:"),
            listProduk
        );
    }

    public Parent getView() { return layout; }

    // Getters untuk Controller
    public TextField getTxtKode() { return txtKode; }
    public TextField getTxtNama() { return txtNama; }
    public TextField getTxtKategori() { return txtKategori; }
    public TextField getTxtHarga() { return txtHarga; }
    public TextField getTxtStok() { return txtStok; }
    public Button getBtnSimpan() { return btnSimpan; }
    public ListView<String> getListProduk() { return listProduk; }
    
    public void clearFields() {
        txtKode.clear();
        txtNama.clear();
        txtKategori.clear();
        txtHarga.clear();
        txtStok.clear();
    }
}