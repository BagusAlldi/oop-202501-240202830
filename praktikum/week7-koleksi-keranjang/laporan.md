
---

# 📘 **Laporan Praktikum Minggu 7**

**Topik:** *Collections dan Implementasi Keranjang Belanja*

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

1. Memahami konsep dasar **Java Collections Framework** (List, Map, Set).
2. Mengimplementasikan **ArrayList** untuk menyimpan dan mengelola kumpulan objek produk secara dinamis.
3. Melakukan operasi dasar collection seperti **tambah, hapus, dan hitung total**.
4. Menganalisis efisiensi penggunaan struktur data yang sesuai dengan kebutuhan sistem **Agri-POS**.

---

## 📚 Dasar Teori

### 1. Collections Framework

Framework Java untuk mengelola sekumpulan objek secara efisien yang terdiri dari beberapa struktur utama:

* **List**
* **Set**
* **Map**

### 2. List (ArrayList)

Koleksi yang **terurut** dan **boleh menyimpan duplikat**, sangat cocok untuk daftar item belanja.

### 3. Map (HashMap)

Struktur data yang menyimpan pasangan **key–value**, memungkinkan pencarian data secara cepat berdasarkan kunci unik.

### 4. Enkapsulasi Data

Teknik menyembunyikan data internal objek dan hanya dapat diakses melalui **getter** dan **setter** untuk menjaga integritas data.

---

## 🛠️ Langkah Praktikum

1. **Setup**
   Membuat struktur folder dalam paket:

   ```
   com.upb.agripos
   ```

2. **Coding**
   Membuat class:

   * `Product`
   * `ShoppingCart` (menggunakan `ArrayList`)
   * `ShoppingCartMap` (menggunakan `HashMap`)

3. **Main Program**
   File `MainCart.java` berisi logika:

   * Menambah produk
   * Menghapus produk
   * Menampilkan total belanja

4. **Run**
   Menjalankan program menggunakan **Maven** dan memastikan output tampil di terminal.

5. **Commit**
   Pesan commit:

   ```
   week7-collections: [fitur] implementasi keranjang belanja
   ```

---

## 💻 Kode Program

Contoh implementasi `ShoppingCart` menggunakan `ArrayList`:

```java
package com.upb.agripos;
import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { 
        items.add(p); // Menambahkan objek produk ke dalam list
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice(); // Iterasi untuk menghitung total harga
        }
        return sum;
    }
}
```

---

## ▶️ Hasil Eksekusi





---

## 🔍 Analisis

### Alur Kerja

Program membuat objek `Product`, lalu menyimpannya ke dalam `ArrayList` di `ShoppingCart`.
Saat `printCart()` dipanggil, sistem melakukan iterasi dan menampilkan seluruh produk beserta total harga.

### Perbedaan Pendekatan

* Minggu sebelumnya: menggunakan **objek tunggal atau array statis**
* Minggu ini: menggunakan **Collection (ArrayList)** yang bersifat **dinamis**

### Kendala

Masalah pada **jumlah produk yang sama (quantity)**.

### Solusi

Menggunakan **HashMap** pada `ShoppingCartMap`:

```text
Product → Key  
Integer → Value (jumlah)
```

Dengan begitu, produk yang sama tidak perlu disimpan berulang kali.

---

## 🧾 Kesimpulan

Penggunaan **Java Collections Framework** menjadikan pengelolaan data pada sistem **Agri-POS** lebih fleksibel dan efisien.

* **ArrayList** cocok untuk daftar belanja sederhana.
* **HashMap** lebih optimal untuk menangani **jumlah item** tanpa menduplikasi objek produk.

---

## 📝 Quiz

### 1. Perbedaan List, Map, dan Set

**Jawaban:**

* **List** → elemen terurut dan boleh duplikat
* **Map** → pasangan key–value
* **Set** → elemen unik tanpa duplikasi

---

### 2. Mengapa ArrayList cocok untuk keranjang belanja?

**Jawaban:**
Karena ArrayList mudah untuk menambah, menghapus, dan menyimpan item secara berurutan dengan ukuran yang fleksibel.

---

### 3. Bagaimana Set mencegah duplikasi?

**Jawaban:**
Set menggunakan metode `hashCode()` dan `equals()` untuk memastikan tidak ada dua elemen yang sama.

---

### 4. Kapan menggunakan Map dibanding List?

**Jawaban:**
Saat data perlu diakses berdasarkan **kunci tertentu**.
Contoh:
Dalam POS, `Map<Product, Integer>` digunakan untuk menyimpan produk dan jumlah belinya agar lebih efisien.

---
