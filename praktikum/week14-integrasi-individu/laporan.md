
---

# 📘 **Laporan Praktikum Week 14**

**Topik:** *Integrasi Individu (OOP + Database + GUI + Testing)*

---

## 🧑‍🎓 Identitas

| Data | Keterangan |
| --- | --- |
| **Nama** | Bagus Alldiansyah |
| **NIM** | 240202830 |
| **Kelas** | 3 IKRA |

---

## 1. Ringkasan Aplikasi (Agri-POS)

Agri-POS adalah aplikasi kasir sederhana untuk toko pertanian. Aplikasi ini memiliki fitur:

1. **Manajemen Produk (CRUD):** Menambah, melihat, dan menghapus produk yang tersimpan di database PostgreSQL.
2. **Keranjang Belanja:** Menambahkan produk ke keranjang, menghitung total belanja secara otomatis menggunakan Java Collections.
3. **Validasi Sistem:** Mencegah input harga negatif atau stok kosong menggunakan Custom Exception.
4. **Persistensi Data:** Semua data produk terhubung langsung ke database.

---

## 2. Keterangan Integrasi (Bab 1–13)

* **Bab 1-5 (OOP):** Penerapan Encapsulation pada Model (`Product`, `CartItem`) dan Abstraction pada interface `ProductDAO`.
* **Bab 6 (SOLID):** Pemisahan tanggung jawab (SRP) antara View, Controller, Service, dan DAO.
* **Bab 7 (Collections):** Penggunaan `HashMap` untuk pengelolaan keranjang belanja agar pencarian item efisien.
* **Bab 9 (Exception):** Custom exception `AgriPosException` untuk menangani validasi bisnis.
* **Bab 10 (Design Pattern & Test):** Penerapan **Singleton Pattern** pada koneksi database dan **JUnit** untuk testing logika keranjang.
* **Bab 11 (JDBC):** Koneksi ke PostgreSQL menggunakan `PreparedStatement`.
* **Bab 12-13 (GUI):** Tampilan interaktif menggunakan JavaFX (`TableView`, `Alert`, `Lambda Expression`).

---

## 3. Artefak UML (Desain Bab 6)

### Class Diagram (Arsitektur MVC + Service + DAO)

![alt text](class.png)
![alt text](actdiagram.png)
![alt text](Sqe.png)
![alt text](<use case.png>)

---

## 4. Tabel Traceability (Implementasi)

| Artefak Bab 6 | Referensi | Handler / Trigger | Controller / Service | DAO | Dampak |
| --- | --- | --- | --- | --- | --- |
| **Use Case** | UC-01 Tambah Produk | Tombol "Simpan Produk" | `PosController.addProduct()` → `ProductService.addProduct()` | `ProductDAO.insert()` | Data tersimpan di DB & TableView refresh. |
| **Use Case** | UC-02 Hapus Produk | Tombol "Hapus Produk" | `PosController.deleteProduct()` → `ProductService.deleteProduct()` | `ProductDAO.delete()` | Data terhapus di DB & TableView refresh. |
| **Activity** | AD-01 Tambah ke Keranjang | Tombol "Add to Cart" | `PosController.addToCart()` → `CartService.addItem()` | - | Produk masuk ke memori (Map), Total harga terupdate di GUI. |
| **Sequence** | SD-01 Load Data | Aplikasi Start / Refresh | `PosController.loadData()` → `ProductService.getAllProducts()` | `ProductDAO.findAll()` | Data dari PostgreSQL muncul di TableView. |

---

## 5. Kendala & Solusi

1. **Kendala:** Kesulitan sinkronisasi antara data di Database dengan data di Keranjang (misal: stok di DB berkurang saat masuk keranjang).
* **Solusi:** Untuk saat ini logika dipisahkan; keranjang hanya menghitung total. Validasi stok dilakukan saat tombol "Add to Cart" ditekan dengan mengecek stok yang tersedia di objek Produk.


2. **Kendala:** Menangani koneksi database yang berulang-ulang.
* **Solusi:** Menerapkan **Singleton Pattern** pada kelas `DatabaseHelper` agar koneksi hanya dibuat satu kali dan dipakai bersama.



---

## 📂 Implementasi Kode Program

Berikut adalah kode lengkap sesuai struktur direktori yang diminta.

### A. Utilitas & Konfigurasi (Design Pattern: Singleton)

