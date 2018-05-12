package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    static List<Number> numbers = new ArrayList<>();

    @FXML
    private Button addButton;

    @FXML
    private TextField number, name, surname;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ListView<String> listView;

    @FXML
    private TableView<Number> table;

    @FXML
    private TableColumn<Number, Integer> colNumber;

    @FXML
    private TableColumn<Number, String> colName, colSurname;

    @FXML

    private String[] NAMES = {"Mazowieckie", "Lubelskie", "Dolnośląskie", "Kujawsko-pomorskie", "Lubuskie", "Łódzkie", "Małopolskie",
            "Dolnoslaskie", "Opolskie", "Podkarpackie", "Podlaskie", "Pomorskie", "Slaskie", "Swietokrzyskie", "Warminsko-mazurskie", "Wielkopolskie", "Zachodnio-pomorskie"};

    public Controller() {
    }

    @FXML
    public void initialize() {
        listView.getItems().addAll(NAMES);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> readIn());

        comboBox.getItems().addAll(NAMES);
        comboBox.getSelectionModel().select(0);
        Database.connect();
        Database.select();

        setTable();
        readIn();

    }

    private void setTable() {
        this.colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));

    }

    public void addNewNumber() {
        if (validate()) {
            Number number = new Number(Integer.valueOf(this.number.getText()), this.name.getText(), this.surname.getText(), this.comboBox.getSelectionModel().getSelectedItem());
            Database.connect();
            Database.insert(number);
            Database.select();
        }
    }

    private boolean validate() {
        if (this.number.getText().length() == 0 || this.name.getText().length() == 0 || this.surname.getText().length() == 0) {
            showAlert("Please fill in all fields");
            return false;
        }

        for (int x : this.number.getText().toCharArray()) {
            if (Character.isLetter(x)) {
                showAlert("Number is numeric only!");
                return false;
            }
        }

        for (char x : this.name.getText().toCharArray()) {
            if (Character.isDigit(x)) {
                showAlert("Name is letter only");
                return false;
            }
        }
        for (char x : this.surname.getText().toCharArray()) {
            if (Character.isDigit(x)) {
                showAlert("Surname is letter only");
                return false;
            }
        }

        if (this.number.getText().length() >= 10) {
            showAlert("Number is maximum 9 digits length");
            return false;
        }

        return true;
    }

    private void readIn() {
        String category = this.listView.getSelectionModel().getSelectedItem();

        List<Number> tempNumbers = new ArrayList<>();
        for (Number x : numbers) {
            if (x.getVoide().equals(category))
                tempNumbers.add(x);
        }
        ObservableList<Number> data = FXCollections.observableArrayList(tempNumbers);

        this.table.setItems(data);

    }

    private void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
