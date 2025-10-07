package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

     public void tambahStok(int jumlah) {
        this.stok += jumlah;
        System.out.println("\n===TRANSAKSI MASUK===");
        System.out.println("Produk : " + nama + ", " + kode);
        System.out.println("Jumlah : +" + jumlah);
        System.out.println("Stok Sekarang : " + stok);
        System.out.println("---------------------------");
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("\n===TRANSAKSI KELUAR===");
            System.out.println("Produk : " + nama + ", " + kode);
            System.out.println("Jumlah : -" + jumlah);
            System.out.println("Stok Sekarang : " + stok);
            System.out.println("---------------------------");
        } else {
            System.out.println("\nStok " + nama + ", " + kode + " tidak mencukupi!");
            System.out.println("Stok tersedia : " + stok + " | Diminta : " + jumlah);
            System.out.println("---------------------------");
        }
    }
}