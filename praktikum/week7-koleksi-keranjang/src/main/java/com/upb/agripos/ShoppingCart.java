package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // 1. Ganti ArrayList jadi HashMap
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int jumlah) {
        // 2. Simpan produk dan jumlahnya ke Map
        items.put(p, items.getOrDefault(p, 0) + jumlah);
    }

    public void removeProduct(Product p) {
        items.remove(p);
    }

    public double getTotal() {
        double sum = 0;
        // 3. Hitung total berdasarkan (Harga x Jumlah)
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        // 4. Looping Map untuk cetak struk
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            Integer jumlah = entry.getValue();
            double subTotal = p.getPrice() * jumlah;
            
            System.out.println("- " + p.hashCode() + " " + p.getName() + " = " + subTotal);
        }
        System.out.println("Total: " + getTotal());
    }
}