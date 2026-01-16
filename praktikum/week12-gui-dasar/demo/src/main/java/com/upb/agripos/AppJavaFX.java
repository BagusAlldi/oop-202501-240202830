package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Inisialisasi Komponen MVC
        ProductService service = new ProductService();
        ProductFormView view = new ProductFormView();
        
        // Controller menghubungkan View dan Service
        new ProductController(service, view);

        // 2. Setup Scene
        Scene scene = new Scene(view.getView(), 400, 500);
        
        primaryStage.setTitle("Agri-POS: Kelola Produk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}