package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Week 10 - Design Pattern & Testing");

        // 1. Cek Singleton Database (Hanya mengetes koneksi, belum query)
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        // Membuktikan bahwa db1 dan db2 adalah object yang SAMA
        System.out.println("Apakah object DB sama? " + (db1 == db2)); 

        // 2. Implementasi MVC
        // Model (Data Dummy)
        Product product = new Product("A001", "Bibit Padi", "Pertanian", 50000, 100);
        
        // View
        ConsoleView view = new ConsoleView();

        // Controller
        ProductController controller = new ProductController(product, view);

        // Tampilkan Awal
        controller.updateView();

        // Update data lewat controller
        System.out.println("-> Mengubah harga lewat Controller...");
        controller.setProductPrice(55000);
        
        // Tampilkan Setelah Update
        controller.updateView();
    }
}