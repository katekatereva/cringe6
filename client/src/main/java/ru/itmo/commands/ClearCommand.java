package ru.itmo.commands;


import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;

public class ClearCommand extends Command{

    public ClearCommand() {
        setTargetTitleForUserInput("clear");
        setDescription("clear : очистить коллекцию");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.DELETE);
        return commandRequest;
    }
}
