
---

# 📘 **Laporan Praktikum Week 12**

**Topik:** *GUI Dasar JavaFX (Event-Driven Programming)*

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

1. Menjelaskan konsep *event-driven programming* dalam konteks antarmuka grafis.
2. Membangun antarmuka (GUI) sederhana menggunakan **JavaFX**.
3. Membuat form input data produk yang fungsional.
4. Mengintegrasikan GUI dengan modul backend (**DAO & Service**) yang telah dibuat sebelumnya.
5. Memastikan implementasi kode sesuai dengan rancangan desain (**UML & SOLID**) dari Bab 6.

---

## 🔗 Traceability (Keterkaitan dengan Bab 6)

Sesuai instruksi, implementasi GUI ini merealisasikan rancangan Use Case, Activity Diagram, dan Sequence Diagram yang telah didefinisikan pada Bab 6.

| Artefak Bab 6 | Referensi ID | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
| --- | --- | --- | --- | --- | --- |
| **Use Case** | `UC-01` (Kelola Produk) | Tombol "Simpan" | `ProductController.addProduct()` memanggil `ProductService.add()` | `ProductDAO.insert()` | Data muncul di List UI dan tersimpan permanen di DB. |
| **Activity** | `AD-01` (Tambah Produk) | Event `setOnAction` | Validasi input (angka/text) → Panggil Service | Eksekusi Query INSERT | Jika sukses input field di-reset, jika gagal muncul error. |
| **Sequence** | `SD-01` (Flow Tambah) | View Trigger | `View` → `Controller` → `Service` | `DAO` → `Database` | Mengikuti urutan sequence: UI tidak akses DB langsung. |

---

## 🛠️ Langkah Praktikum & Implementasi Kode

Struktur proyek mengikuti arsitektur **MVC (Model-View-Controller)** dengan layer tambahan **Service** dan **DAO** untuk menerapkan prinsip **SOLID (Separation of Concerns)**.

### 1. Model (`Product.java`)

*Menggunakan model yang sama dari pertemuan Week 11.*

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
    // Getter & Setter
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    
    @Override
    public String toString() { // Override untuk tampilan di ListView
        return code + " - " + name + " (Rp" + price + ")";
    }
}

```

### 2. Service (`ProductService.java`)

*Layer penghubung antara Controller dan DAO. Controller tidak boleh menyentuh JDBC/SQL langsung.*

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
            // Koneksi Database (Sesuaikan user/pass PostgreSQL Anda)
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", "postgres", "123"
            );
            this.productDAO = new ProductDAOImpl(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product p) throws Exception {
        // Bisa tambahkan validasi bisnis di sini sebelum ke DAO
        if (p.getPrice() < 0) throw new Exception("Harga tidak boleh minus");
        productDAO.insert(p);
    }

    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }
}

```

### 3. View (`ProductFormView.java`)

*Kelas ini HANYA mengatur tata letak (Layout) komponen UI. Tidak ada logika bisnis di sini.*

```java
package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductFormView extends VBox {
    private TextField txtCode = new TextField();
    private TextField txtName = new TextField();
    private TextField txtPrice = new TextField();
    private TextField txtStock = new TextField();
    private Button btnSave = new Button("Simpan Produk");
    private ListView<Product> listView = new ListView<>();

    public ProductFormView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        // Form Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.addRow(0, new Label("Kode:"), txtCode);
        grid.addRow(1, new Label("Nama:"), txtName);
        grid.addRow(2, new Label("Harga:"), txtPrice);
        grid.addRow(3, new Label("Stok:"), txtStock);
        grid.add(btnSave, 1, 4);

        this.getChildren().addAll(new Label("=== Agri-POS Input Produk ==="), grid, new Label("Daftar Produk:"), listView);
    }

    // Getter untuk komponen agar bisa diakses Controller
    public Button getBtnSave() { return btnSave; }
    public ListView<Product> getListView() { return listView; }
    
    public Product getProductFromInput() {
        return new Product(
            txtCode.getText(),
            txtName.getText(),
            Double.parseDouble(txtPrice.getText()),
            Integer.parseInt(txtStock.getText())
        );
    }

    public void clearInput() {
        txtCode.clear(); txtName.clear(); txtPrice.clear(); txtStock.clear();
    }
}

```

