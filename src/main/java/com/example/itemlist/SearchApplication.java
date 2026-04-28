package com.example.itemlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SearchApplication.class.getResource("search-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DnD Item Search");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        Application.launch(SearchApplication.class, args);
    }

}
