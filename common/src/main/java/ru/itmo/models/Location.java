package ru.itmo.models;


import java.io.Serializable;

public class Location implements Serializable {
    private Long x; //Поле не может быть null
    private long y;
    private Long z; //Поле не может быть null

    public Location() {}
    public Location(Location location) {

        if (location != null) {
            this.x = location.x;
            this.y = location.y;
            this.z = location.z;
        }
    }

    public boolean setX(Long x) {
        this.x = x;
        return true;
    }

    public boolean setY(long y) {
        this.y = y;
        return true;
    }

    public boolean setZ(Long z) {
        this.z = z;
        return true;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }


}