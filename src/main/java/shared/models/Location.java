package shared.models;

import java.io.Serializable;

/**
 * Класс для хранения местоположения.
 * Поле name может быть null.
 */
public class Location implements Serializable {
    private float x;
    private long y;
    private String name; // Может быть null

    public Location() {}

    public Location(float x, long y, String name) {
        setX(x);
        setY(y);
        setName(name);
    }

    // Геттеры и сеттеры
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; // Допускается null
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "] " + (name != null ? name : "Unnamed location");
    }
}