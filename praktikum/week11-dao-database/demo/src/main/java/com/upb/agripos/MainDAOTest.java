package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.util.List;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I am Bagus Alldiansyah - 240202830 (Week11)");
        
        // Sesuaikan dengan setting Docker Anda
        String url = "jdbc:postgresql://localhost:5432/agripos";
        String user = "postgres";
        String password = "1234"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Koneksi ke Database Docker Berhasil!");
            ProductDAO dao = new ProductDAOImpl(conn);

            // 1. Tambah Produk
            System.out.println("\n--- Eksekusi INSERT ---");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
            System.out.println("Produk P01 berhasil ditambahkan.");

            // 2. Cari & Update
            System.out.println("\n--- Eksekusi UPDATE ---");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 15));
            Product p = dao.findByCode("P01");
            System.out.println("Data terbaru: " + p.getName() + " | Stock: " + p.getStock());

            // 3. Tampilkan Semua
            System.out.println("\n--- Eksekusi READ ALL ---");
            List<Product> all = dao.findAll();
            for (Product prod : all) {
                System.out.println("- " + prod.getCode() + ": " + prod.getName());
            }

            // 4. Hapus Produk
            System.out.println("\n--- Eksekusi DELETE ---");
            dao.delete("P01");
            System.out.println("Produk P01 berhasil dihapus.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}