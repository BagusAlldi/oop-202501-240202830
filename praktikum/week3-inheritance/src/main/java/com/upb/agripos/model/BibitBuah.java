package com.upb.agripos.model;

public class BibitBuah extends Produk {
    private String jenisBuah;

    public BibitBuah(String kode, String nama, double harga, int stok, String jenisBuah) {
        super(kode, nama, harga, stok);
        this.jenisBuah = jenisBuah;
    }

    public void deskripsi() {
        System.out.println("\n=== DATA PRODUK BIBIT BUAH ===");
        tampilkanInfo();
        System.out.println("Jenis Buah: " + jenisBuah);
    }
}
