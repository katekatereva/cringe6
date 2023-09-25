package ru.itmo.models.utils;


import ru.itmo.models.Location;

public class LocationValidator {

    public static boolean isValidLocation(Location location) {
        Location validLocation = new Location();

        return validLocation.setX(location.getX()) && validLocation.setY(location.getY())
                && validLocation.setZ(location.getZ());
    }

}
