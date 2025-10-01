// HelloFunctional.java
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
