package com.example.itemlist;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.google.gson.Gson;
import java.util.ResourceBundle;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

public class InfoController {

    @FXML
    private Label infoLabel;

    @FXML
    private Button backButton;

    private HttpClient client;

    private Item item;

    public void setItemData(String itemName){

        infoLabel.setText("loading...");

        if(this.client == null) {
            this.client = HttpClient.newHttpClient();
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(new URI("https://www.dnd5eapi.co/api/2014/equipment/" + itemName))
                                             .GET()
                                             .build();

            client.sendAsync(request, BodyHandlers.ofString())
                  .thenApply(HttpResponse::body)
                  .thenAccept(this::processAPIData);
        } catch (URISyntaxException e) {
            System.out.println("Issue with request");
            }
    }

    @FXML
    private void handleBack(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-scene.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) infoLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void  processAPIData (String data) {
        System.out.println(data);
        Gson gson = new Gson();
        try {
            this.item = gson.fromJson(data,Item.class);
        } catch (Exception e) {
            System.out.println("GSON Parsing Failed");
        }

        Platform.runLater(new Runnable() {
            public void run() {
                updateUI();
            }
        });
    }

    protected void updateUI() {
        infoLabel.setText(item.toString());
    }
}
