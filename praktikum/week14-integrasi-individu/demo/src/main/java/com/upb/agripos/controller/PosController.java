package com.upb.agripos.controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.util.ValidationException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PosController extends BorderPane {
    private ProductService productService = new ProductService();
    private CartService cartService = new CartService();

    // Komponen UI
    private TableView<Product> tableProduct;
    private TableView<CartItem> tableCart;
    private Label lblTotal;
    
    // Input Fields
    private TextField txtCode, txtName, txtCategory, txtPrice, txtStock;
    private TextField txtQty; 

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public PosController() {
        setPadding(new Insets(10));
        setLeft(createProductSection());
        setRight(createCartSection());
        loadProducts();
    }

    // --- BAGIAN KIRI ---
    private VBox createProductSection() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");
        HBox.setHgrow(box, Priority.ALWAYS); 
        
        Label lbl = new Label("Daftar Produk (Database)");
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        tableProduct = new TableView<>();
        tableProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        tableProduct.setPrefHeight(300);

        // Kolom Table Product
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Product, String> colCat = new TableColumn<>("Kategori");
        colCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setCellFactory(tc -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText((empty || price == null) ? null : currencyFormat.format(price));
            }
        });
        
        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableProduct.getColumns().addAll(colCode, colName, colCat, colPrice, colStock);

        // [BARU] Event Listener: Klik tabel -> Isi form input
        tableProduct.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtCode.setText(newSelection.getCode());
                txtCode.setEditable(false); // Kunci Kode (Primary Key tidak boleh ganti)
                txtName.setText(newSelection.getName());
                txtCategory.setText(newSelection.getCategory());
                // Tampilkan harga polosan (tanpa Rp) biar mudah diedit
                txtPrice.setText(String.valueOf((long) newSelection.getPrice())); 
                txtStock.setText(String.valueOf(newSelection.getStock()));
            }
        });

        // Form Input
        txtCode = new TextField(); txtCode.setPromptText("Kode");
        txtName = new TextField(); txtName.setPromptText("Nama");
        txtCategory = new TextField(); txtCategory.setPromptText("Kategori");
        txtPrice = new TextField(); txtPrice.setPromptText("Harga");
        txtStock = new TextField(); txtStock.setPromptText("Stok");

        // Tombol CRUD
        Button btnAdd = new Button("Simpan Baru");
        btnAdd.setOnAction(e -> {
            addProduct();
            txtCode.setEditable(true); // Reset agar bisa input kode baru
        });

        // [BARU] Tombol Update
        Button btnUpdate = new Button("Update Data");
        btnUpdate.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black;"); // Kuning
        btnUpdate.setOnAction(e -> updateProductAction());

        Button btnDel = new Button("Hapus");
        btnDel.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");
        btnDel.setOnAction(e -> {
            deleteProduct();
            txtCode.setEditable(true);
        });

        Button btnReset = new Button("Reset Form");
        btnReset.setOnAction(e -> {
            clearFields();
            txtCode.setEditable(true);
            tableProduct.getSelectionModel().clearSelection();
        });

        HBox crudBox = new HBox(10, btnAdd, btnUpdate, btnDel, btnReset);
        crudBox.setAlignment(Pos.CENTER_LEFT);

        // Area Masuk Keranjang
        Label lblQty = new Label("Qty:");
        txtQty = new TextField("1");
        txtQty.setPrefWidth(50);
        
        Button btnCart = new Button("Masuk Keranjang >>");
        btnCart.setStyle("-fx-background-color: lightgreen; -fx-font-weight: bold;");
        btnCart.setOnAction(e -> addToCart());

        HBox cartBox = new HBox(10, lblQty, txtQty, btnCart);
        cartBox.setAlignment(Pos.CENTER_RIGHT);

        box.getChildren().addAll(lbl, tableProduct, 
            new HBox(5, txtCode, txtName),
            new HBox(5, txtCategory, txtPrice, txtStock),
            crudBox, // Tombol CRUD di sini
            cartBox
        );
            
        return box;
    }

    // --- BAGIAN KANAN: KERANJANG ---
    private VBox createCartSection() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setPrefWidth(400); 
        box.setStyle("-fx-background-color: #f4f4f4;");

        Label lbl = new Label("Keranjang Belanja");
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        tableCart = new TableView<>();
        tableCart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<CartItem, String> colItem = new TableColumn<>("Item");
        colItem.setCellValueFactory(new PropertyValueFactory<>("productName"));
        
        TableColumn<CartItem, Integer> colQty = new TableColumn<>("Qty");
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        TableColumn<CartItem, Double> colSub = new TableColumn<>("Subtotal");
        colSub.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        colSub.setCellFactory(tc -> new TableCell<CartItem, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText((empty || price == null) ? null : currencyFormat.format(price));
            }
        });

        tableCart.getColumns().addAll(colItem, colQty, colSub);

        lblTotal = new Label("Total: Rp 0");
        lblTotal.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: blue;");

        Button btnPay = new Button("BAYAR SEKARANG");
        btnPay.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        btnPay.setMaxWidth(Double.MAX_VALUE);
        btnPay.setOnAction(e -> processPayment());

        Button btnClear = new Button("Kosongkan Keranjang");
        btnClear.setStyle("-fx-background-color: #ffcccc;");
        btnClear.setMaxWidth(Double.MAX_VALUE);
        btnClear.setOnAction(e -> clearCart());

        box.getChildren().addAll(lbl, tableCart, lblTotal, btnPay, btnClear);
        return box;
    }

    // --- LOGIC ---

    private void loadProducts() {
        tableProduct.getItems().setAll(productService.findAll());
    }

    private void addProduct() {
        try {
            String priceText = txtPrice.getText();
            String stockText = txtStock.getText();
            if (priceText.isEmpty() || stockText.isEmpty()) throw new ValidationException("Data tidak lengkap!");

            Product p = new Product(
                txtCode.getText(), txtName.getText(), txtCategory.getText(),
                Double.parseDouble(priceText), Integer.parseInt(stockText)
            );
            
            productService.addProduct(p);
            loadProducts();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error Input", "Harga dan Stok harus angka!");
        } catch (ValidationException e) {
            showAlert("Validasi", e.getMessage());
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    // [BARU] Logika Update
    private void updateProductAction() {
        try {
            String priceText = txtPrice.getText();
            String stockText = txtStock.getText();
            
            if (priceText.isEmpty() || stockText.isEmpty()) {
                showAlert("Error", "Pastikan Harga dan Stok terisi!");
                return;
            }

            Product p = new Product(
                txtCode.getText(), // Kode diambil dari field yg dikunci
                txtName.getText(), 
                txtCategory.getText(),
                Double.parseDouble(priceText), 
                Integer.parseInt(stockText)
            );
            
            productService.updateProduct(p);
            loadProducts();
            clearFields();
            txtCode.setEditable(true); 
            showAlert("Sukses", "Data produk berhasil diperbarui!");
            
        } catch (NumberFormatException e) {
            showAlert("Error Input", "Harga dan Stok harus angka!");
        } catch (Exception e) {
            showAlert("Error", "Gagal update: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        Product p = tableProduct.getSelectionModel().getSelectedItem();
        if (p != null) {
            productService.deleteProduct(p.getCode());
            loadProducts();
            clearFields();
        } else {
            showAlert("Info", "Pilih produk yang mau dihapus.");
        }
    }

    private void addToCart() {
        Product p = tableProduct.getSelectionModel().getSelectedItem();
        if (p == null) {
            showAlert("Peringatan", "Pilih produk dulu di tabel kiri!");
            return;
        }

        try {
            int qty = Integer.parseInt(txtQty.getText());
            if (qty <= 0) throw new NumberFormatException();

            if (qty > p.getStock()) {
                showAlert("Stok Kurang", "Stok tersedia hanya: " + p.getStock());
                return;
            }

            cartService.addToCart(p, qty);
            refreshCart();
        } catch (NumberFormatException e) {
            showAlert("Error Input", "Qty harus angka positif!");
        }
    }

    private void processPayment() {
        if (cartService.getCartItems().isEmpty()) {
            showAlert("Info", "Keranjang kosong.");
            return;
        }

        double totalTagihan = cartService.calculateTotal();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Pembayaran");
        dialog.setHeaderText("Total: " + currencyFormat.format(totalTagihan));
        dialog.setContentText("Masukkan Uang Pembayaran (Rp):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                double uangBayar = Double.parseDouble(result.get());

                if (uangBayar < totalTagihan) {
                    showAlert("Gagal", "Uang kurang!");
                } else {
                    double kembalian = uangBayar - totalTagihan;
                    for (CartItem item : cartService.getCartItems()) {
                        productService.reduceStock(
                            item.getProduct().getCode(),
                            item.getProduct().getStock(),
                            item.getQuantity()
                        );
                    }
                    cartService.clearCart();
                    refreshCart();
                    loadProducts();
                    clearFields();
                    
                    showAlert("Sukses", "Pembayaran Berhasil!\nKembalian: " + currencyFormat.format(kembalian));
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Input uang tidak valid!");
            }
        }
    }

    private void clearCart() {
        cartService.clearCart();
        refreshCart();
    }

    private void refreshCart() {
        tableCart.getItems().setAll(cartService.getCartItems());
        lblTotal.setText("Total: " + currencyFormat.format(cartService.calculateTotal()));
    }

    private void clearFields() {
        txtCode.clear(); txtName.clear(); txtCategory.clear(); txtPrice.clear(); txtStock.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}