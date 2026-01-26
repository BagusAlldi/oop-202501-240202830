
---

# Laporan Praktikum Week 5 – Abstraction (Abstract Class & Interface)

## Identitas

* **Nama:** Bagus Alldiansyah
* **NIM:** 240202830
* **Kelas:** 3 IKRA

---

## Tujuan

Praktikum ini bertujuan untuk:

1. Mahasiswa mampu menjelaskan perbedaan konsep *abstract class* dan *interface*.
2. Mahasiswa mampu mendesain *abstract class* dengan *method* abstrak sesuai kebutuhan kasus Agri-POS.
3. Mahasiswa mampu membuat *interface* dan mengimplementasikannya pada *class*.
4. Mahasiswa mampu menerapkan *multiple inheritance* melalui *interface*.
5. Mahasiswa mampu mendokumentasikan kode program dengan baik.

---

## Dasar Teori

### 1. Abstraksi

Abstraksi adalah prinsip OOP untuk menyederhanakan kompleksitas sistem dengan menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas utama.

### 2. Abstract Class

*Abstract class* adalah kelas yang tidak dapat diinstansiasi secara langsung.

* Dapat memiliki *abstract method* (tanpa *body*) dan *concrete method*.
* Digunakan ketika kelas-kelas memiliki *shared state* (variabel) dan perilaku dasar yang sama.
* Contoh: Kelas `Pembayaran` memiliki field `total` dan `invoiceNo`.

### 3. Interface

*Interface* adalah kumpulan kontrak yang berisi deklarasi method tanpa implementasi (sebelum Java 8).

* Digunakan untuk mendefinisikan kemampuan (*capability*) yang bisa dimiliki oleh berbagai kelas yang tidak satu hierarki.
* Mendukung **multiple inheritance** (satu kelas bisa implementasi banyak interface).
* Contoh: `Validatable` (bisa divalidasi) dan `Receiptable` (bisa cetak struk).

---

## Deskripsi Tugas (Studi Kasus Agri-POS)

Dalam modul pembayaran Agri-POS, sistem perlu menangani berbagai metode pembayaran.

* **Pembayaran (Abstract):** Menjadi induk semua metode bayar, menyimpan data invoice dan total.
* **Cash:** Metode tunai, tanpa biaya tambahan.
* **EWallet:** Metode digital, ada biaya admin 1.5% dan butuh validasi (OTP).
* **Fitur Tambahan (Interface):**
* `Validatable`: Kontrak untuk validasi (OTP/PIN).
* `Receiptable`: Kontrak untuk mencetak struk transaksi.



---

## Langkah Praktikum

1. Membuat struktur folder `week5-abstraction-interface`.
2. Membuat **Abstract Class `Pembayaran**` dengan method abstrak `biaya()` dan `prosesPembayaran()`.
3. Membuat **Interface `Validatable**` dan **`Receiptable`**.
4. Membuat **Subclass `Cash**` yang mengextends `Pembayaran` dan implements `Receiptable`.
5. Membuat **Subclass `EWallet**` yang mengextends `Pembayaran` dan implements `Validatable`, `Receiptable` (*Multiple Inheritance*).
6. Membuat class `MainAbstraction` untuk menjalankan simulasi dan class utility `CreditBy`.
7. Menjalankan program dan menganalisis hasil.

---

## Implementasi Kode Program

### 1. Abstract Class: Pembayaran.java

```java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    // Abstract method: wajib di-override oleh subclass
    public abstract double biaya();
    public abstract boolean prosesPembayaran();

    // Concrete method: bisa langsung digunakan
    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
}

```

### 2. Interface: Validatable & Receiptable

```java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); 
}

```

```java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}

```

### 3. Concrete Class: Cash.java

```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0; // Tunai tidak ada biaya admin
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar();
    }

    @Override
    public String cetakStruk() {
        return String.format("[CASH] Invoice: %s | Total: %.2f | Bayar: %.2f | Kembali: %.2f",
                invoiceNo, totalBayar(), tunai, (tunai - totalBayar()));
    }
}

```

### 4. Concrete Class: EWallet.java

