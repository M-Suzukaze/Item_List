package com.example.itemlist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InfoController {


    @FXML
    private Label infoLabel;

    @FXML
    private Button backButton;

    public void setItemData(String itemName){
        infoLabel.setText(itemName);
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

}
