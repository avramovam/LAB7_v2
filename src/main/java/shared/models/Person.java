package shared.models;

import java.io.Serializable;

/**
 * Класс, описывающий режиссёра фильма.
 * Поле passportID должно быть уникальным.
 */
public class Person implements Serializable {
    private String name; // Не может быть null, строка не пустая
    private String passportID; // Не может быть null, должно быть уникальным
    private Location location; // Не может быть null

    public Person() {}

    public Person(String name, String passportID, Location location) {
        setName(name);
        setPassportID(passportID);
        setLocation(location);
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        if (passportID == null) {
            throw new IllegalArgumentException("PassportID cannot be null");
        }
        this.passportID = passportID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
    }

    @Override
    public String toString() {
        return name + " (Passport: " + passportID + ")";
    }
}