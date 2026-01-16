package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductCreation() {
        // Test apakah data yang dimasukkan sesuai
        Product p = new Product("T01", "Traktor", "Alat", 15000000, 2);
        
        assertEquals("T01", p.getKode());
        assertEquals("Traktor", p.getNama());
        assertEquals("Alat", p.getKategori());
        assertEquals(15000000, p.getHarga());
    }

    @Test
    public void testSetStock() {
        // Test mengubah stok
        Product p = new Product();
        p.setStok(50);
        assertEquals(50, p.getStok());
    }
}