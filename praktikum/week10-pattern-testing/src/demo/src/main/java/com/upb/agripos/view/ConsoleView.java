package com.upb.agripos.view;

import com.upb.agripos.model.Product;

public class ConsoleView {
    public void displayProductDetails(Product product) {
        System.out.println("\n=== DETAIL PRODUK (MVC VIEW) ===");
        System.out.println("Kode     : " + product.getKode());
        System.out.println("Nama     : " + product.getNama());
        System.out.println("Kategori : " + product.getKategori());
        System.out.println("Harga    : Rp " + product.getHarga());
        System.out.println("Stok     : " + product.getStok());
        System.out.println("================================\n");
    }
}