package ru.itmo.models.utils;

import ru.itmo.models.Coordinates;

public class CoordinatesValidator {

    public static boolean isValidCoordinate(Coordinates coordinates) {

        Coordinates validCoordinates = new Coordinates();

        return validCoordinates.setX(coordinates.getX()) && validCoordinates.setY(coordinates.getY());
    }

}
