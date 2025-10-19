# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)
## Identitas
- Nama  : Bagus Alldiansyah
- NIM   : 240202830
- Kelas : 3 IKRA

---

## Tujuan
1. Menjelaskan konsep inheritance dalam OOP.
2. Membuat superclass dan subclass produk pertanian.
3. Mendemonstrasikan hierarki class melalui kode.
4. Menggunakan super untuk memanggil konstruktor dan method parent.
5. Menyusun laporan tentang perbedaan inheritance vs class tunggal.
---

## Dasar Teori
3. Superclass: class induk yang mendefinisikan atribut umum.
2. Subclass: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.
3. Super digunakan untuk memanggil konstruktor atau method superclass.---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  
---

## Kode Program
super class
```java
// Contoh produk
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

    // Getter & Setter
    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    // Method umum
    public void tampilkanInfo() {
        System.out.println("Kode: " + kode);
        System.out.println("Nama: " + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok + " unit");
    }
}
```
sub clas
```
// contoh pupuk
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    // Method tambahan
    public void deskripsi() {
        System.out.println("\n=== Detail Produk Pupuk ===");
        tampilkanInfo();
        System.out.println("Jenis: " + jenis);
    }
}

```
```// contoh main
package com.upb.agripos;

import com.upb.agripos.model.AlatPertanian;
import com.upb.agripos.model.Benih;
import com.upb.agripos.model.Pupuk;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Membuat objek dari tiap subclass
        Benih benih = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk pupuk = new Pupuk ("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian alat = new AlatPertanian ("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        // Menampilkan informasi produk dengan method deskripsi()
        benih.deskripsi();
        pupuk.deskripsi();
        alat.deskripsi();

        // Identitas pengembang
        CreditBy.print("240202830", "Bagus Alldiansyah");
    }
}

```
)
---

## Hasil Eksekusi
exsample.png

---

## Analisis
1. Class Produk berperan sebagai superclass yang menyimpan atribut umum: kode, nama, harga, dan stok.
2. Class Benih, Pupuk, dan AlatPertanian adalah subclass yang mewarisi semua atribut dan method dari Produk.
3. Masing-masing subclass menambahkan atribut khusus seperti varietas, jenis, dan bahan.
4. Konstruktor subclass menggunakan super() untuk memanggil konstruktor induk.
5. Method tampilkanInfo() dipanggil dari superclass tanpa perlu ditulis ulang.
6. Method deskripsi() di tiap subclass menampilkan data lengkap produk sesuai jenisnya.
7. Objek dibuat di MainInheritance dan menampilkan informasi secara hierarkis.
---

## Kesimpulan
program ini yang menerapkan Inheritance, dengan class Benih, Pupuk, dan AlatPertanian yang mewarisi atribut dan method dari class Produk, membuat kode menjadi lebih efisien, terstruktur, dan mudah dikembangkan.

---

## Checklist Keberhasilan

* [x] Superclass `Produk` digunakan kembali tanpa duplikasi kode.
* [x] Subclass `Benih`, `Pupuk`, dan `AlatPertanian` berhasil dibuat dengan atribut tambahan.
* [x] Program berjalan menampilkan objek dari setiap subclass.
* [x] `CreditBy` ditampilkan dengan benar.
* [x] Commit sesuai instruksi.
* [x] Laporan singkat lengkap dengan analisis.

---

## Quiz

1. **Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?**
   **Jawaban:** Inheritance membuat kode lebih efisien karena subclass dapat mewarisi atribut dan method dari superclass tanpa menulis ulang.

2. **Bagaimana cara subclass memanggil konstruktor superclass?**
   **Jawaban:** Dengan menggunakan keyword `super(...)` di dalam konstruktor subclass.

3. **Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.**
   **Jawaban:** Contohnya `Pestisida`, `Bibit Buah`, atau `Obat Tanaman` yang juga merupakan turunan dari `Produk`.
