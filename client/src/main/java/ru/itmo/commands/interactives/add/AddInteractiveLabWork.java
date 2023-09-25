package ru.itmo.commands.interactives.add;


import ru.itmo.interactives.question.QuestionInteractive;
import ru.itmo.models.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class AddInteractiveLabWork {

    public static void interactiveX(Coordinates coordinates, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите координату X: ");
                while (!coordinates.setX(Float.parseFloat(scanner.nextLine()))) {
                    System.out.printf("Координата X должна быть не больше %.2f", coordinates.getXMax());
                    System.out.println();
                    System.out.print("Введите координату X: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата X должна быть числом");
            }
        }
    }

    public static void interactiveY(Coordinates coordinates, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Y: ");
                while (!coordinates.setY(Long.parseLong(scanner.nextLine()))) {
                    System.out.printf("Координата Y должна быть больше %d", coordinates.getYMin());
                    System.out.println();
                    System.out.print("Введите координату Y: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Y должна быть числом");
            }
        }
    }

    public static void interactiveBirthDay(Person author, Scanner scanner) {
        while (true) {

            try {
                int day = interactiveDayOfBirthDay(scanner);
                int month = interactiveMonthOfBirthDay(scanner);
                int year = interactiveYearOfBirthDay(scanner);
                author.setBirthday(LocalDate.of(year, month, day));
                break;
            } catch (DateTimeException dateTimeException) {
                System.out.println("Такой даты не существует");
            }

        }

    }
    public static int interactiveDayOfBirthDay(Scanner scanner) {
        while (true) {
            int day;
            try {
                System.out.print("Введите день: ");
                day = Integer.parseInt(scanner.nextLine());

                if (day >= 1 && day <= 31) {
                    return day;
                } else {
                    System.out.println("День должен быть в диапазоне 1 - 31");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("День должен быть числом");
            }

        }
    }

    public static int interactiveMonthOfBirthDay(Scanner scanner) {
        while (true) {

            try {
                System.out.print("Введите месяц: ");
                int month = Integer.parseInt(scanner.nextLine());

                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    System.out.println("Месяц должен быть в диапазоне 1 - 12");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("Месяц должен быть числом");
            }

        }
    }

    public static int interactiveYearOfBirthDay(Scanner scanner) {
        while (true) {

            try {
                System.out.print("Введите год: ");
                int year = Integer.parseInt(scanner.nextLine());

                if (year >= 1) {
                    return year;
                } else {
                    System.out.println("Год должен быть больше нуля");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Год должен быть числом");
            }

        }
    }

    public static void interactiveName(LabWork labWork, Scanner scanner) {
        System.out.print("Введите название работы: ");
        while (!labWork.setName(scanner.nextLine())) {
            System.out.println("Название работы должно быть не пустым");
            System.out.print("Введите название работы: ");
        }
    }

    public static void interactiveMinimalPoint(LabWork labWork, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите значение минимальной точки: ");
                while (!labWork.setMinimalPoint(Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Значение минимальной точки должно быть больше нуля");
                    System.out.print("Введите значение минимальной точки: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Значение минимальной точки должно быть числом");
            }
        }

    }

    public static void interactiveMaximumPoint(LabWork labWork, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите значение максимальной точки: ");
                while (!labWork.setMaximumPoint(Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Значение максимальной точки должно быть больше нуля");
                    System.out.print("Введите значение максимальной точки: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Значение максимальной точки должно быть числом");
            }
        }

    }


    public static void interactiveDifficulty(LabWork labWork, Scanner scanner) {

        Difficulty[] difficulties = Difficulty.values();

        for (int i = 0; i < difficulties.length; i++) {
            System.out.printf("%d. %s", i + 1, difficulties[i]);
            System.out.println();
        }
        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать сложность: ", difficulties.length);
                int positionOfDifficulty = Integer.parseInt(scanner.nextLine());
                if (positionOfDifficulty >= 1 && positionOfDifficulty <= difficulties.length) {
                    labWork.setDifficulty(difficulties[positionOfDifficulty - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

    }



    public static void interactiveLocation(Location location, Scanner scanner) {
        interactiveXofLocation(location, scanner);
        interactiveYofLocation(location, scanner);
        interactiveZofLocation(location, scanner);
    }

    public static void interactiveXofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату X: ");
                location.setX(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата X должна быть числом");
            }
        }

    }

    public static void interactiveYofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Y: ");
                location.setY(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Y должна быть числом");
            }
        }
    }

    public static void interactiveZofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Z: ");
                location.setZ(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Z должна быть числом");
            }
        }
    }

    public static void interactivePerson(Person author, Scanner scanner) {
        interactivePersonName(author, scanner);

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить день рождения автора?", scanner)) {
            interactiveBirthDay(author, scanner);
        }

        interactivePersonEyeColor(author, scanner);

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить цвет волос у автора?", scanner)) {
            interactivePersonHairColor(author, scanner);
        }

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить национальность для автора?", scanner)) {
            interactivePersonNationality(author, scanner);
        }

        Location location = new Location();
        interactiveLocation(location, scanner);
        author.setLocation(location);
    }

    public static void interactivePersonName(Person author, Scanner scanner) {
        System.out.print("Введите имя автора: ");
        while (!author.setName(scanner.nextLine())) {
            System.out.println("Имя автора должно быть не пустым");
            System.out.print("Введите имя автора: ");
        }
    }

    public static void interactivePersonEyeColor(Person author, Scanner scanner) {
        ru.itmo.models.eye.Color[] eyeColors = ru.itmo.models.eye.Color.values();

        for (int i = 0; i < eyeColors.length; i++) {
            System.out.printf("%d. %s", i + 1, eyeColors[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать цвет глаз: ", eyeColors.length);
                int positionOfEyeColor = Integer.parseInt(scanner.nextLine());
                if (positionOfEyeColor >= 1 && positionOfEyeColor <= eyeColors.length) {
                    author.setEyeColor(eyeColors[positionOfEyeColor - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }
    }

    public static void interactivePersonHairColor(Person author, Scanner scanner) {
        ru.itmo.models.hair.Color[] hairColors = ru.itmo.models.hair.Color.values();

        for (int i = 0; i < hairColors.length; i++) {
            System.out.printf("%d. %s", i + 1, hairColors[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать цвет волос: ", hairColors.length);
                int positionOfHairColor = Integer.parseInt(scanner.nextLine());
                if (positionOfHairColor >= 1 && positionOfHairColor <= hairColors.length) {
                    author.setHairColor(hairColors[positionOfHairColor - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

    }

    public static void interactivePersonNationality(Person author, Scanner scanner) {
        Country[] countries = Country.values();

        for (int i = 0; i < countries.length; i++) {
            System.out.printf("%d. %s", i + 1, countries[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать национальность: ", countries.length);
                int positionOfCountry = Integer.parseInt(scanner.nextLine());
                if (positionOfCountry >= 1 && positionOfCountry <= countries.length) {
                    author.setNationality(countries[positionOfCountry - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }
    }


}
