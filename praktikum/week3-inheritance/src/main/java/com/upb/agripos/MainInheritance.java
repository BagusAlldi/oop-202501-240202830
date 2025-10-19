package com.upb.agripos;

import com.upb.agripos.model.AlatPertanian;
import com.upb.agripos.model.Benih;
import com.upb.agripos.model.BibitBuah;
import com.upb.agripos.model.ObatTanaman;
import com.upb.agripos.model.Pestisida;
import com.upb.agripos.model.Pupuk;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Membuat objek dari tiap subclass
        Benih benih = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk pupuk = new Pupuk ("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian alat = new AlatPertanian ("ALT-501", "Cangkul Baja", 90000, 15, "Baja");
        Pestisida pestisida = new Pestisida("PST-301", "Furadan 3GR", 120000, 25, "Insektisida");
        BibitBuah bibitBuah = new BibitBuah("BBH-401", "Bibit Mangga Harum Manis", 45000, 30, "Mangga");
        ObatTanaman obat = new ObatTanaman("OBT-501", "FungiStop 500ml", 80000, 20, "Anti jamur tanaman");

        benih.deskripsi();
        pupuk.deskripsi();
        alat.deskripsi();
        pestisida.deskripsi();
        bibitBuah.deskripsi();
        obat.deskripsi();   
        // Identitas pengembang
        CreditBy.print("240202830", "Bagus Alldiansyah");
    }
}