```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

// Implementasi Multiple Inheritance via Interface
public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // Biaya admin 1.5%
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // Simulasi validasi OTP
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format("[E-WALLET] Invoice: %s | Akun: %s | Total(+Fee): %.2f | Status: %s",
                invoiceNo, akun, totalBayar(), prosesPembayaran() ? "SUCCESS" : "FAILED");
    }
}

```

### 5. Main Class & Utility

```java
package com.upb.agripos;

import com.upb.agripos.model.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        // Polimorfisme: Reference tipe Pembayaran, Objek tipe Cash/EWallet
        Pembayaran struk1 = new Cash("INV-2401", 50000, 100000);
        Pembayaran struk2 = new EWallet("INV-2402", 200000, "bagus@ewallet", "123456");

        // Casting ke Interface Receiptable untuk cetak struk
        System.out.println(((Receiptable) struk1).cetakStruk());
        System.out.println(((Receiptable) struk2).cetakStruk());

        System.out.println("\n------------------------------------------------");
        CreditBy.print("240202830", "Bagus Alldiansyah");
    }
}

```

---

## Analisis

1. **Penerapan Abstract Class:**
Kelas `Pembayaran` dibuat abstrak karena metode pembayaran secara umum pasti memiliki `invoiceNo` dan `total` (*shared state*), namun cara menghitung `biaya()` dan `prosesPembayaran()` berbeda-beda (*abstract method*) tergantung jenisnya (Tunai vs E-Wallet).
2. **Penerapan Interface & Multiple Inheritance:**
Java tidak mendukung *multiple inheritance* pada class (extends > 1 class), tetapi mendukung implementasi banyak interface.
* Pada kelas `EWallet`, kita menerapkan `implements Validatable, Receiptable`.
* Ini memungkinkan `EWallet` memiliki kemampuan validasi DAN kemampuan cetak struk sekaligus, tanpa terikat hierarki orang tua yang kaku.


3. **Polimorfisme:**
Di `MainAbstraction`, objek disimpan dalam referensi `Pembayaran`. Namun, saat method dipanggil, perilaku yang dijalankan adalah milik subclass (`Cash` atau `EWallet`).

---

## Kesimpulan

Melalui praktikum ini, saya memahami bahwa **Abstract Class** sangat tepat digunakan untuk membangun kerangka dasar (template) bagi kelas-kelas yang memiliki kemiripan atribut. Sedangkan **Interface** lebih fleksibel digunakan untuk mendefinisikan kemampuan atau fitur spesifik yang bisa ditempelkan pada berbagai kelas. Gabungan keduanya menciptakan kode yang modular, *reusable*, dan mudah dikembangkan.

---

## Quiz & Jawaban

1. **Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.**
**Jawaban:**
* **Abstract Class:** Digunakan untuk kelas yang memiliki hubungan "is-a" (adalah jenis dari) yang kuat dan berbagi state (variabel) serta kode implementasi yang sama. Contoh: `Cash` adalah jenis `Pembayaran`.
* **Interface:** Digunakan untuk mendefinisikan hubungan "can-do" (kemampuan) tanpa peduli hierarki kelas, dan tidak menyimpan state. Contoh: `EWallet` bisa melakukan `Validatable`.


2. **Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?**
**Jawaban:**
Karena interface tidak memiliki implementasi state/variabel (sebelum Java 8 juga tidak ada method body). Hal ini mencegah **Diamond Problem**, yaitu kebingungan kompilator saat sebuah kelas mewarisi dua method atau variabel yang sama persis dari dua parent berbeda.
3. **Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.**
**Jawaban:**
* **Abstract Class (`Pembayaran`):** Tepat karena semua pembayaran pasti punya *invoice number* dan *total harga*. Ini adalah atribut dasar yang diwariskan.
* **Interface (`Receiptable`):** Tepat karena mencetak struk adalah sebuah fitur/kemampuan. Jika nanti ada fitur "Refund", tidak semua pembayaran harus refund dengan cara yang sama, atau mungkin ada kelas selain pembayaran (misal Laporan) yang juga butuh dicetak (Receiptable).



---
