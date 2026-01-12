package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am BAGUS ALLDIANSYAH -240202830 (Week7)");

        Product p1 = new Product("P01", "Beras", 5000.0);
        Product p2 = new Product("P02", "Pupuk", 30000.0);

        ShoppingCart cart = new ShoppingCart();

        // Mulai bungkus di sini
        try {
            cart.addProduct(p1,1);
            cart.addProduct(p2,1);
            cart.printCart();

            cart.removeProduct(p1);
            cart.printCart();
            
        } catch (Exception e) {
            // Kalau ada error (seperti InvalidQuantity), pesan errornya muncul di sini
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}