**File:** `src/main/java/com/upb/agripos/util/DatabaseHelper.java`

```java
package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    // Singleton Pattern: Hanya ada satu instance koneksi
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Sesuaikan user/pass PostgreSQL Anda
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/agripos", 
                    "postgres", 
                    "123"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

```

**File:** `src/main/java/com/upb/agripos/util/AgriPosException.java` (Custom Exception)

```java
package com.upb.agripos.util;

public class AgriPosException extends Exception {
    public AgriPosException(String message) {
        super(message);
    }
}

```

### B. Model

**File:** `src/main/java/com/upb/agripos/model/Product.java`

```java
package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}

```

**File:** `src/main/java/com/upb/agripos/model/CartItem.java`

```java
package com.upb.agripos.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return product.getPrice() * quantity; }
    
    public void addQuantity(int qty) {
        this.quantity += qty;
    }
}

```

### C. DAO Layer (Data Access Object)

**File:** `src/main/java/com/upb/agripos/dao/ProductDAO.java`

```java
package com.upb.agripos.dao;
import com.upb.agripos.model.Product;
import java.util.List;

public interface ProductDAO {
    void insert(Product p) throws Exception;
    void delete(String code) throws Exception;
    List<Product> findAll() throws Exception;
}

```

**File:** `src/main/java/com/upb/agripos/dao/JdbcProductDAO.java`

```java
package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import com.upb.agripos.util.DatabaseHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDAO implements ProductDAO {
    private Connection connection;

    public JdbcProductDAO() {
        this.connection = DatabaseHelper.getConnection();
    }

    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO products (code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY code ASC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("code"), rs.getString("name"),
                    rs.getDouble("price"), rs.getInt("stock")
                ));
            }
        }
        return list;
    }
}

```

### D. Service Layer (Business Logic & Collections)

**File:** `src/main/java/com/upb/agripos/service/ProductService.java`

```java
package com.upb.agripos.service;

import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import com.upb.agripos.util.AgriPosException;
import java.util.List;

public class ProductService {
    private ProductDAO dao = new JdbcProductDAO();

    public void addProduct(Product p) throws Exception {
        if (p.getPrice() < 0) throw new AgriPosException("Harga tidak boleh negatif!");
        if (p.getName().isEmpty()) throw new AgriPosException("Nama produk wajib diisi!");
        dao.insert(p);
    }

    public void deleteProduct(String code) throws Exception {
        dao.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}

```

**File:** `src/main/java/com/upb/agripos/service/CartService.java`

```java
package com.upb.agripos.service;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import java.util.HashMap;
import java.util.Map;

public class CartService {
    // Collections: Map untuk menyimpan item keranjang (Key: Kode Produk)
    private Map<String, CartItem> cartItems = new HashMap<>();

    public void addItem(Product product, int qty) {
        if (cartItems.containsKey(product.getCode())) {
            CartItem item = cartItems.get(product.getCode());
            item.addQuantity(qty);
        } else {
            cartItems.put(product.getCode(), new CartItem(product, qty));
        }
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems.values()) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    public void clearCart() {
        cartItems.clear();
    }
}

```

### E. View & Controller (GUI JavaFX)

**File:** `src/main/java/com/upb/agripos/view/PosView.java`

