package com.upb.agripos.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.upb.agripos.model.Product;

public class CartServiceTest {

    @Test
    public void testHitungTotalBelanja() {
        // 1. Arrange (Siapkan Data Dummy)
        CartService cart = new CartService();
        Product p1 = new Product("A1", "Apel", "Buah", 10000, 10);
        Product p2 = new Product("B1", "Jeruk", "Buah", 5000, 10);

        // 2. Act (Lakukan Aksi: Masukkan ke keranjang)
        cart.addToCart(p1, 2); // 2 x 10.000 = 20.000
        cart.addToCart(p2, 1); // 1 x 5.000 = 5.000

        // 3. Assert (Pastikan Hasilnya Benar 25.000)
        double expected = 25000.0;
        double actual = cart.calculateTotal();
        
        assertEquals(expected, actual, 0.01);
        
        System.out.println("Unit Test JUnit: Perhitungan Total Keranjang SUKSES!");
    }
}