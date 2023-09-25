package ru.itmo.models.utils;


import ru.itmo.models.LabWork;

public class LabWorkValidator {
    public static boolean isValidLabWork(LabWork labWork) {
        LabWork validLabWork = new LabWork();

        if (labWork.getDate() == null) {
            return false;
        }

        if (labWork.getCoordinates() == null) {
            return false;
        }

        boolean isValidAuthor = true;
        if (labWork.getAuthor() != null) {
            isValidAuthor = PersonValidator.isValidPerson(labWork.getAuthor());
        }


        return isValidAuthor && validLabWork.setName(labWork.getName()) && CoordinatesValidator.isValidCoordinate(labWork.getCoordinates())
                && validLabWork.setMinimalPoint(labWork.getMinimalPoint()) && validLabWork.setMaximumPoint(labWork.getMaximumPoint());
    }
}
