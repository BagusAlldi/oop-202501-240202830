package com.upb.agripos;

import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        // Memanggil View utama kita
        ProductTableView root = new ProductTableView();
        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Agri-POS Week 13 - TableView & Lambda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}