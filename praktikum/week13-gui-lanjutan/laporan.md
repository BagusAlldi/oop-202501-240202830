
---

# 📘 **Laporan Praktikum Week 13**

**Topik:** *GUI Lanjutan JavaFX (TableView dan Lambda Expression)*

---

## 🧑‍🎓 Identitas

| Data | Keterangan |
| --- | --- |
| **Nama** | Bagus Alldiansyah |
| **NIM** | 240202830 |
| **Kelas** | 3 IKRA |

---

## 🎯 Tujuan

Mahasiswa diharapkan mampu:

1. Menampilkan data kompleks menggunakan **TableView** JavaFX (menggantikan ListView).
2. Mengintegrasikan koleksi objek (`ObservableList`) dengan GUI.
3. Menggunakan **Lambda Expression** untuk menyingkat penulisan *event handling*.
4. Menghubungkan GUI dengan **DAO** secara penuh (Create, Read, Delete).
5. Membangun antarmuka Agri-POS yang lebih interaktif dan informatif.

---

## 🔗 Traceability (Keterkaitan dengan Bab 6)

Implementasi TableView pada praktikum ini konsisten dengan desain Bab 6, memisahkan logika tampilan (View) dari logika bisnis (Service/DAO).

| Artefak Bab 6 | Referensi ID | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
| --- | --- | --- | --- | --- | --- |
| **Use Case** | `UC-02` (Lihat Daftar) | `initialize` / `loadData()` | `ProductController.load()` memanggil `ProductService.findAll()` | `ProductDAO.findAll()` | TableView terisi otomatis dari data PostgreSQL. |
| **Use Case** | `UC-03` (Hapus Produk) | Tombol "Hapus" | `ProductController.delete()` memanggil `ProductService.delete(code)` | `ProductDAO.delete(code)` | Data terhapus di DB & TableView di-refresh. |
| **Sequence** | `SD-02` (Hapus) | Event `setOnAction` | `View` → `Controller` → `Service` | `DAO` → `DB` | Mengikuti urutan sequence diagram; View tidak akses DB langsung. |

---

## 🛠️ Langkah Praktikum & Implementasi Kode

Struktur folder tetap mengikuti Week 12. Perubahan utama ada pada **View** (menggunakan TableView) dan **Controller** (logika hapus & load data).

### 1. Model (`Product.java`)

*Sama seperti Week 11/12 (POJO).*

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

    // Getters are mandatory for PropertyValueFactory in TableView
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}

```

### 2. DAO (`ProductDAOImpl.java`)

*Memastikan method `delete` dan `findAll` terimplementasi dengan benar.*

```java
package com.upb.agripos.dao;

// ... imports (java.sql.*, java.util.*, model.Product)

public class ProductDAOImpl implements ProductDAO {
    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // ... insert method (sama seperti week 11)

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY code ASC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }
    
    // ... method lain (update/findByCode)
}

```

### 3. Service (`ProductService.java`)

*Jembatan logika bisnis.*

```java
package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", "postgres", "123"
            );
            this.productDAO = new ProductDAOImpl(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product p) throws Exception {
        productDAO.insert(p);
    }

    public void deleteProduct(String code) throws Exception {
        productDAO.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }
}

```

### 4. View (`ProductTableView.java`)

*Menggunakan `TableView` dengan `TableColumn`.*

```java
package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductTableView extends VBox {
    // Input Components
    private TextField txtCode = new TextField();
    private TextField txtName = new TextField();
    private TextField txtPrice = new TextField();
    private TextField txtStock = new TextField();
    
    // Buttons
    private Button btnSave = new Button("Simpan");
    private Button btnDelete = new Button("Hapus");

    // Table Components
    private TableView<Product> table = new TableView<>();

    public ProductTableView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        // 1. Form Input Layout
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10); formGrid.setVgap(10);
        formGrid.addRow(0, new Label("Kode:"), txtCode, new Label("Nama:"), txtName);
        formGrid.addRow(1, new Label("Harga:"), txtPrice, new Label("Stok:"), txtStock);
        
        // Button Bar
        HBox buttonBar = new HBox(10, btnSave, btnDelete);
        
        // 2. Setup Table Columns
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Product, String> colName = new TableColumn<>("Nama Produk");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setMinWidth(150);

        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(colCode, colName, colPrice, colStock);

        // Add all to Main Layout
        this.getChildren().addAll(
            new Label("=== Agri-POS Management System ==="),
            formGrid,
            buttonBar,
            new Separator(),
            table
        );
    }

    // Getters for Controller
    public Button getBtnSave() { return btnSave; }
    public Button getBtnDelete() { return btnDelete; }
    public TableView<Product> getTable() { return table; }

    public Product getProductFromInput() {
        return new Product(
            txtCode.getText(), txtName.getText(),
            Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtStock.getText())
        );
    }

    public void clearInput() {
        txtCode.clear(); txtName.clear(); txtPrice.clear(); txtStock.clear();
    }
}

