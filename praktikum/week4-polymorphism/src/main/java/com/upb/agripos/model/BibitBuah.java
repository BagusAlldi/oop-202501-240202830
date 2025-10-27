package com.upb.agripos.model;

public class BibitBuah extends Produk {
    private String jenisbuah;

    public BibitBuah(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.jenisbuah = jenisbuah;
    }

    @Override
    public String getInfo() {
        return "jenis buah: " + super.getInfo() + ", produk " + jenisbuah;
    }
}