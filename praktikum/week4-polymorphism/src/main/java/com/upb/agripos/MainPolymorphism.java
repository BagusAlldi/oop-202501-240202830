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