package ru.itmo.interactives.question;

import java.util.Scanner;

public class QuestionInteractive {

    public static boolean yesOrNoQuestion(String message, Scanner scanner) {
        while (true) {
            System.out.print(message + " (y/n): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y") ||
                    answer.equalsIgnoreCase("yes") ||
                    answer.equalsIgnoreCase("да")) {
                return true;
            }
            else if (answer.equalsIgnoreCase("n") ||
                    answer.equalsIgnoreCase("no") ||
                    answer.equalsIgnoreCase("нет")) {
                return false;
            }
        }
    }

}
