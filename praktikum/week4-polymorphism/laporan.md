# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Bagus Alldiansyah
- NIM   : 2420202830
- Kelas : 3 IKRA

---

## Tujuan
- **menjelaskan konsep polymorphism** dalam OOP.  
- **membedakan method overloading dan overriding**.  
- **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  
---

## Dasar Teori
Polymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.  
1. **Overloading** → mendefinisikan method dengan nama sama tetapi parameter berbeda.  
2. **Overriding** → subclass mengganti implementasi method dari superclass.  
3. **Dynamic Binding** → pemanggilan method ditentukan saat runtime, bukan compile time.  

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  
2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  
3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  
4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  
5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  
6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.  


---

## Kode Program

### Produk.java (Overloading & getInfo default)
```java
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }
}
```
### pupuk.java (Overriding)
```java
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", jenis: " + jenis;
    }
}

```

### MainPolymorphism.java
```java
package com.upb.agripos;

import com.upb.agripos.Util.CreditBy;
import com.upb.agripos.model.AlatPertanian;
import com.upb.agripos.model.Benih;
import com.upb.agripos.model.BibitBuah;
import com.upb.agripos.model.Produk;
import com.upb.agripos.model.Pupuk;

public class MainPolymorphism {
    public static void main(String[] args) {
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new Pupuk ("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new BibitBuah("BBH-401", "Bibit Mangga Harum Manis", 45000, 30, "Mangga")

        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("<240202830>", "<Bagus Alldiansyah>");
    }
}
```
---

## Hasil Eksekusi
<img width="890" height="368" alt="Cuplikan layar 2025-10-27 174458" src="https://github.com/user-attachments/assets/525a310f-e7af-4897-9359-d7764686c189" />

---

## **Analisis**

Program membuat beberapa objek turunan dari `Produk`, seperti `AlatPertanian`, `Pupuk`, dan `ObatTanaman`.
Setiap subclass **mengoverride** method `getInfo()` dan melakukan **overloading** pada `tambahStok()`.
Semua objek disimpan dalam array `Produk[]`, lalu Java menjalankan **dynamic binding** untuk memanggil method sesuai tipe objek saat runtime.

Berbeda dengan minggu sebelumnya yang fokus pada **inheritance**, minggu ini menekankan **polymorphism**, sehingga kode lebih fleksibel.
Kendala utama berupa kesalahan struktur package dan file hilang diselesaikan dengan memperbaiki path dan penamaan file.

---

## **Kesimpulan**

Penerapan polymorphism membuat program lebih **dinamis, efisien, dan mudah dikembangkan**.
Melalui overloading dan overriding, objek dapat berperilaku berbeda meski diakses melalui referensi yang sama.

---
## Checklist Keberhasilan
- [x] Overloading `tambahStok` berhasil.  
- [x] Overriding `getInfo` pada subclass berjalan.  
- [x] Dynamic binding berjalan melalui array produk.  
- [x] Output menampilkan identitas mahasiswa.  
- [x] Screenshot & laporan disertakan.

---

## 🧠 **Quiz – Praktikum Week 4: Polymorphism**

1. **Apa perbedaan overloading dan overriding?**
   **Jawaban:**

   * **Overloading** terjadi ketika dua atau lebih method memiliki **nama yang sama**, tetapi **parameter berbeda** (baik dari jumlah, tipe, atau urutannya). Overloading ditentukan **pada saat kompilasi (compile time)**.
   * **Overriding** terjadi ketika subclass membuat ulang method dari superclass dengan **nama, parameter, dan tipe kembalian yang sama**, tetapi dengan **implementasi berbeda**. Overriding ditentukan **pada saat runtime (run time)**.

---

2. **Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?**
   **Jawaban:**
   Dalam **dynamic binding**, Java menentukan method yang dipanggil **berdasarkan objek aktual yang direferensikan** oleh variabel pada **saat program berjalan** (runtime), bukan berdasarkan tipe variabel referensinya.
   Dengan demikian, jika referensi bertipe superclass menunjuk ke objek subclass, method milik subclass yang dioverride akan dijalankan.

---
3. **Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.**
   **Jawaban:**
   Contoh lain adalah sistem POS (Point of Sale) di **restoran**.

   * Superclass: `MenuItem` dengan method `getHarga()`.
   * Subclass: `Makanan`, `Minuman`, dan `Dessert`, masing-masing mengoverride `getHarga()` sesuai kebijakan pajak atau diskon.
   * Dengan polymorphism, sistem dapat memproses seluruh item dalam array `MenuItem[]` tanpa peduli jenisnya, namun tetap memanggil method yang sesuai dengan objek aktualnya.
---
