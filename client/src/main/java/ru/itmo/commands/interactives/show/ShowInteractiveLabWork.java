package ru.itmo.commands.interactives.show;


import ru.itmo.interactives.utils.ShowSpacesCharacters;
import ru.itmo.models.Coordinates;
import ru.itmo.models.LabWork;
import ru.itmo.models.Location;
import ru.itmo.models.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShowInteractiveLabWork {

    public static void showLocation(Location location, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);
        if (location.getX() == null) {
            System.out.println("Координата X: отсутствует");
        } else {
            System.out.printf("Координата X: %d", location.getX());
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Координата Y: %d", location.getY());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (location.getZ() == null) {
            System.out.println("Координата Z: отсутствует");
        } else {
            System.out.printf("Координата Z: %d", location.getZ());
            System.out.println();
        }

    }

    public static void showPerson(Person person, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);

        if (person.getName() == null) {
            System.out.println("Имя автора: отсутствует");
        } else {
            System.out.printf("Имя автора: %s", person.getName());
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getBirthdayPerson() == null) {
            System.out.println("День рождения: отсутствует");
        } else {
            System.out.printf("День рождения: %s", person.getBirthdayPerson().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getEyeColor() == null) {
            System.out.println("Цвет глаз: отсутствует");
        } else {
            System.out.printf("Цвет глаз: %s", person.getEyeColor().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getHairColor() == null) {
            System.out.println("Цвет волос: отсутствует");
        } else {
            System.out.printf("Цвет волос: %s", person.getHairColor().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getNationality() == null) {
            System.out.println("Национальность: отсутствует");
        } else {
            System.out.printf("Национальность: %s", person.getNationality());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getLocation() == null) {
            System.out.println("Информация о локации: отсутствует");
        } else {

            System.out.println("Информация о локации:");
            showLocation(person.getLocation(), levelSpace + 1);

        }


    }


    public static void coordinatesShow(Coordinates coordinates, int levelSpace) {


        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Координата X: %.2f", coordinates.getX());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (coordinates.getY() == null) {
            System.out.println("Координата Y: отсутствует");
        } else {
            System.out.printf("Координата Y: %d", coordinates.getY());
        }
        System.out.println();

    }
    public static void showDayOfBirth(LocalDate birthday) {


        int day = birthday.getDayOfMonth();
        int month = birthday.getMonth().getValue();
        int year = birthday.getYear();

        System.out.printf("День: %d", day);
        System.out.printf("Месяц: %d", month);
        System.out.printf("Год: %d", year);


    }
    public static void showLabWork(LabWork labWork, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getId() == null) {
            System.out.println("ID: отсутствует");
        }
        else {
            System.out.printf("ID: %d", labWork.getId());
            System.out.println();

        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getDate() == null) {
            System.out.println("Дата создания: отсутствует");
        } else {
            System.out.printf("Дата создания: %s", labWork.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getName() == null) {
            System.out.println("Название лабораторной работы: отсутствует");
        }
        else {
            System.out.printf("Название лабораторной работы: %s", labWork.getName());
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getCoordinates() == null) {
            System.out.println("Информация о координатах: отсутствует");
        } else {
            System.out.println("Информация о координатах:");
            coordinatesShow(labWork.getCoordinates(), levelSpace + 1);
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Значение минимальной точки: %d", labWork.getMinimalPoint());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getMaximumPoint() == null) {
            System.out.println("Значение максимальной точки: отсутствует");
        } else {
            System.out.printf("Значение максимальной точки: %d", labWork.getMaximumPoint());
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getDifficulty() == null) {
            System.out.println("Сложность лабораторной работы: отсутствует");
        } else {
            System.out.printf("Сложность лабораторной работы: %s", labWork.getDifficulty().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getAuthor() == null) {
            System.out.println("Информация об авторе: отсутствует");
        } else {

            System.out.println("Информация об авторе:");
            showPerson(labWork.getAuthor(), levelSpace + 1);
            System.out.println();

        }

    }

}
