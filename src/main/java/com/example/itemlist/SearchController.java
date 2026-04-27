package com.example.itemlist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {

    private final int searchHistoryLimit = 10;

    private static ArrayList<String> searchHistory = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>(Arrays.asList("Sword", "Great Sword", "Long Sword",
                                                            "Mace", "Great Axe", "Crossbow",
                                                            "Bag of Holding"));

    Random rand = new Random();

    public static ArrayList<String> getSearchHistory()
    {
        return searchHistory;
    }

    @FXML
    private ComboBox<String> searchBar;

    @FXML
    private ListView<String> itemListView;

    @FXML
    private AnchorPane background;

    @FXML
    public void search(ActionEvent event){
        //Adds current text in search bar to search history, removes 10th item from search history if there are 10 or more items
        searchHistory.add(0, searchBar.getValue());
        searchBar.getItems().clear();
        if (searchHistory.size() >= searchHistoryLimit)
            searchHistory.remove(searchHistoryLimit - 1);
        searchBar.getItems().addAll(searchHistory);
    }

    @FXML
    public void random(ActionEvent event){
        int randomNumber = rand.nextInt(6) + 1;
        searchBar.setValue(String.valueOf(randomNumber));
    }

    //Requests focus when background is clicked so user can click out of and reopen search history more conveniently
    @FXML
    public void backgroundClicked(MouseEvent event){
        background.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        itemListView.getItems().addAll(items);

        //Creates listener object to create drop down for search history when the search bar is focused
        searchBar.focusedProperty().addListener((observable, notFocused, isFocused) -> {
            if (isFocused) {
                searchBar.show();
            }});

        //Checks if search history text file exists, then sets it as current search history if it does
        File searchHistoryFile = new File("searchHistory.txt");
        if (searchHistoryFile.exists()) {
            try (Scanner inputFile = new Scanner(searchHistoryFile)) {
                while (inputFile.hasNext()) {
                    searchHistory.add(inputFile.nextLine());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        searchBar.getItems().addAll(searchHistory);
        }

        itemListView.setOnMouseClicked(event -> {
            String selected = itemListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showInfo(selected);
            }
        });

    }

    private void showInfo(String itemName) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("info-scene.fxml"));
                Scene scene = new Scene(loader.load());
                InfoController controller = loader.getController();
                controller.setItemData(itemName);
                Stage stage = (Stage) itemListView.getScene().getWindow();
                stage.setScene(scene);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

    }


}
