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
(Tuliskan kode utama yang dibuat, contoh:  

```java
// Contoh
Produk p1 = new Produk("BNH-001", "Benih Padi", 25000, 100);
System.out.println(p1.getNama());
```
)
---

## Hasil Eksekusi
<img width="890" height="368" alt="Cuplikan layar 2025-10-27 174458" src="https://github.com/user-attachments/assets/525a310f-e7af-4897-9359-d7764686c189" />

---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini.  
Contoh: *Dengan menggunakan class dan object, program menjadi lebih terstruktur dan mudah dikembangkan.*)

---
## Checklist Keberhasilan
- [x] Overloading `tambahStok` berhasil.  
- [x] Overriding `getInfo` pada subclass berjalan.  
- [x] Dynamic binding berjalan melalui array produk.  
- [x] Output menampilkan identitas mahasiswa.  
- [x] Screenshot & laporan disertakan.

---
Keren banget, ini bagian **Quiz** buat laporan praktikum Week 4 ya 🎓
Biar lengkap, berikut versi **jawaban akademik** yang bisa langsung kamu pakai atau sesuaikan gaya bahasanya:

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
3. *Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.*
   **Jawaban:**
   Contoh lain adalah sistem POS (Point of Sale) di **restoran**.

   * Superclass: `MenuItem` dengan method `getHarga()`.
   * Subclass: `Makanan`, `Minuman`, dan `Dessert`, masing-masing mengoverride `getHarga()` sesuai kebijakan pajak atau diskon.
   * Dengan polymorphism, sistem dapat memproses seluruh item dalam array `MenuItem[]` tanpa peduli jenisnya, namun tetap memanggil method yang sesuai dengan objek aktualnya.
---
