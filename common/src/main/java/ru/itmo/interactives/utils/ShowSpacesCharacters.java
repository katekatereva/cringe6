package ru.itmo.interactives.utils;

public class ShowSpacesCharacters {

    public static void showSpace(int level) {
        int countSpace = 5;
        for (int i = 0; i < level * countSpace; i++) {
            System.out.print(" ");
        }
    }


}
