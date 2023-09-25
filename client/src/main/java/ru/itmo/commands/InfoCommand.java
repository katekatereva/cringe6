package ru.itmo.commands;


import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;
import ru.itmo.response.CommandResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class InfoCommand extends Command {


    public InfoCommand() {
        setTargetTitleForUserInput("info");
        setDescription("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.INFO);
        CommandResponse commandResponse = commandManager.getResponse(commandRequest);

        HashMap<String, Object> parametersOfCommandResponse = (HashMap<String, Object>) commandResponse.getParameters();

        if (parametersOfCommandResponse != null) {
            System.out.printf("Тип коллекции: %s", parametersOfCommandResponse.get("type"));
            System.out.println();

            LocalDate dateInitial = (LocalDate) parametersOfCommandResponse.get("date_initial");
            System.out.printf("Дата инициализации: %s", dateInitial.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println();

            System.out.printf("Кол-во элементов: %s", parametersOfCommandResponse.get("count"));
            System.out.println();
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}
