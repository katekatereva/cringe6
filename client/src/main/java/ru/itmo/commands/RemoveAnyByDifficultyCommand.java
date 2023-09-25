package ru.itmo.commands;


import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.Difficulty;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;

import java.util.HashMap;
import java.util.Scanner;

public class RemoveAnyByDifficultyCommand extends Command {

    private final Scanner scanner;

    public RemoveAnyByDifficultyCommand(Scanner scanner) {
        this.scanner = scanner;
        setTargetTitleForUserInput("remove_any_by_difficulty");
        setDescription("remove_any_by_difficulty difficulty : удалить из коллекции один элемент, значение поля difficulty которого эквивалентно заданному");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        Difficulty[] difficulties = Difficulty.values();

        for (int i = 0; i < difficulties.length; i++) {
            System.out.printf("%d. %s", i + 1, difficulties[i]);
            System.out.println();
        }

        Difficulty difficulty;

        while (true) {
            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать сложность: ", difficulties.length);
                int positionOfDifficulty = Integer.parseInt(scanner.nextLine());
                if (positionOfDifficulty >= 1 && positionOfDifficulty <= difficulties.length) {
                    difficulty = difficulties[positionOfDifficulty - 1];
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }

        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.DELETE);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("difficulty", difficulty);
        commandRequest.setParameters(parameters);

        return commandRequest;


    }
}
