package ru.itmo.commands;


import ru.itmo.commands.interactives.show.ShowInteractiveLabWork;
import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.LabWork;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;
import ru.itmo.response.CommandResponse;
import ru.itmo.response.ResponseType;

import java.util.HashMap;
import java.util.Map;

public class RemoveHeadCommand extends Command{

    public RemoveHeadCommand() {
        setTargetTitleForUserInput("remove_head");
        setDescription("remove_head: вывести первый элемент коллекции и удалить его");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET_FIRST);
        CommandResponse commandResponse = commandManager.getResponse(commandRequest);

        if (commandResponse.getResponseType() == ResponseType.NOT_FOUND) {
            System.out.println("В списке нет лабораторных работ");
            commandRequest.setRequestType(RequestType.INTERNAL);
        }

        else {

            LabWork first = (LabWork) commandResponse.getObject();

            System.out.println("---------------------------------------------------------------------------");
            ShowInteractiveLabWork.showLabWork(first, 0);
            System.out.println("---------------------------------------------------------------------------");

            commandRequest.setRequestType(RequestType.DELETE);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", first.getId());
            commandRequest.setParameters(parameters);
        }


        return commandRequest;
    }
}
