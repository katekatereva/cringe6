package ru.itmo.utils;

/**
 * Генератор id
 */

public class GeneratorID {
    private static int id = 1;
    public static int newId(){
        return id++;
    }
    public static void setId(int id) {
        GeneratorID.id = id;
    }
}
