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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SearchController implements Initializable {

    private final int searchHistoryLimit = 10;

    private static ArrayList<String> searchHistory = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>(Arrays.asList("Abacus", "Acid (vial)", "Alchemist's fire (flask)", "Alchemist's Supplies", "Alms box", "Amulet", "Animal Feed (1 day)", "Antitoxin (vial)", "Arrow", "Backpack", "Bagpipes", "Ball bearings (bag of 1,000)", "Barding: Breastplate", "Barding: Chain mail", "Barding: Chain shirt", "Barding: Half plate", "Barding: Hide", "Barding: Leather", "Barding: Padded", "Barding: Plate", "Barding: Ring mail", "Barding: Scale mail", "Barding: Splint", "Barding: Studded Leather", "Barrel", "Basket", "Battleaxe", "Bedroll", "Bell", "Bit and bridle", "Blanket", "Block and tackle", "Block of incense", "Blowgun", "Blowgun needle", "Book", "Bottle, glass", "Breastplate", "Brewer's Supplies", "Bucket", "Burglar's Pack", "Calligrapher's Supplies", "Caltrops", "Camel", "Candle", "Carpenter's Tools", "Carriage", "Cart", "Cartographer's Tools", "Case, crossbow bolt", "Case, map or scroll", "Censer", "Chain (10 feet)", "Chain Mail", "Chain Shirt", "Chalk (1 piece)", "Chariot", "Chest", "Climber's Kit", "Clothes, common", "Clothes, costume", "Clothes, fine", "Clothes, traveler's", "Club", "Cobbler's Tools", "Component pouch", "Cook's utensils", "Crossbow bolt", "Crossbow, hand", "Crossbow, heavy", "Crossbow, light", "Crowbar", "Crystal", "Dagger", "Dart", "Dice Set", "Diplomat's Pack", "Disguise Kit", "Donkey", "Drum", "Dulcimer", "Dungeoneer's Pack", "Elephant", "Emblem", "Entertainer's Pack", "Explorer's Pack", "Fishing tackle", "Flail", "Flask or tankard", "Flute", "Forgery Kit", "Galley", "Glaive", "Glassblower's Tools", "Grappling hook", "Greataxe", "Greatclub", "Greatsword", "Halberd", "Half Plate Armor", "Hammer", "Hammer, sledge", "Handaxe", "Healer's Kit", "Herbalism Kit", "Hide Armor", "Holy water (flask)", "Horn", "Horse, draft", "Horse, riding", "Hourglass", "Hunting trap", "Ink (1 ounce bottle)", "Ink pen", "Javelin", "Jeweler's Tools", "Jug or pitcher", "Keelboat", "Ladder (10-foot)", "Lamp", "Lance", "Lantern, bullseye", "Lantern, hooded", "Leather Armor", "Leatherworker's Tools", "Light hammer", "Little bag of sand", "Lock", "Longbow", "Longship", "Longsword", "Lute", "Lyre", "Mace", "Magnifying glass", "Manacles", "Mason's Tools", "Mastiff", "Maul", "Mess Kit", "Mirror, steel", "Morningstar", "Mule", "Navigator's Tools", "Net", "Oil (flask)", "Orb", "Padded Armor", "Painter's Supplies", "Pan flute", "Paper (one sheet)", "Parchment (one sheet)", "Perfume (vial)", "Pick, miner's", "Pike", "Piton", "Plate Armor", "Playing Card Set", "Poison, basic (vial)", "Poisoner's Kit", "Pole (10-foot)", "Pony", "Pot, iron", "Potter's Tools", "Pouch", "Priest's Pack", "Quarterstaff", "Quiver", "Ram, portable", "Rapier", "Rations (1 day)", "Reliquary", "Ring Mail", "Robes", "Rod", "Rope, hempen (50 feet)", "Rope, silk (50 feet)", "Rowboat", "Sack", "Saddle, Exotic", "Saddle, Military", "Saddle, Pack", "Saddle, Riding", "Saddlebags", "Sailing ship", "Scale Mail", "Scale, merchant's", "Scholar's Pack", "Scimitar", "Sealing wax", "Shawm", "Shield", "Shortbow", "Shortsword", "Shovel", "Sickle", "Signal whistle", "Signet ring", "Sled", "Sling", "Sling bullet", "Small knife", "Smith's Tools", "Soap", "Spear", "Spellbook", "Spike, iron", "Splint Armor", "Sprig of mistletoe", "Spyglass", "Stabling (1 day)", "Staff", "String (10 feet)", "Studded Leather Armor", "Tent, two-person", "Thieves' Tools", "Tinderbox", "Tinker's Tools", "Torch", "Totem", "Trident", "Vestments", "Vial", "Viol", "Wagon", "Wand", "War pick", "Warhammer", "Warhorse", "Warship", "Waterskin", "Weaver's Tools", "Whetstone", "Whip", "Woodcarver's Tools", "Wooden staff", "Yew wand"));

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
        try
        {
            Files.write(Paths.get("searchHistory.txt"), searchHistory);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
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
            searchBar.getItems().clear();
            searchHistory.clear();
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
