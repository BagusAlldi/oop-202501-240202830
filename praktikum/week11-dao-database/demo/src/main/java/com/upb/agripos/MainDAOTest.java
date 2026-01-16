package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) {
        // --- KONFIGURASI DATABASE ---
        // Menggunakan database 'agripost_db'
        String dbUrl = "jdbc:postgresql://localhost:5432/agripos_db";
        String dbUser = "postgres";
        // Menggunakan password 'bagusafm'
        String dbPass = "bagusafm"; 

        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            ProductDAO dao = new ProductDAOImpl(conn);

            System.out.println("=== TEST CRUD DIMULAI (DB: agripost_db) ===\n");

            // 1. INSERT (Create)
            System.out.println("[1] Menambahkan Produk...");
            // Hapus data lama dulu biar tidak error "Duplicate Key" saat dijalankan berkali-kali
            try { dao.delete("A001"); } catch(Exception ignored) {} 
            
            Product p1 = new Product("A001", "Bibit Padi", "Pertanian", 50000, 100);
            dao.insert(p1);
            System.out.println(" -> Sukses tambah: " + p1.getNama());

            // 2. SELECT ALL (Read)
            System.out.println("\n[2] Menampilkan Semua Produk:");
            List<Product> all = dao.findAll();
            for (Product p : all) {
                System.out.println("    - " + p.getKode() + " | " + p.getNama() + " | " + p.getKategori());
            }

            // 3. UPDATE
            System.out.println("\n[3] Update Stok A001...");
            Product edit = dao.findByCode("A001");
            if (edit != null) {
                edit.setNama("Bibit Padi Super");
                edit.setStok(50);
                dao.update(edit);
                System.out.println(" -> Sukses update.");
            }

            // 4. DELETE
            System.out.println("\n[4] Hapus Produk A001...");
            dao.delete("A001");
            System.out.println(" -> Sukses hapus.");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}