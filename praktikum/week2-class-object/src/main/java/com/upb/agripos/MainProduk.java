package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
         // Membuat objek produk pertanian (tanpa subclass)
        Produk p1 = new Produk("BNH-007", "Benih Jagung Hibrida", 48000, 60);
        Produk p2 = new Produk("PPK-210", "Pupuk Kompos Organik", 175000, 25);
        Produk p3 = new Produk("ALT-312", "Mesin Pompa Air Mini", 1250000, 8);

        System.out.println("=== Daftar Produk Pertanian ===");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Transaksi penambahan stok
        p1.tambahStok(15);  // stok datang dari gudang

        // Transaksi pengurangan stok
        p3.kurangiStok(5); // produk dibeli pelanggan
      
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202830", "Bagus Alldiansyah");
    }
}