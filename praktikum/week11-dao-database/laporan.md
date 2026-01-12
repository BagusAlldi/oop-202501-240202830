
---

# 📘 **Laporan Praktikum Minggu 11**

**Topik:** *Data Access Object (DAO) dan CRUD Database dengan JDBC pada Agri-POS*

---

## 🧑‍🎓 Identitas

| Data      | Keterangan        |
| --------- | ----------------- |
| **Nama**  | Bagus Alldiansyah |
| **NIM**   | 240202830         |
| **Kelas** | 3 IKRA            |

---

## 🎯 Tujuan

Mahasiswa mampu:

1. Memahami konsep **Data Access Object (DAO)** dalam pemrograman berorientasi objek.
2. Menghubungkan aplikasi Java dengan **database PostgreSQL** menggunakan **JDBC**.
3. Mengimplementasikan operasi **CRUD (Create, Read, Update, Delete)** secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi Agri-POS sesuai prinsip desain yang baik. 

---

## 📚 Dasar Teori

### 1. Data Access Object (DAO)

DAO adalah pola desain yang memisahkan **logika akses data** dari **logika bisnis** aplikasi.
Dengan DAO, perubahan database tidak memengaruhi kode utama aplikasi sehingga sistem lebih mudah dipelihara. 

### 2. JDBC

JDBC (Java Database Connectivity) digunakan untuk menghubungkan Java dengan database relasional.
Komponen penting JDBC:

* `DriverManager`
* `Connection`
* `PreparedStatement`
* `ResultSet` 

---

## 🗄️ Spesifikasi Database

Database: **PostgreSQL**
Nama database: `agripos`

Struktur tabel:

```sql
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE PRECISION,
    stock INT
);
```



---

## 🧩 Implementasi

### 1. Model – Product

```java
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



---

### 2. MAIN DAO TEST

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.util.List;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I am Bagus Alldiansyah - 240202830 (Week11)");
        
        // Sesuaikan dengan setting Docker Anda
        String url = "jdbc:postgresql://localhost:5432/agripos";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Koneksi ke Database Docker Berhasil!");
            ProductDAO dao = new ProductDAOImpl(conn);

            // 1. Tambah Produk
            System.out.println("\n--- Eksekusi INSERT ---");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
            System.out.println("Produk P01 berhasil ditambahkan.");

            // 2. Cari & Update
            System.out.println("\n--- Eksekusi UPDATE ---");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 15));
            Product p = dao.findByCode("P01");
            System.out.println("Data terbaru: " + p.getName() + " | Stock: " + p.getStock());

            // 3. Tampilkan Semua
            System.out.println("\n--- Eksekusi READ ALL ---");
            List<Product> all = dao.findAll();
            for (Product prod : all) {
                System.out.println("- " + prod.getCode() + ": " + prod.getName());
            }

            // 4. Hapus Produk
            System.out.println("\n--- Eksekusi DELETE ---");
            dao.delete("P01");
            System.out.println("Produk P01 berhasil dihapus.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```


## ▶️ Hasil Eksekusi

> 📌 Lampirkan screenshot hasil CRUD (`screenshots/crud_result.png`) yang menunjukkan:
>
> * Insert
> * Update
> * Read
> * Delete berhasil dijalankan.

---

## 🔍 Analisis

Dengan pola **DAO**, sistem Agri-POS tidak bergantung langsung pada SQL di `main()`.
Semua operasi database diatur melalui `ProductDAO`, sehingga:

* Kode lebih rapi
* Mudah diuji
* Mudah diganti jika database berubah

Penggunaan **PreparedStatement** juga meningkatkan keamanan dari SQL Injection.

---

## 🧾 Kesimpulan

Penerapan **DAO dan JDBC** membuat Agri-POS lebih profesional karena:

* Data dan logika bisnis terpisah
* Operasi CRUD lebih terstruktur
* Sistem lebih mudah dikembangkan dan dipelihara

---

## 📊 Penilaian (RPS)

| Aspek                       |    Bobot |   |
| --------------------------- | -------: | - |
| Implementasi DAO sesuai OOP |      30% |   |
| CRUD berjalan lengkap       |      30% |   |
| Integrasi dengan aplikasi   |      20% |   |
| Laporan & dokumentasi       |      20% |   |
| **Total**                   | **100%** |   |

---
