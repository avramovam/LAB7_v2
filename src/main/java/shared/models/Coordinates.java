package shared.models;

import java.io.Serializable;

/**
 * Класс для хранения координат.
 * Координата y должна быть > -177.
 */
public class Coordinates implements Serializable {
    private int x;
    private float y; // Значение должно быть > -177

    public Coordinates() {}

    public Coordinates(int x, float y) {
        setX(x);
        setY(y);
    }

    // Геттеры и сеттеры
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        if (y <= -177) {
            throw new IllegalArgumentException("Y coordinate must be greater than -177");
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}