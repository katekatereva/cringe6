package ru.itmo.models;


import java.io.Serializable;

public class Coordinates implements Serializable {
    private float x; //Максимальное значение поля: 863
    private Long y; //Значение поля должно быть больше -733, Поле не может быть null
    private final float xMax = 863;
    private final Long yMin = -733L;

    public Coordinates() {}
    public Coordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.x = coordinates.x;
            this.y = coordinates.y;
        }

    }

    public float getX() {
        return x;
    }

    public boolean setX(float x) {
        if (x <= xMax) {
            this.x = x;
            return true;
        }
        return false;
    }

    public Long getY() {
        return y;
    }

    public boolean setY(Long y) {

        if (y == null) {
            return false;
        }
        if (y > yMin)  {
            this.y = y;
            return true;
        }
        return false;
    }

    public float getXMax() {
        return xMax;
    }

    public Long getYMin() {
        return yMin;
    }

}