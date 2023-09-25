package ru.itmo.commands;


import ru.itmo.commands.interactives.show.ShowInteractiveLabWork;

import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.LabWork;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;
import ru.itmo.response.CommandResponse;
import ru.itmo.response.ResponseType;

public class GetMinCommand extends Command{

    public GetMinCommand() {
        setTargetTitleForUserInput("get_min");
        setDescription("get_min : получить наименьший элемент");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET_MIN);

        CommandResponse commandResponse  = commandManager.getResponse(commandRequest);

        if (commandResponse.getResponseType() == ResponseType.NOT_FOUND) {
            System.out.println("В списке нет лабораторных работ");
        }
        else {
            LabWork labWork = (LabWork) commandResponse.getObject();
            ShowInteractiveLabWork.showLabWork(labWork, 0);
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}
