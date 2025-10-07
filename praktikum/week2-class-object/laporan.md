# Laporan Praktikum Minggu 2
Topik:  Class dan Object (Produk Pertanian)

## Identitas
- Nama  : Bagus Alldiansyah
- NIM   : 240202830
- Kelas : 3 IKRA

---

## Tujuan
- Mahasiswa mampu menjelaskan konsep class, object, atribut, dan method dalam OOP.
- Mahasiswa mampu menerapkan access modifier dan enkapsulasi dalam pembuatan class.
- Mahasiswa mampu mengimplementasikan class Produk pertanian dengan atribut dan method yang sesuai.
- Mahasiswa mampu mendemonstrasikan instansiasi object serta menampilkan data produk pertanian di console.
- Mahasiswa mampu menyusun laporan praktikum dengan bukti kode, hasil eksekusi, dan analisis sederhana.
---

## Dasar Teori
1. Class adalah blueprint dari object.  
2. Object adalah wujud nyata dari class.  
3. Atribut adalah tempat menyimpan data / keadaan Object (state).
4. method adalah tempat mendefinisikan perilaku (aksi) yang dapat di lakukan objecct
5. Access Modifier adalah Pengatur hak akses (private, public, dll).
6. Encapsulation adalah Perlindungan data dengan cara menyembunyikan atribut dan menggunakan getter/setter.

---

## Langkah Praktikum
1. **Membuat Class Produk**
   - Buat file `Produk.java` pada package `model`.
   - Tambahkan atribut: `kode`, `nama`, `harga`, dan `stok`.
   - Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.  

2. **Membuat Class CreditBy**
   - Buat file `CreditBy.java` pada package `util`.
   - Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: `credit by: <240202830> - <bagus alldiansyah>`.

3. **Membuat Objek Produk dan Menampilkan Credit**
   - Buat file `MainProduk.java`.
   - Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
   - Tampilkan informasi produk melalui method getter.  
   - Panggil `CreditBy.print<"240202830"> - <"bagus alldiansyah">` di akhir `main` untuk menampilkan identitas.

4. **Commit dan Push**
   - Commit dengan pesan: `week2-class-object`.
---

## Kode Program

Produk
```
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

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

     public void tambahStok(int jumlah) {
        this.stok += jumlah;
        System.out.println("\n===TRANSAKSI MASUK===");
        System.out.println("Produk : " + nama + ", " + kode);
        System.out.println("Jumlah : +" + jumlah);
        System.out.println("Stok Sekarang : " + stok);
        System.out.println("---------------------------");
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("\n===TRANSAKSI KELUAR===");
            System.out.println("Produk : " + nama + ", " + kode);
            System.out.println("Jumlah : -" + jumlah);
            System.out.println("Stok Sekarang : " + stok);
            System.out.println("---------------------------");
        } else {
            System.out.println("\nStok " + nama + ", " + kode + " tidak mencukupi!");
            System.out.println("Stok tersedia : " + stok + " | Diminta : " + jumlah);
            System.out.println("---------------------------");
        }
    }
}

```
CreditBy
```
package com.upb.agripos.util;
public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}

```MainProduk
package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;
```
MainProduk
```
public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());
  // Transaksi penambahan stok
        p1.tambahStok(20);  // stok datang dari gudang

        // Transaksi pengurangan stok
        p1.kurangiStok(15); // produk dibeli pelanggan
      
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202830", "Bagus Alldiansyah");
    }
}
```
---

## Hasil Eksekusi
![Tampilan Awal](praktikum/week2-class-object
/Screenshot/Class&Object.png)
Gambar di atas menunjukkan hasil eksekusi code di atas.

---

## Analisis
 **Penjelasan Kode**
Program berjalan dari `MainProduk`, membuat tiga objek `Produk`, lalu memanggil method untuk menambah atau mengurangi stok. Setelah transaksi, identitas mahasiswa ditampilkan melalui `CreditBy.print()`.

**Perbedaan Minggu Ini**
Minggu ini sudah menerapkan **OOP dengan class dan object**, sedangkan minggu sebelumnya masih tahap **pengenalan dasar Java** (variabel, input-output, dan struktur program sederhana).

**Kendala dan Solusi**
Kendala utama adalah memahami **hubungan antar class** dan cara memanggil method dari package lain.
Solusinya dengan mempelajari **import package** dan **akses method menggunakan objek**.

---

## **Kesimpulan**

Dengan menggunakan **class dan object**, program menjadi lebih **terstruktur, terorganisir, dan mudah dikembangkan**.
Setiap bagian kode memiliki peran jelas — data disimpan dalam class, aksi dijalankan lewat method, serta konsep **enkapsulasi** membuat data lebih aman dan terkontrol.

---

## **Quiz**

1. **Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?**
   **Jawaban:** Agar data tidak bisa diakses langsung dari luar class, sehingga lebih aman dan hanya bisa diubah melalui method yang dikontrol (enkapsulasi).

2. **Apa fungsi getter dan setter dalam enkapsulasi?**
   **Jawaban:** Getter digunakan untuk mengambil nilai atribut, sedangkan setter digunakan untuk mengubah nilai atribut secara aman sesuai aturan yang ditentukan.

3. **Bagaimana cara class `Produk` mendukung pengembangan aplikasi POS yang lebih kompleks?**
   **Jawaban:** Class `Produk` menjadi dasar untuk mengelola data barang, stok, dan transaksi, sehingga mudah dikembangkan untuk sistem POS dengan fitur seperti penjualan, pembelian, dan laporan inventori.
