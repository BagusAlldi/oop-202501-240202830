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
