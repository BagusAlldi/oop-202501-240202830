package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    // Method tambahan
    public void deskripsi() {
        System.out.println("\n=== Detail Produk Benih ===");
        tampilkanInfo();
        System.out.println("Varietas: " + varietas);
    }
}
