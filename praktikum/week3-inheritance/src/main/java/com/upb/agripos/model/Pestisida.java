package com.upb.agripos.model;

public class Pestisida extends Produk {
    private String jenisPestisida;

    public Pestisida(String kode, String nama, double harga, int stok, String jenisPestisida) {
        super(kode, nama, harga, stok);
        this.jenisPestisida = jenisPestisida;
    }

    public void deskripsi() {
        System.out.println("\n=== DATA PRODUK PESTISIDA ===");
        tampilkanInfo();
        System.out.println("Jenis Pestisida: " + jenisPestisida);
    }
}
