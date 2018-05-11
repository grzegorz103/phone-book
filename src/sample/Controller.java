package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ListView<String> listView;

    private String[] NAMES = {"Mazowieckie", "Lubelskie", "Dolnośląskie", "Kujawsko-pomorskie", "Lubuskie", "Łódzkie", "Małopolskie",
            "Dolnoslaskie", "Opolskie", "Podkarpackie", "Podlaskie", "Pomorskie", "Slaskie", "Swietokrzyskie", "Warminsko-mazurskie", "Wielkopolskie", "Zachodnio-pomorskie"};

    public Controller() {
    }

    @FXML
    public void initialize() {
        listView.getItems().addAll(NAMES);
        comboBox.getItems().addAll(NAMES);
    }
}
