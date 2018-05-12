package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<Number> numbers = new ArrayList<>();
    @FXML
    private Button addButton;

    @FXML
    private TextField number, name, surname;

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

    public void addNewNumber(ActionEvent actionEvent) {
        if (this.number.getText().length() == 0 || this.name.getText().length() == 0 || this.surname.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Fill in all fields please");
            alert.showAndWait();
            return;
        }
        Number number = new Number(Integer.valueOf(this.number.getText()), this.name.getText(), this.surname.getText(), this.comboBox.getSelectionModel().getSelectedItem());
        Database.connect();
        Database.insert(number);
    }
}
