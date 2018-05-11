package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Number {

    private IntegerProperty number;
    private StringProperty name, surname, voide;

    public Number(int number, String name, String surname, String voide){
        this.number = new SimpleIntegerProperty(number);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.voide = new SimpleStringProperty(voide);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getVoide() {
        return voide.get();
    }

    public StringProperty voideProperty() {
        return voide;
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }
}