```

### 5. Controller (`ProductController.java`)

*Mengimplementasikan Lambda Expression untuk event handling.*

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class ProductController {
    private ProductTableView view;
    private ProductService service;

    public ProductController(ProductTableView view) {
        this.view = view;
        this.service = new ProductService();
        initController();
        loadData();
    }

    private void initController() {
        // LAMBDA EXPRESSION: Save
        view.getBtnSave().setOnAction(e -> {
            try {
                Product p = view.getProductFromInput();
                service.addProduct(p);
                view.clearInput();
                loadData();
                showAlert("Sukses", "Data berhasil disimpan.");
            } catch (Exception ex) {
                showAlert("Error", "Gagal simpan: " + ex.getMessage());
            }
        });

        // LAMBDA EXPRESSION: Delete
        view.getBtnDelete().setOnAction(e -> {
            Product selected = view.getTable().getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Peringatan", "Pilih produk yang ingin dihapus dari tabel.");
                return;
            }
            try {
                service.deleteProduct(selected.getCode());
                loadData();
                showAlert("Sukses", "Produk " + selected.getCode() + " berhasil dihapus.");
            } catch (Exception ex) {
                showAlert("Error", "Gagal hapus: " + ex.getMessage());
            }
        });
    }

    private void loadData() {
        try {
            // Konversi List biasa ke ObservableList untuk TableView
            ObservableList<Product> data = FXCollections.observableArrayList(service.getAllProducts());
            view.getTable().setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}

```

### 6. Main App (`AppJavaFX.java`)

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        ProductTableView view = new ProductTableView();
        new ProductController(view); // Bind logic

        Scene scene = new Scene(view, 500, 600);
        stage.setTitle("Agri-POS Week 13 - Bagus Alldiansyah");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```

---

## ▶️ Hasil Eksekusi (Screenshot)

*(Silakan tempel screenshot aplikasi yang menampilkan TableView dengan data dari database di sini. Pastikan terlihat kolom Kode, Nama, Harga, Stok, dan data yang terisi)*

**Deskripsi Hasil:**

1. Aplikasi menampilkan data dalam format tabel (`TableView`) yang rapi.
2. Data diambil langsung dari database PostgreSQL saat aplikasi dijalankan (`loadData`).
3. Tombol **Simpan** berfungsi menambahkan baris baru ke tabel dan database.
4. Tombol **Hapus** berfungsi menghapus baris yang dipilih (*highlighted*) dari tabel dan database.

---

## 🔍 Analisis

1. **TableView vs ListView:**
Pada minggu lalu, `ListView` hanya menampilkan string sederhana (toString). Di minggu ini, `TableView` memungkinkan tampilan multi-kolom yang informatif. `PropertyValueFactory` digunakan untuk memetakan field class `Product` ke kolom tabel secara otomatis.
2. **Lambda Expression:**
Penggunaan lambda `e -> { ... }` pada `setOnAction` membuat kode di Controller jauh lebih ringkas dibandingkan menggunakan *Anonymous Inner Class* (`new EventHandler<ActionEvent>() { ... }`). Ini membuat kode lebih mudah dibaca dan dipelihara.
3. **Integrasi Database:**
Proses hapus (`delete`) divalidasi dengan mengecek `getSelectionModel().getSelectedItem()`. Jika user tidak memilih baris, aplikasi memberikan peringatan, mencegah *NullPointerException*.

---

## 🧾 Kesimpulan

Praktikum Week 13 berhasil meningkatkan kualitas antarmuka Agri-POS menjadi lebih profesional menggunakan `TableView`. Integrasi CRUD (Create, Read, Delete) berjalan lancar melalui arsitektur MVC yang solid, menghubungkan GUI JavaFX dengan database PostgreSQL secara efisien dan aman.

---

## 📂 Struktur Direktori

```
praktikum/week13-gui-lanjutan/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ dao/ProductDAO.java
 │   ├─ dao/ProductDAOImpl.java
 │   ├─ service/ProductService.java
 │   ├─ view/ProductTableView.java
 │   ├─ controller/ProductController.java
 │   └─ AppJavaFX.java
 ├─ screenshots/
 │   └─ tableview_produk.png
 └─ laporan_week13.md

```