```java
package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class PosView extends BorderPane {
    // Form Inputs
    private TextField txtCode = new TextField();
    private TextField txtName = new TextField();
    private TextField txtPrice = new TextField();
    private TextField txtStock = new TextField();
    
    // Buttons
    private Button btnSave = new Button("Simpan Produk");
    private Button btnDelete = new Button("Hapus Produk");
    private Button btnAddToCart = new Button("Tambah ke Keranjang");

    // Display
    private TableView<Product> table = new TableView<>();
    private TextArea txtCartDisplay = new TextArea();
    private Label lblTotal = new Label("Total: Rp 0");

    public PosView() {
        setPadding(new Insets(10));
        
        // --- LEFT SIDE: Product Management ---
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(10));
        leftPane.setStyle("-fx-border-color: #ccc; -fx-border-width: 1;");
        
        GridPane formGrid = new GridPane();
        formGrid.setHgap(5); formGrid.setVgap(5);
        formGrid.addRow(0, new Label("Kode:"), txtCode);
        formGrid.addRow(1, new Label("Nama:"), txtName);
        formGrid.addRow(2, new Label("Harga:"), txtPrice);
        formGrid.addRow(3, new Label("Stok:"), txtStock);
        
        setupTable();
        HBox btnBox = new HBox(5, btnSave, btnDelete);
        leftPane.getChildren().addAll(new Label("=== MANAJEMEN PRODUK ==="), formGrid, btnBox, table);

        // --- RIGHT SIDE: Cart ---
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));
        rightPane.setPrefWidth(250);
        rightPane.setStyle("-fx-background-color: #f0f0f0;");
        
        txtCartDisplay.setEditable(false);
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        rightPane.getChildren().addAll(new Label("=== KERANJANG ==="), btnAddToCart, txtCartDisplay, lblTotal);

        setCenter(leftPane);
        setRight(rightPane);
    }

    private void setupTable() {
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        table.getColumns().addAll(colCode, colName, colPrice, colStock);
    }

    // Getters
    public Product getProductInput() {
        return new Product(txtCode.getText(), txtName.getText(), 
            Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtStock.getText()));
    }
    public TableView<Product> getTable() { return table; }
    public Button getBtnSave() { return btnSave; }
    public Button getBtnDelete() { return btnDelete; }
    public Button getBtnAddToCart() { return btnAddToCart; }
    public TextArea getTxtCartDisplay() { return txtCartDisplay; }
    public Label getLblTotal() { return lblTotal; }
    
    public void clearInput() {
        txtCode.clear(); txtName.clear(); txtPrice.clear(); txtStock.clear();
    }
}

```

**File:** `src/main/java/com/upb/agripos/controller/PosController.java`

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class PosController {
    private PosView view;
    private ProductService productService;
    private CartService cartService;

    public PosController(PosView view) {
        this.view = view;
        this.productService = new ProductService();
        this.cartService = new CartService();
        
        initHandlers();
        loadData();
    }

    private void initHandlers() {
        // 1. Simpan Produk
        view.getBtnSave().setOnAction(e -> {
            try {
                productService.addProduct(view.getProductInput());
                view.clearInput();
                loadData();
                showAlert("Sukses", "Produk disimpan.");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        // 2. Hapus Produk
        view.getBtnDelete().setOnAction(e -> {
            Product selected = view.getTable().getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    productService.deleteProduct(selected.getCode());
                    loadData();
                    showAlert("Sukses", "Produk dihapus.");
                } catch (Exception ex) {
                    showAlert("Error", ex.getMessage());
                }
            }
        });

        // 3. Tambah ke Keranjang
        view.getBtnAddToCart().setOnAction(e -> {
            Product selected = view.getTable().getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (selected.getStock() > 0) {
                    cartService.addItem(selected, 1); // Default qty 1
                    updateCartDisplay();
                } else {
                    showAlert("Stok Habis", "Produk ini stoknya 0.");
                }
            } else {
                showAlert("Peringatan", "Pilih produk di tabel dulu.");
            }
        });
    }

    private void loadData() {
        try {
            view.getTable().setItems(FXCollections.observableArrayList(productService.getAllProducts()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCartDisplay() {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : cartService.getCartItems().values()) {
            sb.append(item.getProduct().getName())
              .append(" x").append(item.getQuantity())
              .append(" = ").append(item.getSubtotal()).append("\n");
        }
        view.getTxtCartDisplay().setText(sb.toString());
        view.getLblTotal().setText("Total: Rp " + cartService.calculateTotal());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}

```

### F. Unit Testing (JUnit)

**File:** `src/test/java/com/upb/agripos/CartServiceTest.java`

```java
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    public void testCalculateTotal() {
        // Setup
        CartService cart = new CartService();
        Product p1 = new Product("P01", "Beras", 10000, 10);
        Product p2 = new Product("P02", "Gula", 5000, 10);

        // Action
        cart.addItem(p1, 2); // 20000
        cart.addItem(p2, 1); // 5000

        // Assertion
        assertEquals(25000.0, cart.calculateTotal(), "Total belanja harus 25000");
    }
}

```

### G. Main Application

**File:** `src/main/java/com/upb/agripos/AppJavaFX.java`

```java
package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.view.PosView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        // Console Identity
        System.out.println("Hello World, I am Bagus Alldiansyah-240202830");

        PosView view = new PosView();
        new PosController(view); // Bind logic

        Scene scene = new Scene(view, 800, 500);
        stage.setTitle("Agri-POS Integrated System (Week 14)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```