### 4. Controller (`ProductController.java`)

*Mengatur Event Handling: Apa yang terjadi ketika tombol diklik.*

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;
import javafx.scene.control.Alert;

public class ProductController {
    private ProductFormView view;
    private ProductService service;

    public ProductController(ProductFormView view) {
        this.view = view;
        this.service = new ProductService();
        
        initController();
        loadData();
    }

    private void initController() {
        // Event Handler: Lambda Expression
        view.getBtnSave().setOnAction(e -> {
            try {
                Product p = view.getProductFromInput();
                service.addProduct(p); // Panggil Service -> DAO -> DB
                view.clearInput();
                loadData(); // Refresh List
                showAlert("Sukses", "Produk berhasil disimpan!");
            } catch (Exception ex) {
                showAlert("Error", "Gagal simpan: " + ex.getMessage());
            }
        });
    }

    private void loadData() {
        try {
            view.getListView().getItems().setAll(service.getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }
}

```

### 5. Main App (`AppJavaFX.java`)

*Entry point aplikasi JavaFX.*

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.view.ProductFormView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        // 1. Init View
        ProductFormView view = new ProductFormView();
        
        // 2. Init Controller (Menghubungkan View dengan Logic)
        new ProductController(view);

        // 3. Tampilkan Scene
        Scene scene = new Scene(view, 400, 500);
        stage.setTitle("Agri-POS Week 12 - Bagus Alldiansyah");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```

---

## ▶️ Hasil Eksekusi GUI

*(Silakan tempel screenshot GUI yang sudah berjalan di sini. Pastikan terlihat jendela form input dan data yang muncul di ListView)*

**Deskripsi Hasil:**

1. Aplikasi menampilkan form input (Kode, Nama, Harga, Stok).
2. Saat tombol "Simpan Produk" diklik, aplikasi memanggil `ProductService`.
3. Data tersimpan ke PostgreSQL melalui JDBC.
4. `ListView` di bagian bawah otomatis terupdate menampilkan data produk yang baru dimasukkan.

---

## 🔍 Analisis Penerapan Design Pattern

1. **MVC (Model-View-Controller):**
Aplikasi dipisah secara tegas. `ProductFormView` hanya berisi kode UI JavaFX. `ProductController` menangani event klik. `ProductService` menangani logika bisnis. Ini memudahkan perawatan kode.
2. **Event-Driven Programming:**
Logika program tidak berjalan linear dari atas ke bawah, melainkan menunggu **trigger**. Dalam kasus ini, trigger-nya adalah event `setOnAction` pada tombol simpan. Program "diam" sampai user melakukan aksi.
3. **Kesesuaian dengan Bab 6 (SOLID):**
* **Single Responsibility Principle (SRP):** View hanya mengurus tampilan, DAO hanya mengurus SQL.
* **Dependency Inversion:** UI tidak bergantung langsung pada Database Driver, melainkan melalui abstraksi Service.



---

## 🧾 Kesimpulan

Praktikum Week 12 ini berhasil mengimplementasikan antarmuka grafis (GUI) menggunakan JavaFX yang terintegrasi penuh dengan backend database. Penggunaan pola MVC dan Service Layer memastikan bahwa logika aplikasi tetap bersih dan sesuai dengan rancangan diagram (Sequence & Class Diagram) yang telah dibuat pada Bab 6.

---

## 📂 Struktur Direktori

```
praktikum/week12-gui-dasar/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ dao/ProductDAO.java
 │   ├─ dao/ProductDAOImpl.java
 │   ├─ service/ProductService.java
 │   ├─ view/ProductFormView.java
 │   ├─ controller/ProductController.java
 │   └─ AppJavaFX.java
 ├─ screenshots/
 │   └─ gui_form_produk.png
 └─ laporan_week12.md

```