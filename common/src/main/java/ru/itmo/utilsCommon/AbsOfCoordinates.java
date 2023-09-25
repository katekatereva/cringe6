package ru.itmo.utilsCommon;

import ru.itmo.models.Coordinates;


public class AbsOfCoordinates {

    public static Long getAbsOfLocation(Coordinates coordinates) {

        return (long)

                Math.sqrt(
                        Math.pow(coordinates.getX(), 2) *
                        Math.pow(coordinates.getY(), 2)
                );

    }

}
