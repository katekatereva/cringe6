package ru.itmo.commands.interactives.update;


import ru.itmo.commands.interactives.add.AddInteractiveLabWork;
import ru.itmo.commands.interactives.show.ShowInteractiveLabWork;
import ru.itmo.interactives.question.QuestionInteractive;
import ru.itmo.models.Coordinates;
import ru.itmo.models.LabWork;
import ru.itmo.models.Location;
import ru.itmo.models.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.itmo.commands.interactives.add.AddInteractiveLabWork.interactivePersonHairColor;

public class UpdateInteractiveLabWork {

    public static void updateLabWork(LabWork labWork, Scanner scanner) {

        List<String> pointsOfLabWork = new ArrayList<>();

        pointsOfLabWork.add("Название лабораторной работы");
        pointsOfLabWork.add("Информация о координатах");
        pointsOfLabWork.add("Значение минимальной точки");
        pointsOfLabWork.add("Значение максимальной точки");
        pointsOfLabWork.add("Сложность лабораторной работы");
        pointsOfLabWork.add("Информация об авторе");



        boolean isUpdate = true;
        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showLabWork(labWork, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfLabWork.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfLabWork.get(i));
                    System.out.println();
                }

                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        AddInteractiveLabWork.interactiveName(labWork, scanner);
                    }
                    case 2 -> {
                        labWork.setCoordinates(UpdateInteractiveLabWork.updateCoordinates(labWork.getCoordinates(), scanner));
                    }
                    case 3 -> {
                        AddInteractiveLabWork.interactiveMinimalPoint(labWork, scanner);
                    }
                    case 4 -> {
                        AddInteractiveLabWork.interactiveMaximumPoint(labWork, scanner);
                    }
                    case 5 -> {
                        if (labWork.getDifficulty() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить сложность работе?", scanner)) {
                                AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить сложность работы?", scanner)) {
                                AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить сложность работе?", scanner)) {
                                labWork.setDifficulty(null);
                            }
                        }

                    }
                    case 6 -> {
                        if (labWork.getAuthor() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить автора работы?", scanner)) {
                                Person author = new Person();
                                AddInteractiveLabWork.interactivePerson(author, scanner);
                                labWork.setAuthor(author);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить автора работы?", scanner)) {
                                Person author = UpdateInteractiveLabWork.updatePerson(labWork.getAuthor(), scanner);
                                labWork.setAuthor(author);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить автора работы?", scanner)) {
                                labWork.setAuthor(null);
                            }
                        }
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfLabWork.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }



    }

    public static void updateBirthDay(Person author, Scanner scanner) {

        List<String> pointsOfBirthDay = new ArrayList<>();

        LocalDate birthday = author.getBirthdayPerson();

        pointsOfBirthDay.add("День");
        pointsOfBirthDay.add("Месяц");
        pointsOfBirthDay.add("Год");



        boolean isUpdate = true;
        while (isUpdate) {

            int day = birthday.getDayOfMonth();
            int month = birthday.getMonth().getValue();
            int year = birthday.getYear();
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showDayOfBirth(birthday);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfBirthDay.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfBirthDay.get(i));
                    System.out.println();
                }
                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());

                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        day = AddInteractiveLabWork.interactiveDayOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    case 2 -> {
                        month = AddInteractiveLabWork.interactiveMonthOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    case 3 -> {
                        year = AddInteractiveLabWork.interactiveYearOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfBirthDay.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            } catch (DateTimeException dateTimeException) {
                System.out.println("Такой даты не существует");
            }



        }

    }

    public static Coordinates updateCoordinates(Coordinates coordinates, Scanner scanner) {

        List<String> pointsOfCoordinates = new ArrayList<>();

        pointsOfCoordinates.add("Координата X");
        pointsOfCoordinates.add("Координата Y");



        boolean isUpdate = true;

        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.coordinatesShow(coordinates, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfCoordinates.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfCoordinates.get(i));
                    System.out.println();
                }

                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        AddInteractiveLabWork.interactiveX(coordinates, scanner);
                    }
                    case 2 -> {
                        AddInteractiveLabWork.interactiveY(coordinates, scanner);
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfCoordinates.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }

        return coordinates;


    }

    public static void updateLocation(Location location, Scanner scanner) {
        List<String> pointsOfLocation = new ArrayList<>();

        pointsOfLocation.add("Координата X");
        pointsOfLocation.add("Координата Y");
        pointsOfLocation.add("Координата Z");

        boolean isUpdate = true;

        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showLocation(location, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfLocation.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfLocation.get(i));
                    System.out.println();
                }

                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());
                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        AddInteractiveLabWork.interactiveXofLocation(location, scanner);
                    }
                    case 2 -> {
                        AddInteractiveLabWork.interactiveYofLocation(location, scanner);
                    }
                    case 3 -> {
                        AddInteractiveLabWork.interactiveZofLocation(location, scanner);
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfLocation.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }

    }

    public static Person updatePerson(Person author, Scanner scanner) {

        List<String> pointsOfPerson = new ArrayList<>();

        pointsOfPerson.add("Имя автора");
        pointsOfPerson.add("День рождение");
        pointsOfPerson.add("Цвет глаз");
        pointsOfPerson.add("Цвет волос");
        pointsOfPerson.add("Национальность");
        pointsOfPerson.add("Информация о локации");



        boolean isUpdate = true;
        while (isUpdate) {
            try {
                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showPerson(author, 0);
                System.out.println("---------------------------------------------------------------------------");


                for (int i = 0; i < pointsOfPerson.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfPerson.get(i));
                    System.out.println();
                }
                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());

                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        AddInteractiveLabWork.interactivePersonName(author, scanner);
                    }
                    case 2 -> {
                        if (author.getBirthdayPerson() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить день рождения автора?", scanner)) {
                                AddInteractiveLabWork.interactiveBirthDay(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить день рождения автора?", scanner)) {
                                updateBirthDay(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить день рождения автора?", scanner)) {
                                author.setBirthday(null);
                            }
                        }
                    }

                    case 3 -> {
                        AddInteractiveLabWork.interactivePersonEyeColor(author, scanner);
                    }

                    case 4 -> {
                        if (author.getHairColor() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить цвет волос автора?", scanner)) {
                                AddInteractiveLabWork.interactivePersonHairColor(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить цвет волос автора?", scanner)) {
                                interactivePersonHairColor(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить цвет волос автора?", scanner)) {
                                author.setHairColor(null);
                            }
                        }
                    }

                    case 5 -> {
                        if (author.getNationality() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить национальность автора?", scanner)) {
                                AddInteractiveLabWork.interactivePersonNationality(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить национальность автора?", scanner)) {
                                AddInteractiveLabWork.interactivePersonNationality(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить национальность автора?", scanner)) {
                                author.setNationality(null);
                            }
                        }
                    }

                    case 6 -> {
                        updateLocation(author.getLocation(), scanner);
                    }

                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfPerson.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

        return author;
    }


}
