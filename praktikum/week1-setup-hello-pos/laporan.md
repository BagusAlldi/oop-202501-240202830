# Laporan Praktikum Minggu 1
Topik: Pengenalan Paradigma Object Oriented Programming OOP

## Identitas
- Nama  : Bagus Alldiansyah
- NIM   : 240202830
- Kelas : 3 IKRA

---

## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).
---

## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:
1. Prosedural: program dibangun sebagai rangkaian perintah (fungsi/prosedur).
2. OOP (Object-Oriented Programming): program dibangun dari objek yang memiliki data    (atribut) dan perilaku (method).
3. Fungsional: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data.
---

## Langkah Praktikum
1. Setup Project.
2. Membuat program Sederhana dalam 3 Paradigma (prosedural, OOP, Fungsional).
3. Commit dan Push.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

Prosedural
```
public class HelloProcedural {
   // [CLASS] Kelas utama program (wajib ada dalam Java).
   public static void main(String[] args) {
      // [MAIN METHOD] Titik awal eksekusi program Java.
      String nim = "240202830";  
      // [VARIABEL] 
      String nama = "Bagus Alldiansyah";  
      // [VARIABEL]

      System.out.println("Hello World, I am " + nama + ", " + nim);  
      // [OUTPUT] Mencetak kalimat "Hello World, I am Bagus Alldiansyah, 240202830"
      }
}

```

OOP
```
class Mahasiswa {
   // [CLASS] Blueprint / cetakan untuk membuat objek Mahasiswa.

   String nama;  
   // [FIELD] 
   int nim;  
   // [FIELD] 

   Mahasiswa(String m, int n) {  
      // [CONSTRUCTOR] Konstruktor yang dipanggil saat objek Mahasiswa dibuat.
      // Parameter m -> nama, n -> nim
      this.nama = m;  
      this.nim = n;   
      // [ASSIGNMENT]
   }

   void sapa() {
      // [METHOD] Fungsi milik Mahasiswa untuk menampilkan pesan.
      System.out.println("Hallo World, I am " + nama + ", " + nim);
      // [OUTPUT] Mencetak data nama dan nim dari objek Mahasiswa.
   }
}

public class HelloOOP {

   public static void main(String[] args) {
      // [MAIN METHOD] Titik awal eksekusi program Java.

      Mahasiswa m = new Mahasiswa("Bagus Alldiansyah", 240202830);  
      // [OBJECT CREATION] Membuat objek Mahasiswa dengan constructor.

      m.sapa();  
      // [METHOD CALL] Memanggil method sapa() milik objek m.
   }
}

```

Functional
```
import java.util.function.BiConsumer; 
// [IMPORT] Mengimpor interface fungsional BiConsumer dari package java.util.function.
// BiConsumer adalah interface fungsional yang menerima dua input, tetapi tidak mengembalikan nilai (void).

public class HelloFunctional { 
    // [CLASS] Mendefinisikan kelas HelloFunctional.

    public static void main(String[] args) { 
        // [MAIN METHOD] Titik awal eksekusi program Java.

        BiConsumer<String, Integer> sapa = (nama, nim) -> 
            System.out.println("Hallo World, I am " + nama + ", " + nim);
        // [BICONSUMER] Membuat lambda expression dengan BiConsumer.

        sapa.accept("Bagus Alldiansyah", 240202830);  
        // [ACCEPT METHOD] Memanggil method accept() milik BiConsumer.
    } 
}

```
)
---

## Hasil Eksekusi
![Tampilan Prosedural](Screenshots/heloProsedural.png)
![Tampilan OOP](Screenshots/heloOOP.png)
![Tampilan Funcional](praktikum/week1-setup-hello-pos/screenshots/heloFuncional.png)
---

## Analisis
### Bagaimana kode berjalan

HelloProcedural → Program langsung menyimpan data ke variabel nama dan nim, lalu mencetak dengan System.out.println().

HelloOOP → Program membuat class Mahasiswa, lalu membuat objek m dan memanggil method sapa() untuk menampilkan output.

HelloFunctional → Program menggunakan BiConsumer dengan lambda expression untuk menampung fungsi cetak, lalu dijalankan dengan accept().

###Perbedaan pendekatan minggu ini dibanding minggu sebelumnya
Minggu sebelumnya (Procedural) → Sederhana, langsung pakai variabel dan cetak.
Minggu ini (OOP & Functional) →OOP menekankan objek (data + method).
Functional menekankan fungsi (kode sebagai data, bisa disimpan dan dipanggil).
Perbedaannya ada pada gaya berpikir: procedural = langkah urut, OOP = objek, functional = fungsi.

###Kendala yang dihadapi dan cara mengatasinya

Kendala: terkait praktek masih bisa mengikuti, namun masih bingung membedakan kapan pakai procedural, OOP, atau functional.

---

## Kesimpulan
Dengan menggunakan class dan object (OOP) serta pendekatan functional, program menjadi lebih terstruktur, fleksibel, dan mudah dikembangkan. Berbeda dengan pendekatan procedural yang lebih sederhana namun kurang rapi untuk program besar, OOP membantu mengelompokkan data dan perilaku dalam objek, sedangkan functional memungkinkan penulisan kode yang ringkas dan efisien dengan lambda expression.

---

## Quiz
---

1. Apakah OOP selalu lebih baik dari prosedural?
   **Jawaban:**
   Tidak. OOP bagus untuk program besar karena modular dan bisa dipakai ulang. Tapi untuk program kecil, prosedural lebih simpel dan cepat dibuat.

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?
   **Jawaban:**
   Functional lebih pas saat butuh olah data besar, kerja paralel/asynchronous, atau manipulasi data dengan cara deklaratif seperti filter, map, dan reduce.

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?
   **Jawaban:**

   * Prosedural: gampang ditulis, tapi susah dirawat kalau program makin besar.
   * OOP: lebih teratur, gampang dikembangkan, dan mudah diperluas.
   * Fungsional: kode singkat, minim efek samping, gampang diuji, dan mudah diparalelkan.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   **Jawaban:**
   Karena POS punya banyak entity (Produk, Transaksi, Pelanggan, dll). OOP bisa bikin entity jadi class, lebih rapi, gampang dikembangkan, dan enak dikelola.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (*boilerplate code*)?
   **Jawaban:**
   Dengan lambda dan higher-order function. Loop panjang bisa diganti map, filter, atau reduce. Jadinya kode lebih ringkas dan jelas.

---
