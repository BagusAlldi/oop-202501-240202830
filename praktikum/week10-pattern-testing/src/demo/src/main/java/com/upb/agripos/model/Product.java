package com.upb.agripos.model;

public class Product {
    private String kode;
    private String nama;
    private String kategori; // Sesuai DB baru
    private double harga;
    private int stok;

    // Constructor Kosong
    public Product() {}

    // Constructor Lengkap
    public Product(String kode, String nama, String kategori, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    // Getters
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    // Setters
    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
}