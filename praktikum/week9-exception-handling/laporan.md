

---

# 📘 **Laporan Praktikum Minggu 9**

**Topik:** *Exception Handling dan Custom Exception pada Agri-POS*

---

## 🧑‍🎓 Identitas

| Data      | Keterangan        |
| --------- | ----------------- |
| **Nama**  | Bagus Alldiansyah |
| **NIM**   | 240202830         |
| **Kelas** | 3 IKRA            |

---

## 🎯 Tujuan

Mahasiswa diharapkan mampu:

1. Membedakan antara **error** dan **exception** dalam pemrograman Java.
2. Mengimplementasikan **try–catch–finally** untuk menangani kondisi tidak normal.
3. Membuat **custom exception** (misalnya `InvalidQuantityException`) sesuai logika bisnis Agri-POS.
4. Memahami penerapan dasar **Design Pattern** seperti **Singleton**.

---

## 📚 Dasar Teori

### 1. Error vs Exception

* **Error** → kondisi fatal yang tidak dapat ditangani program (contoh: memori habis).
* **Exception** → kondisi anomali yang masih bisa ditangani agar program tidak berhenti.

### 2. try–catch–finally

Struktur untuk menangani kesalahan:

* `try` → kode yang berpotensi error
* `catch` → penanganan error
* `finally` → kode yang selalu dijalankan (misalnya menutup koneksi)

### 3. Custom Exception

Class exception buatan sendiri yang mewarisi `Exception`, digunakan untuk mewakili kesalahan khusus dalam domain bisnis.

### 4. Singleton Pattern

Pola desain yang memastikan sebuah class hanya memiliki **satu instance** selama aplikasi berjalan.

---

## 🛠️ Langkah Praktikum

1. **Membuat Custom Exception**

   * `InvalidQuantityException`
   * `ProductNotFoundException`
   * `InsufficientStockException`

2. **Update Model**

   * Menambahkan atribut `stock`
   * Menambahkan metode `reduceStock()` pada class `Product`

3. **Logika Validasi**

   * Menggunakan `throw` pada metode `addProduct()` dan `checkout()` di `ShoppingCart`

4. **Penanganan Error**

   * Menggunakan `try–catch` di `MainExceptionDemo.java`

5. **Commit**

   ```text
   week9-exception: [fitur] implementasi custom exception keranjang
   ```

---

## 💻 Kode Program

Contoh melempar custom exception pada metode `checkout`:

```java
public void checkout() throws InsufficientStockException {
    for (Map.Entry<Product, Integer> entry : items.entrySet()) {
        Product product = entry.getKey();
        int qty = entry.getValue();
        // Validasi stok berdasarkan atribut di Class Diagram
        if (product.getStock() < qty) {
            throw new InsufficientStockException(
                "Stok tidak cukup untuk: " + product.getName()
            );
        }
    }
}
```

---

## ▶️ Hasil Eksekusi


> **"Kesalahan: Quantity harus lebih dari 0"** atau pesan error lainnya.

---

## 🔍 Analisis

### Mekanisme Kerja

Program menggunakan pengecekan kondisi (`if`) yang diikuti dengan `throw` untuk menghentikan proses jika data tidak valid.
Hal ini memaksa pemanggil metode untuk menangani kesalahan menggunakan `try–catch`.

### Perbedaan Pendekatan

Tanpa exception, program bisa langsung **crash**.
Dengan exception, program tetap berjalan dan menampilkan **pesan yang informatif** kepada pengguna.

### Traceability

Pendekatan ini sesuai dengan **Class Diagram Bab 6**, di mana transaksi dan keranjang harus memvalidasi stok sebelum transaksi dianggap sah.

---

## 🧾 Kesimpulan

Exception handling dan custom exception membuat sistem **Agri-POS** lebih **robust dan profesional**.
Aplikasi mampu menangani kesalahan logika bisnis secara terkendali dan memberikan **umpan balik yang jelas** kepada pengguna.

---

## 📝 Quiz

### 1. Perbedaan error dan exception

**Jawaban:**
Error bersifat fatal dan tidak bisa ditangani program (misalnya `StackOverflowError`).
Exception berkaitan dengan kesalahan logika atau kondisi eksternal yang dapat ditangani (misalnya `IOException`).

---

### 2. Fungsi `finally`

**Jawaban:**
Untuk menjalankan kode yang wajib dieksekusi baik terjadi error maupun tidak, seperti menutup file atau koneksi database.

---

### 3. Mengapa custom exception diperlukan?

**Jawaban:**
Agar kesalahan yang spesifik terhadap domain bisnis (seperti `InsufficientStock`) dapat ditangani secara jelas dan mudah dipelihara.

---

### 4. Contoh kasus bisnis yang membutuhkan custom exception

**Jawaban:**

* Saldo e-wallet kurang dari total belanja
* Produk sudah tidak aktif tetapi masih dipilih oleh kasir

---
