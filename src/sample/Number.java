package sample;

import java.io.Serializable;

public class Number implements Serializable {

    private int number;
    private String name, surname, voide;

    public Number(int number, String name, String surname, String voide) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.voide = voide;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getVoide() {
        return voide;
    }
}
