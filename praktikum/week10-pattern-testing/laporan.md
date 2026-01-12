Oke, berarti **sekarang kamu sedang di Minggu 10 (Bab 10 – Design Pattern & Unit Testing)** sesuai file yang kamu upload `10_bab10_pattern_testing.md` .
Kalau polanya sama seperti Minggu 7 dan 9, kita tinggal ubah materi Bab 10 itu menjadi **laporan praktikum Week 10 versi kamu (Agri-POS)**.

Ini versi **Markdown siap pakai** untuk **`laporan_week10.md`** ⬇️

---

# 📘 **Laporan Praktikum Minggu 10**

**Topik:** *Design Pattern (Singleton, MVC) dan Unit Testing pada Agri-POS*

---

## 🧑‍🎓 Identitas

| Data      | Keterangan        |
| --------- | ----------------- |
| **Nama**  | Bagus Alldiansyah |
| **NIM**   | 240202830         |
| **Kelas** | 3 IKRA            |

---

## 🎯 Tujuan

Setelah mengikuti praktikum ini, mahasiswa mampu:

1. Memahami konsep dasar **Design Pattern** dalam rekayasa perangkat lunak.
2. Mengimplementasikan **Singleton Pattern** pada layanan aplikasi.
3. Menerapkan pola **MVC (Model–View–Controller)** pada fitur sederhana Agri-POS.
4. Membuat dan menjalankan **Unit Test** menggunakan **JUnit**.
5. Menganalisis manfaat design pattern dan testing terhadap kualitas sistem.

---

## 📚 Dasar Teori

### 1. Design Pattern

Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak.
Pada minggu ini digunakan:

* **Singleton Pattern**
* **MVC (Model–View–Controller)** 

### 2. Singleton Pattern

Singleton menjamin sebuah class hanya memiliki **satu instance global** selama aplikasi berjalan.
Digunakan pada Agri-POS untuk **koneksi database atau service global** .

### 3. MVC (Model–View–Controller)

Pola arsitektur yang memisahkan tanggung jawab sistem menjadi:

* **Model** → data dan logika bisnis
* **View** → tampilan
* **Controller** → penghubung antara model dan view .

### 4. Unit Testing

Unit testing memastikan setiap fungsi bekerja sesuai harapan dan mencegah bug sejak awal pengembangan .

---

## 🛠️ Langkah Praktikum

1. Membuat **Singleton** pada class `DatabaseConnection`.
2. Mengimplementasikan struktur **MVC**:

   * `Product` sebagai Model
   * `ConsoleView` sebagai View
   * `ProductController` sebagai Controller
3. Membuat **Main Program** `AppMVC` untuk menghubungkan semua komponen.
4. Membuat **Unit Test JUnit** pada class `ProductTest`.
5. Menjalankan test dan menyimpan screenshot hasilnya.

Commit:

```text
week10-pattern-testing: [fitur] implementasi singleton, mvc, dan junit
```



---

## 💻 Implementasi

### Contoh Singleton DatabaseConnection

```java
package com.upb.agripos.config;

public class DatabaseConnection {
    // 1. Atribut static untuk menyimpan instance
    private static DatabaseConnection instance;

    // 2. Constructor private agar tidak bisa di-instansiasi dari luar
    private DatabaseConnection() {
        System.out.println("Koneksi Database Berhasil Dibuat.");
    }

    // 3. Method static untuk mendapatkan instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```



---

### Contoh MVC

#### Model – Product

```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
}
```



#### View – ConsoleView

```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}
```



#### Controller – ProductController

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        // Mengambil data dari model dan mengirimkannya ke view
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```



---

## 🧪 Unit Testing (JUnit)

```java
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;

public class ProductTest {
    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        // Memastikan nama produk yang disimpan sesuai
        assertEquals("Benih Jagung", p.getName());
    }
}
```



---

## ▶️ Hasil Eksekusi

> 📌 Lampirkan screenshot hasil JUnit (`screenshots/junit_result.png`) yang menunjukkan test berjalan tanpa error.

---

## 🔍 Analisis

Pola **Singleton** memastikan hanya ada satu koneksi database yang digunakan di seluruh sistem Agri-POS sehingga lebih efisien dan aman.
Pola **MVC** membuat kode lebih terstruktur karena logika bisnis, tampilan, dan kontrol dipisahkan.
Dengan **JUnit**, setiap perubahan pada class `Product` dapat diuji otomatis untuk mencegah bug.

---

## 🧾 Kesimpulan

Penerapan **Singleton, MVC, dan Unit Testing** meningkatkan kualitas sistem Agri-POS dari sisi:

* Arsitektur
* Keamanan resource
* Kemudahan perawatan
* Keandalan fungsi

Aplikasi menjadi lebih siap dikembangkan dalam skala besar.

---

## 📝 Quiz

1. **Mengapa constructor Singleton harus private?**
   Agar objek tidak bisa dibuat langsung dari luar class.

2. **Manfaat MVC?**
   Memisahkan tanggung jawab sehingga kode lebih rapi dan mudah dirawat.

3. **Peran unit testing?**
   Memastikan setiap fungsi berjalan benar dan mencegah bug.

4. **Risiko Singleton salah implementasi?**
   Bisa menyebabkan banyak instance dan konflik resource.

---

Kalau mau, berikutnya saya bisa bantu **sinkronkan Minggu 7, 9, dan 10 jadi satu paket laporan Agri-POS yang utuh** buat dikumpulkan atau dijadikan repo GitHub 📦🚀
