package ru.itmo.commands;


import ru.itmo.commands.interactives.show.ShowInteractiveLabWork;
import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.LabWork;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;
import ru.itmo.response.CommandResponse;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class FilterByAuthorCommand extends Command{

    private Scanner scanner;

    public FilterByAuthorCommand(Scanner scanner) {
        this.scanner = scanner;
        setTargetTitleForUserInput("filter_by_author");
        setDescription("filter_by_author author : вывести элементы, значение поля author которых равно заданному");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        System.out.print("Введите имя автора: ");
        String authorName = scanner.nextLine();

        while (authorName.isBlank()) {
            System.out.print("Введите имя автора: ");
            authorName = scanner.nextLine();
        }


        CommandRequest commandRequest = new CommandRequest();


        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("author", authorName);

        commandRequest.setParameters(parameters);
        commandRequest.setRequestType(RequestType.GET);

        CommandResponse commandResponse = commandManager.getResponse(commandRequest);


        ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) commandResponse.getObject();

        if (labWorks.size() > 0) {
            System.out.println("---------------------------------------------------------------------------");
            for (LabWork labWork : labWorks) {
                ShowInteractiveLabWork.showLabWork(labWork, 0);
                System.out.println("---------------------------------------------------------------------------");
            }
        }

        else {
            System.out.println("В списке нет лабораторных работ");
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;

    }
}
