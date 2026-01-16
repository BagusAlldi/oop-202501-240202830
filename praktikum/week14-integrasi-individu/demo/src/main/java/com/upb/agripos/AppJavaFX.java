package com.upb.agripos;

import com.upb.agripos.controller.PosController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        // IDENTITAS DI CONSOLE (Sesuai Syarat Tugas)
        System.out.println("===========================================");
        System.out.println("Hello World, I am [Bagus Aldi]-[240202830]");
        System.out.println("Week 14: Integrasi OOP + DB + GUI + Test");
        System.out.println("===========================================");
        
        PosController root = new PosController();
        Scene scene = new Scene(root, 1000, 600); // Ukuran window agak lebar
        
        stage.setTitle("Agri-POS Integrated System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}